package org.webserve.carservice.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.webserve.carservice.car.data.Car;

import java.util.List;
import java.util.Optional;

public interface CarDataRepository extends JpaRepository<Car, Long> {

    List<Car> findByCurrentOwner(String currentOwner);

    Optional<Car> findByRegistration(String registration);
}
