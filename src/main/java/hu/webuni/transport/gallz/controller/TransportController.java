package hu.webuni.transport.gallz.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.transport.gallz.dto.DelayDto;

@RestController
@RequestMapping("/api/transportPlan")
public class TransportController {

	@PostMapping("/{id}/delay")
	public void registerDelay(@PathVariable Long id, @RequestBody DelayDto delayDto) {
		
	}
}
