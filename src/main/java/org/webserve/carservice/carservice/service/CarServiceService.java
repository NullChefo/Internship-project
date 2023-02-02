package org.webserve.carservice.carservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.webserve.carservice.car.data.Car;
import org.webserve.carservice.carservice.data.CarService;
import org.webserve.carservice.carservice.repository.CarServiceRepository;

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
        return carServiceRepository.findById(id).map(CarService -> modelMapper.map(CarService, CarService.class));
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
