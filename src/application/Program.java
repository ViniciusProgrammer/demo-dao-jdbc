package application;

import model.dao.DaoFactory;
import model.dao.VendedorDAO;
import model.entities.Departamento;
import model.entities.Vendedor;

import java.util.Date;

public class Program {
    public static void main(String[] args) {
        Departamento departamento1 = new Departamento(1, "Books");
        Vendedor vendedor1 = new Vendedor(21, "bob", "bob@gmail.com", new Date(), 3000.0, departamento1);

        System.out.println(departamento1);
        System.out.println(vendedor1);

        VendedorDAO vendedorDAO = DaoFactory.criarVendedorDao();

        System.out.println("test 01 seller findById");

        System.out.println(vendedor1);
    }
}
