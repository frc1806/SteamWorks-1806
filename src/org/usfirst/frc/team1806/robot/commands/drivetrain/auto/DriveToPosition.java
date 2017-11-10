package org.usfirst.frc.team1806.robot.commands.drivetrain.auto;

import org.usfirst.frc.team1806.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToPosition extends Command {
	double desiredDistance;
	double desiredPower;
	double currentDisplacement;
	int pThreshold = 100;
	
	double stopThreshold = .25;
	int encoderValue = 24;
	Timer timer;
	double time;
	double turn = 0;
    public DriveToPosition(double inches, double power, double turn, double seconds) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSS);
    	this.turn = turn;
    	desiredDistance = inches * encoderValue;
    	desiredPower = power;
    	time = seconds;
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	Robot.driveSS.rightEncoder.reset();
    	Robot.driveSS.leftEncoder.reset();
    	Robot.driveSS.navx.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currentAngle = Robot.driveSS.getYaw();
    	currentDisplacement = ((Robot.driveSS.rightEncoder.getDistance() / encoderValue) + Robot.driveSS.leftEncoder.getDistance() / encoderValue) / 2;
    	double error = Math.abs(desiredDistance) - Math.abs(currentDisplacement);
    	System.out.println(error);
    	if(error < pThreshold){
    		if(turn == 0){
    			System.out.println("gyro");
        		Robot.driveSS.autoArcadeDrive(error * .003 , 0);
    		} else {
    			System.out.println("turn");
        		Robot.driveSS.autoArcadeDrive(desiredPower, turn);
    		}    	
    	} else {
    		if(turn == 0){
        		Robot.driveSS.autoArcadeDrive(desiredPower , -currentAngle * .02);
    		} else {
    			System.out.println("turn");
        		Robot.driveSS.autoArcadeDrive(desiredPower, turn);
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(currentDisplacement - desiredDistance) <= 50 ;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Done going to distance");
    	Robot.driveSS.leftDrive(0);
    	Robot.driveSS.rightDrive(0);
    	Robot.driveSS.navx.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveSS.navx.reset();
    }
}