package com.ss.isc.data.viid.common.dto.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
    @JSONField(name = "PersonID")
    @JsonProperty("PersonID")
    private String personId;
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
    @JSONField(name = "PersonAppearTime")
    @JsonProperty("PersonAppearTime")
    private String personAppearTime;
    @JSONField(name = "PersonDisAppearTime")
    @JsonProperty("PersonDisAppearTime")
    private String personDisAppearTime;
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
    @JSONField(name = "HeightUpLimit")
    @JsonProperty("HeightUpLimit")
    private Integer heightUpLimit;
    @JSONField(name = "HeightLowerLimit")
    @JsonProperty("HeightLowerLimit")
    private Integer heightLowerLimit;
    @JSONField(name = "BodyType")
    @JsonProperty("BodyType")
    private String bodyType;
    @JSONField(name = "SkinColor")
    @JsonProperty("SkinColor")
    private String skinColor;
    @JSONField(name = "HairStyle")
    @JsonProperty("HairStyle")
    private String hairStyle;
    @JSONField(name = "HairColor")
    @JsonProperty("HairColor")
    private String hairColor;
    @JSONField(name = "Gesture")
    @JsonProperty("Gesture")
    private String gesture;
    @JSONField(name = "Status")
    @JsonProperty("Status")
    private String status;
    @JSONField(name = "FaceStyle")
    @JsonProperty("FaceStyle")
    private String faceStyle;
    @JSONField(name = "FacialFeature")
    @JsonProperty("FacialFeature")
    private String facialFeature;
    @JSONField(name = "PhysicalFeature")
    @JsonProperty("PhysicalFeature")
    private String physicalFeature;
    @JSONField(name = "BodyFeature")
    @JsonProperty("BodyFeature")
    private String bodyFeature;
    @JSONField(name = "HabitualMovement")
    @JsonProperty("HabitualMovement")
    private String habitualMovement;
    @JSONField(name = "Behavior")
    @JsonProperty("Behavior")
    private String behavior;
    @JSONField(name = "BehaviorDescription")
    @JsonProperty("BehaviorDescription")
    private String behaviorDescription;
    @JSONField(name = "Appendant")
    @JsonProperty("Appendant")
    private String appendant;
    @JSONField(name = "AppendantDescription")
    @JsonProperty("AppendantDescription")
    private String appendantDescription;
    @JSONField(name = "UmbrellaColor")
    @JsonProperty("UmbrellaColor")
    private String umbrellaColor;
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
    @JSONField(name = "BagStyle")
    @JsonProperty("BagStyle")
    private String bagStyle;
    @JSONField(name = "BagColor")
    @JsonProperty("BagColor")
    private String bagColor;
    @JSONField(name = "CoatStyle")
    @JsonProperty("CoatStyle")
    private String coatStyle;
    @JSONField(name = "CoatLength")
    @JsonProperty("CoatLength")
    private String coatLength;
    @JSONField(name = "CoatColor")
    @JsonProperty("CoatColor")
    private String coatColor;
    @JSONField(name = "TrousersStyle")
    @JsonProperty("TrousersStyle")
    private String trousersStyle;
    @JSONField(name = "TrousersColor")
    @JsonProperty("TrousersColor")
    private String trousersColor;
    @JSONField(name = "TrousersLen")
    @JsonProperty("TrousersLen")
    private String trousersLen;
    @JSONField(name = "ShoesStyle")
    @JsonProperty("ShoesStyle")
    private String shoesStyle;
    @JSONField(name = "ShoesColor")
    @JsonProperty("ShoesColor")
    private String shoesColor;
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
    @JSONField(name = "SubImageList")
    @JsonProperty("SubImageList")
    private SubImageInfoObject subImageList;

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Person)) return false;
        Person other = (Person) o;
        if (!other.canEqual(this)) return false;
        Object this$personId = getPersonId(), other$personId = other.getPersonId();
        if ((this$personId == null) ? (other$personId != null) : !this$personId.equals(other$personId)) return false;
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
        Object this$personAppearTime = getPersonAppearTime(), other$personAppearTime = other.getPersonAppearTime();
        if ((this$personAppearTime == null) ? (other$personAppearTime != null) : !this$personAppearTime.equals(other$personAppearTime))
            return false;
        Object this$personDisAppearTime = getPersonDisAppearTime(), other$personDisAppearTime = other.getPersonDisAppearTime();
        if ((this$personDisAppearTime == null) ? (other$personDisAppearTime != null) : !this$personDisAppearTime.equals(other$personDisAppearTime))
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
        Object this$heightUpLimit = getHeightUpLimit(), other$heightUpLimit = other.getHeightUpLimit();
        if ((this$heightUpLimit == null) ? (other$heightUpLimit != null) : !this$heightUpLimit.equals(other$heightUpLimit))
            return false;
        Object this$heightLowerLimit = getHeightLowerLimit(), other$heightLowerLimit = other.getHeightLowerLimit();
        if ((this$heightLowerLimit == null) ? (other$heightLowerLimit != null) : !this$heightLowerLimit.equals(other$heightLowerLimit))
            return false;
        Object this$bodyType = getBodyType(), other$bodyType = other.getBodyType();
        if ((this$bodyType == null) ? (other$bodyType != null) : !this$bodyType.equals(other$bodyType)) return false;
        Object this$skinColor = getSkinColor(), other$skinColor = other.getSkinColor();
        if ((this$skinColor == null) ? (other$skinColor != null) : !this$skinColor.equals(other$skinColor))
            return false;
        Object this$hairStyle = getHairStyle(), other$hairStyle = other.getHairStyle();
        if ((this$hairStyle == null) ? (other$hairStyle != null) : !this$hairStyle.equals(other$hairStyle))
            return false;
        Object this$hairColor = getHairColor(), other$hairColor = other.getHairColor();
        if ((this$hairColor == null) ? (other$hairColor != null) : !this$hairColor.equals(other$hairColor))
            return false;
        Object this$gesture = getGesture(), other$gesture = other.getGesture();
        if ((this$gesture == null) ? (other$gesture != null) : !this$gesture.equals(other$gesture)) return false;
        Object this$status = getStatus(), other$status = other.getStatus();
        if ((this$status == null) ? (other$status != null) : !this$status.equals(other$status)) return false;
        Object this$faceStyle = getFaceStyle(), other$faceStyle = other.getFaceStyle();
        if ((this$faceStyle == null) ? (other$faceStyle != null) : !this$faceStyle.equals(other$faceStyle))
            return false;
        Object this$facialFeature = getFacialFeature(), other$facialFeature = other.getFacialFeature();
        if ((this$facialFeature == null) ? (other$facialFeature != null) : !this$facialFeature.equals(other$facialFeature))
            return false;
        Object this$physicalFeature = getPhysicalFeature(), other$physicalFeature = other.getPhysicalFeature();
        if ((this$physicalFeature == null) ? (other$physicalFeature != null) : !this$physicalFeature.equals(other$physicalFeature))
            return false;
        Object this$bodyFeature = getBodyFeature(), other$bodyFeature = other.getBodyFeature();
        if ((this$bodyFeature == null) ? (other$bodyFeature != null) : !this$bodyFeature.equals(other$bodyFeature))
            return false;
        Object this$habitualMovement = getHabitualMovement(), other$habitualMovement = other.getHabitualMovement();
        if ((this$habitualMovement == null) ? (other$habitualMovement != null) : !this$habitualMovement.equals(other$habitualMovement))
            return false;
        Object this$behavior = getBehavior(), other$behavior = other.getBehavior();
        if ((this$behavior == null) ? (other$behavior != null) : !this$behavior.equals(other$behavior)) return false;
        Object this$behaviorDescription = getBehaviorDescription(), other$behaviorDescription = other.getBehaviorDescription();
        if ((this$behaviorDescription == null) ? (other$behaviorDescription != null) : !this$behaviorDescription.equals(other$behaviorDescription))
            return false;
        Object this$appendant = getAppendant(), other$appendant = other.getAppendant();
        if ((this$appendant == null) ? (other$appendant != null) : !this$appendant.equals(other$appendant))
            return false;
        Object this$appendantDescription = getAppendantDescription(), other$appendantDescription = other.getAppendantDescription();
        if ((this$appendantDescription == null) ? (other$appendantDescription != null) : !this$appendantDescription.equals(other$appendantDescription))
            return false;
        Object this$umbrellaColor = getUmbrellaColor(), other$umbrellaColor = other.getUmbrellaColor();
        if ((this$umbrellaColor == null) ? (other$umbrellaColor != null) : !this$umbrellaColor.equals(other$umbrellaColor))
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
        Object this$bagStyle = getBagStyle(), other$bagStyle = other.getBagStyle();
        if ((this$bagStyle == null) ? (other$bagStyle != null) : !this$bagStyle.equals(other$bagStyle)) return false;
        Object this$bagColor = getBagColor(), other$bagColor = other.getBagColor();
        if ((this$bagColor == null) ? (other$bagColor != null) : !this$bagColor.equals(other$bagColor)) return false;
        Object this$coatStyle = getCoatStyle(), other$coatStyle = other.getCoatStyle();
        if ((this$coatStyle == null) ? (other$coatStyle != null) : !this$coatStyle.equals(other$coatStyle))
            return false;
        Object this$coatLength = getCoatLength(), other$coatLength = other.getCoatLength();
        if ((this$coatLength == null) ? (other$coatLength != null) : !this$coatLength.equals(other$coatLength))
            return false;
        Object this$coatColor = getCoatColor(), other$coatColor = other.getCoatColor();
        if ((this$coatColor == null) ? (other$coatColor != null) : !this$coatColor.equals(other$coatColor))
            return false;
        Object this$trousersStyle = getTrousersStyle(), other$trousersStyle = other.getTrousersStyle();
        if ((this$trousersStyle == null) ? (other$trousersStyle != null) : !this$trousersStyle.equals(other$trousersStyle))
            return false;
        Object this$trousersColor = getTrousersColor(), other$trousersColor = other.getTrousersColor();
        if ((this$trousersColor == null) ? (other$trousersColor != null) : !this$trousersColor.equals(other$trousersColor))
            return false;
        Object this$trousersLen = getTrousersLen(), other$trousersLen = other.getTrousersLen();
        if ((this$trousersLen == null) ? (other$trousersLen != null) : !this$trousersLen.equals(other$trousersLen))
            return false;
        Object this$shoesStyle = getShoesStyle(), other$shoesStyle = other.getShoesStyle();
        if ((this$shoesStyle == null) ? (other$shoesStyle != null) : !this$shoesStyle.equals(other$shoesStyle))
            return false;
        Object this$shoesColor = getShoesColor(), other$shoesColor = other.getShoesColor();
        if ((this$shoesColor == null) ? (other$shoesColor != null) : !this$shoesColor.equals(other$shoesColor))
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
        Object this$subImageList = getSubImageList(), other$subImageList = other.getSubImageList();
        return !((this$subImageList == null) ? (other$subImageList != null) : !this$subImageList.equals(other$subImageList));
    }

    protected boolean canEqual(Object other) {
        return other instanceof Person;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $personId = getPersonId();
        result = result * 59 + (($personId == null) ? 0 : $personId.hashCode());
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
        Object $personAppearTime = getPersonAppearTime();
        result = result * 59 + (($personAppearTime == null) ? 0 : $personAppearTime.hashCode());
        Object $personDisAppearTime = getPersonDisAppearTime();
        result = result * 59 + (($personDisAppearTime == null) ? 0 : $personDisAppearTime.hashCode());
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
        Object $heightUpLimit = getHeightUpLimit();
        result = result * 59 + (($heightUpLimit == null) ? 0 : $heightUpLimit.hashCode());
        Object $heightLowerLimit = getHeightLowerLimit();
        result = result * 59 + (($heightLowerLimit == null) ? 0 : $heightLowerLimit.hashCode());
        Object $bodyType = getBodyType();
        result = result * 59 + (($bodyType == null) ? 0 : $bodyType.hashCode());
        Object $skinColor = getSkinColor();
        result = result * 59 + (($skinColor == null) ? 0 : $skinColor.hashCode());
        Object $hairStyle = getHairStyle();
        result = result * 59 + (($hairStyle == null) ? 0 : $hairStyle.hashCode());
        Object $hairColor = getHairColor();
        result = result * 59 + (($hairColor == null) ? 0 : $hairColor.hashCode());
        Object $gesture = getGesture();
        result = result * 59 + (($gesture == null) ? 0 : $gesture.hashCode());
        Object $status = getStatus();
        result = result * 59 + (($status == null) ? 0 : $status.hashCode());
        Object $faceStyle = getFaceStyle();
        result = result * 59 + (($faceStyle == null) ? 0 : $faceStyle.hashCode());
        Object $facialFeature = getFacialFeature();
        result = result * 59 + (($facialFeature == null) ? 0 : $facialFeature.hashCode());
        Object $physicalFeature = getPhysicalFeature();
        result = result * 59 + (($physicalFeature == null) ? 0 : $physicalFeature.hashCode());
        Object $bodyFeature = getBodyFeature();
        result = result * 59 + (($bodyFeature == null) ? 0 : $bodyFeature.hashCode());
        Object $habitualMovement = getHabitualMovement();
        result = result * 59 + (($habitualMovement == null) ? 0 : $habitualMovement.hashCode());
        Object $behavior = getBehavior();
        result = result * 59 + (($behavior == null) ? 0 : $behavior.hashCode());
        Object $behaviorDescription = getBehaviorDescription();
        result = result * 59 + (($behaviorDescription == null) ? 0 : $behaviorDescription.hashCode());
        Object $appendant = getAppendant();
        result = result * 59 + (($appendant == null) ? 0 : $appendant.hashCode());
        Object $appendantDescription = getAppendantDescription();
        result = result * 59 + (($appendantDescription == null) ? 0 : $appendantDescription.hashCode());
        Object $umbrellaColor = getUmbrellaColor();
        result = result * 59 + (($umbrellaColor == null) ? 0 : $umbrellaColor.hashCode());
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
        Object $bagStyle = getBagStyle();
        result = result * 59 + (($bagStyle == null) ? 0 : $bagStyle.hashCode());
        Object $bagColor = getBagColor();
        result = result * 59 + (($bagColor == null) ? 0 : $bagColor.hashCode());
        Object $coatStyle = getCoatStyle();
        result = result * 59 + (($coatStyle == null) ? 0 : $coatStyle.hashCode());
        Object $coatLength = getCoatLength();
        result = result * 59 + (($coatLength == null) ? 0 : $coatLength.hashCode());
        Object $coatColor = getCoatColor();
        result = result * 59 + (($coatColor == null) ? 0 : $coatColor.hashCode());
        Object $trousersStyle = getTrousersStyle();
        result = result * 59 + (($trousersStyle == null) ? 0 : $trousersStyle.hashCode());
        Object $trousersColor = getTrousersColor();
        result = result * 59 + (($trousersColor == null) ? 0 : $trousersColor.hashCode());
        Object $trousersLen = getTrousersLen();
        result = result * 59 + (($trousersLen == null) ? 0 : $trousersLen.hashCode());
        Object $shoesStyle = getShoesStyle();
        result = result * 59 + (($shoesStyle == null) ? 0 : $shoesStyle.hashCode());
        Object $shoesColor = getShoesColor();
        result = result * 59 + (($shoesColor == null) ? 0 : $shoesColor.hashCode());
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
        Object $subImageList = getSubImageList();
        return result * 59 + (($subImageList == null) ? 0 : $subImageList.hashCode());
    }

    public String toString() {
        return "Person(personId=" + getPersonId() + ", infoKind=" + getInfoKind() + ", sourceId=" + getSourceId() + ", deviceId=" + getDeviceId() + ", leftTopX=" + getLeftTopX() + ", leftTopY=" + getLeftTopY() + ", rightBtmX=" + getRightBtmX() + ", rightBtmY=" + getRightBtmY() + ", locationMarkTime=" + getLocationMarkTime() + ", personAppearTime=" + getPersonAppearTime() + ", personDisAppearTime=" + getPersonDisAppearTime() + ", idType=" + getIdType() + ", idNumber=" + getIdNumber() + ", name=" + getName() + ", usedName=" + getUsedName() + ", alias=" + getAlias() + ", genderCode=" + getGenderCode() + ", ageUpLimit=" + getAgeUpLimit() + ", ageLowerLimit=" + getAgeLowerLimit() + ", ethicCode=" + getEthicCode() + ", nationalityCode=" + getNationalityCode() + ", nativeCityCode=" + getNativeCityCode() + ", residenceAdminDivision=" + getResidenceAdminDivision() + ", chineseAccentCod=" + getChineseAccentCod() + ", jobCategory=" + getJobCategory() + ", accompanyNumber=" + getAccompanyNumber() + ", heightUpLimit=" + getHeightUpLimit() + ", heightLowerLimit=" + getHeightLowerLimit() + ", bodyType=" + getBodyType() + ", skinColor=" + getSkinColor() + ", hairStyle=" + getHairStyle() + ", hairColor=" + getHairColor() + ", gesture=" + getGesture() + ", status=" + getStatus() + ", faceStyle=" + getFaceStyle() + ", facialFeature=" + getFacialFeature() + ", physicalFeature=" + getPhysicalFeature() + ", bodyFeature=" + getBodyFeature() + ", habitualMovement=" + getHabitualMovement() + ", behavior=" + getBehavior() + ", behaviorDescription=" + getBehaviorDescription() + ", appendant=" + getAppendant() + ", appendantDescription=" + getAppendantDescription() + ", umbrellaColor=" + getUmbrellaColor() + ", respiratorColor=" + getRespiratorColor() + ", capStyle=" + getCapStyle() + ", capColor=" + getCapColor() + ", glassStyle=" + getGlassStyle() + ", glassColor=" + getGlassColor() + ", bagStyle=" + getBagStyle() + ", bagColor=" + getBagColor() + ", coatStyle=" + getCoatStyle() + ", coatLength=" + getCoatLength() + ", coatColor=" + getCoatColor() + ", trousersStyle=" + getTrousersStyle() + ", trousersColor=" + getTrousersColor() + ", trousersLen=" + getTrousersLen() + ", shoesStyle=" + getShoesStyle() + ", shoesColor=" + getShoesColor() + ", isDriver=" + getIsDriver() + ", isForeigner=" + getIsForeigner() + ", passportType=" + getPassportType() + ", immigrantTypeCode=" + getImmigrantTypeCode() + ", isSuspectedTerrorist=" + getIsSuspectedTerrorist() + ", suspectedTerroristNumber=" + getSuspectedTerroristNumber() + ", isCriminalInvolved=" + getIsCriminalInvolved() + ", criminalInvolvedSpecilisationCode=" + getCriminalInvolvedSpecilisationCode() + ", bodySpeciallMark=" + getBodySpeciallMark() + ", crimeMethod=" + getCrimeMethod() + ", crimeCharacterCode=" + getCrimeCharacterCode() + ", escapedCriminalNumber=" + getEscapedCriminalNumber() + ", isDetainees=" + getIsDetainees() + ", detentionHouseCode=" + getDetentionHouseCode() + ", detaineesIdentity=" + getDetaineesIdentity() + ", detaineesSpecialIdentity=" + getDetaineesSpecialIdentity() + ", memberTypeCode=" + getMemberTypeCode() + ", isVictim=" + getIsVictim() + ", victimType=" + getVictimType() + ", injuredDegree=" + getInjuredDegree() + ", corpseConditionCode=" + getCorpseConditionCode() + ", isSuspiciousPerson=" + getIsSuspiciousPerson() + ", subImageList=" + getSubImageList() + ")";
    }

    public String getPersonId() {
        return this.personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
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

    public String getPersonAppearTime() {
        return this.personAppearTime;
    }

    public void setPersonAppearTime(String personAppearTime) {
        this.personAppearTime = personAppearTime;
    }

    public String getPersonDisAppearTime() {
        return this.personDisAppearTime;
    }

    public void setPersonDisAppearTime(String personDisAppearTime) {
        this.personDisAppearTime = personDisAppearTime;
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

    public Integer getHeightUpLimit() {
        return this.heightUpLimit;
    }

    public void setHeightUpLimit(Integer heightUpLimit) {
        this.heightUpLimit = heightUpLimit;
    }

    public Integer getHeightLowerLimit() {
        return this.heightLowerLimit;
    }

    public void setHeightLowerLimit(Integer heightLowerLimit) {
        this.heightLowerLimit = heightLowerLimit;
    }

    public String getBodyType() {
        return this.bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
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

    public String getGesture() {
        return this.gesture;
    }

    public void setGesture(String gesture) {
        this.gesture = gesture;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getBodyFeature() {
        return this.bodyFeature;
    }

    public void setBodyFeature(String bodyFeature) {
        this.bodyFeature = bodyFeature;
    }

    public String getHabitualMovement() {
        return this.habitualMovement;
    }

    public void setHabitualMovement(String habitualMovement) {
        this.habitualMovement = habitualMovement;
    }

    public String getBehavior() {
        return this.behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public String getBehaviorDescription() {
        return this.behaviorDescription;
    }

    public void setBehaviorDescription(String behaviorDescription) {
        this.behaviorDescription = behaviorDescription;
    }

    public String getAppendant() {
        return this.appendant;
    }

    public void setAppendant(String appendant) {
        this.appendant = appendant;
    }

    public String getAppendantDescription() {
        return this.appendantDescription;
    }

    public void setAppendantDescription(String appendantDescription) {
        this.appendantDescription = appendantDescription;
    }

    public String getUmbrellaColor() {
        return this.umbrellaColor;
    }

    public void setUmbrellaColor(String umbrellaColor) {
        this.umbrellaColor = umbrellaColor;
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

    public String getBagStyle() {
        return this.bagStyle;
    }

    public void setBagStyle(String bagStyle) {
        this.bagStyle = bagStyle;
    }

    public String getBagColor() {
        return this.bagColor;
    }

    public void setBagColor(String bagColor) {
        this.bagColor = bagColor;
    }

    public String getCoatStyle() {
        return this.coatStyle;
    }

    public void setCoatStyle(String coatStyle) {
        this.coatStyle = coatStyle;
    }

    public String getCoatLength() {
        return this.coatLength;
    }

    public void setCoatLength(String coatLength) {
        this.coatLength = coatLength;
    }

    public String getCoatColor() {
        return this.coatColor;
    }

    public void setCoatColor(String coatColor) {
        this.coatColor = coatColor;
    }

    public String getTrousersStyle() {
        return this.trousersStyle;
    }

    public void setTrousersStyle(String trousersStyle) {
        this.trousersStyle = trousersStyle;
    }

    public String getTrousersColor() {
        return this.trousersColor;
    }

    public void setTrousersColor(String trousersColor) {
        this.trousersColor = trousersColor;
    }

    public String getTrousersLen() {
        return this.trousersLen;
    }

    public void setTrousersLen(String trousersLen) {
        this.trousersLen = trousersLen;
    }

    public String getShoesStyle() {
        return this.shoesStyle;
    }

    public void setShoesStyle(String shoesStyle) {
        this.shoesStyle = shoesStyle;
    }

    public String getShoesColor() {
        return this.shoesColor;
    }

    public void setShoesColor(String shoesColor) {
        this.shoesColor = shoesColor;
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

    public SubImageInfoObject getSubImageList() {
        return this.subImageList;
    }

    public void setSubImageList(SubImageInfoObject subImageList) {
        this.subImageList = subImageList;
    }
}
