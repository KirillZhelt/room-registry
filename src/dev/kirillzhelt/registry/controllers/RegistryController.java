package dev.kirillzhelt.registry.controllers;

import dev.kirillzhelt.registry.models.RegistryModel;
import dev.kirillzhelt.registry.models.Room;
import dev.kirillzhelt.registry.models.Unit;
import dev.kirillzhelt.registry.models.UserType;
import dev.kirillzhelt.registry.views.ComboBoxView;
import dev.kirillzhelt.registry.views.LabelView;
import dev.kirillzhelt.registry.views.MenuView;
import dev.kirillzhelt.registry.views.TransferRoomView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.TreeMap;

public class RegistryController {

    private final EnumMap<UserType, ArrayList<ActionListener>> commandHubsForUserTypes =
        new EnumMap<UserType, ArrayList<ActionListener>>(UserType.class) {{
           put(UserType.Manager, new ArrayList<ActionListener>() {{
               add(RegistryController.this::selectInformationType);
               add(RegistryController.this::selectReportType); }});
           put(UserType.Administrator, new ArrayList<ActionListener>() {{
               add(RegistryController.this::selectInformationType);
               add(RegistryController.this::selectReportType);
               add(RegistryController.this::selectRoomForTransfer); }});
           put(UserType.Superintendent, new ArrayList<ActionListener>() {{
               add(RegistryController.this::selectRoomForBook); }});
    }};

    public static final EnumMap<UserType, ArrayList<String>> commandNamesForUserTypes =
        new EnumMap<UserType, ArrayList<String>>(UserType.class) {{
            put(UserType.Manager, new ArrayList<String>() {{
                add("Get information");
                add("Form report"); }});
            put(UserType.Administrator, new ArrayList<String>() {{
                add("Get information");
                add("Form report");
                add("Transfer room"); }});
            put(UserType.Superintendent, new ArrayList<String>() {{
                add("Book keys"); }});
    }};

    private final ArrayList<ActionListener> informationTypesListeners = new ArrayList<ActionListener>() {{
        add(RegistryController.this::selectRoom);
        add(RegistryController.this::selectUnit);
    }};

    public static final ArrayList<String> informationTypesNames = new ArrayList<String>() {{
        add("Rooms");
        add("Units");
    }};

    private final ArrayList<ActionListener> reportTypesListeners = new ArrayList<ActionListener>() {{
        add(RegistryController.this::showRoomsForUnits);
        add(RegistryController.this::selectUnitForHierarchyReport);
    }};

    public static final ArrayList<String> reportTypesNames = new ArrayList<String>() {{
       add("Show rooms for units");
       add("Show unit hierarchy");
    }};

    private MenuView registryView;
    private RegistryModel registryModel;

    private MenuView selectInformationTypeMenu;
    private MenuView selectReportTypeMenu;
    private ComboBoxView selectRoomComboBox;
    private ComboBoxView selectUnitComboBox;
    private ComboBoxView selectRoomForBookComboBox;

    private ArrayList<Integer> roomsNumbers;
    private ArrayList<Integer> unitsNumbers;

    public RegistryController(UserType userType) {
        registryModel = new RegistryModel();

        registryView = new MenuView(this, commandHubsForUserTypes.get(userType),
            commandNamesForUserTypes.get(userType), true);

        selectInformationTypeMenu = new MenuView(this, informationTypesListeners,
            informationTypesNames, false);

        selectReportTypeMenu = new MenuView(this, reportTypesListeners, reportTypesNames,
            false);

        roomsNumbers = registryModel.getRoomsNumbers();
        selectRoomComboBox = new ComboBoxView(this, this::getRoomInformation, roomsNumbers,
            "Select room:", false);

        selectRoomForBookComboBox = new ComboBoxView(this, this::bookRoom,
            roomsNumbers, "Select room:", false);

        unitsNumbers = registryModel.getUnitsNumbers();
        selectUnitComboBox = new ComboBoxView(this, this::getUnitInformation, unitsNumbers,
            "Select unit: ", false);
    }

    public void selectInformationType(ActionEvent e) {
        /*
            Вариант использования “Получить сведения”:
            Краткое описание:
            Данный вариант использования позволяет менеджеру работать с данными помещений и подразделений.
            Сведения о подразделении включают код, номер подразделения, у которого данное подразделение
            состоит в подчинении, полное название и краткое название, а также родительные и дательные
            падежи названий. Сведения о помещении включают номер, в который входит номер корпуса, номер этажа,
            номер помещения, тип помещения, кабинет руководителя, приемная руководителя, лаборатория, цех,
            столовая, а также площадь помещения.
         */
        selectInformationTypeMenu.setVisible(true);

        System.out.println("selectInformationType");
    }

    public void selectRoom(ActionEvent e) {
        selectInformationTypeMenu.setVisible(false);
        selectRoomComboBox.setVisible(true);

        System.out.println("selectRoom");
    }

    public void selectUnit(ActionEvent e) {
        selectInformationTypeMenu.setVisible(false);
        selectUnitComboBox.setVisible(true);

        System.out.println("selectUnit");
    }

    public void getRoomInformation(int roomNumber) {
        Room room = registryModel.getRoom(roomNumber);

        LabelView labelView = new LabelView("Room", room.toHtmlString());

        System.out.println("getRoomInformation");
    }

    public void getUnitInformation(int unitNumber) {
        Unit unit = registryModel.getUnit(unitNumber);

        LabelView labelView = new LabelView("Unit", unit.toHtmlString());

        System.out.println("getUnitInformation");
    }

    public void selectReportType(ActionEvent e) {
        /*
            Вариант использования “Получить отчёт”:
            Краткое описание:
            Данный вариант использования позволяет менеджеру автоматически сгенерировать
            один из видов отчёта: 1) список подразделений и перечень занимаемых им помещений;
            2) список, отображающий иерархию;
         */
        selectReportTypeMenu.setVisible(true);

        System.out.println("selectReportType");
    }

    public void showRoomsForUnits(ActionEvent e) {
        selectReportTypeMenu.setVisible(false);

        TreeMap<Integer, ArrayList<Integer>> roomsForUnits = registryModel.getRoomsForUnits();
        System.out.println(roomsForUnits);

        System.out.println("showRoomsForUnits");
    }

    public void selectUnitForHierarchyReport(ActionEvent e) {
        selectReportTypeMenu.setVisible(false);

        System.out.println("selectUnitForHierarchyReport");
    }

    public void showUnitHierarchyReport(int unitNumber) {

    }

    public void selectRoomForTransfer(ActionEvent e) {
        /*
            Вариант использования “Передать помещение”:
            Краткое описание:
            Данный вариант использования позволяет управляющему помещениями передавать помещения
            от одного подразделения к другому.
         */
        TransferRoomView transferRoomView = new TransferRoomView(this, unitsNumbers, true);

        System.out.println("selectRoomForTransfer");
    }

    public void transferRoom(int unitFrom, int room, int unitTo) {
        registryModel.transferRoom(unitFrom, room, unitTo);

        System.out.println("transferRoom");
    }

    public ArrayList<Integer> getUnitRooms(int unitNumber) {
        return registryModel.getUnitRooms(unitNumber);
    }

    public void selectRoomForBook(ActionEvent e) {
        /*
            Вариант использования “Заказать ключи”:
            Краткое описание:
            Данный вариант использования позволяет заведующему подразделением заказать ключи.
         */
        selectRoomForBookComboBox.setVisible(true);


        System.out.println("bookKeys");
    }

    public void bookRoom(int roomNumber) {
        LabelView labelView = new LabelView("Room", "Room " + roomNumber + " was booked");

        System.out.println("bookRoom");
    }
}
