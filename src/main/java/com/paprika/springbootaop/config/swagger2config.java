package com.paprika.springbootaop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author adam
 * @date 2019/10/25
 */
@Configuration
@EnableSwagger2
public class swagger2config {
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("springboot aop demo")
                .description("springboot aop demo")
                .version("1.0.0")
                .build();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.paprika.springbootaop.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
