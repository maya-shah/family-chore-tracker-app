package com.familychorelocationtracker.components;

import com.codename1.rad.controllers.ApplicationController;
import com.codename1.rad.controllers.FormController;
import com.codename1.twitterui.views.TWTGlobalTabs;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.familychorelocationtracker.controllers.ManageFamilyFormController;
import com.familychorelocationtracker.controllers.SettingsPageFormController;
import com.familychorelocationtracker.views.ChoresPageController;
import com.familychorelocationtracker.views.LocationsPageController;

import java.util.HashMap;
import java.util.Objects;

public class TileComponents extends Container {

    private final Container tileComponents = new Container(new GridLayout(1, 4));
    private final HashMap<String, Character> pages = new HashMap<>();
    private int numTiles;

    public TileComponents() {
        super(new BorderLayout());

        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        pages.put("Locations", FontImage.MATERIAL_LOCATION_ON);
        pages.put("Chores", FontImage.MATERIAL_TASK);
        pages.put("Settings", FontImage.MATERIAL_SETTINGS);
        pages.put("Manage", FontImage.MATERIAL_MANAGE_ACCOUNTS);

        tileComponents.getAllStyles().setMargin(10, 10, 10, 10);

        add(tileComponents);
    }

    public void setTitle(int numTiles) {
        this.numTiles = numTiles;

        for (String page : pages.keySet()) {
            Container tile = new Container();
            tile.setUIID("Tile");

            int size = Display.getInstance().getDisplayWidth() / 4;

            tile.setPreferredSize(new Dimension(size, size));

            tile.getAllStyles().setBgTransparency(255);
            tile.getAllStyles().setBgColor(0xffffff);
            tile.getAllStyles().setMargin(10, 10, 10, 10);


            Button title = new Button(page);
            title.setUIID("ButtonTitle");

            title.setVerticalAlignment(4);

            if (Objects.equals(title.getText(), "Locations")) {
                title.addActionListener(evt -> {
                    evt.consume();
                    TWTGlobalTabs.showTab(
                            evt,
                            new LocationsPageController(ApplicationController.getApplicationController(evt))
                    );
                });

            }

            else if (Objects.equals(title.getText(), "Chores")) {
                title.addActionListener(evt -> {
                    evt.consume();
                    TWTGlobalTabs.showTab(
                            evt,
                            new ChoresPageController(ApplicationController.getApplicationController(evt))
                    );
                });
            }

            else if (Objects.equals(title.getText(), "Settings")) {
                title.addActionListener(evt -> {
                    evt.consume();
                    new SettingsPageFormController(FormController.getCurrentFormController()).show();
                });
            }

            else if (Objects.equals(title.getText(), "Manage")) {
                title.addActionListener(evt -> {
                    evt.consume();
                    new ManageFamilyFormController(FormController.getCurrentFormController()).show();
                });
            }

            Container innerTile = new Container(BoxLayout.xCenter());

            innerTile.setPreferredSize(new Dimension(size, size));

            Label icon = new Label();

            icon.setMaterialIcon(pages.get(page));

            innerTile.add(BoxLayout.encloseYCenter(BoxLayout.encloseXCenter(icon), title));

            tile.add(BorderLayout.centerCenter(innerTile));

            tileComponents.add(tile);
        }

    }

}
