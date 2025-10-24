package bookstore;

import bookstore.app.deleteBook.Gateway;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.err.println("Args cannot be empty");
            return;
        }

        var gateway = new bookstore.db.SimpleGateway("BookStore.txt");
        var cmd = args[0];
        var remainArgs = Arrays.copyOfRange(args, 1, args.length);

        switch (cmd) {
            case "add" -> {
                var uc = new bookstore.app.addnewBook.UseCase(gateway);
                var controller = new bookstore.app.addnewBook.Controller(uc);
                controller.execute(remainArgs);
            }

            case "list" -> {
                var uc = new bookstore.app.listallBook.UseCase(gateway);
                var controller = new bookstore.app.listallBook.Controller(uc);
                controller.execute();
            }
            case "delete" -> {
                var uc = new bookstore.app.deleteBook.UseCase(gateway) ;
                var controller = new bookstore.app.deleteBook.Controller(uc);
                controller.execute(remainArgs);
            }
            case "edit" -> {
                var uc = new bookstore.app.editBook.UseCase(gateway);
                var controller = new bookstore.app.editBook.Controller(uc);
                controller.execute(remainArgs);
            }


            default -> System.err.println("Unknown command: " + cmd);
        }
    }
}
