package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/***
 *
 * Swagger配置类
 *
 * @author wengym
 *
 * @date 2022/5/13 10:23
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enabled}")
    private Boolean swaggerEnabled;

    /***
     *
     * 配置Swagger的Docket的bean实例
     *
     * @author wengym
     *
     * @date 2022/5/13 10:24
     *
     * @return springfox.documentation.spring.web.plugins.Docket
     *
     */
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //enable是否启动swagger,如果为False,则swagger不能在浏觉器中访间
                .enable(swaggerEnabled)
                .select()
                //配置扫描接口的方式
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                /*.paths(PathSelectors.ant("/wang/**"))*/
                .build();
    }

    /***
     *
     * 配置swagger信息=apiInfo
     *
     * @author wengym
     *
     * @date 2022/5/13 10:25
     *
     * @return springfox.documentation.service.ApiInfo
     *
     */
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("wengym", "http://localhost:11000/swagger-ui.html", "123@126.com");
        return new ApiInfo(
                "Api文档标题",
                "Api文档描述",
                "v1.0",
                "urn:tos", contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}

