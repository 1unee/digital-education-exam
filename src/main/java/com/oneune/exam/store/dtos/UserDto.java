package com.oneune.exam.store.dtos;

import com.oneune.exam.store.enums.Country;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class UserDto extends AbstractDto {
    String firstName;
    Integer age;
    Country country;
}
