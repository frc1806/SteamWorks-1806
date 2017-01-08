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
		LOADING, ATSPEED, IDLE, STOPPED
	}
	
	public IntakeStates intakeStatesTracker;
	public IntakePosition intakePositionTracker;
	public Gear gearTracker;
	public ShootSpeed shootSpeedTracker;
	
	public void resetStates(){
		intakeStatesTracker = IntakeStates.STOPPED;
		intakePositionTracker = IntakePosition.UP;
		gearTracker = Gear.LOW;
		shootSpeedTracker = ShootSpeed.IDLE;
	}
}
