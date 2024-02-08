package api.test;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProvider;
import io.restassured.response.Response;
public class DataDrivenTest {
	User userPayload;
	
	@Test(priority=1, dataProvider = "data", dataProviderClass = DataProvider.class)
	public void testPostUser(String Id, String Fname, String Lname, String username, String email, String pwd, String no) {
		
		
		userPayload=new User();
		userPayload.setId(Integer.parseInt(Id));
		userPayload.setFirstname(Fname);
		userPayload.setLastname(Lname);
		userPayload.setUsername(username);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(no);
		
		Response response=UserEndPoints.createuser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	@Test(priority=2, dataProvider = "username", dataProviderClass = DataProvider.class)
	public void deleteTest(String username) {
		
		 Response response=UserEndPoints.deleteUser(username);
		 Assert.assertEquals(response.getStatusCode(), 200);
	}

}
