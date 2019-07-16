package com.example.lj.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lijun on 2018/4/2.
 */
@Configuration
@EnableSwagger2

public class Swagger {
    @Value("${swagger.globalHeader}")
    private Boolean globalHeader = false;
    @Bean
    public Docket helloApi() {
        System.err.println(globalHeader);
        List<Parameter> pars = new ArrayList<>();
        if(globalHeader){
            ParameterBuilder ticketPar = new ParameterBuilder();
            ticketPar.name("Authorization").description("user token")
                    .modelRef(new ModelRef("string")).parameterType("header")
                    .required(false).build();
            pars.add(ticketPar.build());
        }

        Docket  docket=   new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.lj.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
        Set<String> produces = new HashSet<String>(){{add(MediaType.APPLICATION_JSON_VALUE);}};
        Set<String> consumes = new HashSet<String>(){{add(MediaType.APPLICATION_JSON_VALUE);}};
        docket.produces(produces);
        docket.consumes(consumes);
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("服务API文档")
                .description("")
                .termsOfServiceUrl("http://172.17.1.242")
                .version("v1")
                .build();
    }
}
