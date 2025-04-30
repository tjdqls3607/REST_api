package com.mycom.myapp.auth.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserResultDto;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService{

	private final UserRepository userRepository;
	
	@Override
	public UserResultDto login(String email, String password) {

		UserResultDto userResultDto = new UserResultDto(); 
		
		log.debug("login() 시작");
		
		// #1 id 가 아닌 email 로 find
		// 2개의 select 수행
//		Optional<User> optionalUser = userRepository.findByEmail(email);
		
		// #2 id 로 하드코딩 테스트
		// 1개의 join 을 톻한 select
//		Optional<User> optionalUser = userRepository.findById(4L);
		
		// #3 JPQL 이용
		Optional<User> optionalUser = userRepository.findByEmail(email);
		
		if (optionalUser.isPresent()) {
			
			User user = optionalUser.get();
			if (user.getPassword().equals(password)) {
				// user -> UserDto
				// UserRole 도 함께
				Map<Integer, String> roles = new HashMap<>();
				user.getUserRoles().forEach(userRole -> roles.put(userRole.getId(), userRole.getName()));
				
				UserDto userDto = UserDto.builder()
						.id(user.getId())
						.name(user.getName())
						.email(user.getEmail())
						.roles(roles)
						.build();
				
				userResultDto.setUserDto(userDto);
				userResultDto.setResult("success");
			}else {
				userResultDto.setResult("fail");
			}
			
		}else {
			userResultDto.setResult("notfound");
		}
		log.debug("login() 종료");
		
		return userResultDto;
	}

}
