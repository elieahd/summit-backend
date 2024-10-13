package utils.integration;

import io.restassured.RestAssured;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class RestIntegrationTest extends IntegrationTest {

    @LocalServerPort
    private int port;

    @Override
    public void setupBeforeEach() {
        RestAssured.port = port;
    }

}
