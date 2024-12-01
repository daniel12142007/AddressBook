package app.repository;

import app.model.Contact;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileRepositoryImpl implements FileRepository {
    private final String PATH_FILE = "C:\\Users\\User\\IdeaProjects\\address_book\\contacts.csv";
    private final String DELIMITER = ";";

    @Override
    public void saveContact(HashMap<String, Contact> contacts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_FILE, false))) {
            for (Contact value : contacts.values()) {
                writer.write(value.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи файла: " + e.getMessage());
        }
    }

    @Override
    public List<Contact> readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_FILE))) {
            return reader.lines().map(this::parseContact).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public LinkedHashSet<String> getSetFormat() {
        return readFile().stream().map(Contact::getPhone)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public HashMap<String, Contact> getMapFormat() {
        HashMap<String, Contact> contactHashMap = new LinkedHashMap<>();
        for (Contact contact : readFile())
            contactHashMap.put(contact.getPhone(), contact);
        return contactHashMap;
    }

    private Contact parseContact(String line) {
        String[] splitLine = line.split(DELIMITER);
        return new Contact(splitLine[0], splitLine[1], splitLine[2]);
    }
}