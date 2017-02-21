package org.usfirst.frc.team1806.robot.commands.drivetrain;

import org.usfirst.frc.team1806.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunDrive extends Command {
	Timer timer = new Timer();		
	double timeout;
	double power;
    public RunDrive(double newPower, double newTimeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSS);
    	power = newPower;
    	timeout = newTimeout;
    	System.out.println("Run Drive Command ");
    	//initialize();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Run Drive Initalize");
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("running");
    	Robot.driveSS.arcadeDrive(power, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(timer.get() >= timeout){
    		return true;
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("it finished!");
    	Robot.driveSS.leftDrive(0);
    	Robot.driveSS.rightDrive(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
