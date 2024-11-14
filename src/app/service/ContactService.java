package app.service;

import app.model.Contact;
import app.model.enums.Search;

import java.util.*;

public class ContactService {
    private final static HashMap<String, Contact> contacts = new LinkedHashMap<>();
    private final static Set<String> uniquePhone = new LinkedHashSet<>();

    public void createContact(Contact contact) {
        int oldSizeSet = uniquePhone.size();
        String phone = contact.getPhone();
        uniquePhone.add(phone);
        if (oldSizeSet == uniquePhone.size()) {
            System.err.println("Already contact phone number");
            return;
        }
        contacts.put(phone, contact);
    }

    public Contact searchContact(String phoneNumber) {
        return contacts.get(phoneNumber);
    }

    public List<Contact> searchContactUniversal(String search,
                                                Search searchEnum) {
        return contacts.values().stream()
                .filter(a -> a.getValue(searchEnum).startsWith(search)).toList();
    }

    public boolean deleteContactByPhone(String phoneNumber) {
        if (contacts.get(phoneNumber) == null) {
            System.err.println("Not found contact");
            return false;
        }
        contacts.remove(phoneNumber);
        uniquePhone.remove(phoneNumber);
        return true;
    }

    public List<Contact> list() {
        return new ArrayList<>(contacts.values());
    }

    public void updateContact(Contact contact,
                              String phoneNumber) {
        if (!contact.getPhone().equals(phoneNumber)
                && contacts.get(contact.getPhone()) != null) {
            System.err.println("Phone must unique phone number");
            return;
        }if (contacts.get(phoneNumber)==null){
            System.err.println("Not found contact");
            return;
        }
        if (contact.getPhone().equals(phoneNumber))
            contacts.put(phoneNumber, contact);
        else {
            deleteContactByPhone(phoneNumber);
            createContact(contact);
        }
    }
}