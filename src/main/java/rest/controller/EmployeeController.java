package rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.dao.EmployeeDAO;
import rest.model.Employee;
import rest.model.Employees;
import rest.response.AddEmployeeResponse;


@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeDAO employeeDao;

    public EmployeeController(EmployeeDAO employeeDao) {
        this.employeeDao = employeeDao;
    }

    @GetMapping(path = "/", produces = "application/json")
    public Employees getEmployees() {
        return employeeDao.getAllEmployees();
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addEmployee(
            @RequestBody Employee employee) {

        if (!employeeDao.checkIfEmployeeExists(employee)) {
            Integer id = employeeDao.getAllEmployees().getEmployeeList().size() + 1;
            employee.setId(id);
            employeeDao.addEmployee(employee);
            return new ResponseEntity<>(new AddEmployeeResponse(employee, "created", null), HttpStatus.CREATED);
        } else {
            employee = employeeDao.getEmployee(employee);
            return new ResponseEntity<>(new AddEmployeeResponse(employee, "already-exists", null), HttpStatus.OK);
        }

    }

    @GetMapping(value = "/getEmployee", produces = "application/json")
    public ResponseEntity<Object> getEmployee(@RequestParam("index") int index) {
        Employee employee = employeeDao.getEmployee(index);
        if (employee != null) {
            return new ResponseEntity<>(new AddEmployeeResponse(employee, "created", null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new AddEmployeeResponse(null, "error", "Employee with index : [ " + (index) + " ] not found"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/updateEmployee", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> updateEmployee(@RequestParam("index") int index, @RequestBody Employee employeeFromRequest) {
        Employee employee = employeeDao.getEmployee(index);
        if (employee != null) {
            employee.setFirstName(employeeFromRequest.getFirstName());
            employee.setLastName(employeeFromRequest.getLastName());
            employee.setEmail(employeeFromRequest.getEmail());
            employeeDao.updateEmployee(index, employee);
            return new ResponseEntity<>(new AddEmployeeResponse(employee, "updated", null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new AddEmployeeResponse(null, "error", "Employee with index : [ " + (index) + " ] not found"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteEmployee", produces = "application/json")
    public ResponseEntity<Object> deleteEmployee(@RequestParam("index") int index) {
        Employee employee = employeeDao.getEmployee(index);
        if (employee != null) {
            employeeDao.deleteEmployee(employee);
            return new ResponseEntity<>(new AddEmployeeResponse(employee, "deleted", null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new AddEmployeeResponse(null, "error", "Employee with index : [ " + (index) + " ] not found"), HttpStatus.BAD_REQUEST);
        }
    }
}
