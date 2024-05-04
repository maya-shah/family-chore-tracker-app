package com.familychorelocationtracker.controllers;

import com.codename1.rad.controllers.ActionSupport;
import com.codename1.rad.controllers.Controller;
import com.codename1.rad.controllers.FormController;
import com.codename1.rad.models.Entity;
import com.codename1.rad.nodes.ActionNode;
import com.codename1.rad.nodes.ViewNode;
import com.codename1.rad.schemas.Person;
import com.codename1.twitterui.models.TWTUserProfile;
import com.codename1.twitterui.views.TWTSettingsForm;
import com.codename1.twitterui.views.TWTTitleComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.familychorelocationtracker.views.SettingsPage;

import static com.codename1.rad.ui.UI.action;
import static com.codename1.rad.ui.UI.*;
import com.codename1.rad.models.PropertySelector;
import com.codename1.rad.schemas.Thing;
import com.codename1.rad.schemas.PostalAddress;
import com.codename1.ui.list.DefaultListModel;


public class SettingsPageFormController extends FormController {
    public static final ActionNode username = action(
            label("Username"),
            property(entity-> new PropertySelector(entity, Thing.identifier))
    ),
    phone = action(
            label("Phone"),
    tags(Person.telephone)
           ),
    email = action(
            label("Email"),
    tags(Person.email)
           ),
    password = action(
            label("Password")
           ),
    country = action(
            label("Country"),
    radioListY(
            label("Select Country"),
    description("Please select a country from the list below"),
    options(new DefaultListModel("Canada", "United States", "Mexico", "Spain", "England", "France")),
    tags(PostalAddress.addressCountry)
                   )
                           ),
    yourTwitterData = action(
            label("Your twitter data")
           ),
    security = action(
            label("Security")
           ),
    deactivate = action(
            label("Deactivate your account")
           );

    public SettingsPageFormController(Controller parent) {
        super(parent);

        TWTSettingsForm view = new TWTSettingsForm(lookup(TWTUserProfile.class), getViewNode());

        setTitle("Settings and Privacy");

        getCurrentFormController().setTitleComponent(new Label(" "));

        setView(view);
    }

    @Override
    protected ViewNode createViewNode() {
        this.hasBackCommand();
        return new ViewNode(
                section(
                        label("Login and Security"),
                        username, phone, email, password
                ),
                section(
                        label("Data and permissions"),
                        country, yourTwitterData, security
                ),
                section(
                        deactivate
                )
        );
    }

}