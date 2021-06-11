package hu.webuni.transport.gallz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.transport.gallz.model.Milestone;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
	List<Milestone> findByAddressId(long id);
}
