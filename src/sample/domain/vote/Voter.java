package sample.domain.vote;

import java.io.File;

public class Voter {
    private String id;
    private String name;
    private String surname;
    private String phoneNumber;
    private byte[] image;

    public Voter() {
    }

    public Voter(String id, String name, String surnname, String phoneNumber, byte[] image) {
        this.id = id;
        this.name = name;
        this.surname = surnname;
        this.phoneNumber = phoneNumber;
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
