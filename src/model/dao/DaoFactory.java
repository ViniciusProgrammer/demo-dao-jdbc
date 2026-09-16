package model.dao;

import exceptions.DB;
import model.dao.impl.VendedorDaoJDBC;

public class DaoFactory {
    public static VendedorDAO criarVendedorDao() {
        return new VendedorDaoJDBC(DB.getConnection());
    }
}
