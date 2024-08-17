package com.oneune.exam.readers;

import com.google.gson.reflect.TypeToken;
import com.oneune.exam.contracts.abstracts.AbstractReader;
import com.oneune.exam.repositories.UserRepository;
import com.oneune.exam.store.dtos.UserDto;
import com.oneune.exam.store.entities.QUserEntity;
import com.oneune.exam.store.entities.UserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserReader extends AbstractReader<UserEntity, UserDto> {

    public final static Type USER_LIST_TYPE = TypeToken.getParameterized(List.class, UserDto.class).getType();
    private final static QUserEntity qUser = QUserEntity.userEntity;

    UserRepository userRepository;
    ModelMapper modelMapper;
    JPAQueryFactory queryFactory;

    @Override
    protected <PARAM> UserEntity readEntity(Function<PARAM, Optional<UserEntity>> query, PARAM param) {
        return query.apply(param).orElseThrow(() -> new EntityNotFoundException("User not found..."));
    }

    @Override
    protected <PARAM> UserDto readDto(Function<PARAM, Optional<UserEntity>> query, PARAM param) {
        return modelMapper.map(readEntity(query, param), UserDto.class);
    }

    @Override
    protected <PARAM> List<UserEntity> readEntities(Function<PARAM, List<UserEntity>> query, PARAM param) {
        return query.apply(param);
    }

    @Override
    protected <PARAM> List<UserDto> readDtos(Function<PARAM, List<UserEntity>> query, PARAM param) {
        return modelMapper.map(readEntities(query, param), USER_LIST_TYPE);
    }

    @Override
    public UserDto getById(Long userId) {
        return readDto(userRepository::findById, userId);
    }

    public UserEntity getEntityById(Long userId) {
        return readEntity(userRepository::findById, userId);
    }

    @Override
    public List<UserDto> getAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return modelMapper.map(userEntities, USER_LIST_TYPE);
    }

    @Override
    public List<UserDto> search(int page, int size) {
        Page<UserEntity> paginatedUserEntities = userRepository.findAll(PageRequest.of(page, size));
        return modelMapper.map(paginatedUserEntities.getContent(), USER_LIST_TYPE);
    }

    public List<UserDto> getByParams(Integer age) {
        List<UserEntity> userEntities = queryFactory.selectFrom(qUser)
                .where(qUser.age.goe(age))
                .orderBy(qUser.firstName.asc())
                .fetch();
        return modelMapper.map(userEntities, USER_LIST_TYPE);
    }
}
