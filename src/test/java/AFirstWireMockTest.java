
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import io.restassured.response.Response;
import org.junit.*;

import static io.restassured.RestAssured.*;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Test;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.github.tomakehurst.wiremock.client.WireMock.*;


public class AFirstWireMockTest {
	private static final int WIREMOCK_PORT = 9090;
	String bodyText = "You've reached a valid WireMock endpoint";

//	@ClassRule
//	public static WireMockClassRule wireMockClassRule = new WireMockClassRule(WIREMOCK_PORT);
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(WIREMOCK_PORT);

//	Non-JUnit and general Java usage
//    WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(WIREMOCK_PORT));


	@Test
	public void testStatusCodePositive() {
		
		setupStub();
		
		given().
		when().
			get("http://localhost:9090/an/endpoint").
		then().
			assertThat().statusCode(200);
	}
	
	@Test
	public void testStatusCodeNegative() {
		
		setupStub();
		
		given().
		when().
			get("http://localhost:9090/another/endpoint").
		then().
			assertThat().statusCode(404);
	}
	
	@Test
	public void testResponseContents() {
		
		setupStub();

		String response = given().
				when().
				get("http://localhost:9090/an/endpoint").getBody().asString();
		Assert.assertEquals(bodyText, response);
	}
	
	public void setupStub() {
		
		stubFor(get(urlEqualTo("/an/endpoint"))
	            .willReturn(aResponse()
	                .withHeader("Content-Type", "text/plain")
	                .withStatus(200)
	                .withBody(bodyText)));
	}
}