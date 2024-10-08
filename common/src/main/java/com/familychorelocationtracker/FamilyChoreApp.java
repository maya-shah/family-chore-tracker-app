package com.familychorelocationtracker;

import com.codename1.rad.controllers.ApplicationController;
import com.codename1.rad.controllers.ControllerEvent;
import com.codename1.rad.nodes.ActionNode;
import com.codename1.twitterui.models.TWTUserProfile;
import com.codename1.twitterui.models.TWTUserProfileImpl;
import com.codename1.twitterui.views.TWTGlobalTabs;
import com.codename1.twitterui.views.TWTSideBarView;
import com.codename1.ui.FontImage;
import com.familychorelocationtracker.controllers.ChoresPageFormController;
import com.familychorelocationtracker.controllers.SettingsPageFormController;
import com.familychorelocationtracker.providers.EntityListProvider;
import com.familychorelocationtracker.services.TweetAppClient;
import com.familychorelocationtracker.views.HomePageController;
import com.familychorelocationtracker.views.ManageFamilyController;
import com.familychorelocationtracker.views.LocationsPageController;

import static com.codename1.rad.util.NonNull.with;

public class FamilyChoreApp extends ApplicationController {

    public static final ActionNode HOME_TAB = ActionNode.builder().
            icon(FontImage.MATERIAL_HOME).
            addActionListener(evt -> {
                evt.consume();
                TWTGlobalTabs.showTab(
                        evt,
                        new HomePageController(ApplicationController.getApplicationController(evt))
                );
            }).
            build();

    public static final ActionNode CHORES_TAB = ActionNode.builder()
            .icon(FontImage.MATERIAL_TASK)
            .addActionListener(evt -> {
                evt.consume();
                TWTGlobalTabs.showTab(
                        evt,
                        new ChoresPageFormController(ApplicationController.getApplicationController(evt).getCurrentFormController())
                );
            }).build();

    public static final ActionNode LOCATIONS_TAB = ActionNode.builder()
            .icon(FontImage.MATERIAL_LOCATION_ON)
            .addActionListener(evt -> {
                evt.consume();
                TWTGlobalTabs.showTab(
                        evt,
                        new LocationsPageController(ApplicationController.getApplicationController(evt))
                );
            }).build();

    @Override
    protected void initControllerActions() {
        super.initControllerActions();

        // Sidebar Actions which will be injected into the TWTSideBarView
        ActionNode.builder()
                .label("Home")
                .icon(FontImage.MATERIAL_HOME)
                .addToController(this, TWTSideBarView.SIDEBAR_ACTIONS, evt -> System.out.println("Home was clicked"));

        ActionNode.builder()
            .label("Locations")
            .icon(FontImage.MATERIAL_LOCATION_ON)
            .addToController(this, TWTSideBarView.SIDEBAR_ACTIONS, evt -> System.out.println("Locations was clicked"))
                .addActionListener(evt -> {
                    evt.consume();
                    TWTGlobalTabs.showTab(
                            evt,
                            new LocationsPageController(ApplicationController.getApplicationController(evt))
                    );
                });

        ActionNode.builder()
                .label("Chores")
                .icon(FontImage.MATERIAL_TASK)
                .addToController(this, TWTSideBarView.SIDEBAR_ACTIONS, evt -> System.out.println("Chores was clicked"))
                .addActionListener(evt -> {
                    evt.consume();
                    TWTGlobalTabs.showTab(
                            evt,
                            new ChoresPageFormController(getCurrentFormController())
                    );
                });

        ActionNode.builder().
                icon(FontImage.MATERIAL_MANAGE_ACCOUNTS)
                .label("Manage Family")
                .addToController(this, TWTSideBarView.SIDEBAR_ACTIONS, evt ->
                        System.out.println("Manage was clicked")
                )
                .addActionListener(evt -> {
                    evt.consume();
                    new ManageFamilyController(ApplicationController.getApplicationController(evt));
                });

        ActionNode.builder()
                .label("Settings and Privacy")
                .icon(FontImage.MATERIAL_SETTINGS)
                .addToController(this, TWTSideBarView.SIDEBAR_SETTINGS_ACTIONS, evt ->
                        System.out.println("Settings was clicked")
                )
                .addActionListener(evt -> {
                    evt.consume();
                    new SettingsPageFormController(getCurrentFormController()).show();

                });

        ActionNode.builder()
                .label("  Logout    ")
                .icon(FontImage.MATERIAL_EXIT_TO_APP)
                .addToController(this, TWTSideBarView.SIDEBAR_BOTTOM_RIGHT_ACTIONS, evt -> {
                });


        addActions(TWTGlobalTabs.GLOBAL_TABS,
                HOME_TAB, CHORES_TAB, LOCATIONS_TAB
        );

    }

    @Override
    protected void onStartController() {
        super.onStartController();

        addLookup(new EntityListProvider());

        /*
         * Add a TweetAppClient as a lookup so that it will be available throughout
         * the app via {@link #lookup(Class)}
         */
        TweetAppClient client = new TweetAppClient();
        addLookup(client);
        if (client.isLoggedIn()) {
            // The client is logged in.  We hardcode the userprofile here as me
            // but in real app you would have the profile created based on who is logged in.
            TWTUserProfile userProfile = new TWTUserProfileImpl();
            userProfile.setName("Evelyn");
            userProfile.setQualityFilter(true);
            userProfile.setCountry("United Kingdom");
            userProfile.setIdentifier("Evelyn");
            addLookup(TWTUserProfile.class, userProfile);
        }
    }

    public void actionPerformed(ControllerEvent evt) {

        with(evt, StartEvent.class, startEvent -> {
            if (!startEvent.isShowingForm()) {
                startEvent.setShowingForm(true);
                new HomePageController(this).show();
            }

        });
        super.actionPerformed(evt);
    }
}
