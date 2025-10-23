package hrm.app.editcity;

public class Controller {
    private final UseCase useCase;

    public Controller(UseCase useCase) {
        this.useCase = useCase;
    }

    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: edit-city <city_id> <new_name>");
            return;
        }

        var id = args[0];
        var newName = args[1];

        try {
            useCase.execute(id, newName);
            System.out.println("City updated successfully!");
        } catch (CityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
