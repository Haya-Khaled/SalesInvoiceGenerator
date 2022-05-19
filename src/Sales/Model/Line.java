
package Sales.Model;

public class Line {
    private String itemName;
    private double itemPrice;
    private int count;
    private Invoice invoice;

    public Line() {
    }

    public Line(String itemName, double itemPrice, int count, Invoice invoice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
        this.invoice = invoice;
    }

    public Line(int numberOfInvoice, String itemName, double itemPrice, int count, Invoice invoicevalue) {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getTotalLine(){
        return itemPrice*count;
    }
  
    @Override
    public String toString() {
        return "Line{" + "num=" + invoice.getNumber() + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", count=" + count + '}';
    }

    public Invoice getInvoice() {
        return invoice;
    }
    public String getCSV(){
        return invoice.getNumber()+","+itemName+","+itemPrice+","+count;
    } 

    
}
