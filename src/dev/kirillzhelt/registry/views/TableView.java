package dev.kirillzhelt.registry.views;

import dev.kirillzhelt.registry.controllers.RegistryController;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.TreeMap;

class UnitsTableModel extends AbstractTableModel {
    public static final String[] columnNames = new String[] { "Unit", "Rooms" };

    private TreeMap<Integer, ArrayList<Integer>> roomsForUnits;
    private ArrayList<Integer> units;

    public UnitsTableModel(TreeMap<Integer, ArrayList<Integer>> roomsForUnits) {
        this.roomsForUnits = roomsForUnits;
        this.units = new ArrayList<>(roomsForUnits.keySet());
    }

    @Override
    public int getRowCount() {
        return units.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (column == 0) {
            return units.get(row);
        }
        else if (column == 1) {
            return roomsForUnits.get(units.get(row));
        }

        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}

public class TableView extends JFrame {
    private JPanel jPanelMain;
    private JScrollPane jScrollPane;
    private JTable jTable;

    public TableView(RegistryController registryController, TreeMap<Integer, ArrayList<Integer>> roomsForUnits,
                     boolean isVisible) {
        super("Rooms for units");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        setBounds(750, 250, 500, 600);

        setContentPane(jPanelMain);

        UnitsTableModel unitsTableModel = new UnitsTableModel(roomsForUnits);
        jTable.setModel(unitsTableModel);

        pack();
        setVisible(isVisible);
    }
}
