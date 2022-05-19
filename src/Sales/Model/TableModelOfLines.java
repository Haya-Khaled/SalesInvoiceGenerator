
package Sales.Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class TableModelOfLines extends AbstractTableModel{
    private ArrayList<Line> lines;
    private String[] columns ={"No.","Item Name","Item Price","Count","Item Total"};
    private int rowIndex;
    
    public ArrayList<Line> getLines() {
        return lines;
    }

    public TableModelOfLines(ArrayList<Line> lines) {
        this.lines = lines;
    }
    
    @Override
    public String getColumnName(int v) {
        return columns[v];

    }

    @Override
    public int getRowCount() {
        return lines.size();

    }
    
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
   
        Line line = lines.get(rowIndex);
        
        switch (columnIndex){
            case 0: return line.getInvoice().getNumber();
            case 1: return line.getItemName();
            case 2: return line.getItemPrice();
            case 3: return line.getCount();
            case 4: return line.getTotalLine();
            default: return "";
    
}
    }

}
    
    
