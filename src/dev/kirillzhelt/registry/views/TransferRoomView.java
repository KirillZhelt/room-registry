package dev.kirillzhelt.registry.views;

import dev.kirillzhelt.registry.controllers.RegistryController;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class TransferRoomView extends JFrame {

    private JPanel jPanelMain;
    private JComboBox jComboBoxUnitFrom;
    private JComboBox jComboBoxRoom;
    private JComboBox jComboBoxUnitTo;
    private JButton jButtonTransfer;

    public TransferRoomView(RegistryController registryController, ArrayList<Integer> roomsNumbers,
                            boolean isVisible) {
        super("Transfer room");

        setContentPane(jPanelMain);

        jComboBoxRoom.setEnabled(false);
        jComboBoxUnitTo.setEnabled(false);

        for (Integer roomNumber : roomsNumbers) {
            jComboBoxUnitFrom.addItem(roomNumber);
            jComboBoxUnitTo.addItem(roomNumber);
        }

        if (!roomsNumbers.isEmpty()) {
            jComboBoxUnitFrom.setSelectedItem(roomsNumbers.get(0));
            jComboBoxUnitTo.removeItem(roomsNumbers.get(0));
        }

        jComboBoxUnitFrom.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.DESELECTED) {
                    int selectedUnit = (Integer)e.getItem();

                    jComboBoxUnitTo.addItem(selectedUnit);
                }
                else if (e.getStateChange() == ItemEvent.SELECTED) {
                    int selectedUnit = (Integer)e.getItem();

                    jComboBoxUnitTo.removeItem(selectedUnit);
                    jComboBoxUnitTo.setEnabled(true);

                    ArrayList<Integer> roomsNumbers = registryController.getUnitRooms(selectedUnit);

                    for (Integer roomNumber : roomsNumbers)
                        jComboBoxRoom.addItem(roomNumber);

                    jComboBoxRoom.setEnabled(true);
                }
            }
        });

        pack();
        setVisible(isVisible);
    }
}
