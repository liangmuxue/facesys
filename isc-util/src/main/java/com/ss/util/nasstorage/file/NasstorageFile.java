package com.ss.util.nasstorage.file;


public class NasstorageFile {

    private String storageAbsolutePath;
    private String storageRelativePath;

    public String getStorageAbsolutePath() {
        return this.storageAbsolutePath;
    }


    public void setStorageAbsolutePath(String storageAbsolutePath) {
        this.storageAbsolutePath = storageAbsolutePath;
    }


    public String getStorageRelativePath() {
        return this.storageRelativePath;
    }


    public void setStorageRelativePath(String storageRelativePath) {
        this.storageRelativePath = storageRelativePath;
    }


    public NasstorageFile(String storageAbsolutePath, String storageRelativePath) {
        this.storageAbsolutePath = storageAbsolutePath;
        this.storageRelativePath = storageRelativePath;
    }

}
