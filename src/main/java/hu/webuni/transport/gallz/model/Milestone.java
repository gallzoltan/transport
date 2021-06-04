package hu.webuni.transport.gallz.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Milestone {
	
	@Id
    @GeneratedValue
    private Long id;
	
	private LocalDateTime plannedTime;
	
	@ManyToOne
	private Address address;
	
//	@OneToOne
//	private Section section;

	public Milestone() {}

	public Milestone(LocalDateTime plannedTime, Address address) {
		this.plannedTime = plannedTime;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getPlannedTime() {
		return plannedTime;
	}

	public void setPlannedTime(LocalDateTime plannedTime) {
		this.plannedTime = plannedTime;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
//
//	public Section getSection() {
//		return section;
//	}
//
//	public void setSection(Section section) {
//		this.section = section;
//	}
		
}
