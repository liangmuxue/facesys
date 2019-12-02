package com.ss.isc.sync.data.service;

import com.ss.isc.data.resource.common.model.Region;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ISyncService {

    int synchroThirdRegion(List<String> paramList, String paramString) throws IOException;

    int synchroStreet(List<String> paramList, String paramString) throws IOException;

    int synchroVillage(List<String> paramList, String paramString) throws IOException;

    int synchroDoorinfo(List<String> paramList, String paramString) throws IOException;

    int synchroHouse(List<String> paramList, String paramString) throws IOException;

    int synchroDoorFlow(List<String> paramList, String paramString) throws IOException;

    int synchroResident(List<String> paramList, String paramString1, String paramString2, String paramString3) throws IOException;

    int synchroDevice(List<String> paramList, String paramString, Map<String, Region> paramMap) throws IOException, Exception;

    int synchroVehicle(List<String> paramList, String paramString) throws IOException;

}
