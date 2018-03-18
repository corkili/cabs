package org.neu.cabs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.text.SimpleDateFormat;

/**
 * Spring MVC 配置类
 * @author 李浩然
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Bean
    public SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    @Bean
    public SimpleDateFormat timeFormat() {
        return new SimpleDateFormat("HH:mm");
    }

    @Bean SimpleDateFormat datetimeFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }
}
