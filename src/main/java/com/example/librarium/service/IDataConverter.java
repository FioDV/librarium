package com.example.librarium.service;

public interface IDataConverter {
    <T> T getData(String json,Class<T> clazz);
}
