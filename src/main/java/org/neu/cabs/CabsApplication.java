package org.neu.cabs;

import lombok.extern.log4j.Log4j;
import org.neu.cabs.config.ApplicationStartup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * 程序启动器
 * @author 李浩然
 */
@SpringBootApplication
@Log4j
public class CabsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CabsApplication.class, args);
	}
}
