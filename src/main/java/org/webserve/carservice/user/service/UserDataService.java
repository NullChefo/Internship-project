package org.webserve.carservice.user.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.webserve.carservice.security.User;
import org.webserve.carservice.user.repository.UserDataRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDataService {

	private final UserDataRepository userDataRepository;
	private final ModelMapper modelMapper;

	public List<User> getAllUsers() {
		return userDataRepository.findAll();
	}

	public Optional<User> getById(Long id) {
		return userDataRepository.findById(id);
	}

	public User saveUser(User user) {
		return userDataRepository.save(user);
	}

	public void deleteUserById(Long id) {
		userDataRepository.deleteById(id);
	}
}
