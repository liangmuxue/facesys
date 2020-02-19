package com.ss.facesys.web.manage.system.form;

import com.ss.valide.APIDeltGroup;

import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

/**
 * ImgCollectionForm
 *
 * @author FrancisYs
 * @date 2020/2/18
 * @email yaoshuai@ss-cas.com
 */
public class ImgCollectionForm {

    @NotNull(message = "{imgCollection.id.empty}", groups = {APIDeltGroup.class})
    private Integer id;
    private String userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ImgCollectionForm.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("userId='" + userId + "'")
                .toString();
    }

}
