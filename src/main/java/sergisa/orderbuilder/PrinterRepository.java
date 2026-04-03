package sergisa.orderbuilder;

import sergisa.orderbuilder.model.Printer;

import java.util.ArrayList;
import java.util.List;

public class PrinterRepository {
    List<Printer> printers = new ArrayList<>();

    public PrinterRepository addPrinter(String inv, Printer printer) {
        printer.setInvNumber(inv);
        printers.add(printer);
        return this;
    }

    public List<Printer> getPrinters() {
        return printers;
    }
}
