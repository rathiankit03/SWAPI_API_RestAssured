package test;

import config.Endpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class StarWarsFilmTests {

    public Map<String, Object> getLatestFilm() {
        Response response = given().baseUri(Endpoints.BASE_URI).
                when().get(Endpoints.FILMS_ENDPOINT).
                then().extract().response();

        List<Map<String, Object>> films = response.jsonPath().getList("results");

        // Sort by release date
        films.sort((film1, film2) ->
                ((String) film2.get("release_date")).compareTo((String) film1.get("release_date")));

        return films.getFirst();
    }

    @Test
    public void findLatestFilm() {
        Map<String, Object> latestFilm = getLatestFilm();
        System.out.println("Latest Film: " + latestFilm.get("title"));
    }

    @Test
    public void findTallestCharacterInLatestFilm() {
        Map<String, Object> latestFilm = getLatestFilm();

        List<String> characterUrls = (List<String>) latestFilm.get("characters");

        String tallestCharacter = null;
        int maxHeight = 0;

        for (String characterUrl : characterUrls) {
            Response charResponse = given().when().get(characterUrl).then().extract().response();
            int height = Integer.parseInt(charResponse.jsonPath().getString("height"));

            if (height > maxHeight) {
                maxHeight = height;
                tallestCharacter = charResponse.jsonPath().getString("name");
            }
        }
        System.out.println("Tallest character in latest film: " + tallestCharacter);
    }

    @Test
    public void findTallestPersonEver() {
        int page = 1;
        int maxHeight = 0;
        String tallestCharacter = null;

        while (true) {
            Response response = given().baseUri(Endpoints.BASE_URI).queryParam("page", page).
                    when().get(Endpoints.PEOPLE_ENDPOINT).
                    then().extract().response();

            // Get the next URL for pagination
            String next = response.jsonPath().getString("next");

            List<Map<String, Object>> people = response.jsonPath().getList("results");

            for (Map<String, Object> person : people) {
                String heightStr = (String) person.get("height");
                if (!heightStr.equals("unknown")) {
                    int height = Integer.parseInt(heightStr);
                    if (height > maxHeight) {
                        maxHeight = height;
                        tallestCharacter = (String) person.get("name");
                    }
                }
            }

            // Stop if there is no next page
            if (next == null) {
                break;
            }

            page++;
        }
        System.out.println("Tallest person ever played in any Star Wars film: " + tallestCharacter);
    }
}
