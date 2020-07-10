package radar.Tools;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
  *表格的样式类
 * @author 
 *
 */
public class TableStyleUI {
	
	
    public void makeFace(JTable table) {
    	
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer(){
        	
            private static final long serialVersionUID = 1234579841267L;
            
            @Override
            public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected,
            		boolean hasFocus,int row, int column) {
            	
                if(row%2==0){
                    setBackground(new Color(245,245,245));
                }else{
                    setBackground(new Color(255,255,255));
                }               
                // 表格内容居中
                setHorizontalAlignment(JLabel.CENTER);
                // 列头内容居中
                ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
                	.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                table.getTableHeader().setResizingAllowed(true);
                

                return super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
            }
        };

        Dimension size = table.getTableHeader().getPreferredSize();
        size.height = 80;//设置表头高度
        table.getTableHeader().setPreferredSize(size);
      //字体设置
        table.setFont(new Font("仿宋", Font.PLAIN, 16));
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table.setRowHeight(40);//设置行高
        table.setSelectionBackground(new Color(135,206,250));
        table.setSelectionForeground(new Color(255,0,0));

        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn col = table.getColumn(
                    table.getColumnName(i));
            col.setCellRenderer(renderer);
            setTableHeaderColor(table,i,new Color(72,171,247));
        }
    }
    
    public void makeFace(JTable table,int level) {
    	
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer(){
        	
            private static final long serialVersionUID = 1234579841267L;
            
            @Override
            public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected,
            		boolean hasFocus,int row, int column) {
            	
                if(row%2==0){
                    setBackground(new Color(245,245,245));
                }else{
                    setBackground(new Color(255,255,255));
                }               
                // 表格内容居中
                setHorizontalAlignment(JLabel.CENTER);
                // 列头内容居中
                ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
                	.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                table.getTableHeader().setResizingAllowed(true);
                

                return super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
            }
        };

        Dimension size = table.getTableHeader().getPreferredSize();
        if(level == 1)
       	 size.height = 50;//设置表头高度
       else
       	 size.height = 80;//设置表头高度
        table.getTableHeader().setPreferredSize(size);
      //字体设置
        table.setFont(new Font("仿宋", Font.PLAIN, 16));
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        if(level == 1)        	
        	table.setRowHeight(25);//设置行高
       else
    	   	table.setRowHeight(40);//设置行高
        
        table.setSelectionBackground(new Color(135,206,250));
        table.setSelectionForeground(new Color(255,0,0));

        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn col = table.getColumn(
                    table.getColumnName(i));
            col.setCellRenderer(renderer);
            setTableHeaderColor(table,i,new Color(72,171,247));
        }
    }

    /**
     * 该方法主要实现了表格中表头的背景色的设计，表头内容的居中效果
     * 
     * @param table
     *            表格
     * @param columnIndex
     *            要设置的列下标
     * @param c
     *            颜色
     */
    public void setTableHeaderColor(JTable table, int columnIndex, Color c) {
    	
        TableColumn column = table.getTableHeader().getColumnModel().getColumn(columnIndex);
        
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            /** serialVersionUID */
            private static final long serialVersionUID = 43279841267L;

            @Override
            public Component getTableCellRendererComponent(JTable table, 
                    Object value, boolean isSelected,boolean hasFocus,
                    int row, int column) {

                setHorizontalAlignment(JLabel.CENTER);// 表格内容居中
                setFont(new Font("仿宋", Font.PLAIN, 16));
                ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
                        .setHorizontalAlignment(DefaultTableCellRenderer.CENTER);// 列头内容居中
                
                return super.getTableCellRendererComponent(table, value, 
                        isSelected, hasFocus, row, column);
            }
        };
        cellRenderer.setBackground(c);        
        column.setHeaderRenderer(cellRenderer);
    }
}
