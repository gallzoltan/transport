package hu.webuni.transport.gallz.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.assertj.core.api.Assertions.assertThat;

import hu.webuni.transport.gallz.dto.DelayDto;
import hu.webuni.transport.gallz.dto.LoginDto;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TransportControllerIT {
	
	private static final String BASE_URI = "/api/transportPlans";
	private String jwtToken;
	
	@Autowired
	WebTestClient webTestClient;
		
	@BeforeEach
	public void init() {
		LoginDto body = new LoginDto("transportUser", "passwd");
		jwtToken = webTestClient.post()
				.uri("/api/login")
				.bodyValue(body).exchange()
				.expectBody(String.class)
				.returnResult().getResponseBody();
	}
	
	@Test
	public void testThatTransportplanIdDoesNotFound() throws Exception {
		Long transportplanId = 12L;
		DelayDto delayDto = new DelayDto();
		delayDto.setId(7L);
		delayDto.setDelay(120);
		webTestClient.post()
			.uri(String.format("%s/%s/delay", BASE_URI, transportplanId))
				.headers(headers -> headers.setBearerAuth(jwtToken)).bodyValue(delayDto).exchange().expectStatus()
				.isNotFound();
	}
	
	@Test
	public void testThatMilestoneIdNotPartOfTransportPlan() throws Exception {
		Long transportplanId = 11L;
		DelayDto delayDto = new DelayDto();
		delayDto.setId(8L);
		delayDto.setDelay(120);
		webTestClient.post()
			.uri(String.format("%s/%s/delay", BASE_URI, transportplanId))
				.headers(headers -> headers.setBearerAuth(jwtToken)).bodyValue(delayDto).exchange().expectStatus()
				.isBadRequest();
	}
	
	@Test
	public void testThatIncreaseThePlannedTimeAtMilestone() {
		// 
	}
	
	public void testThatExpectedRevenuePercentageReduction() {
		//
	}
}
