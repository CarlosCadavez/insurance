package br.com.csc.insurance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    private static final String TITLE = "Insurance Api";
    private static final String DESCRIPTION = "Api for insurance";
    private static final String API_VERSION = "1.0.0";
    public static final String CONTACT = "Carlos Cadavez";
    public static final String DEVELOPER_GIT_HUB = "https://github.com/CarlosCadavez";
    public static final String DEVELOPER_MAIL = "cs.cadavez@gmail.com";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.csc.insurance"))
                .paths(PathSelectors.any())
                .build().apiInfo(buidApiInfo())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo buidApiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .version(API_VERSION)
                .contact(new Contact(CONTACT, DEVELOPER_GIT_HUB, DEVELOPER_MAIL))
                .build();
    }
}
