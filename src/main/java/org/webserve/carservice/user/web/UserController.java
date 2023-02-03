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
import org.webserve.carservice.security.User;
import org.webserve.carservice.user.service.UserDataService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

	private final UserDataService userDataService;

	@GetMapping
	public String users() {
		return "redirect:/users/viewAllUsers";
	}

	@GetMapping("/viewAllUsers")
	public String viewAllUsers(Model model) {
		model.addAttribute("users", userDataService.getAllUsers());
		return "user/viewAllUsers";
	}

	@GetMapping("/{userId}")
	public String viewUser(@PathVariable Long userId, Model model) {
		model.addAttribute("user", userDataService.getById(userId).orElseThrow());
		return "user/viewUser";
	}

	// Adding, editing and deleting users
	@GetMapping("/addUser")
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		return "user/addUser";
	}

	@PostMapping("/addUser")
	public String addUser(
			@ModelAttribute User user,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "user/addUser";
		}

		Long savedUserId = userDataService.saveUser(user).getId();
		redirectAttributes.addAttribute("savedUserId", savedUserId);
		return "redirect:/users/{savedUserId}";
	}

	@GetMapping("/editUser")
	public String editUser(@RequestParam Long id, Model model) {
		User user = userDataService.getById(id).orElseThrow();
		model.addAttribute("user", user);
		return "user/editUser";
	}

	@PostMapping("/editUser")
	public String editUser(
			@ModelAttribute User user,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "user/editUser";
		}

		Long editedUserId = user.getId();
		redirectAttributes.addAttribute("editedUserId", editedUserId);
		userDataService.saveUser(user);
		return "redirect:/users/{editedUserId}";
	}

	@PostMapping("/deleteUser")
	public String deleteUser(@RequestParam Long id) {
		userDataService.deleteUserById(id);
		return "user/viewAllUsers";
	}

}
