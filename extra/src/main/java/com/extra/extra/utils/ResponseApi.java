package com.extra.extra.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseApi<T> {
    private T data;
    private String message;
    private int status;
}
