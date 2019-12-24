package com.ss.facesys.data.resource.mapper;

import com.ss.facesys.data.resource.common.model.Scene;
import com.ss.mapper.SsMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
/**
* 场景管理
* @author chao
* @create 2019/12/24
* @email lishuangchao@ss-cas.com
**/
@Component
@Mapper
public interface SceneMapper extends SsMapper<Scene> {
    /**
     * 场景列表
     * @return
     */
    List<Scene> list();

    /**
     * 新增场景
     * @param scene
     * @return
     */
    int add(Scene scene);

    /**
     * 修改场景
     * @param scene
     * @return
     */
    int edit(Scene scene);

    /**
     * 删除场景
     * @param scene
     * @return
     */
    int delt(Scene scene);
}
