import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.Matchers.not;


public class PingPongJsonTest {

	String okMessage = "\"input\":PING";
    String faultMessage = "\"input\":PONG";
		
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8080);
	
	@Test
	public void testPingPongPositive() {

		setupStub();

		given().
			body(okMessage).
		when().
			post("http://localhost:8090/pingpong").
		then().
			assertThat().
			statusCode(200).
				and().
				assertThat().body("output", equalToIgnoringWhiteSpace("PONG"));
	}

	@Test
	public void testPingPongNegative() {

		setupStub();

		given().
			body(faultMessage).
		when().
			post("http://localhost:8090/pingpong").
		then().
			assertThat().
			statusCode(404).
				and().
				assertThat().body("output", not("PONG"));
	}
	
	public void setupStub() {
		
		stubFor(
				post(urlEqualTo("/pingpong"))
						.withRequestBody(matching(okMessage))
						.willReturn(aResponse().
								withStatus(200).
								withHeader("Content-Type", "application/json").
								withBody("{\"output\":\"PONG\"}")));
	}
}