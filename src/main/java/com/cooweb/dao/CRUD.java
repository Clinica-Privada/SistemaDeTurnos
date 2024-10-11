package com.cooweb.dao;

import java.util.List;
import jakarta.transaction.Transactional;
@Transactional
public interface CRUD<T> {
    List<T> listarTodos();
    T leerPorId(int id);
    void registrar(T t);
    void actualizar(T t);
    void eliminar(int id);
}