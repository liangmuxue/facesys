package com.ss.isc.data.archives.client;

import com.ss.isc.data.collect.common.web.DispositionVO;

/**
* VIID 布控
* @author chao
* @create 2019/10/31
* @email lishuangchao@ss-cas.com
**/
public interface IDispositionService {
    /**
     * VIID 新增布控
     * @param dispositionVO
     * @return
     */
    String add(DispositionVO dispositionVO);

    /**
     * VIID 撤控
     * @param dispositionVO
     * @return
     */
    String delete(DispositionVO dispositionVO);

    /**
     * VIID 修改布控
     * @param dispositionVO
     * @return
     */
    String edit(DispositionVO dispositionVO);

}
