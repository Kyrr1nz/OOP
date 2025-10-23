package hrm.db;

import hrm.app.addcity.CityExistedException;
import hrm.app.addemployee.EmployeeExistedException;
import hrm.app.addemployee.HomeCityNotFoundException;
import hrm.app.deleteCity.CityDeleteExistedException;
import hrm.app.deleteCity.CityDeleteHasEmployeesException;
import hrm.app.deleteEmployee.EmployeeDeleteExistedException;
import hrm.app.deleteEmployee.HomeCityDeleteNotFoundException;
import hrm.app.editcity.CityNotFoundException;
import hrm.app.editemployee.EmployeeNotFoundException;
import hrm.app.listallcity.CityInfo;
import hrm.app.listallemployee.EmployeeInfo;
import hrm.core.City;
import hrm.core.Employee;
import hrm.core.InvalidCityException;
import hrm.core.InvalidEmployeeException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MySqlGateway
        implements
        hrm.app.addcity.Gateway,
        hrm.app.addemployee.Gateway,
        hrm.app.listallcity.GateWay,
        hrm.app.listallemployee.GateWay,
        hrm.app.deleteEmployee.Gateway,
        hrm.app.deleteCity.Gateway,
        hrm.app.editemployee.Gateway,
        hrm.app.editcity.Gateway {

    private final String dbUrl;
    private final String username;
    private final String password;

    public MySqlGateway(String dbUrl, String username, String password) {
        this.dbUrl = dbUrl;
        this.username = username;
        this.password = password;
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(this.dbUrl, this.username, this.password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void insertCity(City city) throws CityExistedException {
        var sql = "INSERT INTO city (id, name) VALUES (?, ?)";
        try (var conn = this.getConnection();
             var ps = conn.prepareStatement(sql)) {
            ps.setString(1, city.id());
            ps.setString(2, city.name());
            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new CityExistedException(city.id());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertEmployee(Employee e)
            throws EmployeeExistedException, HomeCityNotFoundException {
        var sql = """
                INSERT INTO employee (id, first_name,last_name,birthday, home_city_id)
                VALUES (?, ?, ?, ?, ?)
                """;
        try (var conn = this.getConnection();
             var ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.id());
            ps.setString(2, e.firstname());
            ps.setString(3, e.lastname());
            ps.setObject(4, e.birthday());
            ps.setString(5, e.homeCityId());
            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ex) {
            switch (ex.getSQLState()) {
                case "1062" -> throw new EmployeeExistedException(e.id());
                case "1452" -> throw new HomeCityNotFoundException(e.homeCityId());
                default -> throw new RuntimeException(ex);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ArrayList<CityInfo> getAllCity() {
        var sql = """
                SELECT c.id, c.name,
                (SELECT COUNT(*) FROM employee WHERE home_city_id = C.id) AS employee_number
                FROM city c
                ORDER BY employee_number DESC
                """;
        try (var conn = this.getConnection();
             var statement = conn.createStatement();
             var rs = statement.executeQuery(sql)) {
            var list = new ArrayList<CityInfo>();
            while (rs.next()) {
                var id = rs.getString("id");
                var name = rs.getString("name");
                var employeeNumber = rs.getInt("employee_number");
                var info = new CityInfo(id, name, employeeNumber);
                list.add(info);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<EmployeeInfo> getAllEmployee() {
        var sql = """
                SELECT E.id, E.first_name, E.last_name, E.birthday, C.name AS home_city_name
                FROM employee E
                INNER JOIN city C ON E.home_city_id = C.id
                ORDER BY C.name DESC
                """;
        try (var conn = this.getConnection();
             var statement = conn.createStatement();
             var rs = statement.executeQuery(sql)) {
            var list = new ArrayList<EmployeeInfo>();
            while (rs.next()) {
                var id = rs.getString("id");
                var firstname = rs.getString("first_name");
                var lastname = rs.getString("last_name");
                var birthday = rs.getObject("birthday", LocalDate.class);
                var homeCityName = rs.getString("home_city_name");
                var info = new EmployeeInfo(id, firstname, lastname, birthday, homeCityName);
                list.add(info);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteEmployee(String id)
            throws HomeCityDeleteNotFoundException,
            EmployeeDeleteExistedException,
            InvalidEmployeeException {
        if (id == null || id.isBlank()) {
            throw new InvalidEmployeeException("Employee ID cannot be null or blank");
        }
        var sql = "DELETE FROM employee WHERE id = ?";
        try (var conn = this.getConnection();
             var ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new HomeCityDeleteNotFoundException("Employee with ID " + id + " not found");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new EmployeeDeleteExistedException("Cannot delete employee " + id + " due to constraints");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletecityId(String id)
            throws CityDeleteExistedException,
            CityDeleteHasEmployeesException,
            InvalidCityException {
        if (id == null || id.isBlank()) {
            throw new InvalidCityException("City ID cannot be null or blank");
        }
        var sql = "DELETE FROM city WHERE id = ?";
        try (var conn = this.getConnection();
             var ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new CityDeleteExistedException("City with ID " + id + " not found");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new CityDeleteHasEmployeesException("City " + id + " cannot be deleted because employees still exist");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editCity(String id, String newName)
            throws CityNotFoundException, InvalidCityException {
        var sql = "UPDATE city SET name = ? WHERE id = ?";
        try (var conn = this.getConnection();
             var ps = conn.prepareStatement(sql)) {
            ps.setString(1, newName);
            ps.setString(2, id);
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new CityNotFoundException("City with ID " + id + " not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editEmployee(String id, String newFirstName, String newLastName, LocalDate newBirthday, String homeCityId)
            throws EmployeeNotFoundException, InvalidEmployeeException {
        var sql = """
                UPDATE employee
                SET first_name = ?, last_name = ?, birthday = ?, home_city_id = ?
                WHERE id = ?
                """;
        try (var conn = this.getConnection();
             var ps = conn.prepareStatement(sql)) {
            ps.setString(1, newFirstName);
            ps.setString(2, newLastName);
            ps.setObject(3, newBirthday);
            ps.setString(4, homeCityId);
            ps.setString(5, id);
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database error while updating employee " + id, e);
        }
    }
}
