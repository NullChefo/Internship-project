package org.webserve.carservice.carservices.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.webserve.carservice.car.data.Car;
import org.webserve.carservice.carservices.data.CarService;
import org.webserve.carservice.carservices.repository.CarServiceRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceService {
    private final CarServiceRepository carServiceRepository;
    private final ModelMapper modelMapper;

    public List<CarService> getByCar(Car car) {
        return carServiceRepository.findByCarId(car.getId());
    }

    public Optional<CarService> getByCarServiceId(Long id) {
        return carServiceRepository.findById(id).map(cs -> modelMapper.map(cs, CarService.class));
    }

    public CarService saveCarService(CarService carService) {
        return carServiceRepository.save(carService);
    }

    public CarService updateCarService(CarService updatedCarService) {
        return carServiceRepository.save(updatedCarService);
    }

    public void deleteCarService(Long serviceId) {
        carServiceRepository.deleteById(serviceId);
    }
}
