package com.appvisibility.apptravel002.ui.controller.interfaces.meteorologia;

import java.util.List;

public class Root {
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private Coord coord;
    private List<Weather> weather;
    private Sys sys;
    private int visibility;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public int getVisibility() {
        return visibility/100;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }
}
