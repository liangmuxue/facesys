package com.ss.facesys.web.app.facedb.query;

import com.ss.request.Pagination;
import com.ss.valide.*;

import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

/**
 * FacedbQuery
 *
 * @author FrancisYs
 * @date 2019/12/5
 * @email yaoshuai@ss-cas.com
 */
public class FacedbQuery extends Pagination {

    @NotNull(message = "{facedb.id.empty}", groups = {APIGetsGroup.class})
    private Integer id;
    private String name;
    private String orgId;
    private Integer monitorState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getMonitorState() {
        return monitorState;
    }

    public void setMonitorState(Integer monitorState) {
        this.monitorState = monitorState;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FacedbQuery.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("orgId='" + orgId + "'")
                .add("monitorState=" + monitorState)
                .toString();
    }

}
