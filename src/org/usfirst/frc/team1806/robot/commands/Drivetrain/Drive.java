package org.usfirst.frc.team1806.robot.commands.drivetrain;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.States;
import org.usfirst.frc.team1806.robot.States.Driving;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {
    States states;
    double deadZone = .2;
    public Drive() {
    	states = new States();
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveSS);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	states.drivingTracker = Driving.DRIVING;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		if(states.drivingTracker == Driving.DRIVING){
    		if(Math.abs(Robot.oi.dlsY) > deadZone && Math.abs(Robot.oi.drsX) > deadZone){
    			Robot.driveSS.execute(Robot.oi.dlsY, Robot.oi.drsX);
    		}else if(Math.abs(Robot.oi.dlsY) > deadZone){
    			Robot.driveSS.execute(Robot.oi.dlsY, 0);
    		} else if(Math.abs(Robot.oi.drsX) > deadZone){
    			Robot.driveSS.execute(0, Robot.oi.drsX);
    		} else {
    			Robot.driveSS.execute(0, 0);
    		}
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
