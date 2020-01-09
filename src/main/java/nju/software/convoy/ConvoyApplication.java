package nju.software.convoy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("nju.software.convoy.data.dao")
public class ConvoyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConvoyApplication.class, args);
    }

}
