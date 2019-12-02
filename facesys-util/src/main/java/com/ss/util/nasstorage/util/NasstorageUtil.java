package com.ss.util.nasstorage.util;

import com.ss.util.nasstorage.config.properties.NasstorageProperties;
import com.ss.util.nasstorage.file.NasstorageFile;
import com.ss.util.nasstorage.file.NasstorageFileBuilder;
import com.ss.util.nasstorage.node.NasstorageNode;
import com.ss.util.nasstorage.node.NasstorageNodeChoice;
import com.ss.util.nasstorage.process.NasstorageCamera;
import com.ss.util.nasstorage.process.NasstorageCheckterminal;
import com.ss.util.nasstorage.process.NasstorageDeviceTerminal;
import com.ss.util.nasstorage.process.NasstorageFacegroup;
import com.ss.util.nasstorage.process.NasstorageFavorite;
import com.ss.util.nasstorage.process.NasstorageGuard;
import com.ss.util.nasstorage.process.NasstorageLionanalysis;
import com.ss.util.nasstorage.process.NasstorageOem;
import com.ss.util.nasstorage.process.NasstorageRecog;
import com.ss.util.nasstorage.process.NasstorageResident;
import com.ss.util.nasstorage.process.NasstorageUser;
import com.ss.util.nasstorage.process.NasstorageVehicle;
import com.ss.util.nasstorage.process.NasstorageVictory;
import com.ss.util.nasstorage.process.NasstorageWarning;


public class NasstorageUtil {

    public NasstorageFile getNasstorageFile(NasstorageProperties properties, String picType, String deviceNo, String filename, Long captureTime) {
        NasstorageNode node = (new NasstorageNodeChoice()).getNasstorageNode(properties);

        return (new NasstorageFileBuilder()).nasstorageCameraFile(node, picType, deviceNo, filename, captureTime);
    }


    public NasstorageFile nasstorageCamera(NasstorageProperties properties, Integer imgType, String deviceNo, String imageData, String imageUrl, Long captureTime) {
        return (new NasstorageCamera()).nasstorageCameraCaptureImg(properties, imgType, deviceNo, imageData, imageUrl, captureTime);
    }


    public NasstorageFile nasstorageVehicle(NasstorageProperties properties, Integer imgType, String deviceNo, String imageData, String imageUrl, Long captureTime) {
        return (new NasstorageVehicle()).nasstorageVehicleCaptureImg(properties, imgType, deviceNo, imageData, imageUrl, captureTime);
    }


    public NasstorageFile nasstorageCheckterminal(NasstorageProperties properties, Integer imgType, String deviceNo, String imageData, String imageUrl, Long captureTime) {
        return (new NasstorageCheckterminal()).nasstorageCheckterminalImg(properties, imgType, deviceNo, imageData, imageUrl, captureTime);
    }


    public NasstorageFile nasstorageFacegroup(NasstorageProperties properties, String facegroupId, String imageData, String imageUrl) {
        return (new NasstorageFacegroup()).nasstorageFacegroupImg(properties, facegroupId, imageData, imageUrl);
    }


    public NasstorageFile nasstorageWarning(NasstorageProperties properties, String imageData, String imageUrl, Long warningTime) {
        return (new NasstorageWarning()).nasstorageWarningImg(properties, imageData, imageUrl, warningTime);
    }


    public NasstorageFile nasstorageRecog(NasstorageProperties properties, String imageData, String imageUrl, Long recogTime) {
        return (new NasstorageRecog()).nasstorageRecogImg(properties, imageData, imageUrl, recogTime);
    }


    public NasstorageFile nasstorageVictory(NasstorageProperties properties, String imageData, String imageUrl, Long victoryTime) {
        return (new NasstorageVictory()).nasstorageVictoryImg(properties, imageData, imageUrl, victoryTime);
    }


    public NasstorageFile nasstorageFavorite(NasstorageProperties properties, String imageData, String imageUrl, Long favoriteTime) {
        return (new NasstorageFavorite()).nasstorageFavoriteImg(properties, imageData, imageUrl, favoriteTime);
    }


    public NasstorageFile nasstorageGuard(NasstorageProperties properties, String buildingCode, String imageData, String imageUrl, Long captureTime) {
        return (new NasstorageGuard()).nasstorageGuardCaptureImg(properties, buildingCode, imageData, imageUrl, captureTime);
    }


    public NasstorageFile nasstorageLionanalysis(NasstorageProperties properties, String imageData, String imageUrl, Long analysisTime) {
        return (new NasstorageLionanalysis()).nasstorageLionanalysisImg(properties, imageData, imageUrl, analysisTime);
    }


    public NasstorageFile nasstorageResident(NasstorageProperties properties, String buildingCode, String imageData, String imageUrl) {
        return (new NasstorageResident()).nasstorageResidentImg(properties, buildingCode, imageData, imageUrl);
    }


    public NasstorageFile nasstorageUser(NasstorageProperties properties, String imageData, String imageUrl) {
        return (new NasstorageUser()).nasstorageUserImg(properties, imageData, imageUrl);
    }


    public NasstorageFile nasstorageOem(NasstorageProperties properties, String imageData, String imageUrl, Long oemTime) {
        return (new NasstorageOem()).nasstorageOemImg(properties, imageData, imageUrl, oemTime);
    }


    public NasstorageFile nasstorageDeviceTerminal(NasstorageProperties properties, Integer imgType, String deviceNo, String imageData, String imageUrl, Long captureTime) {
        return (new NasstorageDeviceTerminal()).nasstorageDeviceTerminalImg(properties, imgType, deviceNo, imageData, imageUrl, captureTime);
    }

}
