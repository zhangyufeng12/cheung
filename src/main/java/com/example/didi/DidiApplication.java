package com.example.didi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = "com.example.didi.domain.mapper")
@EnableCaching
public class DidiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DidiApplication.class, args);
	}
}
