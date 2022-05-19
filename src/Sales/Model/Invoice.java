
package Sales.Model;

import java.util.ArrayList;

public class Invoice {
    private int number;
    private String date;
    private String customername;
    private ArrayList<Line> lines;

    public Invoice() {
    }

    public ArrayList<Line> getLines() {
        if (lines==null){
            lines = new ArrayList<>();
        }
        
        return lines;
    }

    public Invoice(int number, String date, String customername) {
        this.number = number;
        this.date = date;
        this.customername = customername;
    }
    
    public double getTotalInvoice(){
        double total = 0.0;
        for (Line line:getLines()){
            total += line.getTotalLine();
        }
        return total;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Invoice{" + "number=" + number + ", date=" + date + ", customername=" + customername + '}';
    }
    public String getCSV(){
        return number+","+date+","+customername;
    }


    
    
    
}
