package com.codenation.swagger;

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

	/*
	 * @return Docket Object
	 * build a swagger configuration for api documentation
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.codenation"))
				.paths(PathSelectors.any())
				.build().apiInfo(apiEndpointsInfo());
	}
	
	/*
	 * @return ApiInfo object
	 * define api information log
	 */
	private ApiInfo apiEndpointsInfo() {
		return new ApiInfoBuilder()
				.title("API Central de Erros")
				.description("Centraliza registros de erros de aplicações | Centralize application error logs")
				.license("MIT License").licenseUrl("https://opensource.org/licenses/MIT")
				.version("0.0.1-SNAPSHOT").build();				
	}
}
