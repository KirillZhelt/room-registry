package dev.kirillzhelt.registry.controllers;

import dev.kirillzhelt.registry.models.RegistryModel;
import dev.kirillzhelt.registry.models.UserType;
import dev.kirillzhelt.registry.views.RegistryView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EnumMap;

public class RegistryController {

    private final EnumMap<UserType, ArrayList<ActionListener>> commandHubsForUserTypes =
        new EnumMap<UserType, ArrayList<ActionListener>>(UserType.class) {{
           put(UserType.Manager, new ArrayList<ActionListener>() {{
               add(RegistryController.this::getInformation);
               add(RegistryController.this::formReport); }});
           put(UserType.Administrator, new ArrayList<ActionListener>() {{
               add(RegistryController.this::getInformation);
               add(RegistryController.this::formReport);
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

    private RegistryView registryView;
    private RegistryModel registryModel;

    public void getInformation(ActionEvent e) {
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

        System.out.println("getInformation");
    }

    public void formReport(ActionEvent e) {
        /*
            Вариант использования “Получить отчёт”:
            Краткое описание:
            Данный вариант использования позволяет менеджеру автоматически сгенерировать
            один из видов отчёта: 1) список подразделений и перечень занимаемых им помещений;
            2) список, отображающий иерархию;
         */

        System.out.println("formReport");
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
        registryView = new RegistryView(this, commandHubsForUserTypes.get(userType),
            commandNamesForUserTypes.get(userType));

    }
}
