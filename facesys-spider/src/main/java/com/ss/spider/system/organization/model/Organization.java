package com.ss.spider.system.organization.model;

import com.ss.entity.DTEntity;
import com.ss.facesys.util.StringUtils;
import com.ss.facesys.util.coordinate.IscCoordinate;
import org.apache.commons.collections.CollectionUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * 单位表实体映射
 *
 * @author FrancisYs
 * @date 2019/12/3
 * @email yaoshuai@ss-cas.com
 */
@Table(name = "cw_ge_organization")
public class Organization extends DTEntity {

    private static final long serialVersionUID = -5245102590723486068L;

    /**
     * 单位ID
     */
    @Id
    @Column(name = "org_id")
    private String orgId;
    /**
     * 单位编号
     */
    @Column(name = "org_code")
    private String orgCode;
    /**
     * 单位中文名
     */
    @Column(name = "org_cname")
    private String orgCname;
    /**
     * 单位英文名
     */
    @Column(name = "org_ename")
    private String orgEname;
    /**
     * 地址
     */
    @Column(name = "address")
    private String address;
    /**
     * 邮编
     */
    @Column(name = "zip_code")
    private String zipCode;
    /**
     * 联系电话
     */
    @Column(name = "telephone")
    private String telephone;
    /**
     * 传真
     */
    @Column(name = "fax")
    private String fax;
    /**
     * 状态[1 有效|0 无效|-1 删除]
     */
    @Column(name = "status")
    private Integer status;
    /**
     * 上级单位ID
     */
    @Column(name = "parent_id")
    private String parentId;
    @Column(name = "departh")
    private String departh;
    /**
     * 备注信息
     */
    @Column(name = "remark")
    private String remark;
    /**
     * 中心点经度
     */
    private Double lon;
    /**
     * 中心点纬度
     */
    private Double lat;
    /**
     * 坐标组信息
     */
    @Column(name = "gis_area")
    private String gisArea;
    /**
     * 单位序号
     */
    private Integer seq;
    @Column(name = "link_man_name")
    private String linkManName;
    @Column(name = "link_man_dept")
    private String linkManDept;
    @Column(name = "link_man_pos")
    private String linkManPos;
    @Column(name = "link_man_tel")
    private String linkManTel;
    @Column(name = "link_man_fax")
    private String linkManFax;
    @Column(name = "link_man_email")
    private String linkManEmail;
    @Column(name = "from_system")
    private String fromSystem;
    @Column(name = "is_linkage")
    private Short isLinkage;

    @Transient
    private List<Organization> children = new ArrayList<>();
    @Transient
    private Integer onlineDeviceCount = 0;
    @Transient
    private Integer offlineDeviceCount = 0;
    @Transient
    private String createUserName;
    @Transient
    private String updateUserName;

    public String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return this.orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgCname() {
        return this.orgCname;
    }

    public void setOrgCname(String orgCname) {
        this.orgCname = orgCname;
    }

    public String getOrgEname() {
        return this.orgEname;
    }

    public void setOrgEname(String orgEname) {
        this.orgEname = orgEname;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDeparth() {
        return this.departh;
    }

    public void setDeparth(String departh) {
        this.departh = departh;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getGisArea() {
        return gisArea;
    }

    public void setGisArea(String gisArea) {
        this.gisArea = gisArea;
    }

    public void setGisArea(List<IscCoordinate> gisList) {
        if (CollectionUtils.isNotEmpty(gisList)) {
            List<String> gisArea = new ArrayList<>();
            gisList.forEach(gis -> {
                gisArea.add(gis.getLatitude() + "," + gis.getLongitude());
            });
            if (CollectionUtils.isNotEmpty(gisArea)) {
                this.gisArea = StringUtils.join(gisArea, "-");
            }
        }
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getLinkManName() {
        return this.linkManName;
    }

    public void setLinkManName(String linkManName) {
        this.linkManName = linkManName;
    }

    public String getLinkManDept() {
        return this.linkManDept;
    }

    public void setLinkManDept(String linkManDept) {
        this.linkManDept = linkManDept;
    }

    public String getLinkManPos() {
        return this.linkManPos;
    }

    public void setLinkManPos(String linkManPos) {
        this.linkManPos = linkManPos;
    }

    public String getLinkManTel() {
        return this.linkManTel;
    }

    public void setLinkManTel(String linkManTel) {
        this.linkManTel = linkManTel;
    }

    public String getLinkManFax() {
        return this.linkManFax;
    }

    public void setLinkManFax(String linkManFax) {
        this.linkManFax = linkManFax;
    }

    public String getLinkManEmail() {
        return this.linkManEmail;
    }

    public void setLinkManEmail(String linkManEmail) {
        this.linkManEmail = linkManEmail;
    }

    public String getFromSystem() {
        return this.fromSystem;
    }

    public void setFromSystem(String fromSystem) {
        this.fromSystem = fromSystem;
    }

    public Short getIsLinkage() {
        return this.isLinkage;
    }

    public void setIsLinkage(Short isLinkage) {
        this.isLinkage = isLinkage;
    }

    public List<String> getDeparthList() {
        if (this.departh == null || "".equals(this.departh)) {
            return null;
        }
        return Arrays.asList(this.departh.split(","));
    }

    public List<Organization> getChildren() {
        return children;
    }

    public void setChildren(List<Organization> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Organization.class.getSimpleName() + "[", "]")
                .add("orgId='" + orgId + "'")
                .add("orgCode='" + orgCode + "'")
                .add("orgCname='" + orgCname + "'")
                .add("orgEname='" + orgEname + "'")
                .add("address='" + address + "'")
                .add("zipCode='" + zipCode + "'")
                .add("telephone='" + telephone + "'")
                .add("fax='" + fax + "'")
                .add("status=" + status)
                .add("parentId='" + parentId + "'")
                .add("departh='" + departh + "'")
                .add("remark='" + remark + "'")
                .add("lon=" + lon)
                .add("lat=" + lat)
                .add("gisArea='" + gisArea + "'")
                .add("seq='" + seq + "'")
                .add("linkManName='" + linkManName + "'")
                .add("linkManDept='" + linkManDept + "'")
                .add("linkManPos='" + linkManPos + "'")
                .add("linkManTel='" + linkManTel + "'")
                .add("linkManFax='" + linkManFax + "'")
                .add("linkManEmail='" + linkManEmail + "'")
                .add("fromSystem='" + fromSystem + "'")
                .add("isLinkage=" + isLinkage)
                .add("children=" + children)
                .toString();
    }

    public Integer getOnlineDeviceCount() {
        return onlineDeviceCount;
    }

    public void setOnlineDeviceCount(Integer onlineDeviceCount) {
        this.onlineDeviceCount = onlineDeviceCount;
    }

    public Integer getOfflineDeviceCount() {
        return offlineDeviceCount;
    }

    public void setOfflineDeviceCount(Integer offlineDeviceCount) {
        this.offlineDeviceCount = offlineDeviceCount;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }
}