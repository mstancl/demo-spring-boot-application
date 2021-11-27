package rest.response;

import rest.model.Employee;

public class AddEmployeeResponse {

    public AddEmployeeResponse(Employee employee, String status, String errorMessage) {
        this.employee = employee;
        this.status = status;
        this.errorMessage = errorMessage;
    }

    private Employee employee;
    private String status;
    private String errorMessage;

    public Employee getEmployee() {
        return employee;
    }

    public String getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "AddEmployeeResponse{" +
                "employee=" + employee +
                ", status='" + status + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
