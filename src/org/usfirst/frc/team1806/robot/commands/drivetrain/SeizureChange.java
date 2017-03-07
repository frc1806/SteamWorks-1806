package org.usfirst.frc.team1806.robot.commands.drivetrain;

import org.usfirst.frc.team1806.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SeizureChange extends Command {
	boolean a;
    public SeizureChange(boolean seizure) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	a = seizure;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSS.isSeizureMode = a;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
