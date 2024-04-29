package com.familychorelocationtracker;

import com.codename1.maps.Coord;
import com.codename1.maps.MapComponent;
import com.codename1.maps.providers.MapProvider;
import com.codename1.maps.providers.OpenStreetMapProvider;
import com.codename1.properties.UiBinding;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;

public class LocationsPageView extends Form {
    public LocationsPageView() {
            super("Map Demo", new BorderLayout());

            // Create a MapComponent
            MapComponent mapComponent = new MapComponent(new OpenStreetMapProvider());

            // Set the initial zoom level
            mapComponent.zoomTo(new Coord(51.5074, -0.1278), 15);

            // Add the MapComponent to the form
            add(BorderLayout.CENTER, mapComponent);




    }
}

