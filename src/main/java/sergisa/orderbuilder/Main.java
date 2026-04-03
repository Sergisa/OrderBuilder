package sergisa.orderbuilder;

import com.formdev.flatlaf.FlatLightLaf;
import sergisa.orderbuilder.model.Cartridge;
import sergisa.orderbuilder.model.Printer;
import sergisa.orderbuilder.ui.OrderBuilderForm;


public class Main {
    static PrinterRepository printerRepository = new PrinterRepository();

    public static void main(String[] args) {
        FlatLightLaf.setup();
        buildRepository();
        //buildOrders();
        OrderBuilderForm orderBuilderForm = new OrderBuilderForm(printerRepository);
    }

    static void buildRepository() {
        Cartridge epsonCartridge = new Cartridge("S050166");
        Cartridge CE278Cartridge = new Cartridge("CE278A");
        Cartridge samsungCartridge = new Cartridge("MLT-D203E");
        printerRepository.addPrinter("101041-133", new Printer("Epson EPL-6200L", "JH9Z001463").setCartridge(epsonCartridge));
        printerRepository.addPrinter("101041-132", new Printer("Epson EPL-6200L", "JH9Z001455").setCartridge(epsonCartridge));
        printerRepository.addPrinter("101041-1174", new Printer("HP LaserJet M1536dnf", "CNC9CCYC7H").setCartridge(CE278Cartridge));
        printerRepository.addPrinter("101344-728", new Printer("Samsung ProExpress M3820ND").setCartridge(samsungCartridge));
        printerRepository.addPrinter("101042-611", new Printer("HP LaserJet P2055DN", "CNCKB77823").setCartridge(new Cartridge("CE505X")));
        //printerManager.addPrinter("101044-2376", new Printer("KYOCERA ECOSYS P5021CDN","VDH8X23904").setCartridge(new Cartridge("")));
    }


}