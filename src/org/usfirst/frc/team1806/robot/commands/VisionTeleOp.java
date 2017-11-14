package org.usfirst.frc.team1806.robot.commands;

import java.util.function.ObjDoubleConsumer;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.States.Driving;
import org.usfirst.frc.team1806.robot.commands.drivetrain.ShiftLow;
import org.usfirst.frc.team1806.robot.commands.drivetrain.shiftHigh;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.TurnToAngle;
import org.usfirst.frc.team1806.robot.commands.sequences.Shimmy;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VisionTeleOp extends Command {
	double desiredAngle;
	double desiredPower;
	double currentAngle;
	double desiredDistance;
	boolean isFirstTime = false;
	double pConstant = .02;
	double lastKnownAngle = 0;
    public VisionTeleOp(double power, double vision, int distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSS);
    	desiredPower = power;
    	desiredAngle = vision;
    	desiredDistance = distance * 24;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSS.leftEncoder.reset();
    	Robot.driveSS.rightEncoder.reset();
    	Robot.driveSS.resetNavx();
    	Robot.driveSS.isVision = true;
//    	Robot.states.drivingTracker = Driving.VISION;
//    	new ShiftLow().start();
    }
    public double returnPID(double angle) {
    	double maxP = .1;
    	double p = 0;
    	if(angle * pConstant > maxP) {
    		p = .2;
    	} else {
    		p = angle * pConstant;
    	}
    	return p;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = Robot.driveSS.getVisionAngle();
    	lastKnownAngle = Robot.driveSS.getLastKnownAngle();
    	
    	if(Math.abs(currentAngle) > 5){
    		System.out.println(currentAngle);
    		if(Math.signum(currentAngle) == 1){
    			Robot.driveSS.leftDrive(desiredPower + returnPID(currentAngle));
    			System.out.println("is it positive");
    			Robot.driveSS.rightDrive(desiredPower);
    		} else if(Math.signum(currentAngle) == -1) {
    			Robot.driveSS.leftDrive(desiredPower);
    			System.out.println("is negative");
    			Robot.driveSS.rightDrive(desiredPower + -returnPID(currentAngle));
	    	} 
    	} else if (currentAngle == 0.0){
    		System.out.println("ayylmao");
    	} else {
    		Robot.driveSS.arcadeDrive(desiredPower, 0);
    	} 
//    	if(Robot.networkTable.getNumber("numberOfContours") == 1 && currentAngle == 0){
//    		System.out.println("you've really done it now chris");
//    		if(Robot.networkTable.getNumberArray("centerX",new double[] {})[0] < Robot.cameraSS.CAMERA_WIDTH / 2) {
//    			Robot.driveSS.arcadeDrive(0, -.25);
//    		} else {
//    			Robot.driveSS.arcadeDrive(0, .25);
//    		}
//    	} 
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("vision finished ");
    	Robot.driveSS.isVision = false;
//    	Robot.states.drivingTracker = Driving.DRIVING;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//new Shimmy().start();
    	System.out.println("vision interrupted ");
    	if(!isFirstTime){
    		isFirstTime = true;
    	} else {
    		new Shimmy().start();
    	}
    }
}
