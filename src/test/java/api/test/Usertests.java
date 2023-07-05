package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import io.restassured.response.Response;

public class Usertests {
	/*
	 * int id; String username; String firstName; String lastName; String email;
	 * String password; String phone; int userStatus=0;
	 */

	Faker fake;
	User payload;

	@BeforeClass
	public void setupData() {

		fake = new Faker();
		payload = new User();

		payload.setId(fake.idNumber().hashCode());
		payload.setUsername(fake.name().username());
		payload.setFirstName(fake.name().firstName());
		payload.setLastName(fake.name().lastName());
		payload.setEmail(fake.internet().safeEmailAddress());
		payload.setPassword(fake.internet().password(6, 8));
		payload.setPhone(fake.phoneNumber().cellPhone());

	}

	@Test(priority = 1)
	public void testpostusers() {

		Response response = UserEndpoints.RecreateUser(payload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2)
	public void TestGetUserByName() {

		Response response = UserEndpoints.readerUser(this.payload.getUsername());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 3)
	public void UpdateUser() {
		// update the user

		payload.setFirstName(fake.name().firstName());
		payload.setLastName(fake.name().lastName());
		payload.setEmail(fake.internet().safeEmailAddress());

		Response response = UserEndpoints.UpdateUser(this.payload.getUsername(), payload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 4)
	public void DeleteTheUser() {

		Response response = UserEndpoints.DeleteUser(this.payload.getUsername());

		Assert.assertEquals(response.getStatusCode(), 200);

	}

}
