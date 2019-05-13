package dev.kirillzhelt.registry.controllers;

import dev.kirillzhelt.registry.models.RegistryModel;
import dev.kirillzhelt.registry.models.UserType;
import dev.kirillzhelt.registry.views.RegistryView;

import java.util.ArrayList;
import java.util.EnumMap;

public class RegistryController {

    public final EnumMap<UserType, ArrayList<Command>> commandHubsForUserTypes =
        new EnumMap<UserType, ArrayList<Command>>(UserType.class) {{
           put(UserType.Manager, new ArrayList<Command>() {{
               add(RegistryController.this::getInformation);
               add(RegistryController.this::formReport); }});
           put(UserType.Administrator, new ArrayList<Command>() {{
               add(RegistryController.this::getInformation);
               add(RegistryController.this::formReport);
               add(RegistryController.this::transferRoom); }});
           put(UserType.Superintendent, new ArrayList<Command>() {{
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

    public void getInformation() {
    }

    public void formReport() {
    }

    public void transferRoom() {
    }

    public void bookKeys() {
    }

    public RegistryController(UserType userType) {
        registryView = new RegistryView(this, commandHubsForUserTypes.get(userType),
            commandNamesForUserTypes.get(userType));

    }
}
