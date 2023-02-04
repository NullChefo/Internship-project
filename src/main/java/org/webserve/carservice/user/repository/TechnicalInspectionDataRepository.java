package org.webserve.carservice.user.repository;

import static javax.persistence.FetchType.LAZY;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.webserve.carservice.car.data.Car;
import org.webserve.carservice.carservices.data.CarService;
import org.webserve.carservice.carservices.data.TechnicalInspection;
import org.webserve.carservice.security.User;

import lombok.Data;


public interface TechnicalInspectionDataRepository  extends JpaRepository<TechnicalInspection, Long> {

	List<TechnicalInspection> findByCarId(Long carId);

}



