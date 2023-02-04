package org.webserve.carservice.carservices.data;

import static javax.persistence.FetchType.LAZY;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.webserve.carservice.car.data.Car;

import lombok.Data;

@Entity
@Data
public class TechnicalInspection {


	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private Date startDate;
	@NotNull
	private Date endDate;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "car_id", nullable = false)
	private Car car;


}
