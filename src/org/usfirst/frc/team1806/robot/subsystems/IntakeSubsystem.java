package org.usfirst.frc.team1806.robot.subsystems;

import org.usfirst.frc.team1806.robot.Constants;
import org.usfirst.frc.team1806.robot.RobotMap;
import org.usfirst.frc.team1806.robot.States;
import org.usfirst.frc.team1806.robot.States.IntakeStates;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {
	// There isn't much here to do lmao
	States states = new States();
	public Talon intakeMotor;
	Constants constants;
	public IntakeSubsystem(){
		constants = new Constants();
		intakeMotor = new Talon(RobotMap.intake);
	}
	public void setIntaking(){
		states.intakeStatesTracker = IntakeStates.INTAKE;
		intakeMotor.set(.3);
	}
	public void setOuttaking(){
		states.intakeStatesTracker = IntakeStates.OUTTAKE;
		intakeMotor.set(-.3);
	}
	public void stopIntake(){
		states.intakeStatesTracker = IntakeStates.STOPPED;
		intakeMotor.set(0);
	}
	public double getSpeed(){
		return intakeMotor.getSpeed();
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

