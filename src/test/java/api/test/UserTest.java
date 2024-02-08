package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	User userpayload;
	
@BeforeClass
public void setupData() {
	
	faker=new Faker();
	userpayload=new User();
	
	userpayload.setId(faker.idNumber().hashCode());
	userpayload.setFirstname(faker.name().firstName());
	userpayload.setUsername(faker.name().username());
	userpayload.setLastname(faker.name().lastName());
	userpayload.setPassword(faker.internet().password(6, 12));
	userpayload.setEmail(faker.internet().safeEmailAddress());
	userpayload.setPhone(faker.phoneNumber().cellPhone());
	
}

 @Test(priority=1)
public  void testCreateUser() {
   Response response= UserEndPoints.createuser(userpayload);
   response.then().log().all();
    Assert.assertEquals(response.getStatusCode(), 200);
	
}
 @Test(priority=2)
 public void testGetUser() {
	 
	 Response response=UserEndPoints.getUser(this.userpayload.getUsername());
	 response.then().log().all();
	 Assert.assertEquals(response.getStatusCode(), 200);
 }
 @Test
 public void testUpdateUser() {
	
	userpayload.setFirstname(faker.name().firstName());
	userpayload.setLastname(faker.name().lastName());
	userpayload.setPassword(faker.internet().password(6, 12));
	userpayload.setEmail(faker.internet().safeEmailAddress());
	 
	Response response= UserEndPoints.updateUser(userpayload, this.userpayload.getUsername());
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(), 200);
 }
 @Test
 public void testDeleteUser() {
	 
	 UserEndPoints.deleteUser(userpayload.getUsername());
	 
 }
}
