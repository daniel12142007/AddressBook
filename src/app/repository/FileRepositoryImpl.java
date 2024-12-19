package app.repository;

import app.model.Contact;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileRepositoryImpl implements FileRepository {
    private final String PATH_FILE;
    private final String DELIMITER = ";";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FileRepositoryImpl(String filePath) {
        this.PATH_FILE = filePath;
    }

    @Override
    public void saveContact(HashMap<String, Contact> contacts) {
        try {
            objectMapper.writeValue(new File(PATH_FILE), contacts);
        } catch (IOException e) {
            System.err.println("Ошибка при записи файла: " + e.getMessage());
        }
    }

    @Override
    public List<Contact> readFile() {
        try {
            File file = new File(PATH_FILE);
            return objectMapper.readValue(file, ArrayList.class);
        } catch (Exception e) {
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
}