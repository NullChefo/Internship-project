package org.webserve.carservice.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.webserve.carservice.carservices.data.CarService;
import org.webserve.carservice.carservices.data.Vignette;

public interface VignetteDataRepository extends JpaRepository<Vignette, Long> {

	List<Vignette> findByCarId(Long carId);

}
