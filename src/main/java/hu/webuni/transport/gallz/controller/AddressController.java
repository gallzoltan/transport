package hu.webuni.transport.gallz.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.transport.gallz.dto.AddressDto;
import hu.webuni.transport.gallz.dto.AddressFilterDto;
import hu.webuni.transport.gallz.model.Address;
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
	
	@GetMapping("/{id}")
	public AddressDto getOneAddress(@PathVariable Long id) {
		Address address = addressService.getOneAddress(id);
		if(address == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return addressMapper.addressToDto(address);
	}	
	
	@PostMapping
	public AddressDto createAddress(@RequestBody @Valid AddressDto addressDto) {
		if((addressDto.equals(null)) || !(addressDto.getId()==null)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else {
			return addressMapper.addressToDto(addressService.save(addressMapper.dtoToAddress(addressDto)));
		}
	}
	
	@DeleteMapping("/{id}")
    public AddressDto deleteAddress(@PathVariable Long id) {
		return addressMapper.addressToDto(addressService.delete(id));		
	}
	
	@PutMapping("/{id}")
	public AddressDto modifyAddress(@PathVariable Long id, @RequestBody @Valid AddressDto addressDto) {
		Long rbId = addressDto.getId(); 
		if((addressDto.equals(null)) || (rbId != null && rbId == id)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else {
			addressDto.setId(id);
			Address updatedAddress = addressService.update(addressMapper.dtoToAddress(addressDto));
			if(updatedAddress == null) 
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			else 
				return addressMapper.addressToDto(updatedAddress);
		}
	}
	
	@PostMapping(value = "/search")
	public ResponseEntity<List<AddressDto>> findByAddress(@RequestBody AddressFilterDto example, Pageable pageable) {
		if(!example.equals(null)) {
			Page<Address> pg = addressService.findAddressByExample(example, pageable);			
			HttpHeaders httpHeaders = new HttpHeaders();
		    httpHeaders.add("X-Total-Count", String.valueOf(pg.getTotalElements()));
			return new ResponseEntity<>(addressMapper.addressesToDtos(pg.getContent()), httpHeaders, HttpStatus.OK);
			//return addressMapper.addressesToDtos(pg.getContent());
		}			
		else
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	}
}
