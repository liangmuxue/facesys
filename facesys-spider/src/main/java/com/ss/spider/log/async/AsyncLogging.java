package com.ss.spider.log.async;

import com.ss.annotation.OpLog;
import com.ss.enums.CommonEnumClass;
import com.ss.spider.log.bean.LoggingInfo;
import com.ss.spider.log.bean.dto.LogInsertDTO;
import com.ss.spider.log.constants.Constants;
import com.ss.spider.log.enums.OperaStateEnum;
import com.ss.spider.log.model.AppLogUser;
import com.ss.spider.log.service.AppLogService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;


@Component
public class AsyncLogging {

    @Autowired
    private AppLogService logService;
    private static final String IGNORE_STR = "内容太长，忽略...";

    @Async("logJobExecutor")
    public void logging(LoggingInfo info) {
        doLogging(info);
    }


    private void doLogging(LoggingInfo info) {
        LogInsertDTO insertDTO = new LogInsertDTO();
        insertDTO.setOperTime(info.getOperTime());
        insertDTO.setTimeConsum(info.getTimeConsum());
        insertDTO.setModuleCode(info.getOpLogInfo().model());
        insertDTO.setType(info.getOpLogInfo().type().getKey());
        insertDTO.setLogType(Short.valueOf((short) info.getOpLogInfo().logType().getCode()));
        JSONObject inPara = transformInParaJson(info.getArgs());

        if (info.getReqestInfo() != null) {
            insertDTO.setIp(info.getReqestInfo().getRemoteIp());
            insertDTO.setUrl(info.getReqestInfo().getFullUrl());
        }

        if (inPara != null && !inPara.isEmpty()) {
            String userId = inPara.getString("userId");
            insertDTO.setUserId(userId);

            if (!StringUtils.isEmpty(userId)) {

                AppLogUser user = this.logService.getUser(userId);
                if (user != null) {

                    insertDTO.setName(user.getName());
                    insertDTO.setWorkCode(user.getWorkCode());
                    insertDTO.setOrgId(user.getOrgId());
                    insertDTO.setPhoneNumber(user.getPhoneNumber());
                }
            }
            Set<String> keyset = inPara.keySet();
            for (String key : keyset) {
                String value = inPara.getString(key);
                if (!StringUtils.isEmpty(value) && value.length() > 5120) {
                    inPara.put(key, "内容太长，忽略...");
                }
            }
            insertDTO.setInPara(inPara.toJSONString());
        }

        if (info.getResult() != null) {
            if (info.getResult().getClass().getName().startsWith("com.ss.response.ResponseEntity")) {

                try {
                    Method method = info.getResult().getClass().getMethod("getFullCode", new Class[0]);
                    Object code = method.invoke(info.getResult(), new Object[0]);

                    if (CommonEnumClass.CommonInterfaceEnum.SUCCESS.getKey().equals(code)) {
                        insertDTO.setIsSuccess(Short.valueOf((short) OperaStateEnum.SUCC.getCode()));
                    } else {

                        insertDTO.setIsSuccess(Short.valueOf((short) OperaStateEnum.FAIL.getCode()));
                        method = info.getResult().getClass().getMethod("getMessage", new Class[0]);
                        Object error = method.invoke(info.getResult(), new Object[0]);

                        if (error != null) {
                            insertDTO.setDesc(info.getOpLogInfo().desc() + "失败，原因：" + error.toString());
                        }
                    }
                } catch (Exception exception) {
                }


            } else if (info
                    .getResult() instanceof org.springframework.security.authentication.AbstractAuthenticationToken) {
                try {
                    Method method = info.getResult().getClass().getMethod("isAuthenticated", new Class[0]);
                    Boolean isAuthenticated = (Boolean) method.invoke(info.getResult(), new Object[0]);

                    if (isAuthenticated.booleanValue()) {
                        insertDTO.setIsSuccess(Short.valueOf((short) OperaStateEnum.SUCC.getCode()));
                    } else {

                        insertDTO.setIsSuccess(Short.valueOf((short) OperaStateEnum.FAIL.getCode()));
                    }
                } catch (Exception exception) {
                }
            } else {

                insertDTO.setIsSuccess(Short.valueOf((short) OperaStateEnum.SUCC.getCode()));
            }
        }

        if (info.getEx() != null) {
            insertDTO.setIsSuccess(Short.valueOf((short) OperaStateEnum.FAIL.getCode()));
            if (info.getEx() instanceof BindException) {
                BindException ex = (BindException) info.getEx();
                List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
                List<String> errors = new ArrayList<String>(fieldErrors.size());
                for (FieldError error : fieldErrors) {
                    errors.add(error.getDefaultMessage());
                }
                insertDTO.setDesc(info.getOpLogInfo().desc() + "失败，原因：" + StringUtils.join(errors, ";"));
            } else {
                insertDTO.setDesc(info.getOpLogInfo().desc() + "失败，原因：" + info.getEx().getMessage());
            }
        }
        if (insertDTO.getIsSuccess().intValue() == OperaStateEnum.SUCC.getCode()) {
            insertDTO.setDesc(info.getOpLogInfo().desc() + "成功");
        }

        if (info.getOpLogInfo().logType() == OpLog.LogType.LOGIN &&
                StringUtils.isEmpty(insertDTO.getUserId()) && insertDTO
                .getIsSuccess().intValue() == OperaStateEnum.SUCC.getCode()) {

            String loginName = null;
            if (inPara != null && !inPara.isEmpty()) {
                loginName = inPara.getString("principal");
            }
            if (!StringUtils.isEmpty(loginName)) {
                List<AppLogUser> loginUsers = this.logService.getUserByLoginName(loginName);
                if (!CollectionUtils.isEmpty(loginUsers)) {
                    insertDTO.setUserId(((AppLogUser) loginUsers.get(0)).getUserId());
                    insertDTO.setWorkCode(((AppLogUser) loginUsers.get(0)).getWorkCode());
                    insertDTO.setName(((AppLogUser) loginUsers.get(0)).getName());
                    insertDTO.setOrgId(((AppLogUser) loginUsers.get(0)).getOrgId());
                    insertDTO.setPhoneNumber(((AppLogUser) loginUsers.get(0)).getPhoneNumber());
                }
            }
        }

        this.logService.insert(insertDTO);
    }


    private JSONObject transformInParaJson(Object[] args) {
        if (args == null || args.length == 0) {
            return null;
        }
        JSONObject params = new JSONObject();
        for (Object o : args) {
            JSONObject inPara = doTransformInPara(o);
            if (inPara != null && !inPara.isEmpty()) {

                params.putAll(inPara);
            }
        }
        if (params.isEmpty()) {
            return null;
        }
        return params;
    }


    private JSONObject doTransformInPara(Object arg) {
        JSONObject inPara = new JSONObject();

        if (arg instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) arg;
            Enumeration<String> enumeration = request.getParameterNames();
            while (enumeration.hasMoreElements()) {
                String name = (String) enumeration.nextElement();
                inPara.put(name, request.getParameter(name));

            }

        } else if (arg instanceof org.springframework.security.core.Authentication) {
            inPara = JSON.parseObject(JSON.toJSONString(arg));
        } else {
            if (filter(arg)) {
                return null;
            }
            inPara = JSON.parseObject(JSON.toJSONString(arg));
        }

        return inPara;
    }


    private boolean filter(Object arg) {
        String className = arg.getClass().getName();
        for (String prefix : Constants.FILTER_PACKAGE_PREFIXS) {
            if (className.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

}
