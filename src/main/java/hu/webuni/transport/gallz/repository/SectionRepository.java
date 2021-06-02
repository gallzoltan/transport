package hu.webuni.transport.gallz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.transport.gallz.model.Section;

public interface SectionRepository extends JpaRepository<Section, Long>  {

	//@EntityGraph(attributePaths = {"transportplan"})
	@Query("SELECT s FROM Section s JOIN s.fromMilestone m WHERE s.transportplan.id=:id")
	List<Section> findAllSection(Long id);

}
