package model;

public class Client {

    private static int nextId = 1;
    private int id;
    private String fullName;
    private String phone;
    private String passport;
    private String email;

    public Client(String fullName, String email,  String passport, String phone) {
        this.email = email;
        this.fullName = fullName;
        this.passport = passport;
        this.phone = phone;
        this.id = nextId++;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassport() {
        return passport;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
