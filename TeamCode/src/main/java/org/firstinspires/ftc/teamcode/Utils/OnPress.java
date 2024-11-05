package org.firstinspires.ftc.teamcode.Utils;

/*
* This class is meant to serve as a way to easily add extra functionallity to buttons
* Because all code runs in a loop it may be difficult if an action is to be carried out only when
* something has been pressed once, because if the state of a button is checked each loop the action
* may run many times. This class serves to only return the first time that a button is pressed
* allowing an action to run once every time something is pressed
* */
public class OnPress {
    boolean active = false;

    public boolean pressed(boolean value) {
        // If value is true and it has not been pressed then activate it and say it has just been pressed
        if (value && !active) {
            active = true;
            return true;
        }
        // If the value is off and it is active well then turn it off, it's no longer being pressed
        if (!value && active) {
            active = false;
        }
        // Because it is not the first press (handled by first 'if') return false
        return false;
    }
}
