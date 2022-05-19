
package Sales.Controller;

import Sales.Model.Invoice;
import Sales.Model.Line;
import Sales.Model.TableModelOfInvoices;
import Sales.Model.TableModelOfLines;
import Sales.View.FrameOfInvoice;
import Sales.View.InvoiceDetails;
import Sales.View.LineDetails;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Controller implements ActionListener, ListSelectionListener{
    private FrameOfInvoice frame;
    private InvoiceDetails invoiceDetails;
    private LineDetails lineDetails;

    public Controller(FrameOfInvoice frame) {
        this.frame=frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        System.out.println("Action: "+actionCommand);
        switch (actionCommand) {   
            case "Save File":
                saveFile();
                break;
            case "Load File":
                loadFile();
                break;
            case "Create New Invoice":
                createNewInvoice();
                break;
            case "Delete Invoice":
                deleteInvoice();
                break; 
            case "createNewInvoiceCancel":
                createNewInvoiceCancel();
                break;
            case "createNewInvoiceOk":
                createNewInvoiceOk();
                break;
            case "Save":
                save();
                break;
            case "Cancel":
                cancel();
                break;
            case "saveNewLineOk":
                saveNewLineOk();
                break;
            case "saveNewLinCancel":
                saveNewLinCancel();
                break;
            
        }
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedArrayIndex = frame.getInvoiceTable().getSelectedRow();
        if (selectedArrayIndex !=-1){
        System.out.println("You have selected row: "+ selectedArrayIndex);
        Invoice currentInvoice = frame.getInvoices().get(selectedArrayIndex);
        frame.getInvoiceNumberLabel().setText(""+currentInvoice.getNumber());
        frame.getInvoiceDateLabel().setText(currentInvoice.getDate());
        frame.getInvoiceTotalLabel().setText(""+currentInvoice.getTotalInvoice());
        frame.getCustomerNameLabel().setText(currentInvoice.getCustomername());
        
        TableModelOfLines tableModelOfLines = new TableModelOfLines(currentInvoice.getLines());
        frame.getLineTable().setModel(tableModelOfLines);
        tableModelOfLines.fireTableDataChanged();
        }
        
    }
    private void loadFile() {
        JFileChooser fc = new JFileChooser();
        try {
            int result = fc.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File loadingInvoiceHeaderFile = fc.getSelectedFile();
                Path loadHeaderPath = Paths.get(loadingInvoiceHeaderFile.getAbsolutePath());
                List<String> headerLines = Files.readAllLines(loadHeaderPath);

                System.out.println("InvoiceHeader has been loaded");
                
                ArrayList<Invoice> arrayOfInvoices = new ArrayList<>();
                for (String headerLine:headerLines) {
                    String [] headerInvoiceColumns = headerLine.split(",");
                    int numberOfInvoice = Integer.parseInt(headerInvoiceColumns[0]);
                    String dateOfInvoice = headerInvoiceColumns [1];
                    String customerName = headerInvoiceColumns [2];
                    
                    Invoice invoice = new Invoice (numberOfInvoice,dateOfInvoice,customerName);
                    arrayOfInvoices.add(invoice);
                } 
                result = fc.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File loadingInvoiceLineFile = fc.getSelectedFile();
                    Path loadLinePath = Paths.get(loadingInvoiceLineFile.getAbsolutePath());
                    List<String> lineLines = Files.readAllLines(loadLinePath);
                    System.out.println("InvoiceLine has been loaded");
                    for (String lineLine:lineLines) {
                        String [] lineInvoiceColumns = lineLine.split(",");
                        int numberOfInvoice = Integer.parseInt(lineInvoiceColumns[0]);
                        String itemName = lineInvoiceColumns [1];
                        double itemPrice = Double.parseDouble(lineInvoiceColumns [2]);
                        int count = Integer.parseInt(lineInvoiceColumns [3]);
                        Invoice invoicevalue = null;
                        for (Invoice invoice:arrayOfInvoices){
                            if (invoice.getNumber()==numberOfInvoice){
                                invoicevalue = invoice;
                                break;
                            }    
                        }
                        Line line = new Line(itemName,itemPrice,count,invoicevalue);
                        invoicevalue.getLines().add(line);
                
                    }
                }
                frame.setInvoices(arrayOfInvoices);
                TableModelOfInvoices tableModelOfInvoices = new TableModelOfInvoices (arrayOfInvoices);
                frame.setTableModelOfInvoices(tableModelOfInvoices);
                frame.getInvoiceTable().setModel(tableModelOfInvoices);
                frame.getTableModelOfInvoices().fireTableDataChanged();
            }
         } catch (IOException ex) {
             ex.printStackTrace();
        }
    }

    private void saveFile() {
        ArrayList<Invoice> invoices = frame.getInvoices();
        String headers="";
        String lines="";
        for (Invoice invoice: invoices){
            String invoiceCSV = invoice.getCSV();
            headers += invoiceCSV;
            headers += "\n";
            
            for (Line line: invoice.getLines()){
                String lineCSV = line.getCSV();
                lines += lineCSV;
                lines += "\n";
            }
        }
        try{
        JFileChooser fc = new JFileChooser();
        int result = fc.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION){
            File headerFile = fc.getSelectedFile();
            FileWriter headerFileWriter = new FileWriter(headerFile);
            headerFileWriter.write(headers);
            headerFileWriter.flush();
            headerFileWriter.close();
            result = fc.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION){
                File lineFile = fc.getSelectedFile();
                FileWriter lineFileWriter = new FileWriter(lineFile);
                lineFileWriter.write(lines);
                lineFileWriter.flush();
                lineFileWriter.close();
            }
         }
        }
        catch(Exception ex){
        }
        }
        
    private void createNewInvoice() {
        invoiceDetails = new InvoiceDetails(frame);
        invoiceDetails.setVisible(true);
    }

    private void deleteInvoice() {
        // this button to delete invoices from invoice table
        int selectedRow = frame.getInvoiceTable().getSelectedRow();
        if (selectedRow != -1){
            frame.getInvoices().remove(selectedRow);
            frame.getTableModelOfInvoices().fireTableDataChanged();
        }
    }
    private void createNewInvoiceOk() {
    // this button in the invoicesheader table when click to create new invoice the popup window has Ok button to add the invoice
        String date = invoiceDetails.getInvoiceDateField().getText();
        String customer = invoiceDetails.getCustomerNameField().getText();
        int number = frame.getNextInvoiceNumber();
        Invoice invoice = new Invoice (number,date,customer);
        frame.getInvoices().add(invoice);
        frame.getTableModelOfInvoices().fireTableDataChanged();
        invoiceDetails.setVisible(false);
        invoiceDetails.dispose();
        invoiceDetails=null;
    }
    private void createNewInvoiceCancel() {
    // this button in the invoicesheader table when click to create new invoice the popup window has cancel button to cancel adding the invoice

        invoiceDetails.setVisible(false);
        invoiceDetails.dispose();
        invoiceDetails = null;
    }
    
    
    private void save() {
        // this button used to create new item in line table
        lineDetails = new LineDetails(frame);
        lineDetails.setVisible(true);
    }

    private void cancel() {
        // this button to delete items from line table
        int selectedInvoice = frame.getInvoiceTable().getSelectedRow();
        int selectedRow = frame.getLineTable().getSelectedRow();
        
        if (selectedInvoice!= -1 && selectedRow!= -1){
            Invoice invoice = frame.getInvoices().get(selectedInvoice);
            invoice.getLines().remove(selectedRow);
            TableModelOfLines tableModelOfLines = new TableModelOfLines(invoice.getLines());
            frame.getLineTable().setModel(tableModelOfLines);
            tableModelOfLines.fireTableDataChanged();
        }
    }

    private void saveNewLineOk() {
        String itemName = lineDetails.getItemNameField().getText();
        String countString = lineDetails.getItemCountField().getText();
        String priceString = lineDetails.getItemPriceField().getText();
        int count = Integer.parseInt(countString);
        double itemPrice = Double.parseDouble(priceString);
        int selectedInvoice = frame.getInvoiceTable().getSelectedRow();
        if (selectedInvoice !=-1){
            Invoice invoice = frame.getInvoices().get(selectedInvoice);
            Line line = new Line (itemName,itemPrice,count,invoice);
            invoice.getLines().add(line);
            TableModelOfLines tableModelOfLines = (TableModelOfLines) frame.getLineTable().getModel();
            //tableModelOfLines.getLines().add(line);
            tableModelOfLines.fireTableDataChanged();
            frame.getTableModelOfInvoices().fireTableDataChanged();
        }
        lineDetails.setVisible(false);
        lineDetails.dispose();
        lineDetails=null;
    }
    private void saveNewLinCancel() {
        lineDetails.setVisible(false);
        lineDetails.dispose();
        lineDetails=null;
    }   
}