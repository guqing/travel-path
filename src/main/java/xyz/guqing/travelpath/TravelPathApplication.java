package xyz.guqing.travelpath;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("xyz.guqing.travelpath.mapper")
public class TravelPathApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelPathApplication.class, args);
	}

}
