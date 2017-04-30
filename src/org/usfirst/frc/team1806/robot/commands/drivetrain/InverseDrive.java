package org.usfirst.frc.team1806.robot.commands.drivetrain;

import org.usfirst.frc.team1806.robot.OI;
import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.States;
import org.usfirst.frc.team1806.robot.Commands.DrivingRequest;
import org.usfirst.frc.team1806.robot.States.Driving;
import org.usfirst.frc.team1806.robot.States.Shifter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class InverseDrive extends Command {
    States states;
    double deadZone = .2;
    public InverseDrive() {
    	states = new States();
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveSS);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	states.drivingTracker = Driving.INVERSE;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		if(states.drivingTracker == Driving.INVERSE){
    		if(Math.abs(Robot.oi.dlsY) > deadZone && Math.abs(Robot.oi.drsX) > deadZone){
    			Robot.driveSS.execute(-Robot.oi.dlsY, Robot.oi.drsX);
    		}else if(Math.abs(Robot.oi.dlsY) > deadZone){
    			Robot.driveSS.execute(-Robot.oi.dlsY, 0);
    		} else if(Math.abs(Robot.oi.drsX) > deadZone){
    			Robot.driveSS.execute(0, Robot.oi.drsX);
    		} else if(Math.abs(Robot.oi.dlsY) > deadZone && Math.abs(Robot.oi.drsX) > deadZone && Robot.states.shifterTracker == Shifter.HIGH){
    			Robot.driveSS.execute(-Robot.oi.dlsY, Math.pow(Robot.oi.drsX, 2));
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
    	Robot.states.drivingTracker = States.Driving.DRIVING;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.states.drivingTracker = States.Driving.DRIVING;
    }
}
