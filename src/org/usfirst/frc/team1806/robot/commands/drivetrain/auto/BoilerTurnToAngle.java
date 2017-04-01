package org.usfirst.frc.team1806.robot.commands.drivetrain.auto;

import org.usfirst.frc.team1806.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BoilerTurnToAngle extends Command {
	double pOut;
	double currentAngle;
    public BoilerTurnToAngle() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSS);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSS.isVision = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = Robot.driveSS.getBoilerAngle();
    	if(Math.abs(currentAngle) > 3){
    		if(Math.abs(currentAngle) > 9){
        		pOut = currentAngle * .0095;
        		Robot.driveSS.rightDrive(pOut);
        		Robot.driveSS.leftDrive(-pOut);
        	} else {
        		if(Math.abs(currentAngle) < 9){
            		if(Math.signum(currentAngle) == 1){
            			Robot.driveSS.rightDrive(.15);
            			Robot.driveSS.leftDrive(-.15);
            		} else {
            			Robot.driveSS.rightDrive(-.15);
            			Robot.driveSS.leftDrive(.15);
            		}
        		} else {
            		if(Math.signum(currentAngle) == 1){
            			Robot.driveSS.rightDrive(.1);
            			Robot.driveSS.leftDrive(-.1);
            		} else {
            			Robot.driveSS.rightDrive(-.1);
            			Robot.driveSS.leftDrive(.1);
            		}
        		}

        	}
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
