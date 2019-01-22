package beans;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.Response;
import org.json.JSONObject;

import static com.jayway.restassured.RestAssured.given;

public class Message {
    private String timeZone;
    private String email;

    public String getTimeZone() {
        return timeZone;
    }

    public String getEmail() {
        return email;
    }

    public Message initFromAPI(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;
        Response response = given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                when().get(endpoint).
                then().contentType(ContentType.JSON).extract().response();

        JSONObject obj = new JSONObject(response.getBody().asString());


        timeZone = obj.getJSONArray("results").getJSONObject(0).getString("email");
        email = obj
                .getJSONArray("results")
                .getJSONObject(0)
                .getJSONObject("location")
                .getJSONObject("timezone")
                .getString("description");
        return this;
    }
}
