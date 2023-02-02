package org.webserve.carservice.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.webserve.carservice.carservice.data.CarService;

import java.util.List;

public interface CarServiceRepository extends JpaRepository<CarService, Long> {
    List<CarService> findByCarId(Long carId);
}
