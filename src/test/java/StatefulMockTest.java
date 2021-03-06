import org.junit.Rule;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.stubbing.Scenario;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static com.github.tomakehurst.wiremock.client.WireMock.*;


public class StatefulMockTest {
		
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8090);
	
	@Test
	public void testStatefulMock() {
		
		setupStub();
		
		given().
		when().
			get("http://localhost:8090/todolist").
		then().
			assertThat().
			statusCode(200).
			and().
			assertThat().body("list",equalTo("Empty"));
		
		given().
		when().
			post("http://localhost:8090/todolist").
		then().
			assertThat().
			statusCode(201);
		
		given().
		when().
			get("http://localhost:8090/todolist").
		then().
			assertThat().
			statusCode(200).
			and().
			assertThat().body("list", not("Empty")).
			and().
			assertThat().body("list.item", equalTo("Item added to list"));
		
	}
	
	public void setupStub() {
		
		stubFor(get(urlEqualTo("/todolist"))
				.inScenario("addItem")
				.whenScenarioStateIs(Scenario.STARTED)
				.willReturn(aResponse()
	                .withStatus(200)
	                .withHeader("Content-Type", "application/xml")
	                .withBody("<list>Empty</list>")));
		
		stubFor(post(urlEqualTo("/todolist"))
				.inScenario("addItem")
				.whenScenarioStateIs(Scenario.STARTED)
				.willSetStateTo("itemAdded")
				.willReturn(aResponse()
					.withHeader("Content-Type", "application/xml")
	                .withStatus(201)));
		
		stubFor(get(urlEqualTo("/todolist"))
				.inScenario("addItem")
				.whenScenarioStateIs("itemAdded")
				.willReturn(aResponse()
	                .withStatus(200)
	                .withHeader("Content-Type", "application/xml")
	                .withBody("<list><item>Item added to list</item></list>")));
		
	}
}