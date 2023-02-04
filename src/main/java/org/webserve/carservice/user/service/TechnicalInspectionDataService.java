package org.webserve.carservice.user.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.webserve.carservice.car.data.Car;
import org.webserve.carservice.carservices.data.TechnicalInspection;
import org.webserve.carservice.user.repository.TechnicalInspectionDataRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TechnicalInspectionDataService {

	private final TechnicalInspectionDataRepository technicalInspectionRepository;
	private final ModelMapper modelMapper;

	public List<TechnicalInspection> getByCar(Car car) {
		return technicalInspectionRepository.findByCarId(car.getId());
	}

	public Optional<TechnicalInspection> getByTechnicalInspectionId(Long id) {
		return technicalInspectionRepository.findById(id).map(cs -> modelMapper.map(cs, TechnicalInspection.class));
	}

	public TechnicalInspection saveTechnicalInspection(TechnicalInspection TechnicalInspection) {
		return technicalInspectionRepository.save(TechnicalInspection);
	}

	public TechnicalInspection updateTechnicalInspection(TechnicalInspection updatedTechnicalInspection) {
		return technicalInspectionRepository.save(updatedTechnicalInspection);
	}

	public void deleteTechnicalInspection(Long serviceId) {
		technicalInspectionRepository.deleteById(serviceId);
	}
}
