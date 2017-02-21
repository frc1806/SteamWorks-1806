package org.usfirst.frc.team1806.robot.subsystems;


import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.RobotMap;
import org.usfirst.frc.team1806.robot.States;
import org.usfirst.frc.team1806.robot.States.Driving;
import org.usfirst.frc.team1806.robot.States.Shifter;
import org.usfirst.frc.team1806.robot.States.Shifter;
import org.usfirst.frc.team1806.robot.commands.drivetrain.Drive;
import org.usfirst.frc.team1806.robot.commands.sequences.SeizureMode;

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
	public Talon rightMotor1;
	public Talon leftMotor1;
	Talon leftMotor2;
	public AHRS navx;
	States states;
	public DoubleSolenoid shifter;
	public boolean creep = false;
	public DrivetrainSubsystem(){
		rightMotor1 = new Talon(RobotMap.rightMotor);
		leftMotor1 = new Talon(RobotMap.leftMotor);
		rightMotor1.setInverted(true);
		shifter = new DoubleSolenoid(RobotMap.shiftHigh, RobotMap.shiftLow);
	}
	public void execute(double power, double turn){
		arcadeDrive(power, turn);
		if(creep){
			arcadeDrive(power / 3, turn / 3);
		} 
	}
	public void leftDrive(double speed){
		leftMotor1.set(speed);
	}
	public void rightDrive(double speed){
		rightMotor1.set(speed);
	}
	public void arcadeDrive(double power, double turn){
		leftDrive(power - turn);
		rightDrive(power + turn);
	}
	public void shiftHigh(){
		System.out.println("shifted high");
		shifter.set(Value.kForward);
	}
	public void shiftLow() {
		System.out.println("shifted low");
		shifter.set(Value.kReverse);
	}
	public boolean isHigh(){
		//home boi u lit
		return states.shifterTracker == Shifter.HIGH;
	}
	public boolean isLow(){
		//sad pepe
		return states.shifterTracker == Shifter.LOW;
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
