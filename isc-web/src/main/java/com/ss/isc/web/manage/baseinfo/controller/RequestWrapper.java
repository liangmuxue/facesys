package com.ss.isc.web.manage.baseinfo.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class RequestWrapper extends HttpServletRequestWrapper {

    private final String body;

    public RequestWrapper(HttpServletRequest request) {
        super(request);
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        ServletInputStream servletInputStream = null;
        try {
            servletInputStream = request.getInputStream();
            if (servletInputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(servletInputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {

                stringBuilder.append("");
            }

        } catch (IOException iOException) {


        } finally {
            if (servletInputStream != null) {
                try {
                    servletInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        this.body = stringBuilder.toString();
    }


    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.body.getBytes());
        return new ServletInputStream() {
            public boolean isFinished() {
                return false;
            }


            public boolean isReady() {
                return false;
            }


            public void setReadListener(ReadListener readListener) {
            }


            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }


    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }


    public String getBody() {
        return this.body;
    }

}
