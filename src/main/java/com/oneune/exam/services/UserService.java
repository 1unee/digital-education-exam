package com.oneune.exam.services;

import com.oneune.exam.contracts.interfaces.CRUDable;
import com.oneune.exam.readers.UserReader;
import com.oneune.exam.repositories.UserRepository;
import com.oneune.exam.store.dtos.UserDto;
import com.oneune.exam.store.entities.UserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserService implements CRUDable<UserDto> {

    ModelMapper modelMapper;
    UserReader userReader;
    UserRepository userRepository;

    @Override
    public UserDto post(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userRepository.saveAndFlush(userEntity);
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto getById(Long userId) {
        return userReader.getById(userId);
    }

    @Override
    public UserDto put(Long userId, UserDto userDto) {
        UserEntity userEntity = userReader.getEntityById(userId);
        modelMapper.map(userDto, userEntity);
        userRepository.flush();
        return userReader.getById(userId);
    }

    @Override
    public UserDto deleteById(Long userId) {
        UserDto user = userReader.getById(userId);
        userRepository.deleteById(userId);
        userRepository.flush();
        return user;
    }

    @Override
    public List<UserDto> getAll() {
        return userReader.getAll();
    }

    @Override
    public List<UserDto> search(int page, int size) {
        return userReader.search(page, size);
    }

    public List<UserDto> getByParams(Integer age) {
        return userReader.getByParams(age);
    }
}
