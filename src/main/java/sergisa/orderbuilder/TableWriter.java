package sergisa.orderbuilder;

import org.apache.poi.xwpf.usermodel.*;
import sergisa.orderbuilder.model.OrderItem;
import sergisa.orderbuilder.model.OrderList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableWriter {
    File mainFile;
    XWPFDocument wordDocument;
    String date = "25.14.2027";
    String documentNumber = "06-163/789";
    String singPerson = "Л.С. Куравский";
    String singnificantPost = "Декан факультета «Информационные технологии»";
    String receiverHeader = "Заведующей сектором материального обеспечения Л.Е. Канель";
    private final int documentReceiverTableIndex = 0;
    private final int documentHeaderTableIndex = 1;

    public TableWriter(File document, OrderList orderList) {
        mainFile = document;
        try (FileInputStream fis = new FileInputStream(document.getAbsolutePath())) {
            wordDocument = new XWPFDocument(fis);
            writeRequisites();
            writeDocumentDateAndNumber();
            for (OrderItem orderItem : orderList.getOrderList()) {
                addApplicationItem(orderItem);
            }
            addSignature();
            wordDocument.write(new FileOutputStream(document));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeRequisites() {
        XWPFTable documentHeader = wordDocument.getTables().get(documentReceiverTableIndex);
        XWPFParagraph dateParagraph = documentHeader.getRows()
                .getFirst()
                .getCell(0)
                .getParagraphs()
                .getFirst();
        dateParagraph.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun dateParagraphRun = dateParagraph.createRun();
        dateParagraphRun.setFontFamily("Times New Roman");
        dateParagraphRun.setFontSize(14);
        dateParagraphRun.setBold(true);
        dateParagraphRun.setText(receiverHeader);

    }

    public void addApplicationItem(OrderItem orderItem) {
        XWPFTable table = wordDocument.createTable(1, 2);
        fillTable(table, orderItem);
        wordDocument.createParagraph();
    }

    private void writeDocumentDateAndNumber() {
        XWPFTable documentHeader = wordDocument.getTables().get(documentHeaderTableIndex);
        XWPFRun dateParagraphRun = documentHeader.getRows().getFirst().getCell(0).getParagraphs().getFirst().createRun();
        XWPFRun numberParagraphRun = documentHeader.getRows().getFirst().getCell(2).getParagraphs().getFirst().createRun();
        dateParagraphRun.setFontFamily("Times New Roman");
        dateParagraphRun.setText(date);
        numberParagraphRun.setFontFamily("Times New Roman");
        numberParagraphRun.setText(documentNumber);
    }

    /**
     * 8.43 / 8.31
     * 50.3% / 49.6%
     */
    private void addSignature() {
        wordDocument.createParagraph();
        XWPFTable signTable = wordDocument.createTable(1, 2);

        signTable.setInsideHBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, null);
        signTable.setInsideVBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, null);
        signTable.setTopBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, null);
        signTable.setBottomBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, null);
        signTable.setLeftBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, null);
        signTable.setRightBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, null);
        XWPFTableRow row = signTable.getRow(0);
        row.getCell(0).setWidth("50.3%");
        row.getCell(1).setWidth("49.6%");
        XWPFRun run = row.getCell(0).getParagraphs().getFirst().createRun();
        run.removeBreak();
        run.setFontFamily("Times New Roman");
        run.setFontSize(14);
        run.setBold(true);
        run.setText(singnificantPost);
        XWPFParagraph paragraph = row.getCell(1).getParagraphs().getFirst();
        paragraph.setAlignment(ParagraphAlignment.RIGHT);
        run = paragraph.createRun();
        run.removeBreak();
        run.setFontFamily("Times New Roman");
        run.setFontSize(14);
        run.setBold(true);
        run.setText(singPerson);
    }

    void fillParagraph(XWPFParagraph paragraph, String string) {
        paragraph.setSpacingBefore(0);
        paragraph.setSpacingAfter(0);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(14);
        run.setFontFamily("Times New Roman");
        run.setText(string);
    }

    /**
     * Наименование оборудования	HP LaserJet 1536 dnf MFP
     * Инвентарный номер оборудования 	101042000000174
     * Тип расходного материала	Тонер-картридж черный CE278A
     * Количество 	2
     * Контактное лицо 	Исаков С.С.
     * Телефон, Адрес, № кабинета 	Открытое шоссе, д. 24, стр. 27, каб. №319
     *
     * @param table
     */
    void fillTable(XWPFTable table, OrderItem orderItem) {

        Map<String, String> tableData = new HashMap<>();
        tableData.put("Наименование оборудования", orderItem.getPrinter().getModelName());
        tableData.put("Инвентарный номер оборудования", orderItem.getPrinter().getInvNumber());
        tableData.put("Тип расходного материала", orderItem.getPrinter().getCartridge().getModelName());
        tableData.put("Количество", String.valueOf(orderItem.getAmount()));
        tableData.put("Контактное лицо", orderItem.getContactPerson());
        tableData.put("Телефон, Адрес, № кабинета", orderItem.getAddress());
        for (Map.Entry<String, String> entry : tableData.entrySet()) {
            XWPFTableRow row = table.createRow();
            fillRow(row, entry);
        }
        table.removeRow(0);
    }

    private void fillRow(XWPFTableRow row, Map.Entry<String, String> rowEntry) {
        List<XWPFTableCell> cellsList = row.getTableCells();
        fillParagraph(cellsList.get(0).getParagraphs().getFirst(), rowEntry.getKey());
        fillParagraph(cellsList.get(1).getParagraphs().getFirst(), rowEntry.getValue());
    }

    public String getDate() {
        return date;
    }

    public TableWriter setDate(String date) {
        this.date = date;
        return this;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public TableWriter setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
        return this;
    }

    public String getSingPerson() {
        return singPerson;
    }

    public TableWriter setSingPerson(String singPerson) {
        this.singPerson = singPerson;
        return this;
    }

    public String getSingnificantPost() {
        return singnificantPost;
    }

    public TableWriter setSingnificantPost(String singnificantPost) {
        this.singnificantPost = singnificantPost;
        return this;
    }

    public String getReceiverHeader() {
        return receiverHeader;
    }

    public TableWriter setReceiverHeader(String receiverHeader) {
        this.receiverHeader = receiverHeader;
        return this;
    }
}
