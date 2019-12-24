package com.ss.facesys.data.resource.service;

import com.ss.facesys.data.baseinfo.service.BaseServiceImpl;
import com.ss.facesys.data.resource.client.ISceneService;
import com.ss.facesys.data.resource.common.model.Scene;
import com.ss.facesys.data.resource.mapper.SceneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 场景管理
* @author chao
* @create 2019/12/24
* @email lishuangchao@ss-cas.com
**/
@Service
@Transactional(rollbackFor = {Exception.class})
public class SceneServiceImpl extends BaseServiceImpl implements ISceneService {

    @Autowired
    private SceneMapper sceneMapper;

    /**
     * 场景列表
     * @return
     */
    @Override
    public List<Scene> list() {
        return this.sceneMapper.list();
    }

    /**
     * 新增场景
     * @param scene
     * @return
     */
    @Override
    public int add(Scene scene) {
        return this.sceneMapper.add(scene);
    }

    /**
     * 修改场景
     * @param scene
     * @return
     */
    @Override
    public int edit(Scene scene) {
        return this.sceneMapper.edit(scene);
    }

    /**
     * 删除场景
     * @param scene
     * @return
     */
    @Override
    public int delt(Scene scene) {
        return this.sceneMapper.delt(scene);
    }
}
