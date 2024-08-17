package com.oneune.exam.store.entities;

import com.oneune.exam.store.enums.Country;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "\"user\"") // sql ругается на служебное слово
@SequenceGenerator(sequenceName = "user_id_seq", name = "id_seq", allocationSize = 1)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class UserEntity extends AbstractEntity {
    String firstName;
    Integer age;
    @Enumerated(EnumType.STRING)
    Country country;
}
