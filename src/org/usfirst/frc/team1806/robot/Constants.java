package org.usfirst.frc.team1806.robot;

public class Constants {
	// Flywheel PID constants, set in Flywheel Subststem
	public final double flyWheelP = .002375;
	public final double flyWheelI = 0;
	public final double flyWheelD = 0;
	
	// This is for the shooter RPM range, adjust this if needed
	public final double minShooterRange = 1880;
	public final double targetPower = 1980;
	public final double maxShooterRange = 2080;
	// This is the coast range, measure this after every change min - coast
	public final double coastRange = 345;
	
}
