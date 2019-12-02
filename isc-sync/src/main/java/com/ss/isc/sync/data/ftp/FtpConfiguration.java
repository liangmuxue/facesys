package com.ss.isc.sync.data.ftp;

import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnClass({GenericObjectPool.class, FTPClient.class})
@ConditionalOnProperty(value = {"ftp.enabled"}, havingValue = "true")
@EnableConfigurationProperties({FtpConfiguration.FtpConfigProperties.class})
public class FtpConfiguration {

    public static final Log log = LogFactory.getLog(FtpConfiguration.class);

    private ObjectPool<FTPClient> intranetPool;


    public FtpConfiguration(FtpConfigProperties props) {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(60000L);
        poolConfig.setSoftMinEvictableIdleTimeMillis(50000L);
        poolConfig.setTimeBetweenEvictionRunsMillis(30000L);
        this.intranetPool = new GenericObjectPool(new FtpClientPooledObjectFactory(props, false), poolConfig);

        preLoadingFtpClient(props.getInitialSize(), poolConfig.getMaxIdle());

        FtpUtil.init(this.intranetPool);
    }


    private void preLoadingFtpClient(Integer initialSize, int maxIdle) {
        if (initialSize == null || initialSize.intValue() <= 0) {
            return;
        }

        int size = Math.min(initialSize.intValue(), maxIdle);
        for (int i = 0; i < size; i++) {
            try {
                this.intranetPool.addObject();
            } catch (Exception e) {
                log.error("preLoadingFtpClient error...", e);
            }
        }
    }

    @PreDestroy
    public void destroy() {
        if (this.intranetPool != null) {
            this.intranetPool.close();
            log.info("销毁ftpClientPool...");
        }
    }


    @ConfigurationProperties(prefix = "ftp")
    static class FtpConfigProperties {

        private int bufferSize = 8096;
        private String encoding = "UTF-8";
        private String intranetHost;
        private String intranetUser;
        private String intranetPwd;
        private int intranetPort = 21;


        private boolean isActiveModel;

        private Integer initialSize = Integer.valueOf(0);


        public String getIntranetHost() {
            return this.intranetHost;
        }


        public void setIntranetHost(String intranetHost) {
            this.intranetHost = intranetHost;
        }


        public String getIntranetUser() {
            return this.intranetUser;
        }


        public void setIntranetUser(String intranetUser) {
            this.intranetUser = intranetUser;
        }


        public String getIntranetPwd() {
            return this.intranetPwd;
        }


        public void setIntranetPwd(String intranetPwd) {
            this.intranetPwd = intranetPwd;
        }


        public int getIntranetPort() {
            return this.intranetPort;
        }


        public void setIntranetPort(int intranetPort) {
            this.intranetPort = intranetPort;
        }


        public int getBufferSize() {
            return this.bufferSize;
        }


        public void setBufferSize(int bufferSize) {
            this.bufferSize = bufferSize;
        }


        public Integer getInitialSize() {
            return this.initialSize;
        }


        public void setInitialSize(Integer initialSize) {
            this.initialSize = initialSize;
        }


        public String getEncoding() {
            return this.encoding;
        }


        public void setEncoding(String encoding) {
            this.encoding = encoding;
        }


        public boolean isActiveModel() {
            return this.isActiveModel;
        }


        public void setActiveModel(boolean isActiveModel) {
            this.isActiveModel = isActiveModel;
        }

    }


    static class FtpClientPooledObjectFactory
            extends Object
            implements PooledObjectFactory<FTPClient> {

        private FtpConfigProperties props;

        private boolean isPrivate;


        public FtpClientPooledObjectFactory(FtpConfigProperties props, boolean isPrivate) {
            this.props = props;
            this.isPrivate = isPrivate;
        }


        public PooledObject<FTPClient> makeObject() throws Exception {
            FTPClient ftpClient = new FTPClient();
            String host = this.props.getIntranetHost();
            int port = this.props.getIntranetPort();
            String username = this.props.getIntranetUser();
            String password = this.props.getIntranetPwd();

            try {
                ftpClient.connect(host, port);
                ftpClient.login(username, password);


                ftpClient.setControlEncoding(this.props.getEncoding());
                ftpClient.setFileType(2);
                if (this.props.isActiveModel) {
                    ftpClient.enterLocalActiveMode();
                } else {
                    ftpClient.enterLocalActiveMode();
                }
                ftpClient.enterLocalPassiveMode();
                return new DefaultPooledObject(ftpClient);
            } catch (Exception e) {
                FtpConfiguration.log.error("建立FTP连接失败", e);
                if (ftpClient.isAvailable()) {
                    ftpClient.disconnect();
                }
                ftpClient = null;
                throw new Exception("建立FTP连接失败", e);
            }
        }


        public void destroyObject(PooledObject<FTPClient> p) throws Exception {
            FTPClient ftpClient = getObject(p);
            if (ftpClient != null && ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        }


        public boolean validateObject(PooledObject<FTPClient> p) {
            FTPClient ftpClient = getObject(p);
            if (ftpClient == null || !ftpClient.isConnected()) {
                return false;
            }
            try {
                ftpClient.changeWorkingDirectory("/");
                return true;
            } catch (Exception e) {

                return false;
            }
        }


        public void activateObject(PooledObject<FTPClient> p) throws Exception {
        }


        public void passivateObject(PooledObject<FTPClient> p) throws Exception {
        }


        private FTPClient getObject(PooledObject<FTPClient> p) {
            if (p == null || p.getObject() == null) {
                return null;
            }
            return (FTPClient) p.getObject();
        }

    }

}
