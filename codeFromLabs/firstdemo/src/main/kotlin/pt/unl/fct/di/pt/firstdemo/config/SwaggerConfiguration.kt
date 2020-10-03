package pt.unl.fct.di.pt.firstdemo.config

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
    @Bean
    fun api(): Docket =
            Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("pt.unl.fct.di.pt.firstdemo"))
                    .paths(PathSelectors.any())
                    .build().apiInfo((apiEndPointsInfo()))

    fun apiEndPointsInfo(): ApiInfo =
            ApiInfoBuilder()
                    .title("Spring Boos REST API for IADI 20/21")
                    .description("grant calls and applications")
                    //.contact(Contact("Our names I guess", "http://di.fct.unl.pt/contact", "email@campus.fct.unl.pt"))
                    .license("Apache 2.0")
                    .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                    .version("1.0.0")
                    .build()
}