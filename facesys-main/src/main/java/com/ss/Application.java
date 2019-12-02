package com.ss;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableEncryptableProperties
public class Application {

    public static void main(String[] args) {
        SpringApplication sa = new SpringApplication(Application.class);
        sa.setBannerMode(Banner.Mode.OFF);
        sa.run(args);
    }

}
