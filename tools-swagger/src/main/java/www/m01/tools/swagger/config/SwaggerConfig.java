package www.m01.tools.swagger.config;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * SwaggerConfig
 *
 * @author: hyr
 * @date: 2022-07-12
 **/
@Configuration
public class SwaggerConfig {

    @Value("${swagger.enabled}")
    private Boolean swaggerEnabled;

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.OAS_30)
            .apiInfo(apiInfo())
            .enable(swaggerEnabled)
            .groupName("M01")
            .select()
            .apis(RequestHandlerSelectors.basePackage("www.m01.tools"))
            .paths(PathSelectors.ant("/controller/**"))
            .build();
    }


    public ApiInfo apiInfo(){
        return new ApiInfo(
            "m01 api",
            "tools project",
            "v1.0",
            //开发者团队的邮箱
            "",
            new Contact("m01", "https://", "@gmail.com"),
            //许可证
            "Apache 2.0",
            //许可证链接
            "",
            new ArrayList<>()
        );
    }
}
