package dev.kirillzhelt.registry.controllers;

import dev.kirillzhelt.registry.models.RegistryModel;
import dev.kirillzhelt.registry.models.UserType;
import dev.kirillzhelt.registry.views.ComboBoxView;
import dev.kirillzhelt.registry.views.MenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EnumMap;

public class RegistryController {

    private final EnumMap<UserType, ArrayList<ActionListener>> commandHubsForUserTypes =
        new EnumMap<UserType, ArrayList<ActionListener>>(UserType.class) {{
           put(UserType.Manager, new ArrayList<ActionListener>() {{
               add(RegistryController.this::selectInformationType);
               add(RegistryController.this::selectReportType); }});
           put(UserType.Administrator, new ArrayList<ActionListener>() {{
               add(RegistryController.this::selectInformationType);
               add(RegistryController.this::selectReportType);
               add(RegistryController.this::transferRoom); }});
           put(UserType.Superintendent, new ArrayList<ActionListener>() {{
               add(RegistryController.this::bookKeys); }});
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

    private MenuView registryView;
    private RegistryModel registryModel;

    private MenuView selectInformationTypeMenu;
    private ComboBoxView selectRoomComboBox;
    private ComboBoxView selectUnitComboBox;

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
        // TODO: retrieve information about roomNumber
        System.out.println(roomNumber);

        System.out.println("getRoomInformation");
    }

    public void getUnitInformation(int unitNumber) {
        // TODO: retrieve information about unitNumber
        System.out.println(unitNumber);

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

        System.out.println("selectReportType");
    }

    public void transferRoom(ActionEvent e) {
        /*
            Вариант использования “Передать помещение”:
            Краткое описание:
            Данный вариант использования позволяет управляющему помещениями передавать помещения
            от одного подразделения к другому.
         */

        System.out.println("transferRoom");
    }

    public void bookKeys(ActionEvent e) {
        /*
            Вариант использования “Заказать ключи”:
            Краткое описание:
            Данный вариант использования позволяет заведующему подразделением заказать ключи.
         */

        System.out.println("bookKeys");
    }

    public RegistryController(UserType userType) {
        registryModel = new RegistryModel();

        registryView = new MenuView(this, commandHubsForUserTypes.get(userType),
            commandNamesForUserTypes.get(userType), true);

        selectInformationTypeMenu = new MenuView(this, informationTypesListeners,
            informationTypesNames, false);

        ArrayList<Integer> rooms = registryModel.getRoomsNumbers();
        selectRoomComboBox = new ComboBoxView(this, this::getRoomInformation, rooms,
            "Select room:", false);

        ArrayList<Integer> units = registryModel.getUnitsNumbers();
        selectUnitComboBox = new ComboBoxView(this, this::getUnitInformation, units,
            "Select unit: ", false);
    }
}
