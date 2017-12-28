package com.learning.recruiter.mapper;

import com.learning.recruiter.entity.BaseEntity;
import org.dozer.DozerBeanMapper;

/**
 * Mapper for mapping from/to Entity. This could be done using BeanUtils.copyProperties and extending only non-similar properties.
 * @param <S>
 * @param <T>
 */
public abstract class BaseMapper<S, T extends BaseEntity> implements Mapper<S, T> {
    private Class<S> dtoClass;
    private Class<T> entityClass;

    private static org.dozer.Mapper mapper = new DozerBeanMapper();

    protected BaseMapper(Class<S> dtoClass, Class<T> entityClass) {
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
    }

    @Override
    public T mapToEntity(S s) {
        return mapper.map(s, entityClass);
    }

    @Override
    public S mapFromEntity(T t) {
        return mapper.map(t, dtoClass);
    }
}
