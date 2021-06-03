package hu.webuni.transport.gallz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.transport.gallz.model.TransportPlan;

public interface TransportplanRepository extends JpaRepository<TransportPlan, Long> {

	@EntityGraph("TransportPlan-entitygraph-full")
	@Query("SELECT t FROM TransportPlan t")
	Optional<TransportPlan> findById(Long id);

}
