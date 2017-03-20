package org.usfirst.frc.team1806.robot.commands.drivetrain.auto;

import java.util.function.ObjDoubleConsumer;

import org.usfirst.frc.team1806.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VisionDriveStraight extends Command {
	double desiredAngle;
	double desiredPower;
	double currentAngle;
	double desiredDistance;
    public VisionDriveStraight(double power, double vision, int distance) {
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
    	Robot.driveSS.isVision = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = Robot.driveSS.getVisionAngle();
    	if(Math.abs(currentAngle) > 5){
    		System.out.println(currentAngle);
    		if(Math.signum(currentAngle) == 1){
    			Robot.driveSS.leftDrive(desiredPower + (currentAngle * .01));
    			System.out.println("is it positive");
    			Robot.driveSS.rightDrive(desiredPower);
    		} else {
    			Robot.driveSS.leftDrive(desiredPower);
    			System.out.println("is negative");
    			Robot.driveSS.rightDrive(desiredPower+ (-currentAngle * .015));
    		}
    	}else if (Robot.driveSS.getVisionAngle() == 0.0){
    		System.out.println("ayylmao");
    	} else {
    		Robot.driveSS.arcadeDrive(desiredPower, 0);
    	} 
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveSS.leftEncoder.get() > desiredDistance &&
        		Robot.driveSS.rightEncoder.get() > desiredDistance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("it finished");
    	Robot.driveSS.isVision = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
