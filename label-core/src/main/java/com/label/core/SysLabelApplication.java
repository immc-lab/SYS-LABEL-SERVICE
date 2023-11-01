package com.label.core;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.label.common","com.label.core"})
public class SysLabelApplication {
    public static void main(String[] args) {
        System.out.println("hahah");
        SpringApplication.run(SysLabelApplication.class, args);
    }

}
