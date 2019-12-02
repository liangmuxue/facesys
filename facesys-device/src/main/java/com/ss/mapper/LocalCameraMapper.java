package com.ss.mapper;

import com.ss.model.Camera;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
* 设备抽帧、推流
* @author chao
* @create 2019/9/16
* @email lishuangchao@ss-cas.com
**/
@Repository
@Mapper
public interface LocalCameraMapper {

    Camera findByCodeOffLine(String code);

    Camera findByCodeOnLine(String code);

    int online(String code);

    int offline(String code);

    List<Camera> findStopCamera();

    List<Camera> findPushCamera();

    Camera findByCodeOffCut(String code);

    Camera findByCodeOnCut(String code);

    int cutOnline(String code);

    int cutOffline(String code);

    List<Camera> findCutStopCamera();

    List<Camera> findCutCamera();

    List<Camera> runCamera();
}
