package org.usfirst.frc.team1806.robot.commands.flywheel;

import java.util.TimerTask;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.States.Conveyor;
import org.usfirst.frc.team1806.robot.States.ShootSpeed;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StartFlywheel extends Command {
	Timer timer;
    public StartFlywheel() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.flywheelSS);
    	timer = new Timer();

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("it started");
    	Robot.states.shootSpeedTracker = ShootSpeed.RUNNING;
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.flywheelSS.setToShootingSpeed();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("it ended");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
