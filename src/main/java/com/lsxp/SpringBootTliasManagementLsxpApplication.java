package com.lsxp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lsxp.mapper")
public class SpringBootTliasManagementLsxpApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTliasManagementLsxpApplication.class, args);
	}

}
