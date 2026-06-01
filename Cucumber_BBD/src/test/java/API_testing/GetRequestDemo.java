package API_testing;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetRequestDemo {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://gorest.co.in";

        String token = "PASTE_YOUR_REAL_GOREST_TOKEN_HERE";

        System.out.println("============== GET REQUEST ==============");

        Response getResponse =
                given()
                .when()
                    .get("/public/v2/users");

        System.out.println("Status Code : " + getResponse.getStatusCode());
        System.out.println("Response Body : ");
        System.out.println(getResponse.asString());
        System.out.println("Response Time : " + getResponse.getTime());
        System.out.println("Content Type : " + getResponse.getContentType());

        System.out.println("\n============== VALIDATIONS ==============");

        given()
        .when()
            .get("/public/v2/users")
        .then()
            .statusCode(200)
            .header("Content-Type", containsString("application/json"))
            .time(lessThan(5000L))
            .body("[0].id", notNullValue())
            .body("[0].name", notNullValue())
            .body("[0].email", notNullValue())
            .body("[0].gender", anyOf(equalTo("male"), equalTo("female")))
            .body("[0].status", anyOf(equalTo("active"), equalTo("inactive")))
            .log().all();

        System.out.println("\n============== JSON EXTRACTION ==============");

        JsonPath jsonPath = getResponse.jsonPath();

        int firstUserId = jsonPath.getInt("[0].id");
        String name = jsonPath.getString("[0].name");
        String email = jsonPath.getString("[0].email");
        String gender = jsonPath.getString("[0].gender");
        String status = jsonPath.getString("[0].status");

        System.out.println("ID : " + firstUserId);
        System.out.println("Name : " + name);
        System.out.println("Email : " + email);
        System.out.println("Gender : " + gender);
        System.out.println("Status : " + status);

        System.out.println("\n============== POST REQUEST ==============");

        String randomEmail = "ashutosh" + System.currentTimeMillis() + "@gmail.com";

        String postRequestBody =
                "{ \"name\":\"Ashutosh Pandey\", " +
                "\"email\":\"" + randomEmail + "\", " +
                "\"gender\":\"male\", " +
                "\"status\":\"active\" }";

        Response postResponse =
                given()
                    .header("Authorization", "Bearer " + token)
                    .contentType(ContentType.JSON)
                    .body(postRequestBody)
                .when()
                    .post("/public/v2/users");

        System.out.println("POST Status Code : " + postResponse.getStatusCode());
        System.out.println("POST Response Body : " + postResponse.asString());

        if (postResponse.getStatusCode() == 201) {

            postResponse.then()
                    .statusCode(201)
                    .body("name", equalTo("Ashutosh Pandey"))
                    .body("email", equalTo(randomEmail))
                    .body("gender", equalTo("male"))
                    .body("status", equalTo("active"))
                    .log().all();

            int createdUserId = postResponse.jsonPath().getInt("id");

            System.out.println("Created User ID : " + createdUserId);

            System.out.println("\n============== PUT REQUEST ==============");

            String putRequestBody =
                    "{ \"name\":\"Ashutosh Updated\", " +
                    "\"email\":\"" + randomEmail + "\", " +
                    "\"gender\":\"male\", " +
                    "\"status\":\"inactive\" }";

            Response putResponse =
                    given()
                        .header("Authorization", "Bearer " + token)
                        .contentType(ContentType.JSON)
                        .body(putRequestBody)
                    .when()
                        .put("/public/v2/users/" + createdUserId);

            System.out.println("PUT Status Code : " + putResponse.getStatusCode());
            System.out.println("PUT Response Body : " + putResponse.asString());

            putResponse.then()
                    .statusCode(200)
                    .body("name", equalTo("Ashutosh Updated"))
                    .body("status", equalTo("inactive"))
                    .log().all();

            System.out.println("\n============== DELETE REQUEST ==============");

            Response deleteResponse =
                    given()
                        .header("Authorization", "Bearer " + token)
                    .when()
                        .delete("/public/v2/users/" + createdUserId);

            System.out.println("DELETE Status Code : " + deleteResponse.getStatusCode());
            System.out.println("DELETE Response Body : " + deleteResponse.asString());

            deleteResponse.then()
                    .statusCode(204)
                    .log().all();

        } else {

            System.out.println("POST failed. Reason: Token missing, invalid, or expired.");
            System.out.println("Create GoRest token and paste it in token variable.");
        }

        System.out.println("\n============== PATH PARAMETER ==============");

        given()
            .pathParam("id", firstUserId)
        .when()
            .get("/public/v2/users/{id}")
        .then()
            .statusCode(200)
            .body("id", equalTo(firstUserId))
            .log().all();

        System.out.println("\n============== QUERY PARAMETER ==============");

        given()
            .queryParam("page", 1)
            .queryParam("per_page", 5)
        .when()
            .get("/public/v2/users")
        .then()
            .statusCode(200)
            .log().all();

        System.out.println("\n============== HEADERS ==============");

        given()
            .header("Content-Type", "application/json")
        .when()
            .get("/public/v2/users")
        .then()
            .statusCode(200)
            .log().headers();

        System.out.println("\n============== PRINT HEADERS ==============");

        getResponse.getHeaders().forEach(System.out::println);

        System.out.println("\n============== PRINT COOKIES ==============");

        getResponse.getCookies().forEach(
                (k, v) -> System.out.println(k + " : " + v)
        );

        System.out.println("\n============== TEST COMPLETED ==============");
    }
}