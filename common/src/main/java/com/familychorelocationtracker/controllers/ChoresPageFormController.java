package com.familychorelocationtracker.controllers;

import com.codename1.rad.controllers.Controller;
import com.codename1.rad.controllers.FormController;
import com.familychorelocationtracker.views.ChoresPageView;

public class ChoresPageFormController extends FormController {

    public ChoresPageFormController(Controller parent) {
        super(parent);

        ChoresPageView choresPageView = new ChoresPageView();

        choresPageView.setTitle("Chores");

        choresPageView.getToolbar().getStyle().setPadding(5, 5, 0, 0);

        setView(choresPageView);
    }
}
