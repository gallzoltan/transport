package hu.webuni.transport.gallz.mapper;

import hu.webuni.transport.gallz.dto.AddressDto;
import hu.webuni.transport.gallz.model.Address;

import java.util.List;

import javax.validation.Valid;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	
	AddressDto addressToDto(Address address);
	
	//@Mapping(ignore = true, target = "milestones")
	Address dtoToAddress(@Valid AddressDto addressDto);
	
	List<AddressDto> addressesToDtos(List<Address> address);
	List<Address> dtosToAddresses(List<AddressDto> addressDto);
}
