import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] contacts = new String[1];
        int nextEmpty = 0;
        int command = 4;
        do {
            System.out.println("""
                    1.Create Contact
                    2.Search Contact
                    3.Delete Contact by name
                    4.Delete Contact by line
                    5.List All Contacts
                    6.Exit
                    """);
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    System.out.println("Create Contact");
                    System.out.println("Name:");
                    String name = scanner.next();
                    System.out.println("Surname");
                    String surname = scanner.next();
                    System.out.println("Phone");
                    String phone = scanner.next();
                    if (nextEmpty == contacts.length) {
                        String[] copy = contacts;
                        contacts = new String[contacts.length + 10];
                        System.arraycopy(copy, 0, contacts, 0, copy.length);
                        System.out.println("The size has been increased by another 10 contacts");
                    }
                    contacts[nextEmpty] = name + " " + surname + " " + phone;
                    nextEmpty++;
                    break;
                case 2:
                    System.out.println("Type name:");
                    String searchName = scanner.next();
                    searchByName(searchName, contacts);
                    break;
                case 3:
                    System.out.println("Delete Contact by name");
                    System.out.println("Type name:");
                    String deleteName = scanner.next();
                    nextEmpty = deleteContact(deleteName, contacts);
                    break;
                case 4:
                    System.out.println("Delete Contact by line");
                    System.out.println("Type line:");
                    int line = scanner.nextInt();
                    nextEmpty = deleteContactByLine(line, contacts);
                    break;
                case 5:
                    for (int i = 0; i < contacts.length; i++) {
                        if (contacts[i] == null) break;
                        System.out.println("Line "+(i + 1) + " | " + contacts[i]);
                    }
                    break;
                default:
                    System.err.println("Invalid command, Command should be in range 1,2,3,4");
                    break;
            }
        } while (command != 6);
    }

    public static void searchByName(String searchName, String[] contacts) {
        int n = 0;
        String nameLowerCase = searchName.toLowerCase();
        for (String contact : contacts) {
            if (contact == null) break;
            if (contact.toLowerCase().startsWith(nameLowerCase)) {
                System.out.println(contact);
                n++;
            }
        }
        if (n == 0) System.out.println("Not found contact name: " + searchName);
    }

    public static void searchByNameOption2(String searchName, String[] contacts) {
        int n = 0;
        String nameLowerCase = searchName.toLowerCase();
        for (String contact : contacts) {
            if (contact == null) break;
            String contactName = contact.split(" ")[0].toLowerCase();
            if (contactName.equals(nameLowerCase.toLowerCase())) {
                System.out.println(contact);
                n++;
            }
        }
        if (n == 0) System.out.println("Not found contact name: " + searchName);
    }

    public static int deleteContact(String name, String[] contacts) {
        String nameLowerCase = name.toLowerCase();
        int n = 0;
        int j = 0;
        String[] copy = new String[contacts.length];
        for (String contact : contacts) {
            if (contact == null) break;
            if (!contact.toLowerCase().startsWith(nameLowerCase)) {
                copy[j] = contact;
                j++;
            } else n++;
        }
        if (n == 0) System.err.println("Unable to find user");
        System.arraycopy(copy, 0, contacts, 0, copy.length - n);
        return j;
    }

    public static int deleteContactByLine(int line, String[] contacts) {
        int n = 0;
        int j = 0;
        String[] copy = new String[contacts.length];
        for (int i = 0; i < contacts.length; i++) {
            if (contacts[i] == null) break;
            if ((i + 1) != line) {
                copy[j] = contacts[i];
                j++;
            } else n++;
        }
        if (n == 0) System.err.println("Line not found");
        System.arraycopy(copy, 0, contacts, 0, copy.length - n);
        return j;
    }
}