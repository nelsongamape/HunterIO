package stepsDefinition;

import utils.DSL;
import io.cucumber.java.Before;
import static io.restassured.RestAssured.*;

public class Hooks extends DSL {

	@Before
	public void initTest() {

		DSL.request = given().header("Accept", "application/json").header("Content-Type", "application/json");

	}

}
