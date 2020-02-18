package com.ss.facesys.web.manage.system.form;

import com.ss.facesys.data.system.common.model.SysPara;

import java.util.List;
import java.util.StringJoiner;

/**
 * SysParaForm
 *
 * @author FrancisYs
 * @date 2020/2/17
 * @email yaoshuai@ss-cas.com
 */
public class SysParaForm {

    private List<SysPara> sysParaList;

    public List<SysPara> getSysParaList() {
        return sysParaList;
    }

    public void setSysParaList(List<SysPara> sysParaList) {
        this.sysParaList = sysParaList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SysParaForm.class.getSimpleName() + "[", "]")
                .add("sysParaList=" + sysParaList)
                .toString();
    }

}
