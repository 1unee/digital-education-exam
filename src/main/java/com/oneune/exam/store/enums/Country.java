package com.oneune.exam.store.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Country {

    RUSSIA("RU", 643),
    GREECE("GR", 300),
    BELARUS("BY", 112),
    SPAIN("ES", 724),
    KAZAKHSTAN("KZ", 398);

    /**
     * In format Alpha2
     */
    String code;
    Integer ISO;
}
