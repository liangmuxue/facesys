package com.ss.isc.data.viid.common.from;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import java.util.List;

/**
* 订阅对象集合
* @author chao
* @create 2019/11/1
* @email lishuangchao@ss-cas.com
**/
public class SubscribeListObjectForm {
    @JsonProperty("SubscribeListObject")
    @JSONField(name = "SubscribeListObject")
    @Valid
    private SubscribeObject subscribeListObject;

    public SubscribeObject getSubscribeListObject() {
        return this.subscribeListObject;
    }

    public void setSubscribeListObject(final SubscribeObject subscribeListObject) {
        this.subscribeListObject = subscribeListObject;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SubscribeListObjectForm)) {
            return false;
        }
        final SubscribeListObjectForm other = (SubscribeListObjectForm) o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$subscribeListObject = this.getSubscribeListObject();
        final Object other$subscribeListObject = other.getSubscribeListObject();
        if (this$subscribeListObject == null) {
            if (other$subscribeListObject == null) {
                return true;
            }
        } else if (this$subscribeListObject.equals(other$subscribeListObject)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SubscribeListObjectForm;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $subscribeListObject = this.getSubscribeListObject();
        result = result * 59 + (($subscribeListObject == null) ? 0 : $subscribeListObject.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "SubscribeListObjectForm(subscribeListObject=" + this.getSubscribeListObject() + ")";
    }
    //订阅对象
    public static class SubscribeObject {
        @JsonProperty("SubscribeObject")
        @JSONField(name = "SubscribeObject")
        @Valid
        private List<SubscribeBaseForm> subscribeObject;

        public List<SubscribeBaseForm> getSubscribeObject() {
            return this.subscribeObject;
        }

        public void setSubscribeObject(final List<SubscribeBaseForm> subscribeObject) {
            this.subscribeObject = subscribeObject;
        }

        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof SubscribeObject)) {
                return false;
            }
            final SubscribeObject other = (SubscribeObject) o;
            if (!other.canEqual(this)) {
                return false;
            }
            final Object this$subscribeObject = this.getSubscribeObject();
            final Object other$subscribeObject = other.getSubscribeObject();
            if (this$subscribeObject == null) {
                if (other$subscribeObject == null) {
                    return true;
                }
            } else if (this$subscribeObject.equals(other$subscribeObject)) {
                return true;
            }
            return false;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof SubscribeObject;
        }

        @Override
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $subscribeObject = this.getSubscribeObject();
            result = result * 59 + (($subscribeObject == null) ? 0 : $subscribeObject.hashCode());
            return result;
        }

        @Override
        public String toString() {
            return "SubscribeListObjectForm.SubscribeObject(subscribeObject=" + this.getSubscribeObject() + ")";
        }
    }
}
