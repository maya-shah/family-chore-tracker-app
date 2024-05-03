package com.familychorelocationtracker;

import com.codename1.maps.BoundingBox;
import com.codename1.maps.Coord;
import com.codename1.maps.MapComponent;
import com.codename1.maps.MapListener;
import com.codename1.maps.providers.OpenStreetMapProvider;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class LocationsPageView extends Container {
    private final MapComponent mapComponent = new MapComponent(new OpenStreetMapProvider());

    private int zoom;

    private final Container legendContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    private final List<Marker> markers = new ArrayList<>();

    private final List<Coord> markerCoordinates = new ArrayList<>();


    public LocationsPageView() {
        super(new BorderLayout());

        Label colorLabel = new Label();
        colorLabel.getAllStyles().setBgColor(0x0000);
        colorLabel.getAllStyles().setBgTransparency(5);
        colorLabel.setPreferredW(2000); // Set preferred width for color label
        colorLabel.setPreferredH(1200); // Set preferred width for color label
        colorLabel.getAllStyles().setMargin(0, 0, 0, 0);

        Container buttons = getButtons();

        legendContainer.setScrollableY(true);


        Container mapContainer = LayeredLayout.encloseIn(
                BorderLayout.centerTotalBelow(mapComponent), BorderLayout.east(BorderLayout.north(buttons)), BorderLayout.center(colorLabel));


        mapContainer.setPreferredSize(new Dimension(400, 1200));


        mapComponent.setPreferredSize(new Dimension(1200, 850));


        add(BorderLayout.NORTH, mapContainer);
        add(BorderLayout.SOUTH, legendContainer);

    }

    private Container getButtons() {
        Container buttons = new Container(new BoxLayout(BoxLayout.X_AXIS));

        Button zoomIn = new Button("+");
        Button zoomOut = new Button("-");

        zoomIn.getAllStyles().setBgColor(0xffffff); // Set background color to black
        zoomIn.getAllStyles().setFgColor(0x000000); // Set foreground color to white

        zoomOut.getAllStyles().setBgColor(0xffffff); // Set background color to black
        zoomOut.getAllStyles().setFgColor(0x000000); // Set foreground color to white

        zoomIn.getAllStyles().setBgTransparency(150);
        zoomOut.getAllStyles().setBgTransparency(150);


        zoomIn.addActionListener(e -> {
            mapComponent.zoomIn();
            mapComponent.revalidate();
        });
        zoomOut.addActionListener(e -> {
            mapComponent.zoomOut();
            mapComponent.revalidate();
        });

        buttons.add(zoomIn);
        buttons.add(zoomOut);


//        buttons.add(BorderLayout.WEST, zoomIn);
//        buttons.add(BorderLayout.EAST, zoomOut);

        return buttons;
    }

    public void setMapComponent(int zoom) {
        this.zoom = zoom;

        mapComponent.setZoomLevel(zoom);

//        mapComponent.addMapListener(new MapListener() {
//            @Override
//            public void mapPositionUpdated(Component source, int zoom, Coord center) {
//                // Handle map position updates dynamically
//                System.out.println("Map position updated - Zoom: " + zoom + ", Center: " + center);
//            }
//        });

        addPersonMarker(mapComponent, new Marker(
                "Evelyn",
                0xFFA500,
                "The Old Firehouse, Exeter EX4 4EP",
                new Coord(50.72674939030453, -3.527954379581619)
        ));

        addPersonMarker(mapComponent, new Marker(
                "Liam",
                0x00FF00,
                "Princesshay, Exeter, EX1 1QA",
                new Coord(50.72440621256099, -3.5278936762219018)
        ));

        addPersonMarker(mapComponent, new Marker(
                "Jasmine",
                0xFF69B4,
                "Exeter Quay, Exeter, EX2 4AN",
                new Coord(50.71886113714453, -3.5293724152158403)
        ));

        addPersonMarker(mapComponent, new Marker(
                "Lucas",
                0x00FFFF,
                "RAMM, Queen Street, Exeter, EX4 3RX",
                new Coord(50.72527398274362, -3.5323839083222404)
        ));


//        mapComponent.addMapListener(new MapListener() {
//            @Override
//            public void mapPositionUpdated(Component source, int zoom, Coord center) {
//                if (mapComponent.even) {}
//            }
//        });


        int index = 0;

        for (Marker marker : markers) {
            legendContainer.add(createLegendContainer(marker, index));
//            addLegendItem(legendContainer, marker, index);
            index++;
        }



        mapComponent.zoomTo(BoundingBox.create(markerCoordinates.toArray(new Coord[0])));

        System.out.println(Arrays.toString(markerCoordinates.toArray(new Coord[0])));

    }


    // Override the map's paint method to draw the markers
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Marker marker : markers) {
            int x = mapComponent.getPointFromCoord(marker.getCoordinate()).getX();
            int y = mapComponent.getPointFromCoord(marker.getCoordinate()).getY();

            drawCircularMarker(g, x, y, marker.getColor());
        }

    }


    // Method to add a circular marker at a specific location
    private void addPersonMarker(MapComponent map, Marker marker) {
        markers.add(marker);
//        markers.put(color, new Coord(latitude, longitude));
        markerCoordinates.add(marker.getCoordinate());

        int markerX = mapComponent.getPointFromCoord(marker.getCoordinate()).getX();
        int markerY = mapComponent.getPointFromCoord(marker.getCoordinate()).getY();

        map.getComponentAt(markerX, markerY).repaint();

    }

    // Method to draw a circular marker
    private void drawCircularMarker(Graphics g, int x, int y, int color) {
        int radius = 18; // Adjust the size of the marker as needed
        g.setColor(color); // Set marker color to red
        g.fillArc(x - radius, y - radius, radius * 2, radius * 2, 0, 360);
    }


    private long getDistance(int index) {
        return MapComponent.distance(markers.get(0).getLatitude(),
                markers.get(0).getLongitude(),
                markers.get(index).getLatitude(),
                markers.get(index).getLongitude());
    }

    private Container createLegendContainer(Marker marker, int index) {
        Container container = new Container(new BorderLayout());

//        Container leftContainer = new Container(BoxLayout.x());

        // Create label for color
        Label colorLabel = new Label();
        colorLabel.getAllStyles().setBgColor(marker.getColor());
        colorLabel.getAllStyles().setBgTransparency(255);
        colorLabel.setPreferredW(40); // Set preferred width for color label
        colorLabel.setPreferredH(210); // Set preferred width for color label

        colorLabel.getAllStyles().setMargin(0, 5, 0, 5);

        // Create label for name
        Label nameLabel = new Label(marker.getName());
        nameLabel.getAllStyles().setPaddingTop(2);
        nameLabel.getUnselectedStyle().setFgColor(0x0000);


//        descriptionLabel.getAllStyles().setPadding(5, 5, 0, 0);

        // Create label for description
        Label descriptionLabel = new Label(marker.getDescription());
        descriptionLabel.getAllStyles().setFont(descriptionLabel.getAllStyles().getFont().derive(Display.getInstance().convertToPixels(2), Font.STYLE_PLAIN)); // Set font size to small
        descriptionLabel.getAllStyles().setPaddingBottom(2);
        // Create label for distance
        Label distanceLabel = new Label(getDistance(index) + "m");
        distanceLabel.setUIID("LegendDistance"); // Set UIID for styling distance label
        distanceLabel.getAllStyles().setAlignment(Component.RIGHT); // Align distance label to the right

        // Create container to hold name, distance, and description labels
        Container leftContainer = new Container(new BorderLayout());
        Container nameDistanceContainer = new Container(new BorderLayout());

        nameDistanceContainer.add(BorderLayout.WEST, nameLabel);
        nameDistanceContainer.add(BorderLayout.EAST, distanceLabel);
        leftContainer.add(BorderLayout.NORTH, nameDistanceContainer);
        leftContainer.add(BorderLayout.SOUTH, descriptionLabel);

        container.add(BorderLayout.WEST, BoxLayout.encloseY(colorLabel));
        container.add(BorderLayout.CENTER, leftContainer);

        return container;
    }

}

