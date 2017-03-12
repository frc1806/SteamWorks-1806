package org.usfirst.frc.team1806.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// Left Motors from Top to Bottom
	 public static int leftMotor = 0;
	 public static int rightMotor = 1;
	 
	 // hopper motor
	 public static int hopperMotor = 0;
	 
	 // conveyor motor
	 public static int conveyorMotor = 3;
	 
	 //Shooter boi
	 public static int flyWheel = 1;
	 
	 //for that little shifter ;)
	 public static int shiftLow = 6;
	 public static int shiftHigh = 7;
	 
	 // intake 
	 public static int intake = 2;
	 // our two climbers
	 public static int climber = 4;
	 
	 // two things for our gear holder
	 public static int gearHolderOut = 5;
	 public static int gearHolderIn = 4;

}
