package api.Test1;

import api.endpoint.Userendpoint;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Usertest {

    Faker faker; //global variable
    User userPayload; //variable
    @BeforeClass
    public void setupdata()
   {
    faker=new Faker();
    userPayload=new User();
    userPayload.setId(faker.idNumber().hashCode());
    userPayload.setFirstname(faker.name().username());
    userPayload.setFirstname(faker.name().firstName());
    userPayload.setLastname(faker.name().lastName());
    userPayload.setEmail(faker.internet().safeEmailAddress());
    userPayload.setPassword(faker.internet().password(5,10));
    userPayload.setPhone(faker.phoneNumber().cellPhone());

   }
 @Test(priority = 1)
   public void testPostUser()
   {
    Response response= Userendpoint.createUser(userPayload);
    response.then().log().all();
       Assert.assertEquals(response.getStatusCode(),200);
   }
   @Test(priority = 2)
    public void testGetUserbyname()
   {
       Response response=Userendpoint.readUser(this.userPayload.getUsername());
       response.then().log().all();
       Assert.assertEquals(response.getStatusCode(),200);
   }

   @Test(priority=3)

    public void testupdateUserbyname()
   {

       // update data using same payload
       userPayload.setFirstname(faker.name().firstName());
       userPayload.setLastname(faker.name().lastName());

       Response response= Userendpoint.updateUser(this.userPayload.getUsername(),userPayload);
       response.then().log().all();
       Assert.assertEquals(response.getStatusCode(),200);

       // cheking after data update
       Response responseafterupdate=Userendpoint.readUser(this.userPayload.getUsername());
       Assert.assertEquals(responseafterupdate.getStatusCode(),200);
   }

   public void testdeleteUserbyname()
   {
       Response response = Userendpoint.deleteUser(this.userPayload.getUsername());
       response.then().log().all().statusCode(200);

       Assert.assertEquals(response.getStatusCode(),200);

   }
}
