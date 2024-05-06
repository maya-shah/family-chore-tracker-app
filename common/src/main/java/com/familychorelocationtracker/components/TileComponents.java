package com.familychorelocationtracker.components;

import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;

import java.util.ArrayList;
import java.util.List;

public class TileComponents extends Container {

    private final Container tileComponents = new Container(new BorderLayout());
    private final Container grid  = new Container(new GridLayout(2, 2));
    private List<Container> tiles = new ArrayList<>();
    private int numTiles;

    public TileComponents() {
        super(new BorderLayout());

        add(BorderLayout.CENTER, tileComponents);
    }

    public void setTitle(int numTiles) {
        this.numTiles = numTiles;

        for (int i = 0; i < numTiles; i++) {
            tiles.add(createTile(numTiles));
            System.out.println(createTile(numTiles).getPreferredSize());
        }

        for (Container tile : tiles) {
            grid.add(tile);
        }

        tileComponents.add(BorderLayout.CENTER, grid);

    }

    private Container createTile(int numTiles) {
        Container tile = new Container();

        int half = numTiles / 2;

        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight()) / numTiles; // adjust size as needed

        tile.setPreferredW(size);
        tile.setPreferredH(size);

        System.out.println(size);
        System.out.println(tile.getPreferredSize());

        tile.getAllStyles().setBgTransparency(255);
        tile.getAllStyles().setBgColor(0x0000);
        tile.getAllStyles().setMargin(10, 10, 10, 10); // Add margins to create space between squares


        return tile;

//        Label titleLabel = new Label(title);
//
//        titleLabel.getAllStyles().setBgColor(0x0000);
//        titleLabel.getAllStyles().setBgTransparency(255);
//
//        tile.add(titleLabel);
//
//        return tile;
    }
}
