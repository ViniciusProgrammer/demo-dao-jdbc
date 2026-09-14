package application;

import model.dao.DaoFactory;
import model.dao.VendedorDAO;
import model.entities.Departamento;

public class Program {
    public static void main(String[] args) {
        Departamento departamento1 = new Departamento(1, "Books");
        System.out.println(departamento1);

        VendedorDAO vendedorDAO = DaoFactory.criarVendedorDao();
    }
}
