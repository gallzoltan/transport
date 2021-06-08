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

import java.time.LocalDateTime;
import java.util.TreeMap;
import java.util.Map.Entry;

import hu.webuni.transport.gallz.config.TransportConfigProperties;
import hu.webuni.transport.gallz.dto.DelayDto;
import hu.webuni.transport.gallz.dto.LoginDto;
import hu.webuni.transport.gallz.model.Milestone;
import hu.webuni.transport.gallz.model.TransportPlan;
import hu.webuni.transport.gallz.repository.AddressRepository;
import hu.webuni.transport.gallz.repository.MilestoneRepository;
import hu.webuni.transport.gallz.repository.SectionRepository;
import hu.webuni.transport.gallz.repository.TransportPlanRepository;
import hu.webuni.transport.gallz.service.InitDbService;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TransportControllerIT {
	
	private static final String BASE_URI = "/api/transportPlans";
	private String jwtToken;
	//private Long transportplanId;
	
	@Autowired
	WebTestClient webTestClient;
	
	@Autowired
	InitDbService initDbService;
	
	@Autowired
	TransportConfigProperties config;
	
	@Autowired
	TransportPlanRepository transportPlanRepository;

	@Autowired
	MilestoneRepository milestoneRepository;
	
	@Autowired
	SectionRepository sectionRepository;
	
	@Autowired
	AddressRepository addressRepository;	
		
	@BeforeEach
	public void init() {
//		addressRepository.deleteAll();
//		sectionRepository.deleteAll();
//		milestoneRepository.deleteAll();
//		transportPlanRepository.deleteAll();
//		transportplanId = initDbService.initDb().getId();
		
		LoginDto body = new LoginDto("transportUser", "passwd");
		jwtToken = webTestClient.post()
				.uri("/api/login")
				.bodyValue(body).exchange()
				.expectBody(String.class)
				.returnResult().getResponseBody();
	}
	
	@Test
	public void testThatTransportplanIdDoesNotFound() throws Exception {
		DelayDto delayDto = new DelayDto(1L, 60);
		webTestClient.post()
			.uri(String.format("%s/%s/delay", BASE_URI, 1L))
				.headers(headers -> headers.setBearerAuth(jwtToken)).bodyValue(delayDto).exchange().expectStatus()
				.isNotFound();
	}
	
	@Test
	public void testThatMilestoneIdNotPartOfTransportPlan() throws Exception {
		DelayDto delayDto = new DelayDto(8L, 120);
		webTestClient.post()
			.uri(String.format("%s/%s/delay", BASE_URI, 11L))
				.headers(headers -> headers.setBearerAuth(jwtToken)).bodyValue(delayDto).exchange().expectStatus()
				.isBadRequest();
	}
	
	@Test
	public void testThatThePlannedTimeAtMilestoneWasIncreased() throws Exception {		
		DelayDto delayDto = new DelayDto(7L, 30);
		Milestone milestoneBefore = milestoneRepository.findById(delayDto.getId()).get();
		LocalDateTime plannedTimeBefore = milestoneBefore.getPlannedTime();
		registerDelayIsOk(11L, delayDto);
		Milestone milestoneAdjusted = milestoneRepository.findById(delayDto.getId()).get();
		assertThat(milestoneAdjusted.getPlannedTime()).isEqualTo(plannedTimeBefore.plusMinutes(delayDto.getDelay()));
	}
	
	@Test
	public void testTheExpectedRevenuePercentageReduction() throws Exception {
		Long transportplanId = 11L;
		DelayDto delayDto = new DelayDto(7L, 30);
		TransportPlan transportplanBefore = transportPlanRepository.findById(transportplanId).get();
		Long incomeBefore = transportplanBefore.getIncome();
		Long incomeExpected = (incomeBefore * (100 - getIncomeDeclinePercentage(delayDto.getDelay()))) / 100;
		registerDelayIsOk(transportplanId, delayDto);
		TransportPlan transportplanAdjusted = transportPlanRepository.findById(transportplanId).get();
		assertThat(transportplanAdjusted.getIncome()).isEqualTo(incomeExpected);
	}
	
	private void registerDelayIsOk(Long transportplanId, DelayDto delayDto) {
		webTestClient.post()
		.uri(String.format("%s/%s/delay", BASE_URI, transportplanId))
			.headers(headers -> headers.setBearerAuth(jwtToken)).bodyValue(delayDto).exchange().expectStatus()
			.isOk();
	}
	
	private long getIncomeDeclinePercentage(int delay) {
		TreeMap<Integer, Integer> limits = config.getIncomeDeclinePercentage().getLimits();
		Entry<Integer, Integer> floorEntry = limits.floorEntry(delay);		
		return floorEntry == null ? 0 : floorEntry.getValue();
	}
}
