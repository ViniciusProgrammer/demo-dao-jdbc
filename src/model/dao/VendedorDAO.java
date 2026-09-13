package model.dao;

import model.entities.Vendedor;

import java.util.List;

public interface VendedorDAO {
    void insert(Vendedor obj);

    void update(Vendedor obj);

    void deleteById(Integer id);

    Vendedor findById(Integer id);

    List<Vendedor> findAll();
}
