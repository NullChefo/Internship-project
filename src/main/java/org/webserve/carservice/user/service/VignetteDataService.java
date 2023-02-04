package org.webserve.carservice.user.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.webserve.carservice.car.data.Car;
import org.webserve.carservice.carservices.data.Vignette;
import org.webserve.carservice.carservices.data.Vignette;
import org.webserve.carservice.user.repository.VignetteDataRepository;
import org.webserve.carservice.user.repository.VignetteDataRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VignetteDataService {


	private final VignetteDataRepository vignetteRepository;
	private final ModelMapper modelMapper;

	public List<Vignette> getByCar(Car car) {
		return vignetteRepository.findByCarId(car.getId());
	}

	public Optional<Vignette> getByVignetteId(Long id) {
		return vignetteRepository.findById(id).map(cs -> modelMapper.map(cs, Vignette.class));
	}

	public Vignette saveVignette(Vignette Vignette) {
		return vignetteRepository.save(Vignette);
	}

	public Vignette updateVignette(Vignette updatedVignette) {
		return vignetteRepository.save(updatedVignette);
	}

	public void deleteVignette(Long serviceId) {
		vignetteRepository.deleteById(serviceId);
	}
	
}
