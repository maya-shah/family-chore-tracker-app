package com.familychorelocationtracker.controllers;

import com.codename1.rad.controllers.Controller;
import com.codename1.rad.controllers.FormController;

public class ManageFamilyFormController extends FormController {
    public ManageFamilyFormController(Controller parent) {
        super(parent);

        this.getView().setUIID("ManageFamilyForm");

        setTitle("Manage Family");

    }
}
