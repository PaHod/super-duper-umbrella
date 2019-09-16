import java.util.Scanner;

public abstract class AbstractConsoleReader {

    protected static final String CREATE_CMD = "create";
    protected static final String READ_CMD = "read";
    protected static final String UPDATE_CMD = "update";
    protected static Scanner scanner;
    protected static AbstractConsoleReader instance;

    public AbstractConsoleReader() {
        initConnection();
//        initScanner();
//        listen();
    }

    protected abstract void initConnection();

    protected void initScanner() {
        scanner = new Scanner(System.in);
    }

    protected void listen() {
        System.out.println("Type command create, read, update");
        while (scanner.hasNext()) {
            String next = scanner.nextLine();
            parseCommand(next);
        }
    }

    protected void parseCommand(String next) {
        next = next.toLowerCase();
        if (CREATE_CMD.equals(next)) {
            proceedCreateCmd();
        } else if (READ_CMD.equals(next)) {
            proceedReadCmd();
        } else if (UPDATE_CMD.equals(next)) {
            proceedUpdateCmd();
        } else {
            System.out.println("No such command.");
//            listen();
        }
    }

    protected void proceedCreateCmd() {
        System.out.println("Create new user:");
        System.out.print("Id: ");
        int id = scanner.nextInt();
        System.out.print("\nName: ");
        String name = scanner.next();
        System.out.print("\nAge: ");
        int age = scanner.nextInt();
        addPerson(id, name, age);
//        listen();
    }

    protected void proceedReadCmd() {
        System.out.println("Read user by:");
        System.out.println("1 - Id;\n2 - Name;\n3 - Age;");
        int num = scanner.nextInt();
        String readBy = null;

        switch (num) {
            case 1:
                readBy = "id";
                break;
            case 2:
                readBy = "name";
                break;
            case 3:
                readBy = "age";
                break;
        }


        System.out.println("enter " + readBy + ": ");
        String value = scanner.next();
        readPeople(readBy, value);
//        listen();
    }

    protected void proceedUpdateCmd() {
        System.out.println("Update not implemented yet");
//        listen();
    }

    protected abstract void addPerson(int id, String name, int age);

    protected abstract void readPeople(String readBy, String value);

    protected abstract void updatePeople(String updateBy, String value);

    protected abstract void printTable();
}
