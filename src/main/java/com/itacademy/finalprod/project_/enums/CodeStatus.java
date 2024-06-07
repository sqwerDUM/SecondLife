package com.itacademy.finalprod.project_.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum CodeStatus {
    NEW("Новый"),
    USED("Использован"),
            ;
    String DESCRIPTION;
}
