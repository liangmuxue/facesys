package com.ss.facesys.web.app.recog.query;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.StringJoiner;

/**
 * RecogQuery
 *
 * @author FrancisYs
 * @date 2019/12/4
 * @email yaoshuai@ss-cas.com
 */
public class RecogQuery implements Serializable {

    @NotBlank(message = "{recog.faceA.empty}")
    private String faceA;
    @NotBlank(message = "{recog.faceB.empty}")
    private String faceB;
    @NotNull(message = "{recog.imgType.empty}")
    private Integer typeA;
    @NotNull(message = "{recog.imgType.empty}")
    private Integer typeB;


    public String getFaceA() {
        return faceA;
    }

    public void setFaceA(String faceA) {
        this.faceA = faceA;
    }

    public String getFaceB() {
        return faceB;
    }

    public void setFaceB(String faceB) {
        this.faceB = faceB;
    }

    public Integer getTypeA() {
        return typeA;
    }

    public void setTypeA(Integer typeA) {
        this.typeA = typeA;
    }

    public Integer getTypeB() {
        return typeB;
    }

    public void setTypeB(Integer typeB) {
        this.typeB = typeB;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RecogQuery.class.getSimpleName() + "[", "]")
                .add("faceA='" + faceA + "'")
                .add("faceB='" + faceB + "'")
                .toString();
    }

}
