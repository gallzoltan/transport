package hu.webuni.transport.gallz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.transport.gallz.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {

	@Query("SELECT a FROM Address a WHERE a.id=:id")
	public Address findAddress(long id);
}
