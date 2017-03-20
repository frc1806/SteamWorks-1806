package org.usfirst.frc.team1806.robot;

import org.usfirst.frc.team1806.robot.States.CameraType;
import org.usfirst.frc.team1806.robot.States.Climber;
import org.usfirst.frc.team1806.robot.States.Conveyor;
import org.usfirst.frc.team1806.robot.States.Driving;
import org.usfirst.frc.team1806.robot.States.GearHolder;
import org.usfirst.frc.team1806.robot.States.Hopper;
import org.usfirst.frc.team1806.robot.States.IntakeStates;
import org.usfirst.frc.team1806.robot.States.Shifter;
import org.usfirst.frc.team1806.robot.States.ShootSpeed;

public class Commands {
	public enum IntakeStatesRequest{
		INTAKE,STOPPED,OUTTAKE
	}
	public enum ConveyorRequest{
		RUNNING,STOPPED
	}
	public enum ShifterRequest{
		HIGH, LOW
	}
	public enum ShootSpeedRequest{
		RUNNING, IDLE, STOPPED
	}
	public enum DrivingRequest{
		DRIVING,VISION,AIMING,CREEP,SEIZURE, SHIMMY
	}
	public enum ClimberRequest{
		RUNNINGATSPEED, STOPPED
	}
	public enum HopperRequest{
		RUNNING,STOPPED
	}
	public enum GearHolderRequest{
		IN, OUT
	}
	public enum CameraTypeRequest{
		LIFT, BOILER
	}
	CameraTypeRequest cameraRequestTracker;
	GearHolderRequest gearRequestTracker;
	HopperRequest hopperRequestTracker;
	ClimberRequest climberRequestTracker;
	DrivingRequest drivingRequestTracker;
	ShootSpeedRequest shootSpeedRequestTracker;
	ShifterRequest shiferRequestTracker;
	IntakeStatesRequest intakeRequestTracker;
	ConveyorRequest conveyorRequestTracker;
	
	public void reset(){
		hopperRequestTracker = HopperRequest.STOPPED;
		climberRequestTracker = ClimberRequest.STOPPED;
		intakeRequestTracker = IntakeStatesRequest.STOPPED;
		conveyorRequestTracker = ConveyorRequest.STOPPED;
		shiferRequestTracker = ShifterRequest.LOW;
		drivingRequestTracker = DrivingRequest.DRIVING;
		shootSpeedRequestTracker = ShootSpeedRequest.STOPPED;
		gearRequestTracker = GearHolderRequest.OUT;
		cameraRequestTracker = CameraTypeRequest.LIFT;
	}
}
