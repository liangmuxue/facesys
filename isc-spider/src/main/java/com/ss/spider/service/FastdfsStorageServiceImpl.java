package com.ss.spider.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.ss.service.FileStorageService;
import com.ss.tools.FileTypeUtils;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;


@Component("fastdfsStorageService")
public class FastdfsStorageServiceImpl implements FileStorageService {

    @Autowired
    private FastFileStorageClient storageClient;

    @Override
    public String storage(byte[] content) {
        String suffix = FileTypeUtils.getType(content);
        ByteArrayInputStream stream = new ByteArrayInputStream(content);
        StorePath storePath = this.storageClient.uploadFile(stream, content.length, suffix, null);
        return storePath.getFullPath();
    }


    @Override
    public String storage(String base64) {
        return storage(Base64Utils.decodeFromString(base64));
    }

    @Override
    public void delete(String path) {
        StorePath storePath = StorePath.praseFromUrl(path);
        this.storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
    }

}
