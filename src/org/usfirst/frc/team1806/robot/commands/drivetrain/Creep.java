package org.usfirst.frc.team1806.robot.commands.drivetrain;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.States;
import org.usfirst.frc.team1806.robot.States.Driving;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Creep extends Command {

    public Creep() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSS);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.states.drivingTracker = States.Driving.CREEP;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.states.drivingTracker == Driving.CREEP){
			Robot.driveSS.execute(Robot.oi.dlsY /3, Robot.oi.drsX /3);
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
