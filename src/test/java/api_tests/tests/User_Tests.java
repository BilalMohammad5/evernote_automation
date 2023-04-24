package api_tests.tests;

import api_tests.endpoint.User_endpoints;
import api_tests.payload.User;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

public class User_Tests {
    static Faker faker;
    static User userPayLoad;

    @BeforeClass
    public static void setUpData() {
        faker = new Faker();
        userPayLoad = new User();

        userPayLoad.setId(faker.idNumber().hashCode());
        userPayLoad.setFirstName(faker.name().firstName());
        userPayLoad.setLastName(faker.name().lastName());
        userPayLoad.setUsername(faker.name().username());
        userPayLoad.setEmail(faker.internet().safeEmailAddress());
        userPayLoad.setPassword(faker.internet().password(5, 10));
        userPayLoad.setPhone(faker.phoneNumber().cellPhone());

    }

    @Test(priority = 1)
    public void create_user() {
        Response response = User_endpoints.create_user(this.userPayLoad);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void get_user() {
        Response response = User_endpoints.read_user(this.userPayLoad.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test(priority = 3)
    public void update_user() {
        userPayLoad.setFirstName(faker.name().firstName());
       Response response = User_endpoints.update_user(this.userPayLoad.getUsername(),userPayLoad);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        Response updated_response = User_endpoints.read_user(this.userPayLoad.getUsername());
        updated_response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test(priority = 4)
    public void delete_user() {
        Response response = User_endpoints.read_user(this.userPayLoad.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

    }
}
