package com.example.didi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DidiApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("账务哈希：");
		System.out.println(Objects.hash("288395888377380867")%1024);
	}

}
