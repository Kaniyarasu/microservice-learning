package com.learning.recruiter.mapper;

public interface Mapper<S, T> {
    T mapToEntity(S s);
    S mapFromEntity(T t);
}
