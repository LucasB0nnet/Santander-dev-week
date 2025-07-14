package me.dio.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;

@Service
public class UserServImpl implements UserService{

	private final UserRepository userRepository;
	
	public UserServImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	@Override
	public User create(User user) {
		if(user.getId() != null && userRepository.existsById(user.getId())) {
			throw new IllegalArgumentException("This user already exists.");
		}
		return userRepository.save(user);
	}

}
