package hu.webuni.transport.gallz.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddressDto {
    private Long id;	
    @Size(min=2, max=2)
    @NotBlank
	private String country;
    @NotBlank
	private String zipcode;
    @NotBlank
	private String city;
    @NotBlank
	private String street;
    @NotBlank
	private String house;	
	
	private Float latitude;	
	
	private Float longitude;
	
	public AddressDto() {}

	public AddressDto(String country, String zipcode, String city, String street,
			String house, Float latitude, Float longitude) {
		this.country = country;
		this.zipcode = zipcode;
		this.city = city;
		this.street = street;
		this.house = house;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
		
}
