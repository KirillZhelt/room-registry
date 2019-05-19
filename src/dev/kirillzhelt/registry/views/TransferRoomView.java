package dev.kirillzhelt.registry.views;

import dev.kirillzhelt.registry.controllers.RegistryController;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TransferRoomView extends JFrame {

    private JPanel jPanelMain;
    private JComboBox jComboBoxUnitFrom;
    private JComboBox jComboBoxRoom;
    private JComboBox jComboBoxUnitTo;
    private JButton jButtonTransfer;
    private JLabel jLabelInvalid;

    public TransferRoomView(RegistryController registryController, ArrayList<Integer> roomsNumbers,
                            boolean isVisible) {
        super("Transfer room");

        setContentPane(jPanelMain);

        setBounds(750, 250, 500, 600);

        jLabelInvalid.setVisible(false);

        for (Integer roomNumber : roomsNumbers) {
            jComboBoxUnitFrom.addItem(roomNumber);
            jComboBoxUnitTo.addItem(roomNumber);
        }

        if (!roomsNumbers.isEmpty()) {
            jComboBoxUnitFrom.setSelectedItem(roomsNumbers.get(0));
            jComboBoxUnitTo.removeItem(roomsNumbers.get(0));

            setComboBoxRoom(registryController, roomsNumbers.get(0));
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

                    setComboBoxRoom(registryController, selectedUnit);
                }
            }
        });

        jButtonTransfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int unitFrom = (Integer)jComboBoxUnitFrom.getSelectedItem();
                    int room = (Integer)jComboBoxRoom.getSelectedItem();
                    int unitTo = (Integer)jComboBoxUnitTo.getSelectedItem();

                    registryController.transferRoom(unitFrom, room, unitTo);
                    TransferRoomView.this.dispatchEvent(new WindowEvent(TransferRoomView.this, WindowEvent.WINDOW_CLOSING));
                } catch (NullPointerException nullPointerException) {
                    showInvalidData();
                }
            }
        });

        pack();
        setVisible(isVisible);
    }

    private void setComboBoxRoom(RegistryController registryController, int unitNumber) {
        ArrayList<Integer> roomsNumbers = registryController.getUnitRooms(unitNumber);

        jComboBoxRoom.removeAllItems();

        for (Integer roomNumber : roomsNumbers)
            jComboBoxRoom.addItem(roomNumber);
    }

    private void showInvalidData() {
        jLabelInvalid.setVisible(true);
    }
}
