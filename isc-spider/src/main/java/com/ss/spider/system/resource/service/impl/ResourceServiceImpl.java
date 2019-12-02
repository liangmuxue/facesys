package com.ss.spider.system.resource.service.impl;

import com.ss.exception.ServiceException;
import com.ss.service.AbstractCWServiceImpl;
import com.ss.spider.system.resource.mapper.ResourceMapper;
import com.ss.spider.system.resource.model.Resource;
import com.ss.spider.system.resource.service.ResourceService;
import com.ss.spider.system.role.mapper.RoleResourceMapper;
import com.ss.spider.system.role.model.RoleResource;
import com.ss.tools.DateUtils;
import com.ss.tools.UUIDUtils;
import com.github.pagehelper.PageHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("resourceService")
public class ResourceServiceImpl
        extends AbstractCWServiceImpl<Resource>
        implements ResourceService<Resource> {

    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Resource> gets(Map<String, Object> args) {
        return this.resourceMapper.gets(args);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Resource> list(Resource entity) {
        return this.resourceMapper.list(entity);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<Resource> pages(Resource entity, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        return this.resourceMapper.pages(entity);
    }

    /**
     * 查询用户资源列表
     * @param args
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Resource> query(Map<String, Object> args) {
        return this.resourceMapper.query(args);
    }

    private Resource get(Map<String, Object> params) {
        List<Resource> list = gets(params);
        return CollectionUtils.isEmpty(list) ? null : (Resource) list.get(0);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Resource get(final String resId) {
        return get(new HashMap<String, Object>(1) {

        });
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public String save(final Resource entity) throws ServiceException {
        entity.setResourceId(UUIDUtils.getUUID());

        if (get(new HashMap<String, Object>(1) {
        }) != null) {

            throw new ServiceException("资源编号[" + entity.getResCode() + "]已存在");
        }

        try {
            this.resourceMapper.save(entity);
        } catch (Exception e) {
            this.logger.error("新增资源信息失败，原因：", e);
            throw new ServiceException("新增资源信息失败", e);
        }

        return entity.getResourceId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int update(final Resource entity) throws ServiceException {
        Resource preRes = get(new HashMap<String, Object>(1) {

        });
        if (preRes != null && !preRes.getResourceId().equals(entity.getResourceId())) {
            throw new ServiceException("资源编号[" + entity.getResCode() + "]已存在");
        }

        try {
            return this.resourceMapper.update(entity);
        } catch (Exception e) {
            this.logger.error("变更资源信息失败，原因：", e);
            throw new ServiceException("变更资源信息失败", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int delete(Resource args) throws ServiceException {
        try {
            if (StringUtils.hasText(args.getResourceId())) {
                RoleResource roleResource = new RoleResource();
                roleResource.setResourceId(args.getResourceId());
                this.roleResourceMapper.delete(roleResource);
            }

            return this.resourceMapper.delete(args);
        } catch (Exception e) {
            this.logger.error("物理删除资源信息失败，原因：", e);
            throw new ServiceException("物理删除资源信息失败", e);
        }
    }

    @Override
    public List<Resource> gets(final List<String> resIds) {
        return gets(new HashMap<String, Object>(1) {
            {
                put("resourceIds", resIds);
            }
        });
    }

    @Override
    public int discard(final List<String> resIds, final String userId) throws ServiceException {
        return discard(new HashMap<String, Object>(3) {

        });
    }

    @Override
    public int delete(final List<String> resIds) throws ServiceException {
        return discard(new HashMap<String, Object>(1) {

        });
    }

    @Override
    public int discard(final String resId, final String userId) throws ServiceException {
        return discard(new HashMap<String, Object>(3) {

        });
    }


    @Override
    public int delete(final String resId) throws ServiceException {
        return delete(new HashMap<String, Object>(1) {

        });
    }


    private int discard(Map<String, Object> args) throws ServiceException {
        try {
            this.roleResourceMapper.remove(args);
            return this.resourceMapper.discard(args);
        } catch (Exception e) {
            this.logger.error("逻辑删除资源信息失败，原因：", e);
            throw new ServiceException("逻辑删除资源信息失败", e);
        }
    }


    public int delete(Map<String, Object> args) throws ServiceException {
        try {
            this.roleResourceMapper.remove(args);
            return this.resourceMapper.remove(args);
        } catch (Exception e) {
            this.logger.error("物理删除资源信息失败，原因：", e);
            throw new ServiceException("物理删除资源信息失败", e);
        }
    }

}
