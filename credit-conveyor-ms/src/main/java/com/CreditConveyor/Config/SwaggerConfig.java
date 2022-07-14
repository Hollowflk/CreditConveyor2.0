package com.CreditConveyor.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Кредитный конвейер")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .email("Hollowflk@gmail.com")
                                                .name("Nikita Bykov"))
                );
    }

}
