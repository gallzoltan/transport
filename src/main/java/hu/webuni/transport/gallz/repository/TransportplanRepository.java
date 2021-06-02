package hu.webuni.transport.gallz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.transport.gallz.model.TransportPlan;

public interface TransportplanRepository extends JpaRepository<TransportPlan, Long> {

	@EntityGraph("TransportPlan-entity-graph-with-milestone")
	@Query("SELECT t FROM TransportPlan t")
	//@Query("SELECT DISTINCT t FROM TransportPlan t LEFT JOIN FETCH t.sections")
	List<TransportPlan> findAllWithSection();

}
