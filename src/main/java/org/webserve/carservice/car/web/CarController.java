package org.webserve.carservice.car.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.webserve.carservice.car.data.Car;
import org.webserve.carservice.car.service.CarDataService;
import org.webserve.carservice.carservice.data.CarService;
import org.webserve.carservice.carservice.service.CarServiceService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarDataService carDataService;
    private final CarServiceService carServiceService;

    @GetMapping
    public String cars() {
        return "redirect:/cars/viewAllCars";
    }

    @GetMapping("/viewAllCars")
    public String viewAllCars(Model model) {
        model.addAttribute("cars", carDataService.getAllCars());
        return "car/viewAllCars";
    }

    @GetMapping("/{carId}")
    public String viewCar(@PathVariable Long carId, Model model) {
        model.addAttribute("car", carDataService.getById(carId).orElseThrow());
        return "car/viewCar";
    }

    // Adding, editing and deleting cars
    @GetMapping("/addCar")
    public String addCar(Model model) {
        model.addAttribute("car", new Car());
        return "car/addCar";
    }

    @PostMapping("/addCar")
    public String addCar(@ModelAttribute Car car, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "car/addCar";
        }

        Long savedCarId = carDataService.saveCar(car).getId();
        redirectAttributes.addAttribute("savedCarId", savedCarId);
        return "redirect:/cars/{savedCarId}";
    }

    @GetMapping("/editCar")
    public String editCar(@RequestParam Long id, Model model) {
        Car car = carDataService.getById(id).orElseThrow();
        model.addAttribute("car", car);
        return "car/editCar";
    }

    @PostMapping("/editCar")
    public String editCar(@ModelAttribute Car car, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors())
            return "car/editCar";

        Long editedCarId = car.getId();
        redirectAttributes.addAttribute("editedCarId", editedCarId);
        carDataService.saveCar(car);
        return "redirect:/cars/{editedCarId}";
    }

    @PostMapping("/deleteCar")
    public String deleteCar(@RequestParam Long id) {
        carDataService.deleteCarById(id);
        return "car/viewAllCars";
    }

    @GetMapping("/addCarService")
    public String addCarService(@RequestParam("id") Long carId, Model model) {
        model.addAttribute("car", carDataService.getById(carId).orElseThrow());
        model.addAttribute("service", new CarService());
        return "carservice/addCarService";
    }

    @PostMapping("/{carId}/addCarService")
    public String addCarService(@PathVariable Long carId, @ModelAttribute CarService carService, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors())
            return "carservice/addCarService";

        Car car = carDataService.getById(carId).orElseThrow();
        car.getCarService().add(carService);
        carService.setCar(car);
        carServiceService.saveCarService(carService);
        carDataService.saveCar(car);
        redirectAttributes.addAttribute("carId", carId);
        return "redirect:/cars/{carId}";
    }

    @GetMapping("/editCarService")
    public String editCarService(@RequestParam Long carId, @RequestParam Long serviceId, Model model) {
        model.addAttribute("car", carDataService.getById(carId).orElseThrow());
        model.addAttribute("service", carServiceService.getByCarServiceId(serviceId).orElseThrow());

        return "carservice/editCarService";
    }

    @PostMapping("/{carId}/editCarService")
    public String editCarService(@PathVariable Long carId,
                                 @ModelAttribute CarService carService, BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            return "redirect:/editCarService";
        }

        Car car = carDataService.getById(carId).orElseThrow();
        redirectAttributes.addAttribute("carId", carId);
        redirectAttributes.addAttribute("serviceId", carService.getId());

        car.getCarService().add(carService);
        carService.setCar(car);
        carDataService.saveCar(car);
        carServiceService.saveCarService(carService);
        return "redirect:/cars/{carId}";
    }

    @PostMapping("/deleteCarService")
    public String deleteCarService(@RequestParam Long carId,@RequestParam Long serviceId,RedirectAttributes redirectAttributes){
        carServiceService.deleteCarService(serviceId);
        redirectAttributes.addAttribute("carId",carId);

        return "redirect:/cars/{carId}";
    }
}
