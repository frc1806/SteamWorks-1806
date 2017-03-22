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
    	Robot.states.shootSpeedTracker = ShootSpeed.RUNNING;
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(timer.get());
    	if(timer.get() > .75){
    		System.out.println("PID ");
        	Robot.flywheelSS.setToShootingSpeed();
    	} else{
    		System.out.println("100%");
    		Robot.flywheelSS.setShooterPower(-1);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > 1;
    }

    // Called once after isFinished returns true
    protected void end() {

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
