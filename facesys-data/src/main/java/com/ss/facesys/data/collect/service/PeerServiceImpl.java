package com.ss.facesys.data.collect.service;

import com.ss.facesys.data.collect.client.IPeerService;
import com.ss.facesys.data.collect.common.dto.PeerDTO;
import com.ss.facesys.data.collect.common.model.AddPersonDetail;
import com.ss.facesys.data.collect.mapper.PeerMapper;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class})
public class PeerServiceImpl implements IPeerService {
    public static final Log LOG = LogFactory.getLog(HouseServiceImpl.class);

    @Autowired
    private PeerMapper peerMapper;

    /**
     * 同行人员查询
     *
     * @param addPersonId
     * @return
     */
    @Override
    public List<PeerDTO> findPeer(String addPersonId) {
        //查询并返回同行人员信息
        return this.peerMapper.findPeer(addPersonId);
    }

    /**
     * 同行人员详细信息查询
     * @param peerDTO
     * @return
     */
    @Override
    public List<AddPersonDetail> findPeerDetails(PeerDTO peerDTO) {
        //查询并返回同行人员详细信息
        return this.peerMapper.findPeerDetails(peerDTO);
    }
}
