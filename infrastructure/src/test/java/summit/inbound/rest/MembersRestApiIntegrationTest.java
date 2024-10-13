package summit.inbound.rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import utils.integration.RestIntegrationTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static utils.Randomizer.randomInteger;
import static utils.Randomizer.randomString;

class MembersRestApiIntegrationTest extends RestIntegrationTest {

    @Test
    void shouldReturnMemberById() {
        final String username = randomString();
        final String name = randomString();
        final Integer id = db.members.insert(username, name);
        given()
                .when()
                .get("/api/v1/members/%s".formatted(id))
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo(name))
                .body("username", equalTo(username));
    }

    @Test
    void shouldReturn404WhenMemberNotFoundById() {
        final Integer id = randomInteger() * randomInteger();
        given()
                .when()
                .get("/api/v1/members/%s".formatted(id))
                .then()
                .statusCode(404)
                .contentType(ContentType.TEXT)
                .body(equalTo("Member '%s' not found".formatted(id)));
    }

    @Test
    void shouldCreateMember() {
        final String username = randomString();
        final String name = randomString();
        final String body = """
                {
                    "username": "%s",
                    "name": "%s"
                }""".formatted(username, name);
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/api/v1/members")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo(name))
                .body("username", equalTo(username));
    }

    @Test
    void shouldReturnBadRequestWhenCreatingMemberWithExistingUsername() {
        final String username = randomString();
        final String name = randomString();
        db.members.insert(username, name);
        final String body = """
                {
                    "username": "%s",
                    "name": "%s"
                }""".formatted(username, name);
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/api/v1/members")
                .then()
                .statusCode(400)
                .contentType(ContentType.TEXT)
                .body(equalTo("'%s' already exists".formatted(username)));
    }

}
