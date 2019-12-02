package com.ss.isc.data.viid.common.dto.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Face {
    @JSONField(name = "FaceID")
    @JsonProperty("FaceID")
    private String faceId;
    @JSONField(name = "InfoKind")
    @JsonProperty("InfoKind")
    private Integer infoKind;
    @JSONField(name = "SourceID")
    @JsonProperty("SourceID")
    private String sourceId;
    @JSONField(name = "DeviceID")
    @JsonProperty("DeviceID")
    private String deviceId;
    @JSONField(name = "LeftTopX")
    @JsonProperty("LeftTopX")
    private Integer leftTopX;
    @JSONField(name = "LeftTopY")
    @JsonProperty("LeftTopY")
    private Integer leftTopY;
    @JSONField(name = "RightBtmX")
    @JsonProperty("RightBtmX")
    private Integer rightBtmX;
    @JSONField(name = "RightBtmY")
    @JsonProperty("RightBtmY")
    private Integer rightBtmY;
    @JSONField(name = "LocationMarkTime")
    @JsonProperty("LocationMarkTime")
    private String locationMarkTime;
    @JSONField(name = "FaceAppearTime")
    @JsonProperty("FaceAppearTime")
    private String faceAppearTime;
    @JSONField(name = "FaceDisAppearTime")
    @JsonProperty("FaceDisAppearTime")
    private String faceDisAppearTime;
    @JSONField(name = "IDType")
    @JsonProperty("IDType")
    private String idType;
    @JSONField(name = "IDNumber")
    @JsonProperty("IDNumber")
    private String idNumber;
    @JSONField(name = "Name")
    @JsonProperty("Name")
    private String name;
    @JSONField(name = "UsedName")
    @JsonProperty("UsedName")
    private String usedName;
    @JSONField(name = "Alias")
    @JsonProperty("Alias")
    private String alias;
    @JSONField(name = "GenderCode")
    @JsonProperty("GenderCode")
    private String genderCode;
    @JSONField(name = "AgeUpLimit")
    @JsonProperty("AgeUpLimit")
    private Integer ageUpLimit;
    @JSONField(name = "AgeLowerLimit")
    @JsonProperty("AgeLowerLimit")
    private Integer ageLowerLimit;
    @JSONField(name = "EthicCode")
    @JsonProperty("EthicCode")
    private String ethicCode;
    @JSONField(name = "NationalityCode")
    @JsonProperty("NationalityCode")
    private String nationalityCode;
    @JSONField(name = "NativeCityCode")
    @JsonProperty("NativeCityCode")
    private String nativeCityCode;
    @JSONField(name = "ResidenceAdminDivision")
    @JsonProperty("ResidenceAdminDivision")
    private String residenceAdminDivision;
    @JSONField(name = "ChineseAccentCod")
    @JsonProperty("ChineseAccentCod")
    private String chineseAccentCod;
    @JSONField(name = "JobCategory")
    @JsonProperty("JobCategory")
    private String jobCategory;
    @JSONField(name = "AccompanyNumber")
    @JsonProperty("AccompanyNumber")
    private Integer accompanyNumber;
    @JSONField(name = "SkinColor")
    @JsonProperty("SkinColor")
    private String skinColor;
    @JSONField(name = "HairStyle")
    @JsonProperty("HairStyle")
    private String hairStyle;
    @JSONField(name = "HairColor")
    @JsonProperty("HairColor")
    private String hairColor;
    @JSONField(name = "FaceStyle")
    @JsonProperty("FaceStyle")
    private String faceStyle;
    @JSONField(name = "FacialFeature")
    @JsonProperty("FacialFeature")
    private String facialFeature;
    @JSONField(name = "PhysicalFeature")
    @JsonProperty("PhysicalFeature")
    private String physicalFeature;
    @JSONField(name = "RespiratorColor")
    @JsonProperty("RespiratorColor")
    private String respiratorColor;
    @JSONField(name = "CapStyle")
    @JsonProperty("CapStyle")
    private String capStyle;
    @JSONField(name = "CapColor")
    @JsonProperty("CapColor")
    private String capColor;
    @JSONField(name = "GlassStyle")
    @JsonProperty("GlassStyle")
    private String glassStyle;
    @JSONField(name = "GlassColor")
    @JsonProperty("GlassColor")
    private String glassColor;
    @JSONField(name = "IsDriver")
    @JsonProperty("IsDriver")
    private Integer isDriver;
    @JSONField(name = "IsForeigner")
    @JsonProperty("IsForeigner")
    private Integer isForeigner;
    @JSONField(name = "PassportType")
    @JsonProperty("PassportType")
    private String passportType;
    @JSONField(name = "ImmigrantTypeCode")
    @JsonProperty("ImmigrantTypeCode")
    private String immigrantTypeCode;
    @JSONField(name = "IsSuspectedTerrorist")
    @JsonProperty("IsSuspectedTerrorist")
    private Integer isSuspectedTerrorist;
    @JSONField(name = "SuspectedTerroristNumber")
    @JsonProperty("SuspectedTerroristNumber")
    private String suspectedTerroristNumber;
    @JSONField(name = "IsCriminalInvolved")
    @JsonProperty("IsCriminalInvolved")
    private Integer isCriminalInvolved;
    @JSONField(name = "CriminalInvolvedSpecilisationCode")
    @JsonProperty("CriminalInvolvedSpecilisationCode")
    private String criminalInvolvedSpecilisationCode;
    @JSONField(name = "BodySpeciallMark")
    @JsonProperty("BodySpeciallMark")
    private String bodySpeciallMark;
    @JSONField(name = "CrimeMethod")
    @JsonProperty("CrimeMethod")
    private String crimeMethod;
    @JSONField(name = "CrimeCharacterCode")
    @JsonProperty("CrimeCharacterCode")
    private String crimeCharacterCode;
    @JSONField(name = "EscapedCriminalNumber")
    @JsonProperty("EscapedCriminalNumber")
    private String escapedCriminalNumber;
    @JSONField(name = "IsDetainees")
    @JsonProperty("IsDetainees")
    private Integer isDetainees;
    @JSONField(name = "DetentionHouseCode")
    @JsonProperty("DetentionHouseCode")
    private String detentionHouseCode;
    @JSONField(name = "DetaineesIdentity")
    @JsonProperty("DetaineesIdentity")
    private String detaineesIdentity;
    @JSONField(name = "DetaineesSpecialIdentity")
    @JsonProperty("DetaineesSpecialIdentity")
    private String detaineesSpecialIdentity;
    @JSONField(name = "MemberTypeCode")
    @JsonProperty("MemberTypeCode")
    private String memberTypeCode;
    @JSONField(name = "IsVictim")
    @JsonProperty("IsVictim")
    private Integer isVictim;
    @JSONField(name = "VictimType")
    @JsonProperty("VictimType")
    private String victimType;
    @JSONField(name = "InjuredDegree")
    @JsonProperty("InjuredDegree")
    private String injuredDegree;
    @JSONField(name = "CorpseConditionCode")
    @JsonProperty("CorpseConditionCode")
    private String corpseConditionCode;
    @JSONField(name = "IsSuspiciousPerson")
    @JsonProperty("IsSuspiciousPerson")
    private Integer isSuspiciousPerson;
    @JSONField(name = "Attitude")
    @JsonProperty("Attitude")
    private Integer attitude;
    @JSONField(name = "Similaritydegree")
    @JsonProperty("Similaritydegree")
    private Double similaritydegree;
    @JSONField(name = "EyebrowStyle")
    @JsonProperty("EyebrowStyle")
    private String eyebrowStyle;
    @JSONField(name = "NoseStyle")
    @JsonProperty("NoseStyle")
    private String noseStyle;
    @JSONField(name = "MustacheStyle")
    @JsonProperty("MustacheStyle")
    private String mustacheStyle;
    @JSONField(name = "LipStyle")
    @JsonProperty("LipStyle")
    private String lipStyle;
    @JSONField(name = "WrinklePouch")
    @JsonProperty("WrinklePouch")
    private String wrinklePouch;
    @JSONField(name = "AcneStain")
    @JsonProperty("AcneStain")
    private String acneStain;
    @JSONField(name = "FreckleBirthmark")
    @JsonProperty("FreckleBirthmark")
    private String freckleBirthmark;
    @JSONField(name = "ScarDimple")
    @JsonProperty("ScarDimple")
    private String scarDimple;
    @JSONField(name = "OtherFeature")
    @JsonProperty("OtherFeature")
    private String otherFeature;
    @JSONField(name = "SubImageList")
    @JsonProperty("SubImageList")
    private SubImageInfoObject subImageList;

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Face)) return false;
        Face other = (Face) o;
        if (!other.canEqual(this)) return false;
        Object this$faceId = getFaceId(), other$faceId = other.getFaceId();
        if ((this$faceId == null) ? (other$faceId != null) : !this$faceId.equals(other$faceId)) return false;
        Object this$infoKind = getInfoKind(), other$infoKind = other.getInfoKind();
        if ((this$infoKind == null) ? (other$infoKind != null) : !this$infoKind.equals(other$infoKind)) return false;
        Object this$sourceId = getSourceId(), other$sourceId = other.getSourceId();
        if ((this$sourceId == null) ? (other$sourceId != null) : !this$sourceId.equals(other$sourceId)) return false;
        Object this$deviceId = getDeviceId(), other$deviceId = other.getDeviceId();
        if ((this$deviceId == null) ? (other$deviceId != null) : !this$deviceId.equals(other$deviceId)) return false;
        Object this$leftTopX = getLeftTopX(), other$leftTopX = other.getLeftTopX();
        if ((this$leftTopX == null) ? (other$leftTopX != null) : !this$leftTopX.equals(other$leftTopX)) return false;
        Object this$leftTopY = getLeftTopY(), other$leftTopY = other.getLeftTopY();
        if ((this$leftTopY == null) ? (other$leftTopY != null) : !this$leftTopY.equals(other$leftTopY)) return false;
        Object this$rightBtmX = getRightBtmX(), other$rightBtmX = other.getRightBtmX();
        if ((this$rightBtmX == null) ? (other$rightBtmX != null) : !this$rightBtmX.equals(other$rightBtmX))
            return false;
        Object this$rightBtmY = getRightBtmY(), other$rightBtmY = other.getRightBtmY();
        if ((this$rightBtmY == null) ? (other$rightBtmY != null) : !this$rightBtmY.equals(other$rightBtmY))
            return false;
        Object this$locationMarkTime = getLocationMarkTime(), other$locationMarkTime = other.getLocationMarkTime();
        if ((this$locationMarkTime == null) ? (other$locationMarkTime != null) : !this$locationMarkTime.equals(other$locationMarkTime))
            return false;
        Object this$faceAppearTime = getFaceAppearTime(), other$faceAppearTime = other.getFaceAppearTime();
        if ((this$faceAppearTime == null) ? (other$faceAppearTime != null) : !this$faceAppearTime.equals(other$faceAppearTime))
            return false;
        Object this$faceDisAppearTime = getFaceDisAppearTime(), other$faceDisAppearTime = other.getFaceDisAppearTime();
        if ((this$faceDisAppearTime == null) ? (other$faceDisAppearTime != null) : !this$faceDisAppearTime.equals(other$faceDisAppearTime))
            return false;
        Object this$idType = getIdType(), other$idType = other.getIdType();
        if ((this$idType == null) ? (other$idType != null) : !this$idType.equals(other$idType)) return false;
        Object this$idNumber = getIdNumber(), other$idNumber = other.getIdNumber();
        if ((this$idNumber == null) ? (other$idNumber != null) : !this$idNumber.equals(other$idNumber)) return false;
        Object this$name = getName(), other$name = other.getName();
        if ((this$name == null) ? (other$name != null) : !this$name.equals(other$name)) return false;
        Object this$usedName = getUsedName(), other$usedName = other.getUsedName();
        if ((this$usedName == null) ? (other$usedName != null) : !this$usedName.equals(other$usedName)) return false;
        Object this$alias = getAlias(), other$alias = other.getAlias();
        if ((this$alias == null) ? (other$alias != null) : !this$alias.equals(other$alias)) return false;
        Object this$genderCode = getGenderCode(), other$genderCode = other.getGenderCode();
        if ((this$genderCode == null) ? (other$genderCode != null) : !this$genderCode.equals(other$genderCode))
            return false;
        Object this$ageUpLimit = getAgeUpLimit(), other$ageUpLimit = other.getAgeUpLimit();
        if ((this$ageUpLimit == null) ? (other$ageUpLimit != null) : !this$ageUpLimit.equals(other$ageUpLimit))
            return false;
        Object this$ageLowerLimit = getAgeLowerLimit(), other$ageLowerLimit = other.getAgeLowerLimit();
        if ((this$ageLowerLimit == null) ? (other$ageLowerLimit != null) : !this$ageLowerLimit.equals(other$ageLowerLimit))
            return false;
        Object this$ethicCode = getEthicCode(), other$ethicCode = other.getEthicCode();
        if ((this$ethicCode == null) ? (other$ethicCode != null) : !this$ethicCode.equals(other$ethicCode))
            return false;
        Object this$nationalityCode = getNationalityCode(), other$nationalityCode = other.getNationalityCode();
        if ((this$nationalityCode == null) ? (other$nationalityCode != null) : !this$nationalityCode.equals(other$nationalityCode))
            return false;
        Object this$nativeCityCode = getNativeCityCode(), other$nativeCityCode = other.getNativeCityCode();
        if ((this$nativeCityCode == null) ? (other$nativeCityCode != null) : !this$nativeCityCode.equals(other$nativeCityCode))
            return false;
        Object this$residenceAdminDivision = getResidenceAdminDivision(), other$residenceAdminDivision = other.getResidenceAdminDivision();
        if ((this$residenceAdminDivision == null) ? (other$residenceAdminDivision != null) : !this$residenceAdminDivision.equals(other$residenceAdminDivision))
            return false;
        Object this$chineseAccentCod = getChineseAccentCod(), other$chineseAccentCod = other.getChineseAccentCod();
        if ((this$chineseAccentCod == null) ? (other$chineseAccentCod != null) : !this$chineseAccentCod.equals(other$chineseAccentCod))
            return false;
        Object this$jobCategory = getJobCategory(), other$jobCategory = other.getJobCategory();
        if ((this$jobCategory == null) ? (other$jobCategory != null) : !this$jobCategory.equals(other$jobCategory))
            return false;
        Object this$accompanyNumber = getAccompanyNumber(), other$accompanyNumber = other.getAccompanyNumber();
        if ((this$accompanyNumber == null) ? (other$accompanyNumber != null) : !this$accompanyNumber.equals(other$accompanyNumber))
            return false;
        Object this$skinColor = getSkinColor(), other$skinColor = other.getSkinColor();
        if ((this$skinColor == null) ? (other$skinColor != null) : !this$skinColor.equals(other$skinColor))
            return false;
        Object this$hairStyle = getHairStyle(), other$hairStyle = other.getHairStyle();
        if ((this$hairStyle == null) ? (other$hairStyle != null) : !this$hairStyle.equals(other$hairStyle))
            return false;
        Object this$hairColor = getHairColor(), other$hairColor = other.getHairColor();
        if ((this$hairColor == null) ? (other$hairColor != null) : !this$hairColor.equals(other$hairColor))
            return false;
        Object this$faceStyle = getFaceStyle(), other$faceStyle = other.getFaceStyle();
        if ((this$faceStyle == null) ? (other$faceStyle != null) : !this$faceStyle.equals(other$faceStyle))
            return false;
        Object this$facialFeature = getFacialFeature(), other$facialFeature = other.getFacialFeature();
        if ((this$facialFeature == null) ? (other$facialFeature != null) : !this$facialFeature.equals(other$facialFeature))
            return false;
        Object this$physicalFeature = getPhysicalFeature(), other$physicalFeature = other.getPhysicalFeature();
        if ((this$physicalFeature == null) ? (other$physicalFeature != null) : !this$physicalFeature.equals(other$physicalFeature))
            return false;
        Object this$respiratorColor = getRespiratorColor(), other$respiratorColor = other.getRespiratorColor();
        if ((this$respiratorColor == null) ? (other$respiratorColor != null) : !this$respiratorColor.equals(other$respiratorColor))
            return false;
        Object this$capStyle = getCapStyle(), other$capStyle = other.getCapStyle();
        if ((this$capStyle == null) ? (other$capStyle != null) : !this$capStyle.equals(other$capStyle)) return false;
        Object this$capColor = getCapColor(), other$capColor = other.getCapColor();
        if ((this$capColor == null) ? (other$capColor != null) : !this$capColor.equals(other$capColor)) return false;
        Object this$glassStyle = getGlassStyle(), other$glassStyle = other.getGlassStyle();
        if ((this$glassStyle == null) ? (other$glassStyle != null) : !this$glassStyle.equals(other$glassStyle))
            return false;
        Object this$glassColor = getGlassColor(), other$glassColor = other.getGlassColor();
        if ((this$glassColor == null) ? (other$glassColor != null) : !this$glassColor.equals(other$glassColor))
            return false;
        Object this$isDriver = getIsDriver(), other$isDriver = other.getIsDriver();
        if ((this$isDriver == null) ? (other$isDriver != null) : !this$isDriver.equals(other$isDriver)) return false;
        Object this$isForeigner = getIsForeigner(), other$isForeigner = other.getIsForeigner();
        if ((this$isForeigner == null) ? (other$isForeigner != null) : !this$isForeigner.equals(other$isForeigner))
            return false;
        Object this$passportType = getPassportType(), other$passportType = other.getPassportType();
        if ((this$passportType == null) ? (other$passportType != null) : !this$passportType.equals(other$passportType))
            return false;
        Object this$immigrantTypeCode = getImmigrantTypeCode(), other$immigrantTypeCode = other.getImmigrantTypeCode();
        if ((this$immigrantTypeCode == null) ? (other$immigrantTypeCode != null) : !this$immigrantTypeCode.equals(other$immigrantTypeCode))
            return false;
        Object this$isSuspectedTerrorist = getIsSuspectedTerrorist(), other$isSuspectedTerrorist = other.getIsSuspectedTerrorist();
        if ((this$isSuspectedTerrorist == null) ? (other$isSuspectedTerrorist != null) : !this$isSuspectedTerrorist.equals(other$isSuspectedTerrorist))
            return false;
        Object this$suspectedTerroristNumber = getSuspectedTerroristNumber(), other$suspectedTerroristNumber = other.getSuspectedTerroristNumber();
        if ((this$suspectedTerroristNumber == null) ? (other$suspectedTerroristNumber != null) : !this$suspectedTerroristNumber.equals(other$suspectedTerroristNumber))
            return false;
        Object this$isCriminalInvolved = getIsCriminalInvolved(), other$isCriminalInvolved = other.getIsCriminalInvolved();
        if ((this$isCriminalInvolved == null) ? (other$isCriminalInvolved != null) : !this$isCriminalInvolved.equals(other$isCriminalInvolved))
            return false;
        Object this$criminalInvolvedSpecilisationCode = getCriminalInvolvedSpecilisationCode(), other$criminalInvolvedSpecilisationCode = other.getCriminalInvolvedSpecilisationCode();
        if ((this$criminalInvolvedSpecilisationCode == null) ? (other$criminalInvolvedSpecilisationCode != null) : !this$criminalInvolvedSpecilisationCode.equals(other$criminalInvolvedSpecilisationCode))
            return false;
        Object this$bodySpeciallMark = getBodySpeciallMark(), other$bodySpeciallMark = other.getBodySpeciallMark();
        if ((this$bodySpeciallMark == null) ? (other$bodySpeciallMark != null) : !this$bodySpeciallMark.equals(other$bodySpeciallMark))
            return false;
        Object this$crimeMethod = getCrimeMethod(), other$crimeMethod = other.getCrimeMethod();
        if ((this$crimeMethod == null) ? (other$crimeMethod != null) : !this$crimeMethod.equals(other$crimeMethod))
            return false;
        Object this$crimeCharacterCode = getCrimeCharacterCode(), other$crimeCharacterCode = other.getCrimeCharacterCode();
        if ((this$crimeCharacterCode == null) ? (other$crimeCharacterCode != null) : !this$crimeCharacterCode.equals(other$crimeCharacterCode))
            return false;
        Object this$escapedCriminalNumber = getEscapedCriminalNumber(), other$escapedCriminalNumber = other.getEscapedCriminalNumber();
        if ((this$escapedCriminalNumber == null) ? (other$escapedCriminalNumber != null) : !this$escapedCriminalNumber.equals(other$escapedCriminalNumber))
            return false;
        Object this$isDetainees = getIsDetainees(), other$isDetainees = other.getIsDetainees();
        if ((this$isDetainees == null) ? (other$isDetainees != null) : !this$isDetainees.equals(other$isDetainees))
            return false;
        Object this$detentionHouseCode = getDetentionHouseCode(), other$detentionHouseCode = other.getDetentionHouseCode();
        if ((this$detentionHouseCode == null) ? (other$detentionHouseCode != null) : !this$detentionHouseCode.equals(other$detentionHouseCode))
            return false;
        Object this$detaineesIdentity = getDetaineesIdentity(), other$detaineesIdentity = other.getDetaineesIdentity();
        if ((this$detaineesIdentity == null) ? (other$detaineesIdentity != null) : !this$detaineesIdentity.equals(other$detaineesIdentity))
            return false;
        Object this$detaineesSpecialIdentity = getDetaineesSpecialIdentity(), other$detaineesSpecialIdentity = other.getDetaineesSpecialIdentity();
        if ((this$detaineesSpecialIdentity == null) ? (other$detaineesSpecialIdentity != null) : !this$detaineesSpecialIdentity.equals(other$detaineesSpecialIdentity))
            return false;
        Object this$memberTypeCode = getMemberTypeCode(), other$memberTypeCode = other.getMemberTypeCode();
        if ((this$memberTypeCode == null) ? (other$memberTypeCode != null) : !this$memberTypeCode.equals(other$memberTypeCode))
            return false;
        Object this$isVictim = getIsVictim(), other$isVictim = other.getIsVictim();
        if ((this$isVictim == null) ? (other$isVictim != null) : !this$isVictim.equals(other$isVictim)) return false;
        Object this$victimType = getVictimType(), other$victimType = other.getVictimType();
        if ((this$victimType == null) ? (other$victimType != null) : !this$victimType.equals(other$victimType))
            return false;
        Object this$injuredDegree = getInjuredDegree(), other$injuredDegree = other.getInjuredDegree();
        if ((this$injuredDegree == null) ? (other$injuredDegree != null) : !this$injuredDegree.equals(other$injuredDegree))
            return false;
        Object this$corpseConditionCode = getCorpseConditionCode(), other$corpseConditionCode = other.getCorpseConditionCode();
        if ((this$corpseConditionCode == null) ? (other$corpseConditionCode != null) : !this$corpseConditionCode.equals(other$corpseConditionCode))
            return false;
        Object this$isSuspiciousPerson = getIsSuspiciousPerson(), other$isSuspiciousPerson = other.getIsSuspiciousPerson();
        if ((this$isSuspiciousPerson == null) ? (other$isSuspiciousPerson != null) : !this$isSuspiciousPerson.equals(other$isSuspiciousPerson))
            return false;
        Object this$attitude = getAttitude(), other$attitude = other.getAttitude();
        if ((this$attitude == null) ? (other$attitude != null) : !this$attitude.equals(other$attitude)) return false;
        Object this$similaritydegree = getSimilaritydegree(), other$similaritydegree = other.getSimilaritydegree();
        if ((this$similaritydegree == null) ? (other$similaritydegree != null) : !this$similaritydegree.equals(other$similaritydegree))
            return false;
        Object this$eyebrowStyle = getEyebrowStyle(), other$eyebrowStyle = other.getEyebrowStyle();
        if ((this$eyebrowStyle == null) ? (other$eyebrowStyle != null) : !this$eyebrowStyle.equals(other$eyebrowStyle))
            return false;
        Object this$noseStyle = getNoseStyle(), other$noseStyle = other.getNoseStyle();
        if ((this$noseStyle == null) ? (other$noseStyle != null) : !this$noseStyle.equals(other$noseStyle))
            return false;
        Object this$mustacheStyle = getMustacheStyle(), other$mustacheStyle = other.getMustacheStyle();
        if ((this$mustacheStyle == null) ? (other$mustacheStyle != null) : !this$mustacheStyle.equals(other$mustacheStyle))
            return false;
        Object this$lipStyle = getLipStyle(), other$lipStyle = other.getLipStyle();
        if ((this$lipStyle == null) ? (other$lipStyle != null) : !this$lipStyle.equals(other$lipStyle)) return false;
        Object this$wrinklePouch = getWrinklePouch(), other$wrinklePouch = other.getWrinklePouch();
        if ((this$wrinklePouch == null) ? (other$wrinklePouch != null) : !this$wrinklePouch.equals(other$wrinklePouch))
            return false;
        Object this$acneStain = getAcneStain(), other$acneStain = other.getAcneStain();
        if ((this$acneStain == null) ? (other$acneStain != null) : !this$acneStain.equals(other$acneStain))
            return false;
        Object this$freckleBirthmark = getFreckleBirthmark(), other$freckleBirthmark = other.getFreckleBirthmark();
        if ((this$freckleBirthmark == null) ? (other$freckleBirthmark != null) : !this$freckleBirthmark.equals(other$freckleBirthmark))
            return false;
        Object this$scarDimple = getScarDimple(), other$scarDimple = other.getScarDimple();
        if ((this$scarDimple == null) ? (other$scarDimple != null) : !this$scarDimple.equals(other$scarDimple))
            return false;
        Object this$otherFeature = getOtherFeature(), other$otherFeature = other.getOtherFeature();
        if ((this$otherFeature == null) ? (other$otherFeature != null) : !this$otherFeature.equals(other$otherFeature))
            return false;
        Object this$subImageList = getSubImageList(), other$subImageList = other.getSubImageList();
        return !((this$subImageList == null) ? (other$subImageList != null) : !this$subImageList.equals(other$subImageList));
    }

    protected boolean canEqual(Object other) {
        return other instanceof Face;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $faceId = getFaceId();
        result = result * 59 + (($faceId == null) ? 0 : $faceId.hashCode());
        Object $infoKind = getInfoKind();
        result = result * 59 + (($infoKind == null) ? 0 : $infoKind.hashCode());
        Object $sourceId = getSourceId();
        result = result * 59 + (($sourceId == null) ? 0 : $sourceId.hashCode());
        Object $deviceId = getDeviceId();
        result = result * 59 + (($deviceId == null) ? 0 : $deviceId.hashCode());
        Object $leftTopX = getLeftTopX();
        result = result * 59 + (($leftTopX == null) ? 0 : $leftTopX.hashCode());
        Object $leftTopY = getLeftTopY();
        result = result * 59 + (($leftTopY == null) ? 0 : $leftTopY.hashCode());
        Object $rightBtmX = getRightBtmX();
        result = result * 59 + (($rightBtmX == null) ? 0 : $rightBtmX.hashCode());
        Object $rightBtmY = getRightBtmY();
        result = result * 59 + (($rightBtmY == null) ? 0 : $rightBtmY.hashCode());
        Object $locationMarkTime = getLocationMarkTime();
        result = result * 59 + (($locationMarkTime == null) ? 0 : $locationMarkTime.hashCode());
        Object $faceAppearTime = getFaceAppearTime();
        result = result * 59 + (($faceAppearTime == null) ? 0 : $faceAppearTime.hashCode());
        Object $faceDisAppearTime = getFaceDisAppearTime();
        result = result * 59 + (($faceDisAppearTime == null) ? 0 : $faceDisAppearTime.hashCode());
        Object $idType = getIdType();
        result = result * 59 + (($idType == null) ? 0 : $idType.hashCode());
        Object $idNumber = getIdNumber();
        result = result * 59 + (($idNumber == null) ? 0 : $idNumber.hashCode());
        Object $name = getName();
        result = result * 59 + (($name == null) ? 0 : $name.hashCode());
        Object $usedName = getUsedName();
        result = result * 59 + (($usedName == null) ? 0 : $usedName.hashCode());
        Object $alias = getAlias();
        result = result * 59 + (($alias == null) ? 0 : $alias.hashCode());
        Object $genderCode = getGenderCode();
        result = result * 59 + (($genderCode == null) ? 0 : $genderCode.hashCode());
        Object $ageUpLimit = getAgeUpLimit();
        result = result * 59 + (($ageUpLimit == null) ? 0 : $ageUpLimit.hashCode());
        Object $ageLowerLimit = getAgeLowerLimit();
        result = result * 59 + (($ageLowerLimit == null) ? 0 : $ageLowerLimit.hashCode());
        Object $ethicCode = getEthicCode();
        result = result * 59 + (($ethicCode == null) ? 0 : $ethicCode.hashCode());
        Object $nationalityCode = getNationalityCode();
        result = result * 59 + (($nationalityCode == null) ? 0 : $nationalityCode.hashCode());
        Object $nativeCityCode = getNativeCityCode();
        result = result * 59 + (($nativeCityCode == null) ? 0 : $nativeCityCode.hashCode());
        Object $residenceAdminDivision = getResidenceAdminDivision();
        result = result * 59 + (($residenceAdminDivision == null) ? 0 : $residenceAdminDivision.hashCode());
        Object $chineseAccentCod = getChineseAccentCod();
        result = result * 59 + (($chineseAccentCod == null) ? 0 : $chineseAccentCod.hashCode());
        Object $jobCategory = getJobCategory();
        result = result * 59 + (($jobCategory == null) ? 0 : $jobCategory.hashCode());
        Object $accompanyNumber = getAccompanyNumber();
        result = result * 59 + (($accompanyNumber == null) ? 0 : $accompanyNumber.hashCode());
        Object $skinColor = getSkinColor();
        result = result * 59 + (($skinColor == null) ? 0 : $skinColor.hashCode());
        Object $hairStyle = getHairStyle();
        result = result * 59 + (($hairStyle == null) ? 0 : $hairStyle.hashCode());
        Object $hairColor = getHairColor();
        result = result * 59 + (($hairColor == null) ? 0 : $hairColor.hashCode());
        Object $faceStyle = getFaceStyle();
        result = result * 59 + (($faceStyle == null) ? 0 : $faceStyle.hashCode());
        Object $facialFeature = getFacialFeature();
        result = result * 59 + (($facialFeature == null) ? 0 : $facialFeature.hashCode());
        Object $physicalFeature = getPhysicalFeature();
        result = result * 59 + (($physicalFeature == null) ? 0 : $physicalFeature.hashCode());
        Object $respiratorColor = getRespiratorColor();
        result = result * 59 + (($respiratorColor == null) ? 0 : $respiratorColor.hashCode());
        Object $capStyle = getCapStyle();
        result = result * 59 + (($capStyle == null) ? 0 : $capStyle.hashCode());
        Object $capColor = getCapColor();
        result = result * 59 + (($capColor == null) ? 0 : $capColor.hashCode());
        Object $glassStyle = getGlassStyle();
        result = result * 59 + (($glassStyle == null) ? 0 : $glassStyle.hashCode());
        Object $glassColor = getGlassColor();
        result = result * 59 + (($glassColor == null) ? 0 : $glassColor.hashCode());
        Object $isDriver = getIsDriver();
        result = result * 59 + (($isDriver == null) ? 0 : $isDriver.hashCode());
        Object $isForeigner = getIsForeigner();
        result = result * 59 + (($isForeigner == null) ? 0 : $isForeigner.hashCode());
        Object $passportType = getPassportType();
        result = result * 59 + (($passportType == null) ? 0 : $passportType.hashCode());
        Object $immigrantTypeCode = getImmigrantTypeCode();
        result = result * 59 + (($immigrantTypeCode == null) ? 0 : $immigrantTypeCode.hashCode());
        Object $isSuspectedTerrorist = getIsSuspectedTerrorist();
        result = result * 59 + (($isSuspectedTerrorist == null) ? 0 : $isSuspectedTerrorist.hashCode());
        Object $suspectedTerroristNumber = getSuspectedTerroristNumber();
        result = result * 59 + (($suspectedTerroristNumber == null) ? 0 : $suspectedTerroristNumber.hashCode());
        Object $isCriminalInvolved = getIsCriminalInvolved();
        result = result * 59 + (($isCriminalInvolved == null) ? 0 : $isCriminalInvolved.hashCode());
        Object $criminalInvolvedSpecilisationCode = getCriminalInvolvedSpecilisationCode();
        result = result * 59 + (($criminalInvolvedSpecilisationCode == null) ? 0 : $criminalInvolvedSpecilisationCode.hashCode());
        Object $bodySpeciallMark = getBodySpeciallMark();
        result = result * 59 + (($bodySpeciallMark == null) ? 0 : $bodySpeciallMark.hashCode());
        Object $crimeMethod = getCrimeMethod();
        result = result * 59 + (($crimeMethod == null) ? 0 : $crimeMethod.hashCode());
        Object $crimeCharacterCode = getCrimeCharacterCode();
        result = result * 59 + (($crimeCharacterCode == null) ? 0 : $crimeCharacterCode.hashCode());
        Object $escapedCriminalNumber = getEscapedCriminalNumber();
        result = result * 59 + (($escapedCriminalNumber == null) ? 0 : $escapedCriminalNumber.hashCode());
        Object $isDetainees = getIsDetainees();
        result = result * 59 + (($isDetainees == null) ? 0 : $isDetainees.hashCode());
        Object $detentionHouseCode = getDetentionHouseCode();
        result = result * 59 + (($detentionHouseCode == null) ? 0 : $detentionHouseCode.hashCode());
        Object $detaineesIdentity = getDetaineesIdentity();
        result = result * 59 + (($detaineesIdentity == null) ? 0 : $detaineesIdentity.hashCode());
        Object $detaineesSpecialIdentity = getDetaineesSpecialIdentity();
        result = result * 59 + (($detaineesSpecialIdentity == null) ? 0 : $detaineesSpecialIdentity.hashCode());
        Object $memberTypeCode = getMemberTypeCode();
        result = result * 59 + (($memberTypeCode == null) ? 0 : $memberTypeCode.hashCode());
        Object $isVictim = getIsVictim();
        result = result * 59 + (($isVictim == null) ? 0 : $isVictim.hashCode());
        Object $victimType = getVictimType();
        result = result * 59 + (($victimType == null) ? 0 : $victimType.hashCode());
        Object $injuredDegree = getInjuredDegree();
        result = result * 59 + (($injuredDegree == null) ? 0 : $injuredDegree.hashCode());
        Object $corpseConditionCode = getCorpseConditionCode();
        result = result * 59 + (($corpseConditionCode == null) ? 0 : $corpseConditionCode.hashCode());
        Object $isSuspiciousPerson = getIsSuspiciousPerson();
        result = result * 59 + (($isSuspiciousPerson == null) ? 0 : $isSuspiciousPerson.hashCode());
        Object $attitude = getAttitude();
        result = result * 59 + (($attitude == null) ? 0 : $attitude.hashCode());
        Object $similaritydegree = getSimilaritydegree();
        result = result * 59 + (($similaritydegree == null) ? 0 : $similaritydegree.hashCode());
        Object $eyebrowStyle = getEyebrowStyle();
        result = result * 59 + (($eyebrowStyle == null) ? 0 : $eyebrowStyle.hashCode());
        Object $noseStyle = getNoseStyle();
        result = result * 59 + (($noseStyle == null) ? 0 : $noseStyle.hashCode());
        Object $mustacheStyle = getMustacheStyle();
        result = result * 59 + (($mustacheStyle == null) ? 0 : $mustacheStyle.hashCode());
        Object $lipStyle = getLipStyle();
        result = result * 59 + (($lipStyle == null) ? 0 : $lipStyle.hashCode());
        Object $wrinklePouch = getWrinklePouch();
        result = result * 59 + (($wrinklePouch == null) ? 0 : $wrinklePouch.hashCode());
        Object $acneStain = getAcneStain();
        result = result * 59 + (($acneStain == null) ? 0 : $acneStain.hashCode());
        Object $freckleBirthmark = getFreckleBirthmark();
        result = result * 59 + (($freckleBirthmark == null) ? 0 : $freckleBirthmark.hashCode());
        Object $scarDimple = getScarDimple();
        result = result * 59 + (($scarDimple == null) ? 0 : $scarDimple.hashCode());
        Object $otherFeature = getOtherFeature();
        result = result * 59 + (($otherFeature == null) ? 0 : $otherFeature.hashCode());
        Object $subImageList = getSubImageList();
        return result * 59 + (($subImageList == null) ? 0 : $subImageList.hashCode());
    }

    public String toString() {
        return "Face(faceId=" + getFaceId() + ", infoKind=" + getInfoKind() + ", sourceId=" + getSourceId() + ", deviceId=" + getDeviceId() + ", leftTopX=" + getLeftTopX() + ", leftTopY=" + getLeftTopY() + ", rightBtmX=" + getRightBtmX() + ", rightBtmY=" + getRightBtmY() + ", locationMarkTime=" + getLocationMarkTime() + ", faceAppearTime=" + getFaceAppearTime() + ", faceDisAppearTime=" + getFaceDisAppearTime() + ", idType=" + getIdType() + ", idNumber=" + getIdNumber() + ", name=" + getName() + ", usedName=" + getUsedName() + ", alias=" + getAlias() + ", genderCode=" + getGenderCode() + ", ageUpLimit=" + getAgeUpLimit() + ", ageLowerLimit=" + getAgeLowerLimit() + ", ethicCode=" + getEthicCode() + ", nationalityCode=" + getNationalityCode() + ", nativeCityCode=" + getNativeCityCode() + ", residenceAdminDivision=" + getResidenceAdminDivision() + ", chineseAccentCod=" + getChineseAccentCod() + ", jobCategory=" + getJobCategory() + ", accompanyNumber=" + getAccompanyNumber() + ", skinColor=" + getSkinColor() + ", hairStyle=" + getHairStyle() + ", hairColor=" + getHairColor() + ", faceStyle=" + getFaceStyle() + ", facialFeature=" + getFacialFeature() + ", physicalFeature=" + getPhysicalFeature() + ", respiratorColor=" + getRespiratorColor() + ", capStyle=" + getCapStyle() + ", capColor=" + getCapColor() + ", glassStyle=" + getGlassStyle() + ", glassColor=" + getGlassColor() + ", isDriver=" + getIsDriver() + ", isForeigner=" + getIsForeigner() + ", passportType=" + getPassportType() + ", immigrantTypeCode=" + getImmigrantTypeCode() + ", isSuspectedTerrorist=" + getIsSuspectedTerrorist() + ", suspectedTerroristNumber=" + getSuspectedTerroristNumber() + ", isCriminalInvolved=" + getIsCriminalInvolved() + ", criminalInvolvedSpecilisationCode=" + getCriminalInvolvedSpecilisationCode() + ", bodySpeciallMark=" + getBodySpeciallMark() + ", crimeMethod=" + getCrimeMethod() + ", crimeCharacterCode=" + getCrimeCharacterCode() + ", escapedCriminalNumber=" + getEscapedCriminalNumber() + ", isDetainees=" + getIsDetainees() + ", detentionHouseCode=" + getDetentionHouseCode() + ", detaineesIdentity=" + getDetaineesIdentity() + ", detaineesSpecialIdentity=" + getDetaineesSpecialIdentity() + ", memberTypeCode=" + getMemberTypeCode() + ", isVictim=" + getIsVictim() + ", victimType=" + getVictimType() + ", injuredDegree=" + getInjuredDegree() + ", corpseConditionCode=" + getCorpseConditionCode() + ", isSuspiciousPerson=" + getIsSuspiciousPerson() + ", attitude=" + getAttitude() + ", similaritydegree=" + getSimilaritydegree() + ", eyebrowStyle=" + getEyebrowStyle() + ", noseStyle=" + getNoseStyle() + ", mustacheStyle=" + getMustacheStyle() + ", lipStyle=" + getLipStyle() + ", wrinklePouch=" + getWrinklePouch() + ", acneStain=" + getAcneStain() + ", freckleBirthmark=" + getFreckleBirthmark() + ", scarDimple=" + getScarDimple() + ", otherFeature=" + getOtherFeature() + ", subImageList=" + getSubImageList() + ")";
    }

    public String getFaceId() {
        return this.faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    public Integer getInfoKind() {
        return this.infoKind;
    }

    public void setInfoKind(Integer infoKind) {
        this.infoKind = infoKind;
    }

    public String getSourceId() {
        return this.sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getLeftTopX() {
        return this.leftTopX;
    }

    public void setLeftTopX(Integer leftTopX) {
        this.leftTopX = leftTopX;
    }

    public Integer getLeftTopY() {
        return this.leftTopY;
    }

    public void setLeftTopY(Integer leftTopY) {
        this.leftTopY = leftTopY;
    }

    public Integer getRightBtmX() {
        return this.rightBtmX;
    }

    public void setRightBtmX(Integer rightBtmX) {
        this.rightBtmX = rightBtmX;
    }

    public Integer getRightBtmY() {
        return this.rightBtmY;
    }

    public void setRightBtmY(Integer rightBtmY) {
        this.rightBtmY = rightBtmY;
    }

    public String getLocationMarkTime() {
        return this.locationMarkTime;
    }

    public void setLocationMarkTime(String locationMarkTime) {
        this.locationMarkTime = locationMarkTime;
    }

    public String getFaceAppearTime() {
        return this.faceAppearTime;
    }

    public void setFaceAppearTime(String faceAppearTime) {
        this.faceAppearTime = faceAppearTime;
    }

    public String getFaceDisAppearTime() {
        return this.faceDisAppearTime;
    }

    public void setFaceDisAppearTime(String faceDisAppearTime) {
        this.faceDisAppearTime = faceDisAppearTime;
    }

    public String getIdType() {
        return this.idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return this.idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsedName() {
        return this.usedName;
    }

    public void setUsedName(String usedName) {
        this.usedName = usedName;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getGenderCode() {
        return this.genderCode;
    }

    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }

    public Integer getAgeUpLimit() {
        return this.ageUpLimit;
    }

    public void setAgeUpLimit(Integer ageUpLimit) {
        this.ageUpLimit = ageUpLimit;
    }

    public Integer getAgeLowerLimit() {
        return this.ageLowerLimit;
    }

    public void setAgeLowerLimit(Integer ageLowerLimit) {
        this.ageLowerLimit = ageLowerLimit;
    }

    public String getEthicCode() {
        return this.ethicCode;
    }

    public void setEthicCode(String ethicCode) {
        this.ethicCode = ethicCode;
    }

    public String getNationalityCode() {
        return this.nationalityCode;
    }

    public void setNationalityCode(String nationalityCode) {
        this.nationalityCode = nationalityCode;
    }

    public String getNativeCityCode() {
        return this.nativeCityCode;
    }

    public void setNativeCityCode(String nativeCityCode) {
        this.nativeCityCode = nativeCityCode;
    }

    public String getResidenceAdminDivision() {
        return this.residenceAdminDivision;
    }

    public void setResidenceAdminDivision(String residenceAdminDivision) {
        this.residenceAdminDivision = residenceAdminDivision;
    }

    public String getChineseAccentCod() {
        return this.chineseAccentCod;
    }

    public void setChineseAccentCod(String chineseAccentCod) {
        this.chineseAccentCod = chineseAccentCod;
    }

    public String getJobCategory() {
        return this.jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public Integer getAccompanyNumber() {
        return this.accompanyNumber;
    }

    public void setAccompanyNumber(Integer accompanyNumber) {
        this.accompanyNumber = accompanyNumber;
    }

    public String getSkinColor() {
        return this.skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getHairStyle() {
        return this.hairStyle;
    }

    public void setHairStyle(String hairStyle) {
        this.hairStyle = hairStyle;
    }

    public String getHairColor() {
        return this.hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getFaceStyle() {
        return this.faceStyle;
    }

    public void setFaceStyle(String faceStyle) {
        this.faceStyle = faceStyle;
    }

    public String getFacialFeature() {
        return this.facialFeature;
    }

    public void setFacialFeature(String facialFeature) {
        this.facialFeature = facialFeature;
    }

    public String getPhysicalFeature() {
        return this.physicalFeature;
    }

    public void setPhysicalFeature(String physicalFeature) {
        this.physicalFeature = physicalFeature;
    }

    public String getRespiratorColor() {
        return this.respiratorColor;
    }

    public void setRespiratorColor(String respiratorColor) {
        this.respiratorColor = respiratorColor;
    }

    public String getCapStyle() {
        return this.capStyle;
    }

    public void setCapStyle(String capStyle) {
        this.capStyle = capStyle;
    }

    public String getCapColor() {
        return this.capColor;
    }

    public void setCapColor(String capColor) {
        this.capColor = capColor;
    }

    public String getGlassStyle() {
        return this.glassStyle;
    }

    public void setGlassStyle(String glassStyle) {
        this.glassStyle = glassStyle;
    }

    public String getGlassColor() {
        return this.glassColor;
    }

    public void setGlassColor(String glassColor) {
        this.glassColor = glassColor;
    }

    public Integer getIsDriver() {
        return this.isDriver;
    }

    public void setIsDriver(Integer isDriver) {
        this.isDriver = isDriver;
    }

    public Integer getIsForeigner() {
        return this.isForeigner;
    }

    public void setIsForeigner(Integer isForeigner) {
        this.isForeigner = isForeigner;
    }

    public String getPassportType() {
        return this.passportType;
    }

    public void setPassportType(String passportType) {
        this.passportType = passportType;
    }

    public String getImmigrantTypeCode() {
        return this.immigrantTypeCode;
    }

    public void setImmigrantTypeCode(String immigrantTypeCode) {
        this.immigrantTypeCode = immigrantTypeCode;
    }

    public Integer getIsSuspectedTerrorist() {
        return this.isSuspectedTerrorist;
    }

    public void setIsSuspectedTerrorist(Integer isSuspectedTerrorist) {
        this.isSuspectedTerrorist = isSuspectedTerrorist;
    }

    public String getSuspectedTerroristNumber() {
        return this.suspectedTerroristNumber;
    }

    public void setSuspectedTerroristNumber(String suspectedTerroristNumber) {
        this.suspectedTerroristNumber = suspectedTerroristNumber;
    }

    public Integer getIsCriminalInvolved() {
        return this.isCriminalInvolved;
    }

    public void setIsCriminalInvolved(Integer isCriminalInvolved) {
        this.isCriminalInvolved = isCriminalInvolved;
    }

    public String getCriminalInvolvedSpecilisationCode() {
        return this.criminalInvolvedSpecilisationCode;
    }

    public void setCriminalInvolvedSpecilisationCode(String criminalInvolvedSpecilisationCode) {
        this.criminalInvolvedSpecilisationCode = criminalInvolvedSpecilisationCode;
    }

    public String getBodySpeciallMark() {
        return this.bodySpeciallMark;
    }

    public void setBodySpeciallMark(String bodySpeciallMark) {
        this.bodySpeciallMark = bodySpeciallMark;
    }

    public String getCrimeMethod() {
        return this.crimeMethod;
    }

    public void setCrimeMethod(String crimeMethod) {
        this.crimeMethod = crimeMethod;
    }

    public String getCrimeCharacterCode() {
        return this.crimeCharacterCode;
    }

    public void setCrimeCharacterCode(String crimeCharacterCode) {
        this.crimeCharacterCode = crimeCharacterCode;
    }

    public String getEscapedCriminalNumber() {
        return this.escapedCriminalNumber;
    }

    public void setEscapedCriminalNumber(String escapedCriminalNumber) {
        this.escapedCriminalNumber = escapedCriminalNumber;
    }

    public Integer getIsDetainees() {
        return this.isDetainees;
    }

    public void setIsDetainees(Integer isDetainees) {
        this.isDetainees = isDetainees;
    }

    public String getDetentionHouseCode() {
        return this.detentionHouseCode;
    }

    public void setDetentionHouseCode(String detentionHouseCode) {
        this.detentionHouseCode = detentionHouseCode;
    }

    public String getDetaineesIdentity() {
        return this.detaineesIdentity;
    }

    public void setDetaineesIdentity(String detaineesIdentity) {
        this.detaineesIdentity = detaineesIdentity;
    }

    public String getDetaineesSpecialIdentity() {
        return this.detaineesSpecialIdentity;
    }

    public void setDetaineesSpecialIdentity(String detaineesSpecialIdentity) {
        this.detaineesSpecialIdentity = detaineesSpecialIdentity;
    }

    public String getMemberTypeCode() {
        return this.memberTypeCode;
    }

    public void setMemberTypeCode(String memberTypeCode) {
        this.memberTypeCode = memberTypeCode;
    }

    public Integer getIsVictim() {
        return this.isVictim;
    }

    public void setIsVictim(Integer isVictim) {
        this.isVictim = isVictim;
    }

    public String getVictimType() {
        return this.victimType;
    }

    public void setVictimType(String victimType) {
        this.victimType = victimType;
    }

    public String getInjuredDegree() {
        return this.injuredDegree;
    }

    public void setInjuredDegree(String injuredDegree) {
        this.injuredDegree = injuredDegree;
    }

    public String getCorpseConditionCode() {
        return this.corpseConditionCode;
    }

    public void setCorpseConditionCode(String corpseConditionCode) {
        this.corpseConditionCode = corpseConditionCode;
    }

    public Integer getIsSuspiciousPerson() {
        return this.isSuspiciousPerson;
    }

    public void setIsSuspiciousPerson(Integer isSuspiciousPerson) {
        this.isSuspiciousPerson = isSuspiciousPerson;
    }

    public Integer getAttitude() {
        return this.attitude;
    }

    public void setAttitude(Integer attitude) {
        this.attitude = attitude;
    }

    public Double getSimilaritydegree() {
        return this.similaritydegree;
    }

    public void setSimilaritydegree(Double similaritydegree) {
        this.similaritydegree = similaritydegree;
    }

    public String getEyebrowStyle() {
        return this.eyebrowStyle;
    }

    public void setEyebrowStyle(String eyebrowStyle) {
        this.eyebrowStyle = eyebrowStyle;
    }

    public String getNoseStyle() {
        return this.noseStyle;
    }

    public void setNoseStyle(String noseStyle) {
        this.noseStyle = noseStyle;
    }

    public String getMustacheStyle() {
        return this.mustacheStyle;
    }

    public void setMustacheStyle(String mustacheStyle) {
        this.mustacheStyle = mustacheStyle;
    }

    public String getLipStyle() {
        return this.lipStyle;
    }

    public void setLipStyle(String lipStyle) {
        this.lipStyle = lipStyle;
    }

    public String getWrinklePouch() {
        return this.wrinklePouch;
    }

    public void setWrinklePouch(String wrinklePouch) {
        this.wrinklePouch = wrinklePouch;
    }

    public String getAcneStain() {
        return this.acneStain;
    }

    public void setAcneStain(String acneStain) {
        this.acneStain = acneStain;
    }

    public String getFreckleBirthmark() {
        return this.freckleBirthmark;
    }

    public void setFreckleBirthmark(String freckleBirthmark) {
        this.freckleBirthmark = freckleBirthmark;
    }

    public String getScarDimple() {
        return this.scarDimple;
    }

    public void setScarDimple(String scarDimple) {
        this.scarDimple = scarDimple;
    }

    public String getOtherFeature() {
        return this.otherFeature;
    }

    public void setOtherFeature(String otherFeature) {
        this.otherFeature = otherFeature;
    }

    public SubImageInfoObject getSubImageList() {
        return this.subImageList;
    }

    public void setSubImageList(SubImageInfoObject subImageList) {
        this.subImageList = subImageList;
    }
}
