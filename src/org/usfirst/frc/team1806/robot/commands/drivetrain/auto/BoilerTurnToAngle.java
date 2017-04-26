package org.usfirst.frc.team1806.robot.commands.drivetrain.auto;

import org.usfirst.frc.team1806.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BoilerTurnToAngle extends Command {
	double pOut;
	double currentAngle;
	double wow;
	boolean toPosition = true;
	Timer timer;
    public BoilerTurnToAngle(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSS);
    	timer = new Timer();
    	wow = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    	Robot.driveSS.isVision = true;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double oldAngle = currentAngle;
    	currentAngle = Robot.driveSS.getBoilerAngle();
    	System.out.println("Current Angle: "+currentAngle + "  oldAngle: " + oldAngle );
    	
    	if(toPosition){
        	if(Math.abs(currentAngle) > 3){
        		if(Math.abs(currentAngle) > 11){
            		pOut = currentAngle * .008;
            		System.out.println(pOut);
            		Robot.driveSS.rightDrive(pOut);
            		Robot.driveSS.leftDrive(-pOut);
            	} else {
            		if(Math.signum(currentAngle) == 1){
            			Robot.driveSS.arcadeDrive(0, -.1);
            		} else {
            			//neg statement
                		Robot.driveSS.arcadeDrive(0, .1);
            		}
            	}
        	}
    	}

    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > wow;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSS.isVision = false;

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveSS.isVision = false;
    }
}
