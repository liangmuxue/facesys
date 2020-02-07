package com.ss.facesys.data.collect.service;

import com.github.pagehelper.PageHelper;
import com.ss.facesys.data.baseinfo.service.BaseServiceImpl;
import com.ss.facesys.data.collect.client.IInternetBarService;
import com.ss.facesys.data.collect.common.model.InternetBar;
import com.ss.facesys.data.collect.mapper.InternetBarMapper;
import com.ss.facesys.data.resource.common.web.InternetBarVO;
import com.ss.facesys.util.constant.NumberConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 网吧
* @author chao
* @create 2020/2/6
* @email lishuangchao@ss-cas.com
**/
@Service
@Transactional(rollbackFor = {Exception.class})
public class InternetBarServiceImpl extends BaseServiceImpl implements IInternetBarService {
  public static final Log logger = LogFactory.getLog(InternetBarServiceImpl.class);

  @Autowired
  private InternetBarMapper internetBarMapper;

  /**
   * 网吧分页查询
   * @param internetBarVO
   * @return
   */
  @Override
  public List<InternetBar> internetBarPage(InternetBarVO internetBarVO) {
    PageHelper.startPage(internetBarVO.getCurrentPage(), internetBarVO.getPageSize());
    List<InternetBar> internetBars = this.internetBarMapper.internetBarPage(internetBarVO);
    return internetBars;
  }

  /**
   * 添加网吧
   * @param internetBarVO
   * @return
   */
  @Override
  public int insertInternetBar(InternetBarVO internetBarVO) {
    internetBarVO.setCreateTime(String.valueOf(System.currentTimeMillis()));
    internetBarVO.setUpdateTime(String.valueOf(System.currentTimeMillis()));
    internetBarVO.setStatus(NumberConstant.ONE);
    int result = this.internetBarMapper.insertInternetBar(internetBarVO);
    return result;
  }

  /**
   * 修改网吧信息
   * @param internetBarVO
   * @return
   */
  @Override
  public int updateInternetBar(InternetBarVO internetBarVO) {
    internetBarVO.setUpdateTime(String.valueOf(System.currentTimeMillis()));
    int result = this.internetBarMapper.updateInternetBar(internetBarVO);
    return result;
  }

  /**
   * 删除网吧
   * @param internetBarVO
   * @return
   */
  @Override
  public int deleteInternetBar(InternetBarVO internetBarVO) {
    int result = this.internetBarMapper.deleteInternetBar(internetBarVO);
    return result;
  }

  /**
   * 查询网吧详情
   * @param internetBarVO
   * @return
   */
  @Override
  public InternetBar detail(InternetBarVO internetBarVO) {
    InternetBar result = this.internetBarMapper.detail(internetBarVO);
    return result;
  }
}
