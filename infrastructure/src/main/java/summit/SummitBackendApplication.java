package summit;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Summit backend",
                description = "Summit backend",
                contact = @Contact(name = "Elie", email = "eliedhr@gmail.com"),
                version = "0.1.0"
        )
)
public class SummitBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SummitBackendApplication.class, args);
    }

}
