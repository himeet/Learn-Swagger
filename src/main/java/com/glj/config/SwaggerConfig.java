package com.glj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 配置Swagger的Docket的bean实例
     * @return
     */
    @Bean
    public Docket docket(Environment env) {

        // 设置哪些环境需要显示Swagger
        Profiles profiles = Profiles.of("dev", "test");

        // 通过env.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = env.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                /** 1.修改Swagger界面的显示信息 */
                .apiInfo(apiInfo())

                /** 2.修改Swagger界面的组信息（右上角） */
                .groupName("团队0")

                /** 3.通过flag标志来是实现开发环境启用Swagger，生产环境关闭Swagger的效果 */
                .enable(flag)  // 是否启用Swagger，若设置为false，则不能在浏览器中访问Swagger

                .select()

                /** 4.指定扫描的package */
                .apis(RequestHandlerSelectors.basePackage("com.glj.controller"))  // RequestHandlerSelectors控制要扫描的接口

                /**5.指定扫描的方法注解（仅对含有指定注解的方法生成Swagger） */
                .apis(RequestHandlerSelectors.withMethodAnnotation(RequestMapping.class))  // RequestHandlerSelectors控制要扫描的接口

                /** 6.指定扫描的类注解（仅对含有指定注解的类生成Swagger） */
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))  // RequestHandlerSelectors控制要扫描的接口

                /** 7.指定扫描满足条件的API ant()内书写正则表达式 */
                .paths(PathSelectors.ant("/hello/**"))
                .build();
    }

    /**
     * 添加一个Docket的bean
     * @return
     */
    @Bean
    public Docket docket1() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("团队1");
    }

    /**
     * 添加一个Docket的bean
     * @return
     */
    @Bean
    public Docket docket2() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("团队2");
    }

    /**
     * 添加一个Docket的bean
     * @return
     */
    @Bean
    public Docket docket3() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("团队3");
    }

    /**
     * 修改Swagger ui界面的显示信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfo("小高的Swagger API文档",
                "这是关于API文档的描述",
                "1.0",
                "http://putong.xyz",
                new Contact("glj", "http://putong.xyz", "himeet@163.com"),  // 作者信息
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());
    }

}
