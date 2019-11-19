package com.safefood;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket postApi() {
		return new Docket(DocumentationType.SWAGGER_2)
		.groupName("Controller")
		.apiInfo(apiInfo())
		.select()
		.apis(RequestHandlerSelectors.basePackage("com.safefood.controller"))
		.paths(PathSelectors.ant("/api/**"))
		.build();//request Mapping이 어떤애들로 잡을지
	}
	
	
	
	
	private ApiInfo apiInfo() {
		// TODO Auto-generated method stub
		return new ApiInfoBuilder()
				.title("Controller API")
				.license("ONLY 지윤&준영&창수!⎛⎝(•‿•)⎠⎞")
				.version("0.9")
				.build();
	}



}
