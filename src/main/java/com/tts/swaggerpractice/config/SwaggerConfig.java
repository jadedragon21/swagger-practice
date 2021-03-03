package com.tts.swaggerpractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

@Configuration
//@EnableSwagger2
//only needed with Swagger 2 which Im not implementing atm
public class SwaggerConfig {

    @Bean
    public Docket apiV1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Version 1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tts.swaggerpractice"))
                // (above) ignores the built-in Spring Boot error handler
                // controllers that get pulled in by default Slide 15-16
                .paths(PathSelectors.ant("/v1/**"))
                .build()
                .apiInfo(buildApiInfoV1());
                //(above)We then call this method in the main api() method
                //so that it gets pulled into the configuration.Slide 19
    }
    //(Below)
    //We can also add information about the API that will display at the top of the generated Swagger page.
    //This can include various pieces of information including the API title, description, license, and contact info.
    //Create a new ApiInfo object using the ApiInfoBuilder.
    //Builder methods are special constructor methods that allow for optional constructor arguments to be passed in in any order.
    //For clarity, the ApiInfo object is built in a separate method.Slide 17
    private ApiInfo buildApiInfoV1() {
        return new ApiInfoBuilder()
                .title("Menu Items API")
                .description("REST API for interacting with menu items")
                .version("1.0.0")
               // .contact(new Contact("Developer Name", "website.com", "developer@website.com"))
                .contact(new Contact("Sarah Hoffman", "Sarah.works", "hoffman@sarah.works"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
    //apiV2 version 2 of the api
    @Bean
    public Docket apiV2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Version 2")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tts.swaggerpractice"))
                .paths(PathSelectors.ant("/v2/**"))
                .build()
                .apiInfo(buildApiInfoV2());
    }

    private ApiInfo buildApiInfoV2() {
        return new ApiInfoBuilder()
                .title("Menu Items API")
                .description("REST API for interacting with menu items")
                .version("2.0.0")
                .contact(new Contact("Sarah Hoffman", "Sarah.works", "hoffman@sarah.works"))
                .license("Apache License Version 2.0")
                .license("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
    //(Below)the ability to customize various display options by adding another bean to the configuration class.
    //A common option is to have all the endpoints appear automatically expanded, instead of having to manually click on each one.
    //This is accomplished with the following code. There are, of course, lots of other options that can be specified here.
    //See the documentation for details. Slide 18
    @Bean
    public UiConfiguration buildUiConfig() {
        return UiConfigurationBuilder.builder()
                .docExpansion(DocExpansion.FULL)
                .build();
    }
}