package hu.webuni.transport.gallz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.transport.gallz.model.Section;

public interface SectionRepository extends JpaRepository<Section, Long>  {

	//@EntityGraph()
	@Query("SELECT s FROM Section s "
			+ "WHERE s.transportplan.id = :transportPlanId "
			+ "AND (s.fromMilestone.id = :milestoneId OR s.toMilestone.id = :milestoneId)")
	List<Section> findByTransportAndMilestoneId(Long transportPlanId, Long milestoneId);

}
