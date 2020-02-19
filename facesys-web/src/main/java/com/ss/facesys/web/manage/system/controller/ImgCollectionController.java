package com.ss.facesys.web.manage.system.controller;

import com.github.pagehelper.Page;
import com.ss.exception.ServiceException;
import com.ss.facesys.data.system.client.IImgCollectionService;
import com.ss.facesys.data.system.common.model.ImgCollection;
import com.ss.facesys.data.system.common.model.SysPara;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.system.form.ImgCollectionForm;
import com.ss.facesys.web.manage.system.query.ImgCollectionQuery;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.valide.APIDeltGroup;
import com.ss.valide.APIPageGroup;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * ImgCollectionController
 *
 * @author FrancisYs
 * @date 2020/2/18
 * @email yaoshuai@ss-cas.com
 */
@RestController
@RequestMapping({"/collection/img"})
public class ImgCollectionController extends BaseController {

    @Resource
    private IImgCollectionService imgCollectionService;

    /**
     * 查询收藏分页列表
     *
     * @param query
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/pages"}, method = {RequestMethod.POST})
    public ResponseEntity<PageEntity<ImgCollection>> pages(@RequestBody @Validated({APIPageGroup.class}) ImgCollectionQuery query, BindingResult bindingResult) throws BindException {
        ResponseEntity<PageEntity<ImgCollection>> resp = validite(bindingResult);
        /*
            TODO 【抓拍库、人证库】的收藏数据需要筛选用户权限下的未删除的设备数据
         */
        ImgCollection imgCollection = new ImgCollection();
        BeanUtils.copyProperties(query, imgCollection);
        Page<ImgCollection> data = (Page) imgCollectionService.pages(imgCollection, query.getCurrentPage(), query.getPageSize());
        resp.setData(new PageEntity(data));
        return resp;
    }

    /* 收藏图片 */
    @RequestMapping(value = {"/collect"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, String>> infoMap(@RequestBody SysPara sysPara, BindingResult bindingResult) throws Exception {
//        try {
//            ResponseEntity<Map<String, String>> resp = validite(bindingResult);
//            Map<String, String> map = this.sysParaService.getSysParaInfoMap(sysPara);
//            resp.setData(map);
//            return resp;
//        } catch (Exception e) {
//            this.logger.error(e.toString(), e);
//            throw e;
//        }
        return null;
    }

    /**
     * 取消收藏
     *
     * @param form
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/remove"}, method = {RequestMethod.POST})
    public ResponseEntity<String> remove(@RequestBody @Validated({APIDeltGroup.class}) ImgCollectionForm form, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        ImgCollection imgCollection = new ImgCollection();
        BeanUtils.copyProperties(form, imgCollection);
        try {
            resp.setData(this.imgCollectionService.remove(imgCollection));
        } catch (ServiceException e) {
            this.logger.error("取消收藏失败，错误码：{}，异常信息：{}", e.getCode(), e.getMessage(), e);
            return createFailResponse(e);
        }
        return resp;
    }

}
