package xyz.guqing.travelpath;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("xyz.guqing.travelpath.mapper")
@EnableCaching
public class TravelPathApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelPathApplication.class, args);
	}

}
