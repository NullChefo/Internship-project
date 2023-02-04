package org.webserve.carservice.user.web;

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
import org.webserve.carservice.car.service.CarDataService;
import org.webserve.carservice.carservices.data.Vignette;
import org.webserve.carservice.carservices.data.Vignette;
import org.webserve.carservice.carservices.data.Vignette;
import org.webserve.carservice.user.service.VignetteDataService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vignettes")
public class VignetteController {



	private final VignetteDataService vignetteDataService;

	private final CarDataService carDataService;


	@GetMapping("/addVignette")
	public String addVignette(@RequestParam("id") Long carId, Model model) {
		model.addAttribute("car", carDataService.getById(carId).orElseThrow());
		model.addAttribute("service", new Vignette());
		return "vignette/addVignette";
	}

	@PostMapping("/{carId}/addVignette")
	public String addVignette(@PathVariable Long carId, @ModelAttribute Vignette vignette, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors())
			return "vignette/addVignette";

		Car car = carDataService.getById(carId).orElseThrow();
		car.getCarVignette().add(vignette);
		vignette.setCar(car);
		vignetteDataService.saveVignette(vignette);
		carDataService.saveCar(car);
		redirectAttributes.addAttribute("carId", carId);
		return "redirect:/cars/{carId}";
	}

	@GetMapping("/editVignette")
	public String editVignette(@RequestParam Long carId, @RequestParam Long serviceId, Model model) {
		model.addAttribute("car", carDataService.getById(carId).orElseThrow());
		model.addAttribute("service", vignetteDataService.getByVignetteId(serviceId).orElseThrow());

		return "vignette/editVignette";
	}

	@PostMapping("/{carId}/editVignette")
	public String editVignette(@PathVariable Long carId,
								 @ModelAttribute Vignette vignette, BindingResult bindingResult,
								 RedirectAttributes redirectAttributes) {

		if(bindingResult.hasErrors()){
			return "redirect:/editVignette";
		}

		Car car = carDataService.getById(carId).orElseThrow();
		redirectAttributes.addAttribute("carId", carId);
		redirectAttributes.addAttribute("serviceId", vignette.getId());

		car.getCarVignette().add(vignette);
		vignette.setCar(car);
		carDataService.saveCar(car);
		vignetteDataService.saveVignette(vignette);
		return "redirect:/cars/{carId}";
	}

	@PostMapping("/deleteVignette")
	public String deleteVignette(@RequestParam Long carId,@RequestParam Long serviceId,RedirectAttributes redirectAttributes){
		vignetteDataService.deleteVignette(serviceId);
		redirectAttributes.addAttribute("carId",carId);

		return "redirect:/cars/{carId}";
	}


}
