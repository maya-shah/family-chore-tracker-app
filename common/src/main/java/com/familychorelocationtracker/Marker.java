package com.familychorelocationtracker;


import com.codename1.maps.Coord;


public class Marker {
    private String name;
    private int color;
    private String description;
    private Coord coord;

    /**
     * Constructor for the Marker class
     * @param name T
     * @param color
     * @param description
     * @param coord
     */
    public Marker(String name, int color, String description, Coord coord) {
        this.name = name;
        this.color = color;
        this.description = description;
        this.coord = coord;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return this.coord.getLatitude();
    }

    public double getLongitude() {
        return this.coord.getLongitude();
    }

    public Coord getCoordinate() {
        return this.coord;
    }

    public int getColor() {
        return this.color;
    }

    public String getDescription() {
        return this.description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setLatitude(double latitude) {
        this.coord.setLatitude(latitude);
    }

    public void setLongitude(double longitude) {
        this.coord.setLongitude(longitude);
    }

    public void setCoordinate(Coord coord) {
        this.coord = coord;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
