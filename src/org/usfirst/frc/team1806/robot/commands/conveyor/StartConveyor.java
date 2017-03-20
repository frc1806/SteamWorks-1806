package org.usfirst.frc.team1806.robot.commands.conveyor;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.States.Conveyor;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StartConveyor extends Command {

    public StartConveyor() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.flywheelSS);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.states.conveyorTracker = Conveyor.RUNNING;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.flywheelSS.setConveyor();
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
