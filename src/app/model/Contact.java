package app.model;

import app.model.enums.Search;

public class Contact {
    private String name;
    private String surname;
    private String phone;

    public Contact() {

    }

    public Contact(String name, String surname, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getValue(Search search) {
        return search == Search.NAME ? name : surname;
    }

    @Override
    public String toString() {
        return name + ';' + surname + ';' + phone;
    }
}