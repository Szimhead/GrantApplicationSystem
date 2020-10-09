package pt.unl.fct.di.pt.grantManagementApplication.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    fun apiInfo(): ApiInfo = ApiInfoBuilder()
            .title("Grant Application API")
            .version("1.0.2")
            .description("Grant Application Management System")
            .contact(Contact("Group 41 - Repository", "https://bitbucket.org/tykey/iadi2020-49942-59559-59562/src/master/", ""))
            .license("Apache 2.0")
            .licenseUrl("https://www.apache.org/license/LICENSE-2.0")
            .build()


    @Bean
    fun api(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("pt.unl.fct.di.pt.grantManagementApplication"))
                .paths(PathSelectors.any())
                .build()
    }
}