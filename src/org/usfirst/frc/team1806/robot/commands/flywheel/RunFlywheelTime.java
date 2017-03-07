package org.usfirst.frc.team1806.robot.commands.flywheel;

import org.usfirst.frc.team1806.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunFlywheelTime extends Command {

	Timer runTime;
	double targetTime;
	
    public RunFlywheelTime(double _targetTime) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	targetTime = _targetTime;
    	requires(Robot.flywheelSS);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	runTime.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.flywheelSS.setToShootingSpeed();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return runTime.get() > targetTime;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.flywheelSS.stopFlyWheel();
    }
    

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
