import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class APITests {

    @Test
    public void testCreateUser() {
        // Definir a base URI
        RestAssured.baseURI = "https://reqres.in/api";

        // Criar um objeto User
        User user = new User("morpheus", "leader");

        // Enviar a requisição POST
        Response response = 
            given()
                .header("Content-Type", "application/json")
                .body(user)
            .when()
                .post("/users")
            .then()
                .statusCode(201)  // Verificar o status code
                .body("name", equalTo("morpheus"))  // Verificar campo "name"
                .body("job", equalTo("leader"))  // Verificar campo "job"
                .extract()
                .response();

        // Validar campos obrigatórios
        response.then().assertThat().body("$", hasKey("id"));
        response.then().assertThat().body("$", hasKey("createdAt"));
    }
}
