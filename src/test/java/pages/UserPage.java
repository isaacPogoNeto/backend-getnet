package pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class UserPage {
    
    private static final String BASE_URI = "https://reqres.in/api";
    
    // Método para criar um usuário
    public Response createUser(User user) {
        RestAssured.baseURI = BASE_URI;
        
        return given()
                    .header("Content-Type", "application/json")
                    .body(user)
                .when()
                    .post("/users")
                .then()
                    .statusCode(201)  // Verificar o status code
                    .body("name", equalTo(user.getName()))  // Verificar campo "name"
                    .body("job", equalTo(user.getJob()))  // Verificar campo "job"
                    .extract()
                    .response();
    }
    
    // Método para validar campos obrigatórios
    public void validateMandatoryFields(Response response) {
        response.then().assertThat().body("$", hasKey("id"));
        response.then().assertThat().body("$", hasKey("createdAt"));
    }
}
