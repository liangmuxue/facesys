package com.ss.spider.log.aspect;

import com.ss.annotation.OpLog;
import com.ss.spider.log.async.AsyncLogging;
import com.ss.spider.log.bean.LoggingInfo;
import com.ss.spider.log.bean.ReqestInfo;
import com.ss.spider.log.constants.Constants;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LogAspect {

    @Autowired
    private AsyncLogging asyncLogging;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Around(value = "@annotation(opLog)", argNames = "joinPoint,opLog")
    public Object around(ProceedingJoinPoint joinPoint, OpLog opLog) throws Throwable {
        long operTime = System.currentTimeMillis();
        Object res = null;
        Throwable ex = null;
        try {
            res = joinPoint.proceed();
        } catch (Throwable throwable) {
            ex = throwable;
        }
        Long timeConsum = Long.valueOf(System.currentTimeMillis() - operTime);
        try {
            ReqestInfo reqInfo = (ReqestInfo) Constants.REQEST_INFO.get();
            Constants.REQEST_INFO.remove();
            LoggingInfo loggingInfo = new LoggingInfo(reqInfo, opLog, joinPoint.getArgs(), res, Long.valueOf(operTime), Integer.valueOf(timeConsum.intValue()), ex);
            this.asyncLogging.logging(loggingInfo);
        } catch (Exception exception) {
        }

        if (ex != null) {
            throw ex;
        }
        return res;
    }

}
