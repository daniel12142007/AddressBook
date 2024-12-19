package app;

import app.model.Contact;
import app.model.enums.Search;
import app.service.ContactService;

import java.util.Scanner;

import static app.model.enums.Search.*;

public class Main {
    private static final ContactService contactService =
            new ContactService("C:\\Users\\User\\IdeaProjects\\address_book\\contacts.json");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int command;
        do {
            System.out.println("""
                    1.Create Contact
                    2.Search Contact
                    3.Delete Contact by phoneNumber
                    4.List All Contacts
                    5.Update Contact
                    6.Exit
                    """);
            command = scanner.nextInt();
            scanner.nextLine();
            switch (command) {
                case 1:
                    System.out.println("Create Contact(Cancel enter B)");
                    System.out.println("Name:");
                    String name = scanner.nextLine();
                    if (name.equalsIgnoreCase("b")) break;
                    System.out.println("Surname");
                    String surname = scanner.nextLine();
                    System.out.println("Phone");
                    String phone = scanner.nextLine();
                    contactService.createContact(new Contact(
                            name,
                            surname,
                            phone
                    ));
                    break;
                case 2:
                    System.out.println("""
                            1.Search Contact by name
                            2.Search Contact by surname
                            3.Search Contact by phoneNumber
                            (Cancel enter B)
                            """);
                    String searchCategory = scanner.nextLine();
                    if (searchCategory.equalsIgnoreCase("b")) break;
                    if (!"123".contains(searchCategory)) {
                        System.err.println("Enter a number from 1 to 3");
                        break;
                    }
                    Search categorySearch =
                            searchCategory.equals("1") ?
                                    NAME : searchCategory.equals("2") ?
                                    SURNAME : PHONE_NUMBER;
                    System.out.println("Type " + categorySearch + ":");
                    String search = scanner.nextLine();
                    if (categorySearch == PHONE_NUMBER) {
                        Contact contact = contactService.searchContact(search);
                        System.out.println(
                                contact == null ?
                                        "Not found contact" : contact
                        );
                    } else System.out.println(contactService.searchContactUniversal(search, categorySearch));
                    break;
                case 3:
                    System.out.println("Delete Contact by phoneNumber(Cancel enter B)");
                    System.out.println("Type phoneNumber:");
                    String deletePhoneNumber = scanner.nextLine();
                    if (deletePhoneNumber.equalsIgnoreCase("b")) break;
                    if (contactService.deleteContactByPhone(deletePhoneNumber))
                        System.out.println("Contact deleted");
                    else System.err.println("Failed delete contact");
                    break;
                case 4:
                    var list = contactService.list();
                    System.out.println(
                            list == null ?
                                    "There is nothing yet" : list
                    );
                    break;
                case 5:
                    System.out.println("Update Contact(Cancel enter B)");
                    System.out.println("Enter phoneNumber:");
                    String searchPhoneNumber = scanner.nextLine();
                    if (searchPhoneNumber.equalsIgnoreCase("b")) break;
                    System.out.println("Name:");
                    String nameUpdate = scanner.nextLine();
                    System.out.println("Surname");
                    String surnameUpdate = scanner.nextLine();
                    System.out.println("Phone");
                    String phoneUpdate = scanner.nextLine();
                    contactService.updateContact(
                            new Contact(
                                    nameUpdate,
                                    surnameUpdate,
                                    phoneUpdate
                            ),
                            searchPhoneNumber);
                    break;
                default:
                    if (command != 6)
                        System.err.println("Invalid command, Command should be in range 1,2,3,4,5,6");
                    break;
            }
        } while (command != 6);
    }
}