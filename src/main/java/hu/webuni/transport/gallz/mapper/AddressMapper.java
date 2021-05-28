package hu.webuni.transport.gallz.mapper;

import hu.webuni.transport.gallz.dto.AddressDto;
import hu.webuni.transport.gallz.model.Address;

import javax.validation.Valid;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	
	AddressDto addressToDto(Address address);
	Address dtoToAddress(@Valid AddressDto addressDto);
}
