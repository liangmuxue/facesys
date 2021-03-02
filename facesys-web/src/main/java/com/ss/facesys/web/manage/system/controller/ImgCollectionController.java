package com.ss.facesys.web.manage.system.controller;

import com.github.pagehelper.Page;
import com.ss.enums.StatusEnum;
import com.ss.exception.ServiceException;
import com.ss.facesys.data.collect.common.model.DevicePersoncard;
import com.ss.facesys.data.collect.common.model.OfflineVideo;
import com.ss.facesys.data.collect.mapper.DevicePersoncardMapper;
import com.ss.facesys.data.collect.mapper.OfflineVideoMapper;
import com.ss.facesys.data.resource.common.model.Camera;
import com.ss.facesys.data.resource.mapper.CameraMapper;
import com.ss.facesys.data.system.client.IImgCollectionService;
import com.ss.facesys.data.system.common.dto.ImgCollectionResultDTO;
import com.ss.facesys.data.system.common.model.ImgCollection;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.constant.CommonConstant;
import com.ss.facesys.util.em.ResourceType;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.facesys.web.manage.system.form.ImgCollectionForm;
import com.ss.facesys.web.manage.system.query.ImgCollectionQuery;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.valide.APIAddGroup;
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
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
    @Resource
    private CameraMapper cameraMapper;
    @Resource
    private OfflineVideoMapper offlineVideoMapper;
    @Resource
    private DevicePersoncardMapper devicePersoncardMapper;

    /**
     * 查询收藏分页列表
     *
     * @param query
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/pages"}, method = {RequestMethod.POST})
    public ResponseEntity<PageEntity<ImgCollectionResultDTO>> pages(@RequestBody @Validated({APIPageGroup.class}) ImgCollectionQuery query, BindingResult bindingResult) throws BindException {
        ResponseEntity<PageEntity<ImgCollectionResultDTO>> resp = validite(bindingResult);
        ImgCollection imgCollection = new ImgCollection();
        BeanUtils.copyProperties(query, imgCollection);
        Page<ImgCollectionResultDTO> data = (Page) imgCollectionService.pages(imgCollection, query.getCurrentPage(), query.getPageSize());
        resp.setData(new PageEntity(data));
        return resp;
    }

    /**
     * 收藏图片
     *
     * @param form
     * @param bindingResult
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/collect"}, method = {RequestMethod.POST})
    public ResponseEntity<String> collect(@RequestBody @Validated({APIAddGroup.class}) ImgCollectionForm form, BindingResult bindingResult) throws BindException {
        ResponseEntity<String> resp = validite(bindingResult);
        ImgCollection imgCollection = new ImgCollection();
        BeanUtils.copyProperties(form, imgCollection);
        try {
            resp.setData(this.imgCollectionService.collect(imgCollection));
        } catch (ServiceException e) {
            this.logger.error("收藏失败，错误码：{}，异常信息：{}", e.getCode(), e.getMessage(), e);
            return createFailResponse(e);
        }
        return resp;
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
