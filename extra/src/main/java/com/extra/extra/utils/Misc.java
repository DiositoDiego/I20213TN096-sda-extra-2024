package com.extra.extra.utils;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

public class Misc {
    public static String getErrors(Set<ConstraintViolation<?>> errors){
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<?> error : errors) {
            sb.append(error.getMessage());
            sb.append(", ");
        }
        return sb.substring(0, sb.toString().length() - 2);
    }
}
