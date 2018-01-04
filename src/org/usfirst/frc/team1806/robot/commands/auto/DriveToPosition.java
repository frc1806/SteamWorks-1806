package org.usfirst.frc.team1806.robot.commands.auto;

import org.usfirst.frc.team1806.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToPosition extends Command {
	double desiredDistance;
    public DriveToPosition(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSS);
    	desiredDistance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSS.startDriveToPosition(75);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveSS.isDriveOnTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSS.arcadeDrive(0, 0);
    	Robot.driveSS.stopDriveToPosition();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveSS.arcadeDrive(0, 0);

    }
}
