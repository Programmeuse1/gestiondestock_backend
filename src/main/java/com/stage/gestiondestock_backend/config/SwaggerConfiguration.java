package com.stage.gestiondestock_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfiguration implements WebMvcConfigurer {

    public static final String AUTHORIZATION_HEADER ="Authorization";
    @Bean
    public Docket swaggerSpringfoxApiDocket(){
        StopWatch watch = new StopWatch();
        Contact contact = new Contact(
                "",
                "",
                ""
        );
        ApiInfo apiInfo = new ApiInfo(
                "gestion de stock_backend",
                "",
                "2.0",
                "",
                contact,
                "", "",
                new ArrayList<>()
        );
        watch.start();
        Docket docket = createDocket()
                .apiInfo(apiInfo)
                .select()
                .build();
        watch.stop();
        return docket;
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addRedirectViewController("/", "swagger-ui.html");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/");
    }
    protected Docket createDocket(){
        return new Docket(DocumentationType.SWAGGER_2);
    }

    private ApiKey apiKey(){
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }
    private SecurityContext securityContext(){
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }
    List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(
                new SecurityReference("JWT", authorizationScopes)
        );

    }



}
   /* public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(
        new ApiInfoBuilder()
        .description("Gestion Des Stagiaires API documentation")
        .title("Gestion Des Stagiaires REST API")
        .build()
        )
        .groupName("Rest API V1")
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.yousseu.GestionDesStagiaires"))
        .paths(PathSelectors.ant(APP_ROOT +"/**"))
        .build();

        }
        }*/