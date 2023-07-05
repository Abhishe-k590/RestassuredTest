package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest {
	
	@Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProviders.class)
	void testpostrequest(String Userid, String username,String Firstname, String Lastname,String Email,String Password,String Phone ) {
		
		User payload = new User();
		
		payload.setId(Integer.parseInt(Userid));
		payload.setUsername(username);
		payload.setFirstName(Firstname);
		payload.setLastName(Lastname);
		payload.setEmail(Email);
		payload.setPassword(Password);
		payload.setPhone(Phone);
		
		
		Response response = UserEndpoints.RecreateUser(payload);
		Assert.assertEquals(response.getStatusCode(),200);
		
		}
	@Test(priority = 2, dataProvider = "UserNames",dataProviderClass = DataProviders.class)
	public void testGetUser(String Username) {
		
		Response response = UserEndpoints.readerUser(Username);
		
	Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	
	

}
