package hu.webuni.transport.gallz.dto;

import javax.validation.constraints.Size;

import hu.webuni.transport.gallz.model.Milestone;

public class AdrressDto {
    private Long id;
	
    @Size(min=2, max=2)
	private String contry;
	private String zipcode;
	private String city;
	private String street;
	private String house;
	
	@Size(min=-90, max=90)
	private Float latitude;
	
	@Size(min=-180, max=180)
	private Float longitude;
	
	private Milestone milestone;

	public AdrressDto() {}

	public AdrressDto(Long id, @Size(min = 2, max = 2) String contry, String zipcode, String city, String street,
			String house, @Size(min = -90, max = 90) Float latitude, @Size(min = -180, max = 180) Float longitude,
			Milestone milestone) {
		this.id = id;
		this.contry = contry;
		this.zipcode = zipcode;
		this.city = city;
		this.street = street;
		this.house = house;
		this.latitude = latitude;
		this.longitude = longitude;
		this.milestone = milestone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContry() {
		return contry;
	}

	public void setContry(String contry) {
		this.contry = contry;
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

	public Milestone getMilestone() {
		return milestone;
	}

	public void setMilestone(Milestone milestone) {
		this.milestone = milestone;
	}
		
}
