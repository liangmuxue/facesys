package com.ss.service;

public interface FileStorageService {

    String storage(byte[] paramArrayOfByte);

    String storage(String paramString);

    void delete(String paramString);

}
