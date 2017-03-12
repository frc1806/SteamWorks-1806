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
	double kP = .025;
    public VisionDriveStraight(double power, double vision) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSS);
    	desiredPower = power;
    	desiredAngle = vision;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = Robot.driveSS.getVisionAngle();
    	if(Math.abs(currentAngle) > 3){
    		System.out.println(currentAngle);
    		if(Math.signum(currentAngle) == 1){
    			Robot.driveSS.leftDrive(desiredPower + (currentAngle * .015));
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
        return Robot.driveSS.getVisionDistance() < 32;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
