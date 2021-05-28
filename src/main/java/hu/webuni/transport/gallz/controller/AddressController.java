package hu.webuni.transport.gallz.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.transport.gallz.dto.AddressDto;
import hu.webuni.transport.gallz.mapper.AddressMapper;
import hu.webuni.transport.gallz.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

	@Autowired
	AddressService addressService;
	
	@Autowired
	AddressMapper addressMapper;
	
	@GetMapping
	public List<AddressDto> getAllAddresses(){
		return addressMapper.addressesToDtos(addressService.getAllAddress());
	}
	
	@PostMapping
	public AddressDto createAddress(@RequestBody @Valid AddressDto addressDto) {
		if(!(addressDto.equals(null)) || (addressDto.getId()==null)) {
			return addressMapper.addressToDto(addressService.save(addressMapper.dtoToAddress(addressDto)));
		} else
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	}
}
