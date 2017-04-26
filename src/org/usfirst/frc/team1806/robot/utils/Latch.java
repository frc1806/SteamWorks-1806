package org.usfirst.frc.team1806.robot.utils;

public class Latch {
    private boolean lastVal;
    boolean toggle = false;
    boolean theThingToRun = false;
    public boolean update(boolean newVal) {
		if (toggle && newVal) {  // Only execute once per Button push
			  toggle = false;  // Prevents this section of code from being called again until the Button is released and re-pressed
			  if (theThingToRun) {  // Decide which way to set the motor this time through (or use this as a motor value instead)
				  theThingToRun= false;
			  } else {
			    theThingToRun = true;
			  }
			} else if(!newVal) { 
			    toggle = true; // Button has been released, so this allows a re-press to activate the code above.
			}
		return theThingToRun;
    }
    public boolean returnStatus(){
    	return theThingToRun;
    }
}