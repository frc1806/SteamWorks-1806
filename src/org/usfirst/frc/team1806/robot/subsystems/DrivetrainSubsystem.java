package org.usfirst.frc.team1806.robot.subsystems;

import java.awt.Robot;

import org.usfirst.frc.team1806.robot.RobotMap;
import org.usfirst.frc.team1806.robot.States;
import org.usfirst.frc.team1806.robot.States.Gear;
import org.usfirst.frc.team1806.robot.commands.Drivetrain.Drive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DrivetrainSubsystem extends Subsystem {
	Talon rightMotor1, rightMotor2, rightMotor3, leftMotor1, leftMotor2, leftMotor3, flyWheel;
	AHRS navx;
	States states;
	DoubleSolenoid shifter;
	public DrivetrainSubsystem(){
		rightMotor1 = new Talon(RobotMap.rightMotor1);
		rightMotor2 = new Talon(RobotMap.rightMotor2);
		rightMotor3 = new Talon(RobotMap.rightMotor3);
		
		leftMotor1 = new Talon(RobotMap.leftMotor1);
		leftMotor2 = new Talon(RobotMap.leftMotor2);
		leftMotor3 = new Talon(RobotMap.leftMotor3);
		shifter = new DoubleSolenoid(RobotMap.shiftHigh, RobotMap.shiftLow);
	}
	public void execute(double power, double turn){
		states.drivingTracker = States.Driving.DRIVING;
		arcadeDrive(power, turn);
	}
	public void leftDrive(double speed){
		leftMotor1.set(speed);
		leftMotor2.set(speed);
		leftMotor3.set(speed);
	}
	public void rightDrive(double speed){
		rightMotor1.set(speed);
		rightMotor2.set(speed);
		rightMotor3.set(speed);
	}
	public void arcadeDrive(double power, double turn){
		leftDrive(power - turn);
		rightDrive(power + turn);
	}
	public void shiftHigh(){
		states.gearTracker = Gear.LOW;
		shifter.set(Value.kForward);
	}
	public void shiftLow() {
		states.gearTracker = Gear.LOW;
		shifter.set(Value.kReverse);
	}
	public boolean isHigh(){
		//nigga u lit
		return states.gearTracker == Gear.HIGH;
	}
	public boolean isLow(){
		//sad pepe
		return states.gearTracker == Gear.LOW;
	}
	//------------NAVX --------------------
	   public void resetYaw(){
	    	navx.zeroYaw();
	    }
	    
	    public void resetNavx(){
	    	navx.reset();
	    }
	    
	    public boolean isNavxConnected(){
	    	return navx.isConnected();
	    }

	    
	    public double getTrueAngle(){
	    	return navx.getAngle();
	    }
	    
	    
	    public double getYaw(){
	    	return navx.getYaw();
	    }
	    
	    public double getPitch(){
	    	return navx.getPitch();
	    }
	    
	    public double getRoll(){
	    	return navx.getRoll();
	    }
	    
	    public double getRotationalSpeed(){
	    	return navx.getRate();
	    }
	    
	    public double getQuaternion(){
	    	return navx.getQuaternionZ() * 180;
	    }
	    
	    public double getTilt(){
	    	return Math.sqrt(Math.pow(navx.getPitch(), 2) + Math.pow(navx.getRoll(), 2));
	    }
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Drive());
	}
}
