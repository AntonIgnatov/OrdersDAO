package net.bigmir.venzor.DAO;

import java.util.List;

public interface SimpleDAO<T> {
    void add(T arg);
    void delete(String name);
    List<T> getAll();
    T find(String name);

}
