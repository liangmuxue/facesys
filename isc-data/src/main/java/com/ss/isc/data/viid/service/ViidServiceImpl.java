package com.ss.isc.data.viid.service;

import com.ss.isc.data.viid.client.IViidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 视图库接口实现
 *
 * @author FrancisYs
 * @date 2019/8/21
 * @email yaoshuai@ss-cas.com
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class ViidServiceImpl implements IViidService {

    private static final Logger logger = LoggerFactory.getLogger(ViidServiceImpl.class);

}
