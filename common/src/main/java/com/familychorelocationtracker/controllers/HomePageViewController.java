package com.familychorelocationtracker.controllers;

import com.codename1.rad.controllers.Controller;
import com.codename1.rad.controllers.ViewController;
import com.codename1.rad.nodes.ActionNode;
import com.codename1.rad.schemas.Thing;
import com.codename1.twitterui.models.Tweet;
import com.codename1.twitterui.models.TweetWrapper;
import com.codename1.twitterui.views.TweetRowView;
import com.codename1.ui.FontImage;
import com.codename1.ui.Toolbar;

public class HomePageViewController extends ViewController {
    /**
     * Creates a new ViewController with the given parent controller.
     *
     * @param parent
     */
    public HomePageViewController(Controller parent) {
        super(parent);


    }

    @Override
    protected void initControllerActions() {
        super.initControllerActions();

        Toolbar tb = new Toolbar(true);


    }
}
