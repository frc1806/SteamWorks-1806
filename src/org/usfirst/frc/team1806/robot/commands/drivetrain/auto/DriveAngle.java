package org.usfirst.frc.team1806.robot.commands.drivetrain.auto;

import org.usfirst.frc.team1806.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveAngle extends Command {
	double desiredAngle;
	double threshHold = 2;
    public DriveAngle(double angle) {
    	desiredAngle = angle;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSS);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSS.navx.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.signum(desiredAngle) == 1){
    		Robot.driveSS.leftDrive(.85);
    		Robot.driveSS.rightDrive(.35);
    	} else {
    		Robot.driveSS.leftDrive(.35);
    		Robot.driveSS.rightDrive(.85);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.driveSS.getYaw()) > Math.abs(desiredAngle) - threshHold;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
