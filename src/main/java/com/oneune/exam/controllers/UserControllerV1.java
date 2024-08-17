package com.oneune.exam.controllers;

import com.oneune.exam.contracts.interfaces.CRUDable;
import com.oneune.exam.services.UserService;
import com.oneune.exam.store.dtos.UserDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserControllerV1 implements CRUDable<UserDto> {

    UserService userService;

    @PostMapping
    @Override
    public UserDto post(@RequestBody UserDto user) {
        return userService.post(user);
    }

    @GetMapping("{user-id}")
    @Override
    public UserDto getById(@PathVariable(name = "user-id") Long userId) {
        return userService.getById(userId);
    }

    @PutMapping("{user-id}")
    @Override
    public UserDto put(@PathVariable(name = "user-id") Long userId, @RequestBody UserDto user) {
        return userService.post(user);
    }

    @DeleteMapping("{user-id}")
    @Override
    public UserDto deleteById(@PathVariable(name = "user-id") Long userId) {
        return userService.deleteById(userId);
    }

    @GetMapping
    @Override
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("search")
    @Override
    public List<UserDto> search(@RequestParam int page, @RequestParam int size) {
        return userService.search(page, size);
    }

    @GetMapping("additional-info")
    public List<UserDto> getByParams(@RequestParam Integer age) {
        return userService.getByParams(age);
    }
}
