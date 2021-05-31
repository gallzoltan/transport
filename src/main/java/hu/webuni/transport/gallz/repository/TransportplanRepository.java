package hu.webuni.transport.gallz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.transport.gallz.model.TransportPlan;

public interface TransportplanRepository extends JpaRepository<TransportPlan, Long> {

}
