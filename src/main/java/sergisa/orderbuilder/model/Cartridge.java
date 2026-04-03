package sergisa.orderbuilder.model;

public class Cartridge {
    String modelName;

    public Cartridge(String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return modelName;
    }

    public Cartridge setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }
}
