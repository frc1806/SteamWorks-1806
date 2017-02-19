package org.usfirst.frc.team1806.robot.commands.hopper;


import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.States.Hopper;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReverseHopper extends Command {

    public ReverseHopper() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.hopperSS);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.states.hopperTracker = Hopper.RUNNING;
    	Robot.hopperSS.setReverseMotor();
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
