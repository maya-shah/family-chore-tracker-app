package com.familychorelocationtracker.controllers;


import com.codename1.rad.controllers.Controller;
import com.codename1.rad.controllers.ViewController;
import com.familychorelocationtracker.FamilyChoreApp;
import com.familychorelocationtracker.LocationsPageView;
import com.familychorelocationtracker.views.LocationsPageController;
import com.familychorelocationtracker.views.LocationsPageModel;

import javax.swing.text.View;


public class LocationsPageViewController extends ViewController {

    public LocationsPageViewController(Controller parent) {
        super(parent);
    }

    @Override
    protected void initControllerActions() {
        new LocationsPageView().show();
    }


//    /**
//     * Creates a new ViewController with the given parent controller.
//     *
//     * @param parent
//     */
//    public LocationsPageViewController(Controller parent) {
//        super(parent);
//        this.mapComponent = mapComponent;
//    }
//
//    @Override
//    protected void initControllerActions() {
//        String htmlContent = "<iframe width=\"100%\" height=\"100%\" frameborder=\"0\" scrolling=\"no\" marginheight=\"0\" marginwidth=\"0\" src=\"https://www.openstreetmap.org/export/embed.html?bbox=<longitude_min>,<latitude_min>,<longitude_max>,<latitude_max>&layer=mapnik\"></iframe>";
//        // Replace <longitude_min>, <latitude_min>, <longitude_max>, <latitude_max> with your desired coordinates or bounding box
//
//        ((HTMLComponent) find("mapComponent")).setBody(htmlContent);

    }




