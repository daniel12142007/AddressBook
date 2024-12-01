package app.repository;

import app.model.Contact;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

public interface FileRepository {
    void saveContact(HashMap<String,Contact> contacts);
    List<Contact>readFile();
    LinkedHashSet<String> getSetFormat();
    HashMap<String,Contact>getMapFormat();
}