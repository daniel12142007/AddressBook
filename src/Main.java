import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] contacts = new String[10];
        int nextEmpty = 0;
        int command = 6;
        do {
            System.out.println("""
                    1.Create Contact
                    2.Search Contact
                    3.Delete Contact by phoneNumber
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
                    System.out.println("""
                            1.Search Contact by name
                            2.Search Contact by surname
                            3.Search Contact by phoneNumber
                            """);
                    int searchCategory = scanner.nextInt();
                    if (searchCategory < 1 || searchCategory > 3) {
                        System.err.println("Enter a number from 1 to 3");
                        break;
                    }
                    String categorySearch =
                            searchCategory == 1 ? "Name" : searchCategory == 2 ? "Surname" : "PhoneNumber";
                    System.out.println("Type " + categorySearch + ":");
                    String search = scanner.next();
                    search(search, contacts, searchCategory - 1);
                    break;
                case 3:
                    System.out.println("Delete Contact by phoneNumber");
                    System.out.println("Type phoneNumber:");
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
                        System.out.println("Line " + (i + 1) + " | " + contacts[i]);
                    }
                    break;
                default:
                    if (6 != command) System.err.println("Invalid command, Command should be in range 1,2,3,4");
                    break;
            }
        } while (command != 6);
    }

    private static void search(String search, String[] contacts, int index) {
        int n = 0;
        String categorySearch = index == 0 ? "Name" : index == 1 ? "Surname" : "PhoneNumber";
        String nameLowerCase = search.toLowerCase();
        for (String contact : contacts) {
            if (contact == null) break;
            String contactName = contact.split(" ")[index].toLowerCase();
            if (contactName.startsWith(nameLowerCase)) {
                System.out.println(contact);
                n++;
            }
        }
        if (n == 0) System.out.println("Not found contact " + categorySearch + ": " + search);
    }

    private static int deleteContact(String phoneNumber, String[] contacts) {
        int n = 0;
        int j = 0;
        String[] copy = new String[contacts.length];
        for (String contact : contacts) {
            if (contact == null) break;
            String contactName = contact.split(" ")[2];
            if (!contactName.equals(phoneNumber)) {
                copy[j] = contact;
                j++;
            } else n++;
        }
        if (n == 0) System.err.println("Unable to find user");
        System.arraycopy(copy, 0, contacts, 0, copy.length);
        return j;
    }

    private static int deleteContactByLine(int line, String[] contacts) {
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
        System.arraycopy(copy, 0, contacts, 0, copy.length);
        return j;
    }
}