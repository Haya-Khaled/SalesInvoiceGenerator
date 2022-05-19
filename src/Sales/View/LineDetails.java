
package Sales.View;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LineDetails extends JDialog{

    private JTextField itemNameField;
    private JTextField itemCountField;
    private JTextField itemPriceField;
    private JLabel itemNameLbl;
    private JLabel itemCountLbl;
    private JLabel itemPriceLbl;
    private JButton okButton;
    private JButton cancelButton;
    

    public LineDetails(FrameOfInvoice frame) {

        itemNameField = new JTextField(20);
        itemNameLbl = new JLabel("Item Name");
        
        itemCountField = new JTextField(15);
        itemCountLbl = new JLabel("Item Count");
        
        itemPriceField = new JTextField(15);
        itemPriceLbl = new JLabel("Item Price");
        
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        okButton.setActionCommand("saveNewLineOk");
        cancelButton.setActionCommand("saveNewLinCancel");
        
        okButton.addActionListener(frame.getController());
        cancelButton.addActionListener(frame.getController());
        setLayout(new GridLayout(4, 2));
        
        add(itemNameLbl);
        add(itemNameField);
        add(itemCountLbl);
        add(itemCountField);
        add(itemPriceLbl);
        add(itemPriceField);
        add(okButton);
        add(cancelButton);
        
        pack();
    }
    public JTextField getItemNameField() {
        return itemNameField;
    }
    
    public JTextField getItemCountField() {
        return itemCountField;
    }

    public JTextField getItemPriceField() {
        return itemPriceField;
    } }  