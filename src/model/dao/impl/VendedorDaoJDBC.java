package model.dao.impl;

import exceptions.DB;
import exceptions.DbException;
import model.dao.VendedorDAO;
import model.entities.Departamento;
import model.entities.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendedorDaoJDBC implements VendedorDAO {
    private Connection connection;

    public VendedorDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Vendedor obj) {

    }

    @Override
    public void update(Vendedor obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Vendedor findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentId = department.Id "
                            + "WHERE seller.Id = ?");

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Departamento departamento = instanciarDepartamento(resultSet);
                Vendedor vendedor = instanciarVendedor(resultSet, departamento);

                return vendedor;
            }

            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public List<Vendedor> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentId = department.Id "
                            + "ORDER BY Name");

            resultSet = preparedStatement.executeQuery();

            List<Vendedor> vendedores = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();

            while (resultSet.next()) {
                Departamento dep = map.get(resultSet.getInt("DepartmentId"));

                if (dep == null) {
                    dep = instanciarDepartamento(resultSet);
                    map.put(resultSet.getInt("Department"), dep);
                }

                Vendedor vendedor = instanciarVendedor(resultSet, dep);
                vendedores.add(vendedor);
            }

            return vendedores;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public List<Vendedor> findByDepartament(Departamento departamento) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentId = department.Id "
                            + "WHERE DepartmentId = ? "
                            + "ORDER BY Name");

            preparedStatement.setInt(1, departamento.getId());
            resultSet = preparedStatement.executeQuery();

            List<Vendedor> vendedores = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();

            while (resultSet.next()) {
                Departamento dep = map.get(resultSet.getInt("DepartmentId"));

                if (dep == null) {
                    dep = instanciarDepartamento(resultSet);
                    map.put(resultSet.getInt("Department"), dep);
                }

                Vendedor vendedor = instanciarVendedor(resultSet, dep);
                vendedores.add(vendedor);
            }

            return vendedores;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    private Departamento instanciarDepartamento(ResultSet resultSet) throws SQLException {
        Departamento departamento = new Departamento();

        departamento.setId(resultSet.getInt("DepartamentoId"));
        departamento.setNome(resultSet.getString("DepartamentoNome"));

        return departamento;
    }

    private Vendedor instanciarVendedor(ResultSet resultSet, Departamento departamento) throws SQLException {
        Vendedor vendedor = new Vendedor();
        vendedor.setId(resultSet.getInt("Id"));
        vendedor.setNome(resultSet.getString("Nome"));
        vendedor.setEmail(resultSet.getString("Email"));
        vendedor.setSalarioBase(resultSet.getDouble("SalarioBase"));
        vendedor.setDataAniversario(resultSet.getDate("DataAniversario"));
        vendedor.setDepartamento(departamento);

        return vendedor;
    }
}
