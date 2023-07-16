package com.css.wiki.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

/**
 * swagger3文档访问地址：http://ip:port/swagger-ui/index.html
 */

@Configuration
@EnableSwagger2
@EnableKnife4j
@EnableOpenApi
public class Knife4jConfig {

    @Bean
    public Docket defaultApi(Environment environment) {
        Profiles profiles = Profiles.of("dev", "native", "test");
        boolean flag = environment.acceptsProfiles(profiles);
        //设置请求头参数
        return new Docket(DocumentationType.OAS_30)
                .enable(flag)
                .apiInfo(apiInfo())
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.css"))
                .paths(PathSelectors.any())
                .build();
    }

    //logintoken
    private ApiKey apiKey() {
        return new ApiKey("authorization", "authorization", "header");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("wiki-web")
                .description("Wiki API接口文档")
                .contact(new Contact("wiki-web", "http://www.apache.org/licenses/LICENSE-2.0", ""))
                .version("1.0")
                .build();
    }
}
