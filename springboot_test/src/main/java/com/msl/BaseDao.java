package com.msl;

import java.util.List;

/**
 * @author msl on 2020/5/8.
 */
public interface BaseDao<ID, T> {
    boolean insert(T t);

    boolean update(T t);

    T getOneById(ID id);

    List<T> getListByParam(T t);
}
