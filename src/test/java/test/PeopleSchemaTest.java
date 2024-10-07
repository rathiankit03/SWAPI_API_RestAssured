package test;

import config.Endpoints;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PeopleSchemaTest {

    @Test
    public void validatePeopleSchema() {
        given().baseUri(Endpoints.BASE_URI).
                when().get(Endpoints.PEOPLE_ENDPOINT)
                .then().assertThat()
                .body(matchesJsonSchemaInClasspath("peopleSchema.json"));
    }
}
