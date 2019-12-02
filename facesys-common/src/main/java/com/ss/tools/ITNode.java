package com.ss.tools;

import java.io.Serializable;

public interface ITNode extends Serializable {

    String getId();

    String getTitle();

    String getCode();

    String getParentId();

}
