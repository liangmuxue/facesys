package com.ss.tools;

/**
 * IResultCode
 *
 * @author FrancisYs
 * @date 2019/12/4
 * @email yaoshuai@ss-cas.com
 */
public interface IResultCode {

    /**
     * 信息编码
     *
     * @return resultCode
     */
    String getCode();

    /**
     * 信息描述
     *
     * @return resultDesc
     */
    String getDesc();

}
