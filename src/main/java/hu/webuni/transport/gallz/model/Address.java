package hu.webuni.transport.gallz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Address {
	
	@Id
    @GeneratedValue
    private Long id;
	
	@Size(min=2, max=2)
	@NotBlank
	private String country;
	private String zipcode;
	private String city;
	private String street;
	private String house;
	private Float latitude;
	private Float longitude;
	
	//@OneToMany(mappedBy = "address")
	//private List<Milestone> milestones;

	public Address() {}

	public Address(String contry, String zipcode, String city, String street, String house) {
		this.country = contry;
		this.zipcode = zipcode;
		this.city = city;
		this.street = street;
		this.house = house;
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

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

//	public List<Milestone> getMilestones() {
//		return milestones;
//	}
//
//	public void setMilestones(List<Milestone> milestones) {
//		this.milestones = milestones;
//	}
//	
//	public void addMilestone(Milestone milestone) {
//		if(this.milestones==null)
//			this.milestones = new ArrayList<>();
//		this.milestones.add(milestone);
//		milestone.setAddress(this);
//	}
}
