package com.lizhi.base.config;/**
 * @program: upp
 * @name Swagger2
 * @description: Swagger2生成接口文档，配置文件
 * @author: lizhi
 * @create: 2020-06-03 16:43
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *@program: upp
 *@name Swagger2
 *@description: Swagger2生成接口文档，配置文件
 *@author: lizhi
 *@create: 2020-06-03 16:43
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    /*swagger2*/
    public static final String SWAGGER_ENABLE = "${swagger.enable}";

    public static final String SWAGGER_PORT = "${server.port}";

    public static final String SWAGGER_URL = "https://localhost:";

    public static final String SWAGGER_VERSION = "1.0.0";


    @Value(SWAGGER_ENABLE)
    private boolean enable;

    @Value(SWAGGER_PORT)
    private Integer port;

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lizhi"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     *功能描述 请求地址(http://localhost:9085/swagger-ui.html)
     * @author lizhi
     * @date 2021/3/3
     * @param
     * @return springfox.documentation.service.ApiInfo
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("盆地肌康复治疗仪控制系统api文档")
                .description("简单优雅的restful风格")
                .termsOfServiceUrl(SWAGGER_URL + port)
                .contact(new Contact("li zhi", "", ""))
                .version(SWAGGER_VERSION)
                .build();
    }
}
