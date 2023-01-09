package com.example.stockcart.common.resource;

import static com.example.stockcart.common.resource.Status.EMPTY;
import static com.example.stockcart.common.resource.Status.ERROR;
import static com.example.stockcart.common.resource.Status.LOADING;
import static com.example.stockcart.common.resource.Status.SUCCESS;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<E> {

    @NonNull
    public Status status;

    @Nullable
    public E data;

    @Nullable
    private final String message;

    @Nullable
    private final String code;

    public Resource(@NonNull Status status, @Nullable E data, @Nullable String message, @Nullable String code) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public static <E> Resource<E> success(@Nullable E data, @Nullable String message) {
        return new Resource<>(SUCCESS, data, message, null);
    }

    public static <E> Resource<E> error(String message) {
        return new Resource<>(ERROR, null, message, null);
    }

    public static <E> Resource<E> textError(String message, String code) {
        return new Resource<>(ERROR, null, message, code);
    }

    public static <E> Resource<E> loading() {
        return new Resource<>(LOADING, null, null, null);
    }

    public static <E> Resource<E> empty() {
        return new Resource<>(EMPTY, null, null, null);
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    @Nullable
    public String getCode() {
        return code;
    }
}
