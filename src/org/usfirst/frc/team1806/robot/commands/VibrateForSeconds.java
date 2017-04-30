package org.usfirst.frc.team1806.robot.commands;

import java.util.function.ObjDoubleConsumer;

import org.usfirst.frc.team1806.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VibrateForSeconds extends Command {
	double ayy;
	Timer timer;
    public VibrateForSeconds(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	ayy = time;
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.oi.setDriverRumble();
    	Robot.oi.setOperatorRumble();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > ayy;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.oi.stopRumble();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
