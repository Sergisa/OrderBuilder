package sergisa.orderbuilder.model;

public class OrderItem {
    Printer printer;
    int amount;
    String address;
    String contactPerson;

    public OrderItem() {
    }

    public String getAddress() {
        return address;
    }

    public OrderItem setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public OrderItem setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public OrderItem setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }

    public Printer getPrinter() {
        return printer;
    }

    public OrderItem setPrinter(Printer printer) {
        this.printer = printer;
        return this;
    }
}
