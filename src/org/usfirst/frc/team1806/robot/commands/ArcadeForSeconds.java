package org.usfirst.frc.team1806.robot.commands;

import org.usfirst.frc.team1806.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeForSeconds extends Command {
	double kPower;
	Timer timer;
	double kSeconds;
    public ArcadeForSeconds(double power, double seconds) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	kPower = power;
    	kSeconds = seconds;
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSS.arcadeDrive(0, kPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > kSeconds;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
