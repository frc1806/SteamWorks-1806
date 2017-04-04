package org.usfirst.frc.team1806.robot.commands.boiler;

import org.usfirst.frc.team1806.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BoilerToggle extends Command {
	boolean blah;
	
    public BoilerToggle(boolean blah) {
    	requires(Robot.driveSS);
    	this.blah = blah;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSS.isBoiler = blah;
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
    	Robot.driveSS.isBoiler = false;
    }
}
