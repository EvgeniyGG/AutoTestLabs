import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.List;
import static io.restassured.RestAssured.given;

public class AppTest {

    private String uri =  "http://api.openweathermap.org/data/2.5";
    private final int expectedStatusCode = 200;
    private Response response = null;

    private Response doGetRequest(String endpoint)
    {
        RestAssured.defaultParser = Parser.JSON;

        return given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                when().get(endpoint).
                then().contentType(ContentType.JSON).extract().response();
    }

    @Test
    public void openWeatherMapTest1()
    {
       String params = "/box/city?bbox=52,48,56,54,10&appid=b166aaec37c9bd75096c18c0bf24420d";
       response = doGetRequest(uri + params);
       List<String> citynames = response.jsonPath().getList("list.name");

        Assert.assertEquals("Status code assert", response.getStatusCode(), expectedStatusCode);
        Assert.assertTrue("Response content assert",
                citynames.contains("Orenburg"));
    }

    @Test
    public void openWeatherMapTest2()
    {
        String params = "/weather?zip=20001&appid=b166aaec37c9bd75096c18c0bf24420d&units=metric";
        String name;

        response = doGetRequest(uri + params);
        name = response.jsonPath().getString("name");

        Assert.assertEquals("Status code assert", response.getStatusCode(), expectedStatusCode);
        Assert.assertEquals("Response content assert", "Washington", name);
    }
}
