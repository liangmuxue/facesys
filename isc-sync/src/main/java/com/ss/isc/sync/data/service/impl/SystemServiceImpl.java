package com.ss.isc.sync.data.service.impl;

import com.ss.isc.sync.data.mapper.SystemMapper;
import com.ss.isc.sync.data.model.Camera;
import com.ss.isc.sync.data.service.ISystemService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = {Exception.class})
public class SystemServiceImpl implements ISystemService {

    @Autowired
    private SystemMapper systemMapper;

    public List<Camera> queryCamera() {
        return this.systemMapper.queryCamera();
    }


    public Camera getCameraByNo(String cameraNo, String productCode) {
        return this.systemMapper.getCameraByNo(cameraNo, productCode);
    }


    public int insertCamera(Camera camera) {
        return this.systemMapper.insertCamera(camera);
    }


    public int updateCamera(Camera camera) {
        return this.systemMapper.updateCamera(camera);
    }

}
