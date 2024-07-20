import org.testng.annotations.Test;
import io.restassured.response.Response;
import pages.User;
import pages.UserPage;

public class APITests {

    @Test
    public void testCreateUser() {
        // Criar um objeto User
        User user = new User("morpheus", "leader");
        
        // Instanciar a página UserPage
        UserPage userPage = new UserPage();
        
        // Enviar a requisição POST
        Response response = userPage.createUser(user);
        
        // Validar campos obrigatórios
        userPage.validateMandatoryFields(response);
    }
}
