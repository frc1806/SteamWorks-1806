package org.usfirst.frc.team1806.robot.commands.camera;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.States.CameraType;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwitchToLift extends Command {

    public SwitchToLift() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.cameraS);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.states.cameraTracker = CameraType.LIFT;
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
