package org.usfirst.frc.team1806.robot;

public class States {
	public enum IntakeStates{
		INTAKE,STOPPED,OUTTAKE
	}
	public enum Conveyor{
		RUNNING,STOPPED
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
	public enum Climber{
		RUNNINGATSPEED, STOPPED
	}
	public IntakeStates intakeStatesTracker;
	public Conveyor conveyorTracker;
	public Gear gearTracker;
	public ShootSpeed shootSpeedTracker;
	public Driving drivingTracker;
	public Climber climberTracker;
	
	public void resetStates(){
		climberTracker = Climber.RUNNINGATSPEED;
		intakeStatesTracker = IntakeStates.STOPPED;
		conveyorTracker = Conveyor.STOPPED;
		gearTracker = Gear.LOW;
		drivingTracker = Driving.DRIVING;
		shootSpeedTracker = ShootSpeed.IDLE;
	}
}
