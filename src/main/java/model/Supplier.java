package model;

public class Supplier {
    private int supplierId;
    private String name;
    private String contactPerson;
    private String phone;
    private String address;
    private String email;

    // Constructors, getters, setters
    public Supplier() {}

    public Supplier(int supplierId, String name, String contactPerson, String phone, String address, String email) {
        this.supplierId = supplierId;
        this.name = name;
        this.contactPerson = contactPerson;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Tên NCC: %s | Người liên hệ: %s  | SĐT: %s | Địa chỉ: %s | Email: %s ",
                supplierId, name, contactPerson, phone, address, email);
    }
}
