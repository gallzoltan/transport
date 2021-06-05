package hu.webuni.transport.gallz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.transport.gallz.model.TransportPlan;

public interface TransportplanRepository extends JpaRepository<TransportPlan, Long> {

	@EntityGraph("TransportPlan-full")
	@Query("SELECT t FROM TransportPlan t WHERE t.id=:id")
	Optional<TransportPlan> findById(Long id);

}
