package com.mindden.service;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mindden.mapper.CheckingsMapper;
import com.mindden.mapper.RequestsMapper;
import com.mindden.mapper.UsersMapper;
import com.mindden.model.CheckingBasicInfo;
import com.mindden.model.RequestBasicInfo;
import com.mindden.model.UserInfo;
import com.mindden.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {
	
	private final UsersRepository usersRepository;
	private final UsersMapper usersMapper;
	private final CheckingsMapper checkingsMapper;
	private final RequestsMapper requestsMapper;
	
	@Transactional
	public void create(UserInfo dto) {	
		usersRepository.save(usersMapper.toEntity(dto));
	}
	
	public List<UserInfo> findAll(){
        return usersMapper.toDtos(usersRepository.findAll());
	}
	
	public Collection<CheckingBasicInfo> findCheckingsUserById(final Integer id) {
		return checkingsMapper.toBasicDtos(usersRepository.findById(id).get().getCheckings());
	}
	
	public UserInfo findById(final Integer id) {
		return usersMapper.toDto(usersRepository.findById(id).get());
	}

	public Collection<RequestBasicInfo> findRequestsUserById(final Integer id) {
		return requestsMapper.toBasicDtos(usersRepository.findById(id).get().getRequests());
	}
}
