package hrm.app.deleteEmployee;

import hrm.core.InvalidEmployeeException;

public class Controller {
    private final UseCase useCase;

    public Controller(UseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(String[] args) {
        if (args.length < 1) {
            System.err.println("Missing employee id!");
            return;
        }
        var id = args[0];
        try {
            useCase.execute(id);
            System.out.println("Employee " + id + " deleted successfully!");
        } catch (InvalidEmployeeException e) {
            System.err.println("Invalid ID: " + e.getMessage());
        } catch (EmployeeDeleteExistedException e) {
            System.err.println("Employee cannot be deleted: " + e.getMessage());
        } catch (HomeCityDeleteNotFoundException e) {
            System.err.println("Employee not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}
