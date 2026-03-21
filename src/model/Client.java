package model;

public class Client {

    private static int nextId = 1;
    private int id;
    private String name;
    private String lastname;
    private String phone;
    private String passport;
    private String email;

    public Client( String lastname, String name, String email,  String passport, String phone) {
        this.lastname = lastname;
        this.email = email;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    public String getPhone() {
        return phone;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public void setName(String fullName) {
        this.name = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
