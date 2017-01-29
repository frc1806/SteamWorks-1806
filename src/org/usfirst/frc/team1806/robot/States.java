package org.usfirst.frc.team1806.robot;

public class States {
	public enum IntakeStates{
		INTAKE,STOPPED,OUTTAKE
	}
	public enum IntakePosition{
		UP, DOWN
	}
	public enum Gear{
		HIGH, LOW
	}
	public enum ShootSpeed{
		ATSPEED, IDLE, STOPPED
	}
	public enum Driving{
		DRIVING,AIMING
	}
	public IntakeStates intakeStatesTracker;
	public IntakePosition intakePositionTracker;
	public Gear gearTracker;
	public ShootSpeed shootSpeedTracker;
	public Driving drivingTracker;
	
	public void resetStates(){
		intakeStatesTracker = IntakeStates.STOPPED;
		intakePositionTracker = IntakePosition.UP;
		gearTracker = Gear.LOW;
		drivingTracker = Driving.DRIVING;
		shootSpeedTracker = ShootSpeed.IDLE;
	}
}
