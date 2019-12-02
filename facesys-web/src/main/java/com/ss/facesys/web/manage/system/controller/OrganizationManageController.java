package com.ss.facesys.web.manage.system.controller;

import com.ss.facesys.data.system.client.IOrganizationRegionService;
import com.ss.facesys.data.system.common.dto.UserPermission;
import com.ss.facesys.data.system.common.model.OrganizationRegion;
import com.ss.facesys.data.system.common.model.Region;
import com.ss.facesys.util.constant.CacheConstant;
import com.ss.facesys.util.jedis.JedisUtil;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.response.ResponseEntity;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({"/system/organization"})
public class OrganizationManageController extends BaseController {

    @Resource
    private IOrganizationRegionService oService;
    @Resource
    private JedisUtil jedisUtil;

    @RequestMapping(value = {"/list"}, method = {RequestMethod.POST})
    public ResponseEntity<List<OrganizationRegion>> list(HttpServletRequest request, HttpServletResponse response, @RequestBody OrganizationRegion dto, BindingResult bindingResult) throws Exception {
        try {
            ResponseEntity<List<OrganizationRegion>> resp = validite(bindingResult);
            List<OrganizationRegion> oList = this.oService.getList(dto);
            resp.setData(oList);
            return resp;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            throw e;
        }
    }


    @RequestMapping(value = {"/inserts"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, String>> update(HttpServletRequest request, HttpServletResponse response, @RequestBody OrganizationRegion dto, BindingResult bindingResult) throws Exception {
        try {
            ResponseEntity<Map<String, String>> resp = validite(bindingResult);
            this.oService.update(dto);
            this.jedisUtil.del(new String[]{CacheConstant.REDIS_KEY_USERPERMISSION});
            return resp;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            throw e;
        }
    }


    @RequestMapping(value = {"/delt"}, method = {RequestMethod.POST})
    public ResponseEntity<List<Region>> delt(HttpServletRequest request, HttpServletResponse response, @RequestBody OrganizationRegion dto, BindingResult bindingResult) throws Exception {
        try {
            ResponseEntity<List<Region>> resp = validite(bindingResult);

            List<Region> list = this.oService.getTree();

            resp.setData(list);
            return resp;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            throw e;
        }
    }


    @RequestMapping(value = {"/findUserPermission"}, method = {RequestMethod.POST})
    public ResponseEntity<List<UserPermission>> findUserPermission(HttpServletRequest request, BindingResult bindingResult) throws Exception {
        try {
            ResponseEntity<List<UserPermission>> resp = validite(bindingResult);
            resp.setData(this.oService.findUserPermission());
            return resp;
        } catch (Exception e) {
            this.logger.error(e.toString(), e);
            throw e;
        }
    }

//    @RequestMapping(value = {"/findUserPermission"}, method = {RequestMethod.POST})
//    public ResponseEntity<List<UserPermission>> improtFile(HttpServletRequest request, BindingResult bindingResult) throws Exception {
//        try {
//            ResponseEntity<List<UserPermission>> resp = validite(bindingResult);
//            //创建要读入的文件的输入流
//            InputStream inp = new FileInputStream("workboot.xml");
//            //根据上述创建的输入流 创建工作簿对象
//            WorkerBook wb = new HSSFWorkboot(inp);
//            //得到第一页sheet   从0开始索引
//            Sheet sheet = wb.getSheetAt(0);
//            //利用foreach循环 遍历sheet中所有的行
//            for(Row row:sheet){
//                //遍历row中所有的方格
//                for(Cell cell:row){
//                   System.out.print(cell.toString()+" ");
//                }
//                //每一个行输出后换行
//                System.out.println();
//            }
//            resp.setData(this.oService.findUserPermission());
//            return resp;
//        } catch (Exception e) {
//            this.logger.error(e.toString(), e);
//            throw e;
//        }
//    }



}
