package org.usfirst.frc.team1806.robot;

public class Constants {
		
	//--------------------------FLYWHEEL---------------------------------
	// Flywheel PID constants, set in Flywheel Subststem
	public final static double flyWheelP = .002375;
	public final static double flyWheelI = 0;
	public final static double flyWheelD = 0;
	public final static double flyWheelF = -.04375;
	
	// This is for the shooter RPM range, adjust this if needed
	public final double minShooterRange = 1880;
	// cam u are in the code 
	public static double camCoder = 2150;
	public static double maxShooterRange = 2080;
	
	// This is the coast range, measure this after every change min - coast, not sure if used
	public final static double coastRange = 345;
	
	//-------------------------- INTAKE ---------------------------------
	public final static double intakeSpeed = .8;
	//-------------------------- CONVEYOR ---------------------------------
	public final static double conveyorSpeed = 1;
	
	//-------------------------- hopper ---------------------------------
	public final static double hopperSpeed = .6;

	
}
