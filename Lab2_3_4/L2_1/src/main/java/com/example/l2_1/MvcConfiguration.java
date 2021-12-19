package com.example.l2_1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.xslt.XsltViewResolver;

@Configuration
@ComponentScan(basePackages = "com.example.l2_1")
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer {

    @Bean
    public XsltViewResolver xsltViewResolver(){
        XsltViewResolver resolver = new XsltViewResolver();
        resolver.setPrefix("classpath:/xslt/");
        resolver.setSuffix(".xslt");
        return resolver;
    }
}
