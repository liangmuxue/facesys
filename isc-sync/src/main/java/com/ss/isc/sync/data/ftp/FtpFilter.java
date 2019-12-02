package com.ss.isc.sync.data.ftp;

import java.util.regex.Pattern;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;


public class FtpFilter implements FTPFileFilter {

    private Pattern pattern;

    public FtpFilter(String regex) {
        this.pattern = Pattern.compile(regex);
    }


    public boolean accept(FTPFile file) {
        return this.pattern.matcher(file.getName()).matches();
    }

}
