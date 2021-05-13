package com.revature.project0.screens;

/**
 * Blueprint for Screen class creation.
 */
public abstract class Screen {
    protected String name;
    protected String route;

    public Screen(String name, String route) {
        this.name = name;
        this.route = route;
    }

    public String getName() {
        return name;
    }

    public String getRoute() {
        return route;
    }

    public abstract void render();
}
