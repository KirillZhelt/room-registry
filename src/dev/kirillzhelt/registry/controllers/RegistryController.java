package dev.kirillzhelt.registry.controllers;

import dev.kirillzhelt.registry.models.UserType;

import java.util.ArrayList;
import java.util.EnumMap;

public class RegistryController {
    public static final EnumMap<UserType, ArrayList<Command>> commandHubsForUserTypes =
        new EnumMap<UserType, ArrayList<Command>>(UserType.class) {{
           put(UserType.Manager, new ArrayList<Command>() {{
               add(RegistryController::getInformation);
               add(RegistryController::formReport); }});
           put(UserType.Administrator, new ArrayList<Command>() {{
               add(RegistryController::getInformation);
               add(RegistryController::formReport);
               add(RegistryController::transferRoom); }});
           put(UserType.Superintendent, new ArrayList<Command>() {{
               add(RegistryController::bookKeys); }});
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

    public static void getInformation() {
    }

    public static void formReport() {

    }

    public static void transferRoom() {

    }

    public static void bookKeys() {

    }

    public RegistryController(UserType userType) {
        createMenuForm(commandHubsForUserTypes.get(userType), commandNamesForUserTypes.get(userType));
    }

    private void createMenuForm(ArrayList<Command> commandHub, ArrayList<String> commandNames) {
        System.out.println(commandHub);
        System.out.println(commandNames);
    }

}
