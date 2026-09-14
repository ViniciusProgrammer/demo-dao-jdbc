package model.dao;

import model.dao.impl.VendedorDaoJDBC;

public class DaoFactory {
    public static VendedorDAO criarVendedorDao() {
        return new VendedorDaoJDBC();
    }
}
