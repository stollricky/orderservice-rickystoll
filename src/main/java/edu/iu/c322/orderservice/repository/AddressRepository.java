package edu.iu.c322.orderservice.repository;

import edu.iu.c322.orderservice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
