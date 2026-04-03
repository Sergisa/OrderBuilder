package sergisa.orderbuilder.ui.list;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import sergisa.orderbuilder.RoundedBorder;
import sergisa.orderbuilder.model.Printer;

import javax.swing.*;
import java.awt.*;

public class PrinterListViewItemRenderer extends JPanel implements ListCellRenderer<Printer> {
    JLabel modelLabel = new JLabel();
    JLabel serialLabel = new JLabel();
    JLabel invLabel = new JLabel();
    JLabel cartridgeLabel = new JLabel();
    JLabel checkIcon = new JLabel();
    private boolean isCartridgeVisible = false;
    private boolean isIconCheckable = true;

    public PrinterListViewItemRenderer() {
        setLayout(new GridBagLayout());
        ((GridBagLayout) getLayout()).columnWidths = new int[]{21, 0, 0, 0};
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        checkIcon.setIcon(new FlatSVGIcon(getClass().getResource("check-square-svgrepo-com.svg")));
        invLabel.setBorder(new RoundedBorder(Color.BLUE, 5));

        add(modelLabel, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        add(serialLabel, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        add(invLabel, new GridBagConstraints(2, 1, 1, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        add(cartridgeLabel, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        add(checkIcon, new GridBagConstraints(0, 0, 1, 3, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        setOpaque(true);
        modelLabel.setFont(modelLabel.getFont().deriveFont(Font.BOLD));
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Printer> list, Printer printer, int printerIndex, boolean isSelected, boolean cellHasFocus) {
        serialLabel.setText(printer.getSerialNumber());
        modelLabel.setText(printer.getModelName());
        invLabel.setText(printer.getInvNumber());
        cartridgeLabel.setText(printer.getCartridge().getModelName());

        cartridgeLabel.setVisible(isCartridgeVisible);
        if (isIconCheckable) {
            checkIcon.setVisible(isSelected);
            setBackground(Color.WHITE);
        } else {
            checkIcon.setVisible(false);
            setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);
        }

        //
        return this;
    }

    public void setCartridgeVisible() {
        isCartridgeVisible = true;
        repaint();
    }

    public void setIconNotCheckable() {
        isIconCheckable = false;
    }
}
