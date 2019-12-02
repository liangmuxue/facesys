package com.ss.isc.sync.data.service;

import com.ss.isc.sync.data.model.Camera;

import java.util.List;

public interface ISystemService {

    List<Camera> queryCamera();

    Camera getCameraByNo(String paramString1, String paramString2);

    int insertCamera(Camera paramCamera);

    int updateCamera(Camera paramCamera);

}
