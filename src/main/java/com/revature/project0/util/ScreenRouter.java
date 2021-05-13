package com.revature.project0.util;

import com.revature.project0.screens.Screen;

/**
Store each Screen class here (except abstract).
When called via navigate(), the associated screen class will
render information for the user to respond to with input.
 */

public class ScreenRouter {

    private LinkedList<Screen> screens = new LinkedList<>();

    /**
     * Adds screen to a linked list, initialized in this class,
     * to be called upon when user is to navigate to that app screen.
     *
     * @param screen
     *
     * @return ScreenRouter
     */
    public ScreenRouter addScreen(Screen screen) {
        screens.add(screen);
        return this;
    }


    /**
     * Searches for screen with specified route String tag in a linked list to navigate to.
     *
     * @param route
     */
    public void navigate(String route) {
        for (int i = 0; i < screens.size(); i++) {
            Screen screen = screens.get(i);
            if (screen.getRoute().equals(route)) {//route is just a glorified string id at this point
                screen.render();//outputs strings in the selected Screen class
            }
        }
    }

}
