package com.ss.facesys.web.manage.collect.controller;

import com.github.pagehelper.Page;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.collect.client.IHotelService;
import com.ss.facesys.data.collect.common.model.*;
import com.ss.facesys.data.resource.common.web.HotelVO;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.spider.log.constants.ModuleCode;
import com.ss.valide.APIAddGroup;
import com.ss.valide.APIDeltGroup;
import com.ss.valide.APIEditGroup;
import com.ss.valide.APIGetsGroup;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* 酒店
* @author chao
* @create 2020/2/4
* @email lishuangchao@ss-cas.com
**/
@RestController
@CrossOrigin
@RequestMapping({"/hotel"})
public class HotelController extends BaseController {

    @Resource
    private IHotelService hotelService;

    /**
     * 酒店分页查询
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/page"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "酒店分页查询", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<PageEntity<House>> hotelPage(@RequestBody HotelVO para , BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<House>> resp = validite(bindingResult);
        try {
            Page<House> data = (Page) this.hotelService.hotelPage(para);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            //酒店分页查询失败处理
            this.logger.error("酒店分页查询失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 添加酒店
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/insert"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "添加酒店", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> insertHotel(@RequestBody @Validated({APIAddGroup.class})HotelVO para , BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            int num = this.hotelService.insertHotel(para);
            if (num > 0) {
                resp.setData("添加成功");
            } else {
                resp = createFailResponse();
                resp.setMessage("添加失败，请联系管理员");
            }
        } catch (Exception e) {
            //添加酒店失败处理
            this.logger.error("添加酒店失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 修改酒店信息
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "修改酒店信息", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> updateHotel(@RequestBody @Validated({APIEditGroup.class})HotelVO para , BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            int num = this.hotelService.updateHotel(para);
            if (num > 0) {
                resp.setData("修改成功");
            } else {
                resp = createFailResponse();
                resp.setMessage("修改失败，请联系管理员");
            }
        } catch (Exception e) {
            //修改酒店失败处理
            this.logger.error("修改酒店信息失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 删除酒店
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "删除酒店", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> deleteHotel(@RequestBody @Validated({APIDeltGroup.class})HotelVO para , BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            String message = this.hotelService.deleteHotel(para);
            if ("删除成功".equals(message)) {
                resp.setData(message);
            } else {
                resp = createFailResponse();
                resp.setMessage(message);
            }
        } catch (Exception e) {
            //删除酒店失败处理
            this.logger.error("删除酒店失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 查询酒店详情
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/detail"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.RESOURCE, desc = "查询酒店详情", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Hotel> hotelDetail(@RequestBody @Validated({APIGetsGroup.class})HotelVO para , BindingResult bindingResult) throws Exception {
        ResponseEntity<Hotel> resp = validite(bindingResult);
        try {
            Hotel hotel = this.hotelService.detail(para);
            resp.setData(hotel);
        } catch (Exception e) {
            //查询酒店详情失败处理
            this.logger.error("查询酒店详情失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }
}
