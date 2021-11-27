package rest.dao;

import org.springframework.stereotype.Repository;
import rest.model.Employee;
import rest.model.Employees;

@Repository
public class EmployeeDAO {
    private static Employees list = new Employees();

    static {
        list.getEmployeeList().add(new Employee(1, "Martin", "Stancl", "martin.stancl@madeupdomain.com"));
        list.getEmployeeList().add(new Employee(2, "Milos", "Zeman", "milos.zeman@prazskyhrad.cz"));
        list.getEmployeeList().add(new Employee(3, "Andrej", "Babis", "andrej.babis@primeminister.cz"));
    }

    public Employees getAllEmployees() {
        return list;
    }


    public void addEmployee(Employee employee) {
        list.getEmployeeList().add(employee);
    }

    public void deleteEmployee(Employee employee) {
        list.getEmployeeList().remove(employee);
    }

    public void updateEmployee(int index, Employee employee) {
        list.getEmployeeList().set(index, employee);
    }


    public Boolean checkIfEmployeeExists(Employee employee) {
        for (Employee employeeFromTheList : getAllEmployees().getEmployeeList()) {
            if (employeeFromTheList.getFirstName().toLowerCase().equals(employee.getFirstName().toLowerCase()) &&
                    employeeFromTheList.getLastName().toLowerCase().equals(employee.getLastName().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public Employee getEmployee(Employee employee) {
        for (Employee employeeFromTheList : getAllEmployees().getEmployeeList()) {
            if (employeeFromTheList.getFirstName().toLowerCase().equals(employee.getFirstName().toLowerCase()) &&
                    employeeFromTheList.getLastName().toLowerCase().equals(employee.getLastName().toLowerCase())) {
                return employeeFromTheList;
            }
        }
        throw new NullPointerException("Could not find :[ " + employee + " ] in the database");
    }

    public Employee getEmployee(int index) {
        for (Employee employeeFromTheList : getAllEmployees().getEmployeeList()) {
            if (employeeFromTheList.getId() == index) {
                return employeeFromTheList;
            }
        }
        return null;

    }

    public int getEmployeeId(Employee employee) {
        for (Employee employeeFromTheList : getAllEmployees().getEmployeeList()) {
            if (employeeFromTheList == employee) {
                return employee.getId();
            }
        }
        throw new NullPointerException("Could not find :[ " + employee + " ] in the database");
    }
}
