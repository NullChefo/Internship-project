package org.webserve.carservice.car.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.webserve.carservice.carservices.data.CarService;
import org.webserve.carservice.carservices.data.TechnicalInspection;
import org.webserve.carservice.carservices.data.Vignette;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Year;
import java.util.Set;

import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.EAGER;

@Entity
@Data
public class Car {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String currentOwner;
    @NotBlank
    private String make;
    @NotBlank
    private String model;
    @NotNull
    private Year productionYear;
    @NotBlank
    private String registration;
    @OneToMany(mappedBy = "car", fetch = EAGER, cascade = REMOVE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<CarService> carService;

	@OneToMany(mappedBy = "car", fetch = EAGER, cascade = REMOVE)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Vignette> carVignette;

	@OneToMany(mappedBy = "car", fetch = EAGER, cascade = REMOVE)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<TechnicalInspection> carTechnicalInspection;




}
