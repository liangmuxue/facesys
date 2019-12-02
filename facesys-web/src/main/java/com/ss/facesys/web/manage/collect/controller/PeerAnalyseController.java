package com.ss.facesys.web.manage.collect.controller;

import com.ss.facesys.data.collect.client.IPeerService;
import com.ss.facesys.data.collect.common.dto.PeerDTO;
import com.ss.facesys.data.collect.common.model.AddPersonDetail;
import com.ss.facesys.web.manage.baseinfo.controller.BaseController;
import com.ss.response.ResponseEntity;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 同行人员查询
 * @author chao
 * @create 2019/8/21
 * @email lishuangchao@ss-cas.com
 **/
@RestController
@RequestMapping({"/collect/peerAnalysis"})
public class PeerAnalyseController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(PeerAnalyseController.class);

    @Resource
    private IPeerService peerService;

    /**
     * 同行人员查询
     * @param request
     * @param response
     * @param peerDTO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/peers"}, method = {RequestMethod.POST})
    public ResponseEntity<List<PeerDTO>> peers(HttpServletRequest request, HttpServletResponse response, @RequestBody @Validated({com.ss.valide.APIGetsGroup.class}) PeerDTO peerDTO, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<PeerDTO>> resp = validite(bindingResult);
        try {
            //同行人员查询处理
            List<PeerDTO> list = this.peerService.findPeer(peerDTO.getAddPersonId());
            resp.setData(list);
        } catch (Exception e) {
            //同行人员查询失败处理
            resp = createFailResponse();
            resp.setMessage("同行人员查询失败");
            this.logger.error("同行人员查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

    /**
     * 同行人员详细信息查询
     * @param request
     * @param response
     * @param peerDTO
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/peers/detail"}, method = {RequestMethod.POST})
    public ResponseEntity<List<AddPersonDetail>> peersDetail(HttpServletRequest request, HttpServletResponse response, @RequestBody @Validated({com.ss.valide.APIListGroup.class}) PeerDTO peerDTO, BindingResult bindingResult) throws Exception {
        ResponseEntity<List<AddPersonDetail>> resp = validite(bindingResult);
        try {
            //同行人员详细信息查询处理
            List<AddPersonDetail> list = this.peerService.findPeerDetails(peerDTO);
            resp.setData(list);
        } catch (Exception e) {
            //同行人员详细信息查询失败处理
            resp = createFailResponse();
            resp.setMessage("同行人员详情信息查询失败");
            this.logger.error("同行人员详情信息查询失败，原因：" + e.toString(), e);
        }
        return resp;
    }

}
