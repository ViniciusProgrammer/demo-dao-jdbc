package application;

import model.dao.DaoFactory;
import model.dao.VendedorDAO;
import model.entities.Departamento;
import model.entities.Vendedor;

import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        Departamento departamento1 = new Departamento(1, "Books");
        Vendedor vendedor1 = new Vendedor(21, "bob", "bob@gmail.com", new Date(), 3000.0, departamento1);

        System.out.println(departamento1);
        System.out.println(vendedor1);

        VendedorDAO vendedorDAO = DaoFactory.criarVendedorDao();

        System.out.println("test 01 seller findById");

        System.out.println(vendedor1);

        System.out.println("test 02 seller findByIdDepartment");
        Departamento departamento2 = new Departamento(2, null);
        List<Vendedor> vendedores = vendedorDAO.findByDepartament(departamento2);

        for (Vendedor vendedor : vendedores) {
            System.out.println(vendedor);
        }

        System.out.println("test 03 seller findByAll");
        vendedores = vendedorDAO.findAll();

        for (Vendedor vendedor : vendedores) {
            System.out.println(vendedor);
        }

        System.out.println("test 04 seller Insert");
        Vendedor vendedor2 = new Vendedor(null, "Greg", "greg@gmail.com", new Date(), 4000.0, departamento1);

        vendedorDAO.insert(vendedor2);
        System.out.println(vendedor2.getId());
    }
}
