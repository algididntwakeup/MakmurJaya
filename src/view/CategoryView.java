package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CategoryView extends JFrame {
    private JTable categoryTable;
    private DefaultTableModel tableModel;

    public CategoryView() {
        setTitle("Category List");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {"ID", "Name", "Description"};
        tableModel = new DefaultTableModel(columnNames, 0);
        categoryTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(categoryTable);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void setCategoryList(String[][] categories) {
        tableModel.setRowCount(0);
        for (String[] category : categories) {
            tableModel.addRow(category);
        }
    }

    public void setModal(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setModal'");
    }
}
