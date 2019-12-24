package com.ss.facesys.data.resource.common.model;

import com.ss.valide.APIAddGroup;
import com.ss.valide.APIDeltGroup;
import com.ss.valide.APIEditGroup;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
* 场景类
* @author chao
* @create 2019/12/24
* @email lishuangchao@ss-cas.com
**/
@Table(name = "cw_base_scene")
public class Scene implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotNull(message = "{building.id.empty}", groups = {APIEditGroup.class, APIDeltGroup.class})
    @Id
    private Integer id;
    @NotBlank(message = "{building.id.empty}", groups = {APIAddGroup.class, APIEditGroup.class})
    private String scene;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }
}
