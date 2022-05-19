package Sales.View;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InvoiceDetails extends JDialog {
    private final JTextField customerNameField;
    private final JTextField invoiceDateField;
    private final JLabel custNameLbl;
    private final JLabel invDateLbl;
    private final JButton okButton;
    private final JButton cancelButton;

    public InvoiceDetails(FrameOfInvoice frame) {
        custNameLbl = new JLabel("Customer Name:");
        customerNameField = new JTextField(15);
        invDateLbl = new JLabel("Invoice Date:");
        invoiceDateField = new JTextField(15);
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        okButton.setActionCommand("createNewInvoiceOk");
        cancelButton.setActionCommand("createNewInvoiceCancel");
        
        okButton.addActionListener(frame.getController());
        cancelButton.addActionListener(frame.getController());
        setLayout(new GridLayout(3, 1));
        
        add(invDateLbl);
        add(invoiceDateField);
        add(custNameLbl);
        add(customerNameField);
        add(okButton);
        add(cancelButton);
        
        pack();
        
    }

    public JTextField getCustomerNameField() {
        return customerNameField;
    }

    public JTextField getInvoiceDateField() {
        return invoiceDateField;
    }
    
}