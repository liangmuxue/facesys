package com.ss.spider.system.organization.service.impl;

import com.google.common.collect.Lists;

import com.ss.enums.StatusEnum;
import com.ss.exception.ServiceException;
import com.ss.service.AbstractCWServiceImpl;
import com.ss.spider.system.department.mapper.DepartmentMapper;
import com.ss.spider.system.department.model.Department;
import com.ss.spider.system.organization.mapper.OrganizationMapper;
import com.ss.spider.system.organization.model.Organization;
import com.ss.spider.system.organization.model.OrganizationExp;
import com.ss.spider.system.organization.service.OrganizationService;
import com.ss.spider.system.sequence.model.AppSequence;
import com.ss.spider.system.sequence.model.SysSeqEnum;
import com.ss.spider.system.sequence.service.AppSequenceService;
import com.ss.tools.DateUtils;
import com.ss.tools.UUIDUtils;
import com.github.pagehelper.PageHelper;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service("organizationService")
public class OrganizationServiceImpl extends AbstractCWServiceImpl<Organization> implements OrganizationService<Organization> {

    private static final String ORG_SEQ_CODE = SysSeqEnum.ORG_ID_SEQ.getCode();
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

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<Organization> pages(Organization org, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        return this.organizationMapper.pages(org);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Organization> list(Organization org) {
        //首先拿到全部数据
        List<Organization> deptList = organizationMapper.list(org);

        //该集合用于接受第一个节点的数据
        List<Organization> mapList = new ArrayList<>();

        //得到第一个接点的数据
        for (int i = 0; i < deptList.size(); i++) {
            if (deptList.get(i).getParentId() == null || "".equals(deptList.get(i).getParentId())) {
                mapList.add(deptList.get(i));
            }
        }
        //调用getDe方法
        List<Organization> result = getDe(mapList);
        return result;
    }

    public List<Organization> getDe(List<Organization> mapList) {
        //核心代码,递归调用
        for (int i = 0; i < mapList.size(); i++) {
            //如果该数据有子节点,则执行
            if (organizationMapper.getDept(mapList.get(i).getOrgId(), mapList.get(i).getStatus()) != null) {
                List<Organization> map = organizationMapper.getDept(mapList.get(i).getOrgId(), mapList.get(i).getStatus());
                for(int j = 0; j < map.size(); j++){
                    map.get(j).setParentName(mapList.get(i).getOrgCname());
                }
                mapList.get(i).setChildren(map);
                getDe(map);
            }
        }
        return mapList;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Organization> gets(Map<String, Object> args) {
        return this.organizationMapper.gets(args);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Organization get(final String orgId) {
        return get(new HashMap<String, Object>() {

        });
    }


    private Organization get(Map<String, Object> params) {
        List<Organization> list = gets(params);
        return CollectionUtils.isEmpty(list) ? null : (Organization) list.get(0);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public String save(final Organization entity) throws ServiceException {
        List<Organization> nameAndCodeList = this.organizationMapper.selectNameAndCode(entity);

        for (Organization organization : nameAndCodeList) {
            if (organization.getOrgCname() != null) {
                throw new ServiceException("单位编号[" + entity.getOrgCode() + "]已存在");
            }

            if (organization.getOrgCode() != null) {
                throw new ServiceException("单位名称[" + entity.getOrgCname() + "]已存在");
            }
        }
        entity.setOrgId(getNewOrgId());
        try {
            if (StringUtils.hasText(entity.getParentId())) {
                Organization parent = this.organizationMapper.selectDeparth(entity.getParentId(), entity.getStatus());
                entity.setDeparth(parent.getDeparth() + "|" + entity.getOrgId());
            } else {
                Organization org = new Organization();
                org.setParentId("-1");
                List<Organization> roots = list(org);
                if (CollectionUtils.isNotEmpty(roots)) {
                    throw new ServiceException("系统只能存在一个顶级单位");
                }
                entity.setDeparth(entity.getOrgId());
            }

            this.organizationMapper.save(entity);
        } catch (Exception e) {
            this.logger.error("新增组织结构失败，原因：", e);
            if (e instanceof ServiceException) {
                throw e;
            }
            throw new ServiceException("新增组织结构失败", e);
        }

        return entity.getOrgId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int update(final Organization entity) throws ServiceException {
        List<Organization> nameAndCodeList = this.organizationMapper.selectNameAndCode(entity);

        for (Organization organization : nameAndCodeList) {
            if (organization.getOrgCname() != null) {
                throw new ServiceException("单位编号[" + entity.getOrgCode() + "]已存在");
            }

            if (organization.getOrgCode() != null) {
                throw new ServiceException("单位名称[" + entity.getOrgCname() + "]已存在");
            }
        }

        if (StringUtils.hasText(entity.getParentId())) {
            Organization parent = this.organizationMapper.selectDeparth(entity.getParentId(), entity.getStatus());
            entity.setDeparth(parent.getDeparth() + "|" + entity.getOrgId());
        } else {
            entity.setDeparth(entity.getOrgId());
        }

        try {
            return this.organizationMapper.update(entity);
        } catch (Exception e) {
            this.logger.error("变更组织单位信息失败，原因：", e);
            throw new ServiceException("变更组织单位信息失败", e);
        }
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
    public int discard(final List<String> orgIds, final String userId) throws ServiceException {
        long timestamp = System.currentTimeMillis();
        Map map = new HashMap();
        map.put("deletedTime", timestamp);
        map.put("deletedUserid", userId);
        map.put("orgIds", orgIds);
        return this.organizationMapper.discard(map);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int delete(final List<String> orgIds) throws ServiceException {
        return delete(new HashMap<String, Object>(1) {

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
    public int insertOrg(MultipartFile file,String userId) {
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
                if (!StringUtils.hasText(org.getParentName()) || "null".equals(org.getParentName())) {
                    org.setOrgId(UUIDUtils.getUUID());
                    parentList.add(org);
                }
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
