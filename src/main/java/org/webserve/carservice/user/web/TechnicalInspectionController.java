package org.webserve.carservice.user.web;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.webserve.carservice.car.data.Car;
import org.webserve.carservice.car.repository.CarDataRepository;
import org.webserve.carservice.car.service.CarDataService;
import org.webserve.carservice.carservices.data.TechnicalInspection;
import org.webserve.carservice.carservices.data.TechnicalInspection;
import org.webserve.carservice.user.service.TechnicalInspectionDataService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/technicalInspections")
public class TechnicalInspectionController {


	private final TechnicalInspectionDataService technicalInspectionDataService;

	private final CarDataService carDataService;


	@GetMapping("/addTechnicalInspection")
	public String addTechnicalInspection(@RequestParam("id") Long carId, Model model) {
		model.addAttribute("car", carDataService.getById(carId).orElseThrow());
		model.addAttribute("service", new TechnicalInspection());
		return "technicalInspection/addTechnicalInspection";
	}

	@PostMapping("/{carId}/addTechnicalInspection")
	public String addTechnicalInspection(@PathVariable Long carId, @ModelAttribute TechnicalInspection technicalInspection, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors())
			return "technicalInspection/addTechnicalInspection";

		Car car = carDataService.getById(carId).orElseThrow();
		car.getCarTechnicalInspection().add(technicalInspection);
		technicalInspection.setCar(car);
		technicalInspectionDataService.saveTechnicalInspection(technicalInspection);
		carDataService.saveCar(car);
		redirectAttributes.addAttribute("carId", carId);
		return "redirect:/cars/{carId}";
	}

	@GetMapping("/editTechnicalInspection")
	public String editTechnicalInspection(@RequestParam Long carId, @RequestParam Long serviceId, Model model) {
		model.addAttribute("car", carDataService.getById(carId).orElseThrow());
		model.addAttribute("service", technicalInspectionDataService.getByTechnicalInspectionId(serviceId).orElseThrow());

		return "technicalInspection/editTechnicalInspection";
	}

	@PostMapping("/{carId}/editTechnicalInspection")
	public String editTechnicalInspection(@PathVariable Long carId,
								 @ModelAttribute TechnicalInspection technicalInspection, BindingResult bindingResult,
								 RedirectAttributes redirectAttributes) {

		if(bindingResult.hasErrors()){
			return "redirect:/editTechnicalInspection";
		}

		Car car = carDataService.getById(carId).orElseThrow();
		redirectAttributes.addAttribute("carId", carId);
		redirectAttributes.addAttribute("serviceId", technicalInspection.getId());

		car.getCarTechnicalInspection().add(technicalInspection);
		technicalInspection.setCar(car);
		carDataService.saveCar(car);
		technicalInspectionDataService.saveTechnicalInspection(technicalInspection);
		return "redirect:/cars/{carId}";
	}

	@PostMapping("/deleteTechnicalInspection")
	public String deleteTechnicalInspection(@RequestParam Long carId,@RequestParam Long serviceId,RedirectAttributes redirectAttributes){
		technicalInspectionDataService.deleteTechnicalInspection(serviceId);
		redirectAttributes.addAttribute("carId",carId);

		return "redirect:/cars/{carId}";
	}



}
