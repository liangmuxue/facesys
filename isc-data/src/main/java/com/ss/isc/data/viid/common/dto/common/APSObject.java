package com.ss.isc.data.viid.common.dto.common;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;


public class APSObject {
    @JSONField(name = "APSObject")
    private List<APE> apsObject;

    public String toString() {
        return "APSObject(apsObject=" + getApsObject() + ")";
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $apsObject = getApsObject();
        return result * 59 + (($apsObject == null) ? 0 : $apsObject.hashCode());
    }

    protected boolean canEqual(Object other) {
        return other instanceof APSObject;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof APSObject)) return false;
        APSObject other = (APSObject) o;
        if (!other.canEqual(this)) return false;
        Object this$apsObject = getApsObject(), other$apsObject = other.getApsObject();
        return !((this$apsObject == null) ? (other$apsObject != null) : !this$apsObject.equals(other$apsObject));
    }

    public List<APE> getApsObject() {
        return this.apsObject;
    }

    public void setApsObject(List<APE> apsObject) {
        this.apsObject = apsObject;
    }

    public static class APE {
        @JSONField(name = "ApeID")
        private String apeId;
        @JSONField(name = "Name")
        private String name;
        @JSONField(name = "Model")
        private String model;
        @JSONField(name = "IPAddr")
        private String ipAddr;
        @JSONField(name = "IPV6Addr")
        private String ipV6Addr;
        @JSONField(name = "Port")
        private Integer port;
        @JSONField(name = "Longitude")
        private Double longitude;
        @JSONField(name = "Latitude")
        private Double latitude;
        @JSONField(name = "PlaceCode")
        private String placeCode;
        @JSONField(name = "Place")
        private String place;
        @JSONField(name = "OrgCode")
        private String orgCode;
        @JSONField(name = "CapDirection")
        private Integer capDirection;
        @JSONField(name = "MonitorDirection")
        private String monitorDirection;
        @JSONField(name = "MonitorAreaDesc")
        private String monitorAreaDesc;
        @JSONField(name = "IsOnline")
        private String isOnline;
        @JSONField(name = "OwnerApsID")
        private String ownerApsId;
        @JSONField(name = "UserId")
        private String userId;
        @JSONField(name = "Password")
        private String password;

        public boolean equals(Object o) {
            if (o == this) return true;
            if (!(o instanceof APE)) return false;
            APE other = (APE) o;
            if (!other.canEqual(this)) return false;
            Object this$apeId = getApeId(), other$apeId = other.getApeId();
            if ((this$apeId == null) ? (other$apeId != null) : !this$apeId.equals(other$apeId)) return false;
            Object this$name = getName(), other$name = other.getName();
            if ((this$name == null) ? (other$name != null) : !this$name.equals(other$name)) return false;
            Object this$model = getModel(), other$model = other.getModel();
            if ((this$model == null) ? (other$model != null) : !this$model.equals(other$model)) return false;
            Object this$ipAddr = getIpAddr(), other$ipAddr = other.getIpAddr();
            if ((this$ipAddr == null) ? (other$ipAddr != null) : !this$ipAddr.equals(other$ipAddr)) return false;
            Object this$ipV6Addr = getIpV6Addr(), other$ipV6Addr = other.getIpV6Addr();
            if ((this$ipV6Addr == null) ? (other$ipV6Addr != null) : !this$ipV6Addr.equals(other$ipV6Addr))
                return false;
            Object this$port = getPort(), other$port = other.getPort();
            if ((this$port == null) ? (other$port != null) : !this$port.equals(other$port)) return false;
            Object this$longitude = getLongitude(), other$longitude = other.getLongitude();
            if ((this$longitude == null) ? (other$longitude != null) : !this$longitude.equals(other$longitude))
                return false;
            Object this$latitude = getLatitude(), other$latitude = other.getLatitude();
            if ((this$latitude == null) ? (other$latitude != null) : !this$latitude.equals(other$latitude))
                return false;
            Object this$placeCode = getPlaceCode(), other$placeCode = other.getPlaceCode();
            if ((this$placeCode == null) ? (other$placeCode != null) : !this$placeCode.equals(other$placeCode))
                return false;
            Object this$place = getPlace(), other$place = other.getPlace();
            if ((this$place == null) ? (other$place != null) : !this$place.equals(other$place)) return false;
            Object this$orgCode = getOrgCode(), other$orgCode = other.getOrgCode();
            if ((this$orgCode == null) ? (other$orgCode != null) : !this$orgCode.equals(other$orgCode)) return false;
            Object this$capDirection = getCapDirection(), other$capDirection = other.getCapDirection();
            if ((this$capDirection == null) ? (other$capDirection != null) : !this$capDirection.equals(other$capDirection))
                return false;
            Object this$monitorDirection = getMonitorDirection(), other$monitorDirection = other.getMonitorDirection();
            if ((this$monitorDirection == null) ? (other$monitorDirection != null) : !this$monitorDirection.equals(other$monitorDirection))
                return false;
            Object this$monitorAreaDesc = getMonitorAreaDesc(), other$monitorAreaDesc = other.getMonitorAreaDesc();
            if ((this$monitorAreaDesc == null) ? (other$monitorAreaDesc != null) : !this$monitorAreaDesc.equals(other$monitorAreaDesc))
                return false;
            Object this$isOnline = getIsOnline(), other$isOnline = other.getIsOnline();
            if ((this$isOnline == null) ? (other$isOnline != null) : !this$isOnline.equals(other$isOnline))
                return false;
            Object this$ownerApsId = getOwnerApsId(), other$ownerApsId = other.getOwnerApsId();
            if ((this$ownerApsId == null) ? (other$ownerApsId != null) : !this$ownerApsId.equals(other$ownerApsId))
                return false;
            Object this$userId = getUserId(), other$userId = other.getUserId();
            if ((this$userId == null) ? (other$userId != null) : !this$userId.equals(other$userId)) return false;
            Object this$password = getPassword(), other$password = other.getPassword();
            return !((this$password == null) ? (other$password != null) : !this$password.equals(other$password));
        }

        protected boolean canEqual(Object other) {
            return other instanceof APE;
        }

        public int hashCode() {
            int PRIME = 59;
            int result = 1;
            Object $apeId = getApeId();
            result = result * 59 + (($apeId == null) ? 0 : $apeId.hashCode());
            Object $name = getName();
            result = result * 59 + (($name == null) ? 0 : $name.hashCode());
            Object $model = getModel();
            result = result * 59 + (($model == null) ? 0 : $model.hashCode());
            Object $ipAddr = getIpAddr();
            result = result * 59 + (($ipAddr == null) ? 0 : $ipAddr.hashCode());
            Object $ipV6Addr = getIpV6Addr();
            result = result * 59 + (($ipV6Addr == null) ? 0 : $ipV6Addr.hashCode());
            Object $port = getPort();
            result = result * 59 + (($port == null) ? 0 : $port.hashCode());
            Object $longitude = getLongitude();
            result = result * 59 + (($longitude == null) ? 0 : $longitude.hashCode());
            Object $latitude = getLatitude();
            result = result * 59 + (($latitude == null) ? 0 : $latitude.hashCode());
            Object $placeCode = getPlaceCode();
            result = result * 59 + (($placeCode == null) ? 0 : $placeCode.hashCode());
            Object $place = getPlace();
            result = result * 59 + (($place == null) ? 0 : $place.hashCode());
            Object $orgCode = getOrgCode();
            result = result * 59 + (($orgCode == null) ? 0 : $orgCode.hashCode());
            Object $capDirection = getCapDirection();
            result = result * 59 + (($capDirection == null) ? 0 : $capDirection.hashCode());
            Object $monitorDirection = getMonitorDirection();
            result = result * 59 + (($monitorDirection == null) ? 0 : $monitorDirection.hashCode());
            Object $monitorAreaDesc = getMonitorAreaDesc();
            result = result * 59 + (($monitorAreaDesc == null) ? 0 : $monitorAreaDesc.hashCode());
            Object $isOnline = getIsOnline();
            result = result * 59 + (($isOnline == null) ? 0 : $isOnline.hashCode());
            Object $ownerApsId = getOwnerApsId();
            result = result * 59 + (($ownerApsId == null) ? 0 : $ownerApsId.hashCode());
            Object $userId = getUserId();
            result = result * 59 + (($userId == null) ? 0 : $userId.hashCode());
            Object $password = getPassword();
            return result * 59 + (($password == null) ? 0 : $password.hashCode());
        }

        public String toString() {
            return "APSObject.APE(apeId=" + getApeId() + ", name=" + getName() + ", model=" + getModel() + ", ipAddr=" + getIpAddr() + ", ipV6Addr=" + getIpV6Addr() + ", port=" + getPort() + ", longitude=" + getLongitude() + ", latitude=" + getLatitude() + ", placeCode=" + getPlaceCode() + ", place=" + getPlace() + ", orgCode=" + getOrgCode() + ", capDirection=" + getCapDirection() + ", monitorDirection=" + getMonitorDirection() + ", monitorAreaDesc=" + getMonitorAreaDesc() + ", isOnline=" + getIsOnline() + ", ownerApsId=" + getOwnerApsId() + ", userId=" + getUserId() + ", password=" + getPassword() + ")";
        }

        public String getApeId() {
            return this.apeId;
        }

        public void setApeId(String apeId) {
            this.apeId = apeId;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getModel() {
            return this.model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getIpAddr() {
            return this.ipAddr;
        }

        public void setIpAddr(String ipAddr) {
            this.ipAddr = ipAddr;
        }

        public String getIpV6Addr() {
            return this.ipV6Addr;
        }

        public void setIpV6Addr(String ipV6Addr) {
            this.ipV6Addr = ipV6Addr;
        }

        public Integer getPort() {
            return this.port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public Double getLongitude() {
            return this.longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public Double getLatitude() {
            return this.latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public String getPlaceCode() {
            return this.placeCode;
        }

        public void setPlaceCode(String placeCode) {
            this.placeCode = placeCode;
        }

        public String getPlace() {
            return this.place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getOrgCode() {
            return this.orgCode;
        }

        public void setOrgCode(String orgCode) {
            this.orgCode = orgCode;
        }

        public Integer getCapDirection() {
            return this.capDirection;
        }

        public void setCapDirection(Integer capDirection) {
            this.capDirection = capDirection;
        }

        public String getMonitorDirection() {
            return this.monitorDirection;
        }

        public void setMonitorDirection(String monitorDirection) {
            this.monitorDirection = monitorDirection;
        }

        public String getMonitorAreaDesc() {
            return this.monitorAreaDesc;
        }

        public void setMonitorAreaDesc(String monitorAreaDesc) {
            this.monitorAreaDesc = monitorAreaDesc;
        }

        public String getIsOnline() {
            return this.isOnline;
        }

        public void setIsOnline(String isOnline) {
            this.isOnline = isOnline;
        }

        public String getOwnerApsId() {
            return this.ownerApsId;
        }

        public void setOwnerApsId(String ownerApsId) {
            this.ownerApsId = ownerApsId;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

}
