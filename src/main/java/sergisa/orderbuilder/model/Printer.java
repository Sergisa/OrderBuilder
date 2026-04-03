package sergisa.orderbuilder.model;

public class Printer {
    String modelName;
    String invNumber;
    String serialNumber;
    Cartridge cartridge;


    String roomNumber;

    public Printer(String modelName, String serialNumber) {
        this(modelName);
        this.serialNumber = serialNumber;
    }

    public Printer(String modelName) {
        this.modelName = modelName;
    }

    public String getInvNumber() {
        return invNumber;
    }

    public Printer setInvNumber(String invNumber) {
        this.invNumber = invNumber;
        return this;
    }

    public String getModelName() {
        return modelName;
    }

    public Printer setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public Printer setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public Cartridge getCartridge() {
        return cartridge;
    }

    public Printer setCartridge(Cartridge cartridge) {
        this.cartridge = cartridge;
        return this;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Printer setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }
}
