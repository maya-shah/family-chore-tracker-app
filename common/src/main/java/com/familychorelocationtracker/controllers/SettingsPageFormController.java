package com.familychorelocationtracker.controllers;

import com.codename1.rad.controllers.Controller;
import com.codename1.rad.controllers.FormController;
import com.codename1.rad.nodes.ActionNode;
import com.codename1.rad.nodes.ViewNode;
import com.codename1.rad.schemas.Person;
import com.codename1.twitterui.models.TWTUserProfile;
import com.codename1.twitterui.views.TWTSettingsForm;
import com.codename1.ui.Label;

import static com.codename1.rad.ui.UI.action;
import static com.codename1.rad.ui.UI.*;

import com.codename1.rad.models.PropertySelector;
import com.codename1.rad.schemas.Thing;
import com.codename1.rad.schemas.PostalAddress;
import com.codename1.ui.list.DefaultListModel;

import java.util.Arrays;
import java.util.Collection;


public class SettingsPageFormController extends FormController {
    private static final DefaultListModel<String> items = new DefaultListModel<>
            (Arrays.asList("Canada", "United States", "Mexico", "Spain", "England", "France"));

    public static final ActionNode username = action(
            label("Username"),
            property(entity -> new PropertySelector(entity, Thing.identifier))
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
                            options(items),
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