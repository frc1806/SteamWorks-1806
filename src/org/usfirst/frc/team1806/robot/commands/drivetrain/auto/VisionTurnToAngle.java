package org.usfirst.frc.team1806.robot.commands.drivetrain.auto;

import org.usfirst.frc.team1806.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VisionTurnToAngle extends Command {
	double currentAngle;
	double pOut;
	double time;
	Timer timer;
    public VisionTurnToAngle(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSS);
    	this.time = time;
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = Robot.driveSS.getVisionAngle();
    	if(Math.abs(currentAngle) > 2){
    		pOut = currentAngle * .035;
    		Robot.driveSS.rightDrive(-pOut);
    		Robot.driveSS.leftDrive(pOut);
//    	} else if(currentAngle == 0){
//    		boolean stage1 = false;
//    		Timer timer = new Timer();
//    		timer.start();
//    		if(!stage1){
//    			if(timer.get() < .5){
//            		Robot.driveSS.arcadeDrive(0, -.2);
//            		if(timer.get() > .5){
//            			stage1 = true;
//            		}
//        		}
//    		} else {
//    			Robot.driveSS.arcadeDrive(0, .2);
//    		}
//    		
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > time || Math.abs(currentAngle) < 2;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSS.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
