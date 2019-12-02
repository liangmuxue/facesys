package com.ss.util.nasstorage.file;

import com.ss.util.nasstorage.node.NasstorageNode;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class NasstorageFileBuilder {

    public static final String FILE_SEPARATOR = System.getProperty("file.separator");


    public NasstorageFile nasstorageCameraFile(NasstorageNode node, String picType, String deviceNo, String filename, Long captureTime) {
        StringBuilder absolutePath = new StringBuilder(node.getMountPath());
        StringBuilder relativePath = new StringBuilder();
        Calendar date = null;

        if (null == captureTime) {
            date = Calendar.getInstance();
        } else {
            date = Calendar.getInstance();
            date.setTime(new Date(captureTime.longValue()));
        }
        int year = date.get(1);
        int month = date.get(2) + 1;
        int day = date.get(5);


        try {
            absolutePath = absolutePath.append(picType).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(day).append(FILE_SEPARATOR).append(deviceNo).append(FILE_SEPARATOR).append(filename);


            relativePath = relativePath.append(FILE_SEPARATOR).append(node.getServerDeputy()).append(FILE_SEPARATOR).append(node.getMountDeputy()).append(FILE_SEPARATOR).append(picType).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(day).append(FILE_SEPARATOR).append(deviceNo).append(FILE_SEPARATOR).append(filename);

            return new NasstorageFile(absolutePath.toString(), relativePath.toString());
        } catch (Exception exp) {
            return null;
        }
    }


    public NasstorageFile nasstorageVehicleCaptureFile(NasstorageNode node, String picType, String deviceNo, String filename, Long captureTime) {
        StringBuilder absolutePath = new StringBuilder(node.getMountPath());
        StringBuilder relativePath = new StringBuilder();
        Calendar date = null;

        if (null == captureTime) {
            date = Calendar.getInstance();
        } else {
            date = Calendar.getInstance();
            date.setTime(new Date(captureTime.longValue()));
        }
        int year = date.get(1);
        int month = date.get(2) + 1;
        int day = date.get(5);


        try {
            absolutePath = absolutePath.append(picType).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(day).append(FILE_SEPARATOR).append(deviceNo).append(FILE_SEPARATOR).append(filename);


            relativePath = relativePath.append(FILE_SEPARATOR).append(node.getServerDeputy()).append(FILE_SEPARATOR).append(node.getMountDeputy()).append(FILE_SEPARATOR).append(picType).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(day).append(FILE_SEPARATOR).append(deviceNo).append(FILE_SEPARATOR).append(filename);

            return new NasstorageFile(absolutePath.toString(), relativePath.toString());
        } catch (Exception exp) {
            return null;
        }
    }


    public NasstorageFile nasstorageCheckterminalFile(NasstorageNode node, String picType, String deviceNo, String filename, Long captureTime) {
        StringBuilder absolutePath = new StringBuilder(node.getMountPath());
        StringBuilder relativePath = new StringBuilder();
        Calendar date = null;

        if (null == captureTime) {
            date = Calendar.getInstance();
        } else {
            date = Calendar.getInstance();
            date.setTime(new Date(captureTime.longValue()));
        }
        int year = date.get(1);
        int month = date.get(2) + 1;
        int day = date.get(5);


        try {
            absolutePath = absolutePath.append(picType).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(day).append(FILE_SEPARATOR).append(deviceNo).append(FILE_SEPARATOR).append(picType + filename);


            relativePath = relativePath.append(FILE_SEPARATOR).append(node.getServerDeputy()).append(FILE_SEPARATOR).append(node.getMountDeputy()).append(FILE_SEPARATOR).append(picType).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(day).append(FILE_SEPARATOR).append(deviceNo).append(FILE_SEPARATOR).append(picType + filename);

            return new NasstorageFile(absolutePath.toString(), relativePath.toString());
        } catch (Exception exp) {
            return null;
        }
    }


    public NasstorageFile nasstorageFacegroupFile(NasstorageNode node, String rootDir, String facegroupId, String filename, Integer faceGroupSubDirCount) {
        StringBuilder absolutePath = new StringBuilder(node.getMountPath());
        StringBuilder relativePath = new StringBuilder();
        int random = (new Random()).nextInt(faceGroupSubDirCount.intValue());


        try {
            absolutePath = absolutePath.append(rootDir).append(FILE_SEPARATOR).append(facegroupId).append(FILE_SEPARATOR).append(random).append(FILE_SEPARATOR).append(filename);


            relativePath = relativePath.append(FILE_SEPARATOR).append(node.getServerDeputy()).append(FILE_SEPARATOR).append(node.getMountDeputy()).append(FILE_SEPARATOR).append(rootDir).append(FILE_SEPARATOR).append(facegroupId).append(FILE_SEPARATOR).append(random).append(FILE_SEPARATOR).append(filename);

            return new NasstorageFile(absolutePath.toString(), relativePath.toString());
        } catch (Exception exp) {
            return null;
        }
    }


    public NasstorageFile nasstorageWarningFile(NasstorageNode node, String rootDir, String filename, Long warningTime) {
        StringBuilder absolutePath = new StringBuilder(node.getMountPath());
        StringBuilder relativePath = new StringBuilder();
        Calendar date = null;

        if (null == warningTime) {
            date = Calendar.getInstance();
        } else {
            date = Calendar.getInstance();
            date.setTime(new Date(warningTime.longValue()));
        }
        int year = date.get(1);
        int month = date.get(2) + 1;
        int day = date.get(5);


        try {
            absolutePath = absolutePath.append(rootDir).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(day).append(FILE_SEPARATOR).append(filename);


            relativePath = relativePath.append(FILE_SEPARATOR).append(node.getServerDeputy()).append(FILE_SEPARATOR).append(node.getMountDeputy()).append(FILE_SEPARATOR).append(rootDir).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(day).append(FILE_SEPARATOR).append(filename);

            return new NasstorageFile(absolutePath.toString(), relativePath.toString());
        } catch (Exception exp) {
            return null;
        }
    }


    public NasstorageFile nasstorageRecogFile(NasstorageNode node, String rootDir, String filename, Long recogTime) {
        StringBuilder absolutePath = new StringBuilder(node.getMountPath());
        StringBuilder relativePath = new StringBuilder();
        Calendar date = null;

        if (null == recogTime) {
            date = Calendar.getInstance();
        } else {
            date = Calendar.getInstance();
            date.setTime(new Date(recogTime.longValue()));
        }
        int year = date.get(1);
        int month = date.get(2) + 1;
        int day = date.get(5);


        try {
            absolutePath = absolutePath.append(rootDir).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(day).append(FILE_SEPARATOR).append(filename);


            relativePath = relativePath.append(FILE_SEPARATOR).append(node.getServerDeputy()).append(FILE_SEPARATOR).append(node.getMountDeputy()).append(FILE_SEPARATOR).append(rootDir).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(day).append(FILE_SEPARATOR).append(filename);

            return new NasstorageFile(absolutePath.toString(), relativePath.toString());
        } catch (Exception exp) {
            return null;
        }
    }


    public NasstorageFile nasstorageVictoryFile(NasstorageNode node, String rootDir, String filename, Long createTime) {
        StringBuilder absolutePath = new StringBuilder(node.getMountPath());
        StringBuilder relativePath = new StringBuilder();
        Calendar date = null;

        if (null == createTime) {
            date = Calendar.getInstance();
        } else {
            date = Calendar.getInstance();
            date.setTime(new Date(createTime.longValue()));
        }
        int year = date.get(1);
        int month = date.get(2) + 1;
        int day = date.get(5);


        try {
            absolutePath = absolutePath.append(rootDir).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(day).append(FILE_SEPARATOR).append(filename);


            relativePath = relativePath.append(FILE_SEPARATOR).append(node.getServerDeputy()).append(FILE_SEPARATOR).append(node.getMountDeputy()).append(FILE_SEPARATOR).append(rootDir).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(day).append(FILE_SEPARATOR).append(filename);

            return new NasstorageFile(absolutePath.toString(), relativePath.toString());
        } catch (Exception exp) {
            return null;
        }
    }


    public NasstorageFile nasstorageFavoriteFile(NasstorageNode node, String rootDir, String filename, Long createTime) {
        StringBuilder absolutePath = new StringBuilder(node.getMountPath());
        StringBuilder relativePath = new StringBuilder();
        Calendar date = null;

        if (null == createTime) {
            date = Calendar.getInstance();
        } else {
            date = Calendar.getInstance();
            date.setTime(new Date(createTime.longValue()));
        }
        int year = date.get(1);
        int month = date.get(2) + 1;
        int day = date.get(5);


        try {
            absolutePath = absolutePath.append(rootDir).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(day).append(FILE_SEPARATOR).append(filename);


            relativePath = relativePath.append(FILE_SEPARATOR).append(node.getServerDeputy()).append(FILE_SEPARATOR).append(node.getMountDeputy()).append(FILE_SEPARATOR).append(rootDir).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(day).append(FILE_SEPARATOR).append(filename);

            return new NasstorageFile(absolutePath.toString(), relativePath.toString());
        } catch (Exception exp) {
            return null;
        }
    }


    public NasstorageFile nasstorageGuardFile(NasstorageNode node, String rootDir, String buildingCode, String filename, Long captureTime) {
        StringBuilder absolutePath = new StringBuilder(node.getMountPath());
        StringBuilder relativePath = new StringBuilder();
        Calendar date = null;

        if (null == captureTime) {
            date = Calendar.getInstance();
        } else {
            date = Calendar.getInstance();
            date.setTime(new Date(captureTime.longValue()));
        }
        int year = date.get(1);
        int month = date.get(2) + 1;
        int day = date.get(5);


        try {
            absolutePath = absolutePath.append(rootDir).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(day).append(FILE_SEPARATOR).append(buildingCode).append(FILE_SEPARATOR).append(filename);


            relativePath = relativePath.append(FILE_SEPARATOR).append(node.getServerDeputy()).append(FILE_SEPARATOR).append(node.getMountDeputy()).append(FILE_SEPARATOR).append(rootDir).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(day).append(FILE_SEPARATOR).append(buildingCode).append(FILE_SEPARATOR).append(filename);

            return new NasstorageFile(absolutePath.toString(), relativePath.toString());
        } catch (Exception exp) {
            return null;
        }
    }


    public NasstorageFile nasstorageResidentFile(NasstorageNode node, String rootDir, String buildingCode, String filename) {
        StringBuilder absolutePath = new StringBuilder(node.getMountPath());
        StringBuilder relativePath = new StringBuilder();


        try {
            absolutePath = absolutePath.append(rootDir).append(FILE_SEPARATOR).append(buildingCode).append(FILE_SEPARATOR).append(filename);


            relativePath = relativePath.append(FILE_SEPARATOR).append(node.getServerDeputy()).append(FILE_SEPARATOR).append(node.getMountDeputy()).append(FILE_SEPARATOR).append(rootDir).append(FILE_SEPARATOR).append(buildingCode).append(FILE_SEPARATOR).append(filename);

            return new NasstorageFile(absolutePath.toString(), relativePath.toString());
        } catch (Exception exp) {
            return null;
        }
    }


    public NasstorageFile nasstorageLionanalysisFile(NasstorageNode node, String rootDir, String filename, Long analysisTime) {
        StringBuilder absolutePath = new StringBuilder(node.getMountPath());
        StringBuilder relativePath = new StringBuilder();
        Calendar date = null;

        if (null == analysisTime) {
            date = Calendar.getInstance();
        } else {
            date = Calendar.getInstance();
            date.setTime(new Date(analysisTime.longValue()));
        }
        int year = date.get(1);
        int month = date.get(2) + 1;
        int day = date.get(5);


        try {
            absolutePath = absolutePath.append(rootDir).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(day).append(FILE_SEPARATOR).append(filename);


            relativePath = relativePath.append(FILE_SEPARATOR).append(node.getServerDeputy()).append(FILE_SEPARATOR).append(node.getMountDeputy()).append(FILE_SEPARATOR).append(rootDir).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(day).append(FILE_SEPARATOR).append(filename);

            return new NasstorageFile(absolutePath.toString(), relativePath.toString());
        } catch (Exception exp) {
            return null;
        }
    }


    public NasstorageFile nasstorageUserFile(NasstorageNode node, String rootDir, String filename) {
        StringBuilder absolutePath = new StringBuilder(node.getMountPath());
        StringBuilder relativePath = new StringBuilder();
        Calendar date = null;

        date = Calendar.getInstance();
        int year = date.get(1);
        int month = date.get(2) + 1;


        try {
            absolutePath = absolutePath.append(rootDir).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(filename);


            relativePath = relativePath.append(FILE_SEPARATOR).append(node.getServerDeputy()).append(FILE_SEPARATOR).append(node.getMountDeputy()).append(FILE_SEPARATOR).append(rootDir).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(filename);

            return new NasstorageFile(absolutePath.toString(), relativePath.toString());
        } catch (Exception exp) {
            return null;
        }
    }


    public NasstorageFile nasstorageOemFile(NasstorageNode node, String rootDir, String filename, Long warningTime) {
        StringBuilder absolutePath = new StringBuilder(node.getMountPath());
        StringBuilder relativePath = new StringBuilder();
        Calendar date = null;

        if (null == warningTime) {
            date = Calendar.getInstance();
        } else {
            date = Calendar.getInstance();
            date.setTime(new Date(warningTime.longValue()));
        }
        int year = date.get(1);
        int month = date.get(2) + 1;
        int day = date.get(5);


        try {
            absolutePath = absolutePath.append(rootDir).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(day).append(FILE_SEPARATOR).append(filename);


            relativePath = relativePath.append(FILE_SEPARATOR).append(node.getServerDeputy()).append(FILE_SEPARATOR).append(node.getMountDeputy()).append(FILE_SEPARATOR).append(rootDir).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(day).append(FILE_SEPARATOR).append(filename);

            return new NasstorageFile(absolutePath.toString(), relativePath.toString());
        } catch (Exception exp) {
            return null;
        }
    }


    public NasstorageFile nasstorageDeviceTerminalFile(NasstorageNode node, String picType, String deviceNo, String filename, Long captureTime) {
        StringBuilder absolutePath = new StringBuilder(node.getMountPath());
        StringBuilder relativePath = new StringBuilder();
        Calendar date = null;

        if (null == captureTime) {
            date = Calendar.getInstance();
        } else {
            date = Calendar.getInstance();
            date.setTime(new Date(captureTime.longValue()));
        }
        int year = date.get(1);
        int month = date.get(2) + 1;
        int day = date.get(5);


        try {
            absolutePath = absolutePath.append(picType).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(day).append(FILE_SEPARATOR).append(deviceNo).append(FILE_SEPARATOR).append(filename);


            relativePath = relativePath.append(FILE_SEPARATOR).append(node.getServerDeputy()).append(FILE_SEPARATOR).append(node.getMountDeputy()).append(FILE_SEPARATOR).append(picType).append(FILE_SEPARATOR).append(year).append(FILE_SEPARATOR).append(month).append(FILE_SEPARATOR).append(day).append(FILE_SEPARATOR).append(deviceNo).append(FILE_SEPARATOR).append(filename);

            return new NasstorageFile(absolutePath.toString(), relativePath.toString());
        } catch (Exception exp) {
            return null;
        }
    }

}
