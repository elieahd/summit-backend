package summit.inbound.rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import summit.models.TrackerInterval;
import utils.integration.RestIntegrationTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static utils.Randomizer.random;
import static utils.Randomizer.randomInteger;
import static utils.Randomizer.randomString;

class TrackerTemplatesRestApiIntegrationTest extends RestIntegrationTest {

    @Test
    void shouldReturnTrackerTemplateById() {
        final String name = randomString();
        final String description = randomString();
        final TrackerInterval interval = random(TrackerInterval.class);
        final Integer id = db.trackerTemplates.insert(name, description, interval);
        given()
                .when()
                .get("/api/v1/tracker-templates/%s".formatted(id))
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("description", equalTo(description))
                .body("interval", equalTo(interval.name()));
    }

    @Test
    void shouldReturn404WhenTrackerTemplateNotFoundById() {
        final Integer id = randomInteger() * randomInteger();
        given()
                .when()
                .get("/api/v1/tracker-templates/%s".formatted(id))
                .then()
                .statusCode(404)
                .contentType(ContentType.TEXT)
                .body(equalTo("Tracker template '%s' not found".formatted(id)));
    }

    @Test
    void shouldCreateTrackerTemplate() {
        final String name = randomString();
        final String description = randomString();
        final TrackerInterval interval = random(TrackerInterval.class);
        final String body = """
                {
                    "name": "%s",
                    "description": "%s",
                    "interval": "%s"
                }""".formatted(name, description, interval.name());
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/api/v1/tracker-templates")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo(name))
                .body("description", equalTo(description))
                .body("interval", equalTo(interval.name()));
    }

}
