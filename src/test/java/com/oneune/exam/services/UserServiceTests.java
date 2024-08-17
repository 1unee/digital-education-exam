package com.oneune.exam.services;

import com.oneune.exam.readers.UserReader;
import com.oneune.exam.repositories.UserRepository;
import com.oneune.exam.store.dtos.UserDto;
import com.oneune.exam.store.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    ModelMapper modelMapper;
    @Mock
    UserReader userReader;
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;
    UserDto userDto;
    UserEntity userEntity;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userEntity = new UserEntity();
    }

    @Test
    @DisplayName("Проверка метода post: создание и сохранение нового пользователя")
    void shouldCreateAndSaveUser() {
        // дано (given)
        when(modelMapper.map(userDto, UserEntity.class)).thenReturn(userEntity);
        when(modelMapper.map(userEntity, UserDto.class)).thenReturn(userDto);

        // когда (when)
        UserDto result = userService.post(userDto);

        // тогда (then)
        verify(userRepository).saveAndFlush(userEntity);
        assertEquals(userDto, result);
    }

    @Test
    @DisplayName("Проверка метода getById: получение пользователя по ID")
    void shouldGetUserById() {
        // дано
        Long userId = 1L;
        when(userReader.getById(userId)).thenReturn(userDto);

        // когда
        UserDto result = userService.getById(userId);

        // тогда
        verify(userReader).getById(userId);
        assertEquals(userDto, result);
    }

    @Test
    @DisplayName("Проверка метода put: обновление информации о пользователе по ID")
    void shouldUpdateUserById() {
        // дано
        Long userId = 1L;
        when(userReader.getEntityById(userId)).thenReturn(userEntity);
        when(userReader.getById(userId)).thenReturn(userDto);

        // когда
        UserDto result = userService.put(userId, userDto);

        // тогда
        verify(modelMapper).map(userDto, userEntity);
        verify(userRepository).flush();
        assertEquals(userDto, result);
    }

    @Test
    @DisplayName("Проверка метода deleteById: удаление пользователя по ID")
    void shouldDeleteUserById() {
        // дано
        Long userId = 1L;
        when(userReader.getById(userId)).thenReturn(userDto);

        // когда
        UserDto result = userService.deleteById(userId);

        // тогда
        verify(userReader).getById(userId);
        verify(userRepository).deleteById(userId);
        verify(userRepository).flush();
        assertEquals(userDto, result);
    }

    @Test
    @DisplayName("Проверка метода getAll: получение списка всех пользователей")
    void shouldGetAllUsers() {
        // дано
        List<UserDto> userDtos = Arrays.asList(new UserDto(), new UserDto());
        when(userReader.getAll()).thenReturn(userDtos);

        // когда
        List<UserDto> result = userService.getAll();

        // тогда
        verify(userReader).getAll();
        assertEquals(userDtos, result);
    }

    @Test
    @DisplayName("Проверка метода search: поиск пользователей с пагинацией")
    void shouldSearchUsersWithPagination() {
        // дано
        int page = 0;
        int size = 10;
        List<UserDto> userDtos = Arrays.asList(new UserDto(), new UserDto());
        when(userReader.search(page, size)).thenReturn(userDtos);

        // когда
        List<UserDto> result = userService.search(page, size);

        // тогда
        verify(userReader).search(page, size);
        assertEquals(userDtos, result);
    }

    @Test
    @DisplayName("Проверка метода getByParams: получение пользователей по параметрам (например, возраст)")
    void shouldGetUsersByParams() {
        // дано
        int age = 25;
        List<UserDto> userDtos = Arrays.asList(new UserDto(), new UserDto());
        when(userReader.getByParams(age)).thenReturn(userDtos);

        // когда
        List<UserDto> result = userService.getByParams(age);

        // тогда
        verify(userReader).getByParams(age);
        assertEquals(userDtos, result);
    }
}

