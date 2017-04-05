package org.usfirst.frc.team1806.robot.commands;

import java.util.function.ObjDoubleConsumer;

import org.usfirst.frc.team1806.robot.Robot;
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
    	Robot.driveSS.isVision = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = Robot.driveSS.getVisionAngle();
    	lastKnownAngle = Robot.driveSS.lastKnownAngle;

    	if(Math.abs(currentAngle) > 5){
    		System.out.println(currentAngle);
    		if(Math.signum(currentAngle) == 1){
    			Robot.driveSS.leftDrive(desiredPower + (currentAngle * .02));
    			System.out.println("is it positive");
    			Robot.driveSS.rightDrive(desiredPower);
    		} else {
    			Robot.driveSS.leftDrive(desiredPower);
    			System.out.println("is negative");
    			Robot.driveSS.rightDrive(desiredPower+ (-currentAngle * .02));
    		}
    	}else if (Robot.driveSS.getVisionAngle() == 0.0){
    		System.out.println("ayylmao");
    	} else {
    		Robot.driveSS.arcadeDrive(desiredPower, 0);
    	} 
//    	if(Robot.networkTable.getNumber("numberOfContours") == 1 && currentAngle == 0){
//    		double placeToTurn = Math.signum(lastKnownAngle);
//    		new TurnToAngle(placeToTurn * 15, .25, .2).start();
//    	} 
    	if(Robot.networkTable.getNumberArray("centerX")[0] < 320 / 2 && Robot.networkTable.getNumber("numberOfContours") == 1){
    		new TurnToAngle(15, .25, .2).start();
    	} else if(Robot.networkTable.getNumberArray("centerX")[0] > 320 / 2 && Robot.networkTable.getNumber("numberOfContours") == 1){
    		new TurnToAngle(-15, .25, .2).start();

    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("vision finished ");
    	Robot.driveSS.isVision = false;
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
