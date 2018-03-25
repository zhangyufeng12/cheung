package com.example.didi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
/*
	扫描mapper
*/
@MapperScan(basePackages = "com.example.didi.domain.mapper")

/*
* 	简单的缓存
* */
@EnableCaching
public class DidiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DidiApplication.class, args);
	}
}
