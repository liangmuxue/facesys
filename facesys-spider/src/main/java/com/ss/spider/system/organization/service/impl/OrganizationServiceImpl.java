package com.ss.spider.system.organization.service.impl;

import com.github.pagehelper.PageHelper;
import com.ss.enums.StatusEnum;
import com.ss.exception.ServiceException;
import com.ss.facesys.util.StringUtils;
import com.ss.service.AbstractSsServiceImpl;
import com.ss.spider.system.department.mapper.DepartmentMapper;
import com.ss.spider.system.department.model.Department;
import com.ss.spider.system.organization.mapper.OrganizationMapper;
import com.ss.spider.system.organization.model.Organization;
import com.ss.spider.system.organization.model.OrganizationExp;
import com.ss.spider.system.organization.service.OrganizationService;
import com.ss.spider.system.sequence.model.AppSequence;
import com.ss.spider.system.sequence.service.AppSequenceService;
import com.ss.tools.DateUtils;
import com.ss.tools.UUIDUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 单位管理
 *
 * @author FrancisYs
 * @date 2019/12/3
 * @email yaoshuai@ss-cas.com
 */
@Service("organizationService")
public class OrganizationServiceImpl extends AbstractSsServiceImpl<Organization> implements OrganizationService<Organization> {

    private static OrganizationServiceImpl poi = null;
    @Autowired
    private OrganizationMapper organizationMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    @Qualifier("appSequenceService")
    private AppSequenceService<AppSequence> appSequenceService;

    @Override
    public String getNewOrgId() throws ServiceException {
        return UUIDUtils.getUUID();
    }

    /**
     * 查询单位列表
     *
     * @param org
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Organization> list(Organization org) {
        Example example = new Example(Organization.class);
        example.createCriteria().andEqualTo("status", StatusEnum.EFFECT.getCode());
        example.setOrderByClause("create_time desc, org_code asc");
        return organizationMapper.selectByExample(example);
    }

    /**
     * 查询单位分页列表
     *
     * @param org
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<Organization> pages(Organization org, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        Example example = this.entityToExample(org);
        example.getOredCriteria().get(0).andEqualTo("status", StatusEnum.EFFECT.getCode());
        example.setOrderByClause("create_time desc, org_code asc");
        return organizationMapper.selectByExample(example);
    }

    /**
     * 查询单位树
     *
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Organization> treeData() {
        Example example = new Example(Organization.class);
        example.createCriteria().andEqualTo("status", StatusEnum.EFFECT.getCode());
        example.orderBy("seq").asc();
        List<Organization> organizations = organizationMapper.selectByExample(example);
        return createOrgTree(organizations);
    }

    private List<Organization> createOrgTree(List<Organization> organizationList) {
        if (CollectionUtils.isEmpty(organizationList)) {
            return Collections.emptyList();
        }
        // 创建根节点
        Organization root = new Organization();
        // 组装Map数据
        Map<String, Organization> dataMap = new HashMap<>(16);
        for (Organization organization : organizationList) {
            dataMap.put(organization.getOrgId(), organization);
        }
        // 组装树形结构
        Set<Map.Entry<String, Organization>> entrySet = dataMap.entrySet();
        for (Map.Entry<String, Organization> entry : entrySet) {
            Organization currentNode = entry.getValue();
            if (StringUtils.isEmpty(currentNode.getParentId()) || "0".equals(currentNode.getParentId())) {
                root.getChildren().add(currentNode);
            } else {
                dataMap.get(currentNode.getParentId()).getChildren().add(currentNode);
            }
        }
        return root.getChildren();
    }

    /**
     * 查询单位信息
     *
     * @param orgId
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Organization get(final String orgId) {
        Organization organization = new Organization();
        organization.setOrgId(orgId);
        organization.setStatus(StatusEnum.EFFECT.getCode());
        return organizationMapper.selectOne(organization);
    }

    /**
     * 校验是否单位编号或者名称是否重复
     *
     * @param entity
     */
    private void duplicateCheck(final Organization entity) throws ServiceException {
        Example example = new Example(Organization.class);
        example.createCriteria().orEqualTo("orgCode", entity.getOrgCode()).orEqualTo("orgCname", entity.getOrgCname());
        example.and().andEqualTo("status", StatusEnum.EFFECT.getCode());
        if (StringUtils.isNotBlank(entity.getOrgId())){
            example.and().andNotEqualTo("orgId", entity.getOrgId());
        }
        List<Organization> nameAndCodeList = organizationMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(nameAndCodeList)) {
            String message = (nameAndCodeList.get(0).getOrgCode().equals(entity.getOrgCode()) ? "单位编号[" + entity.getOrgCode() : "单位名称[" + entity.getOrgCname()) + "]已存在";
            throw new ServiceException(message);
        }
    }

    /**
     * 新增单位信息
     *
     * @param entity
     * @return
     * @throws ServiceException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public String save(final Organization entity) throws ServiceException {
        duplicateCheck(entity);
        entity.setOrgId(getNewOrgId());
        this.organizationMapper.insertSelective(entity);
        return entity.getOrgId();
    }

    /**
     * 修改单位信息
     *
     * @param entity
     * @return
     * @throws ServiceException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int update(final Organization entity) throws ServiceException {
        duplicateCheck(entity);
        return this.organizationMapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 批量逻辑删除
     *
     * @param orgIds
     * @param userId
     * @return
     * @throws ServiceException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int discard(final List<String> orgIds, final String userId) throws ServiceException {
        // 更新字段
        Organization param = new Organization();
        param.setDeleteTime(System.currentTimeMillis());
        param.setDeleteUserId(userId);
        param.setStatus(StatusEnum.EXPIRE.getCode());
        // 更新条件
        Example example = new Example(Organization.class);
        example.createCriteria().andIn("orgId", orgIds);
        return organizationMapper.updateByExampleSelective(param, example);
    }

    /**
     * 批量物理删除
     *
     * @param orgIds
     * @return
     * @throws ServiceException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int delete(final List<String> orgIds) throws ServiceException {
        Example example = new Example(Organization.class);
        example.createCriteria().andIn("orgId", orgIds);
        return organizationMapper.deleteByExample(example);
    }


    private Organization get(Map<String, Object> params) {
        List<Organization> list = gets(params);
        return CollectionUtils.isEmpty(list) ? null : (Organization) list.get(0);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Organization> gets(Map<String, Object> args) {
        return this.organizationMapper.gets(args);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int delete(final Organization org) throws ServiceException {
        try {
            this.departmentMapper.delete(new Department() {

            });
            return this.organizationMapper.delete(org);
        } catch (Exception e) {
            this.logger.error("物理删除组织单位失败，原因：", e);
            throw new ServiceException("物理删除组织单位失败", e);
        }
    }

    @Override
    public List<Organization> gets(final List<String> orgIds) {
        return gets(new HashMap<String, Object>(1) {

        });
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int discard(final String orgId, final String userId) throws ServiceException {
        return discard(new HashMap<String, Object>(3) {

        });
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int delete(final String orgId) throws ServiceException {
        return delete(new HashMap<String, Object>(1) {

        });
    }

    @Override
    public List<String> getLowerIds(String orgId) {
        Organization org = new Organization();
        org.setDeparth(orgId);
        org.setStatus(Integer.valueOf(StatusEnum.EFFECT.getCode()));
        List<Organization> list = list(org);
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList();
        }
        return (List) list.stream().map(Organization::getOrgId).collect(Collectors.toList());
    }

    @Override
    public List<String> getNotExistIds(final List<String> orgIds) {
        if (CollectionUtils.isEmpty(orgIds)) {
            return null;
        }
        List<Organization> list = gets(new HashMap<String, Object>(2) {

        });
        if (CollectionUtils.isEmpty(list)) {
            return orgIds;
        }

        Set<String> existIds = (Set) list.stream().map(Organization::getOrgId)
                .collect(Collectors.toSet());

        return (List) orgIds.stream().filter(s -> !existIds.contains(s)).collect(Collectors.toList());
    }


    private int delete(Map<String, Object> args) throws ServiceException {
        try {
            this.departmentMapper.remove(args);
            this.organizationMapper.remove(args);
        } catch (Exception e) {
            this.logger.error("物理删除组织单位失败，原因：", e);
            throw new ServiceException("物理删除组织单位失败", e);
        }

        return 1;
    }


    private int discard(Map<String, Object> args) throws ServiceException {
        try {
            this.departmentMapper.discard(args);
            this.organizationMapper.discard(args);
        } catch (Exception e) {
            this.logger.error("逻辑删除组织单位失败，原因：", e);
            throw new ServiceException("逻辑删除组织单位失败", e);
        }

        return 1;
    }


    @Override
    public Organization getTopOrganization() {
        List<Organization> organizations = this.organizationMapper.getTopOrg();
        return CollectionUtils.isNotEmpty(organizations) ? (Organization) organizations.get(0) : null;
    }

    @Override
    public Organization getTopOrgIgnoreIsLinkage() {
        List<Organization> organizations = this.organizationMapper.getTopOrg();
        if (CollectionUtils.isEmpty(organizations)) {
            return null;
        }
        Organization top = (Organization) organizations.get(0);

        if (top.getIsLinkage().shortValue() == 1) {
            Organization query = new Organization();
            query.setParentId(top.getOrgId());
            query.setIsLinkage(Short.valueOf((short) 0));
            List<Organization> res = this.organizationMapper.list(query);
            if (CollectionUtils.isNotEmpty(res)) {
                top = (Organization) res.get(0);
            }
        }
        return top;
    }

    @Override
    public Organization getIgnoreStatus(String orgId) {
        return (Organization) this.organizationMapper.selectByPrimaryKey(orgId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int insertOrg(MultipartFile file, String userId) {
        try {
            this.organizationMapper.deleteTreeData();
            POIFSFileSystem pois = new POIFSFileSystem(file.getInputStream());
            //新建WorkBook
            HSSFWorkbook wb = new HSSFWorkbook(pois);
            List<OrganizationExp> parentList = new ArrayList<>();
            List<OrganizationExp> dataList = new ArrayList<>();
            //获取Sheet（工作薄）
            HSSFSheet sheet = wb.getSheetAt(0);
            //开始行数
            int firstRow = sheet.getFirstRowNum();
            //结束行数
            int lastRow = sheet.getLastRowNum();
            //获取第一行
            HSSFRow startrow = sheet.getRow(firstRow);
            //开始列数
            int firstCell = startrow.getFirstCellNum();
            //结束列数
            int lastCell = startrow.getLastCellNum();

            for (int j = firstRow; j < lastRow; j++) {
                //获取数据行
                HSSFRow datarow = sheet.getRow(j + 1);
                List list = new ArrayList();
                for (int k = firstCell; k < lastCell; k++) {
                    //获取一个单元格
                    HSSFCell cell = datarow.getCell(k);
                    Object str = "";
                    if (cell != null && !cell.equals("")) {
                        //获取单元格，值的类型
                        str = getCellValue(cell);
                    }
                    list.add(str);
                }
                OrganizationExp organizationExp = new OrganizationExp();
                organizationExp.setOrgId(UUIDUtils.getUUID());
                organizationExp.setOrgCode(String.valueOf(list.get(0)));
                organizationExp.setOrgCname(String.valueOf(list.get(1)));
                organizationExp.setOrgEname(String.valueOf(list.get(2)));
                organizationExp.setAddress(String.valueOf(list.get(3)));
                organizationExp.setZipCode(String.valueOf(list.get(4)));
                organizationExp.setTelephone(String.valueOf(list.get(5)));
                organizationExp.setFax(String.valueOf(list.get(6)));
                organizationExp.setParentName(String.valueOf(list.get(7)));
                organizationExp.setParentId("");
                organizationExp.setLinkManName(String.valueOf(list.get(8)));
                organizationExp.setLinkManDept(String.valueOf(list.get(9)));
                organizationExp.setLinkManPos(String.valueOf(list.get(10)));
                organizationExp.setLinkManTel(String.valueOf(list.get(11)));
                organizationExp.setLinkManFax(String.valueOf(list.get(12)));
                organizationExp.setLinkManEmail(String.valueOf(list.get(13)));
                organizationExp.setRemark(String.valueOf(list.get(14)));
                organizationExp.setDeparth(organizationExp.getOrgId());
                organizationExp.setFromSystem("excel导入");
                organizationExp.setDeletedTime(Long.valueOf(DateUtils.getCurrentTime()));
                organizationExp.setDeletedUserid(userId);
                organizationExp.setUpdatedUserid(userId);
                organizationExp.setCreatedUserid(userId);
                organizationExp.setStatus((short) StatusEnum.EFFECT.getCode());
                organizationExp.setIsLinkage(Short.valueOf((short) StatusEnum.INVALID.getCode()));
                organizationExp.setCreatedTime(Long.valueOf(DateUtils.getCurrentTime()));
                organizationExp.setUpdatedTime(Long.valueOf(DateUtils.getCurrentTime()));
                dataList.add(organizationExp);
            }
            //取出顶级父节点数据
            for (OrganizationExp org : dataList) {
//                if (!StringUtils.hasText(org.getParentName()) || "null".equals(org.getParentName())) {
//                    org.setOrgId(UUIDUtils.getUUID());
//                    parentList.add(org);
//                }
            }
            ListHandle(parentList, dataList);
            this.organizationMapper.insertOrg(dataList);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("【POI异常上传失败...】");
        }
        return 0;
    }

    public List<OrganizationExp> ListHandle(List<OrganizationExp> mapList, List<OrganizationExp> dataList) {
        //核心代码,递归调用
        List<OrganizationExp> list = new ArrayList<>();
        for (int i = 0; i < mapList.size(); i++) {
            String parentName = mapList.get(i).getOrgCname();
            for (OrganizationExp org : dataList) {
                if (org.getParentName() != null && parentName.equals(org.getParentName())) {
                    org.setParentId(mapList.get(i).getOrgId());
                    org.setDeparth(mapList.get(i).getOrgId() + "|" + org.getOrgId());
                    list.add(org);
                }
            }
            ListHandle(list, dataList);
        }
        return dataList;
    }

    public static OrganizationServiceImpl getInstance() {
        if (poi == null) {
            synchronized (OrganizationServiceImpl.class) {
                if (poi == null) {
                    poi = new OrganizationServiceImpl();
                }
            }
        }
        return poi;
    }

    public String getCellValue(Cell cell) {
        String value = null;
        if (cell != null) {
            //简单的查检列类型
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING://字符串
                    value = cell.getRichStringCellValue().toString();
                    break;
                case Cell.CELL_TYPE_NUMERIC://数字
                    long dd = (long) cell.getNumericCellValue();
                    value = dd + "";
                    break;
                case Cell.CELL_TYPE_BLANK:
                    value = "";
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    value = String.valueOf(cell.getCellFormula());
                    break;
                case Cell.CELL_TYPE_BOOLEAN://boolean型值
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_ERROR:
                    value = String.valueOf(cell.getErrorCellValue());
                    break;
                default:
                    break;
            }
        }
        return value;
    }

}