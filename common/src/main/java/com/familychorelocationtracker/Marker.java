package com.familychorelocationtracker;


import com.codename1.maps.Coord;


public class Marker {
    private String name;
    private int color;
    private String description;
    private Coord coord;

    /**
     * Constructor for the Marker class
     * @param name to represent the name of a family member
     * @param color to represent the hexadecimal color for a marker and legend item
     * @param description where the family member is for the legend
     * @param coord longitude and latitude coordinated for the location of the family member
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
