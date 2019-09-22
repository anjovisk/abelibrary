package com.imarques.abelibrary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.imarques.abelibrary.controller.v1"))
				.build()
				.apiInfo(apiInfo())
				.tags(
						new Tag("Books", "API REST para o acervo de livros"),
						new Tag("Baskets", "API REST para o carrinho de compra de livros"),
						new Tag("Orders", "API REST para os pedidos"));
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("ABE Library REST API")
				.description("\"ABE Library REST API to learning interesting stuff\"")
				.version("1.0.0")
				.license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
				.build();
	}
}
