package org.webserve.carservice.carservice.data;

import lombok.Data;
import org.webserve.carservice.car.data.Car;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity
@Data
public class CarService {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private Date date;
    @NotNull
    private Long odometerReading;
    @NotBlank
    private String description;
    private boolean scheduled;
    @NotNull
    private Date nextDate;
    @NotNull
    private Long nextChangeIn;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "car_id",nullable = false)
    private Car car;
}
