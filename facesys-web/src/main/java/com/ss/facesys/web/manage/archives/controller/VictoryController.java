package com.ss.facesys.web.manage.archives.controller;

import com.github.pagehelper.Page;
import com.ss.annotation.OpLog;
import com.ss.enums.OperaTypeEnum;
import com.ss.facesys.data.archives.client.IVictoryService;
import com.ss.facesys.data.archives.common.model.CasePolice;
import com.ss.facesys.data.archives.common.model.Victory;
import com.ss.facesys.data.archives.common.model.VictoryStatistics;
import com.ss.facesys.data.archives.common.web.VictoryVO;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.response.PageEntity;
import com.ss.response.ResponseEntity;
import com.ss.spider.log.constants.ModuleCode;
import com.ss.spider.system.organization.model.Organization;
import com.ss.valide.APIAddGroup;
import com.ss.valide.APIDeltGroup;
import com.ss.valide.APIEditGroup;
import com.ss.valide.APIGetsGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
* 战果汇总
* @author chao
* @create 2020/2/17
* @email lishuangchao@ss-cas.com
**/
@RestController
@RequestMapping({"/victory"})
public class VictoryController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(VictoryController.class);
    @Resource
    private IVictoryService victoryService;

    /**
     * 战果汇总分页查询
     * @param request
     * @param victoryVO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/page"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.BUSINESS, desc = "战果汇总分页查询", type = OperaTypeEnum.SEARCH)
    public ResponseEntity<PageEntity<Victory>> victoryPage(HttpServletRequest request, @RequestBody VictoryVO victoryVO, BindingResult bindingResult) throws Exception {
        ResponseEntity<PageEntity<Victory>> resp = validite(bindingResult);
        try {
            //战果汇总分页查询
            Page<Victory> data = (Page) this.victoryService.victoryPage(victoryVO);
            resp.setData(new PageEntity(data));
        } catch (Exception e) {
            //战果汇总分页查询失败处理
            resp = createFailResponse();
            resp.setMessage("战果汇总分页查询失败");
            this.logger.error("战果汇总分页查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 账户树查询
     * @return
     * @throws BindException
     */
    @RequestMapping(value = {"/tree"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.BUSINESS, desc = "查询账户树", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<Organization>> tree() throws BindException {
        ResponseEntity<List<Organization>> resp = createSuccResponse();
        try {
            resp.setData(this.victoryService.treeData());
        } catch (Exception e) {
            resp = createFailResponse();
            resp.setMessage("账户树查询失败");
            this.logger.error("账户树查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 添加战果汇总
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/insert"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.BUSINESS, desc = "添加战果汇总", type = OperaTypeEnum.ADD)
    public ResponseEntity<String> insertVictory(@RequestBody @Validated({APIAddGroup.class})VictoryVO para , BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            int num = this.victoryService.insertVictory(para);
            if (num > 0) {
                resp.setData("添加成功");
            } else {
                resp = createFailResponse();
                resp.setMessage("添加失败，请联系管理员");
            }
        } catch (Exception e) {
            //添加战果汇总失败处理
            this.logger.error("添加战果汇总失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 修改战果汇总信息
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/update"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.BUSINESS, desc = "修改战果汇总信息", type = OperaTypeEnum.EDIT)
    public ResponseEntity<String> updateVictory(@RequestBody @Validated({APIEditGroup.class})VictoryVO para , BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            int num = this.victoryService.updateVictory(para);
            if (num > 0) {
                resp.setData("修改成功");
            } else {
                resp = createFailResponse();
                resp.setMessage("修改失败，请联系管理员");
            }
        } catch (Exception e) {
            //修改战果汇总失败处理
            this.logger.error("修改战果汇总信息失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 删除战果汇总
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.BUSINESS, desc = "删除战果汇总", type = OperaTypeEnum.DELETE)
    public ResponseEntity<String> deleteVictory(@RequestBody @Validated({APIDeltGroup.class})VictoryVO para , BindingResult bindingResult) throws Exception {
        ResponseEntity<String> resp = validite(bindingResult);
        try {
            int num = this.victoryService.deleteVictory(para);
            if (num > 0) {
                resp.setData("删除成功");
            } else {
                resp = createFailResponse();
                resp.setMessage("删除失败，请联系管理员");
            }
        } catch (Exception e) {
            //删除战果汇总失败处理
            this.logger.error("删除战果汇总失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 查询战果汇总详情
     * @param para
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/detail"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.BUSINESS, desc = "查询战果汇总详情", type = OperaTypeEnum.SELECT)
    public ResponseEntity<Victory> victoryDetail(@RequestBody @Validated({APIGetsGroup.class})VictoryVO para , BindingResult bindingResult) throws Exception {
        ResponseEntity<Victory> resp = validite(bindingResult);
        try {
            Victory victory = this.victoryService.victoryDetail(para);
            resp.setData(victory);
        } catch (Exception e) {
            //查询战果汇总详情失败处理
            this.logger.error("查询战果汇总详情失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 查询战果统计
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/count"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.BUSINESS, desc = "查询战果统计", type = OperaTypeEnum.SELECT)
    public ResponseEntity<VictoryStatistics> victoryCount(@RequestBody VictoryVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<VictoryStatistics> resp = validite(bindingResult);
        try {
            VictoryStatistics count = this.victoryService.findCount();
            resp.setData(count);
        } catch (Exception e) {
            //查询战果统计失败处理
            this.logger.error("查询战果统计失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }

    /**
     * 查询战果排行
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/rank"}, method = {RequestMethod.POST})
    @OpLog(model = ModuleCode.BUSINESS, desc = "查询战果排行", type = OperaTypeEnum.SELECT)
    public ResponseEntity<List<CasePolice>> victoryRank(@RequestBody VictoryVO para, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<CasePolice>> resp = validite(bindingResult);
        try {
            List<CasePolice> count = this.victoryService.findRank();
            resp.setData(count);
        } catch (Exception e) {
            //查询战果排行失败处理
            this.logger.error("查询战果排行失败原因：+" + e.toString(), e);
            resp = createFailResponse();
            resp.setMessage("操作失败！请联系管理员！");
        }
        return resp;
    }
}
