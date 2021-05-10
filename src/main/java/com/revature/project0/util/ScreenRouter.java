package com.revature.project0.util;

import com.revature.project0.screens.Screen;

/*
Store each screen class here (except abstract, obviously).
When called via navigate(), the associated screen class will
have its render function called to bring up its text stuff.
 */

public class ScreenRouter {

    private LinkedList<Screen> screens = new LinkedList<>();

    public ScreenRouter addScreen(Screen screen) {
        screens.add(screen);
        return this;
    }

    public void navigate(String route) {
        for (int i = 0; i < screens.size(); i++) {
            Screen screen = screens.get(i);
            if (screen.getRoute().equals(route)) {//route is just a glorified string id at this point
                screen.render();//outputs strings in the selected Screen class
            }
        }
        //there's a problem if screen.render() wasn't called, which shouldn't happen
    }

}
