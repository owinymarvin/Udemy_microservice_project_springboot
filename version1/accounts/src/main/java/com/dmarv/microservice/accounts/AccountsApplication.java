package com.dmarv.microservice.accounts;

import com.dmarv.microservice.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts Microservice REST API " +
                        "Documentation",
                description = "EazyBank Accounts Microservice " +
                        "RESTAPI Documentation",
                version = "v1.0.0",
                contact = @Contact(
                        name = "Owiny Marvin",
                        email = "marvino@oliveeb.com",
                        url = "https://oliveeb.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
         externalDocs = @ExternalDocumentation(
                description = "EasyBank Accounts Microservice REST API " +
                        "Documentation",
                url = "https://oliveeb.com/swagger-ui/index.html"
        )
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
