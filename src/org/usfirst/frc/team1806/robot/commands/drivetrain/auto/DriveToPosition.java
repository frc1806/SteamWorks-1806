package org.usfirst.frc.team1806.robot.commands.drivetrain.auto;

import org.usfirst.frc.team1806.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToPosition extends Command {
	int desiredDistance;
	double desiredPower;
	double currentDisplacement;
	int pThreshold = 3;
	double stopThreshold = .25;
	int encoderValue = 24;
	Timer timer;
	double time;
    public DriveToPosition(int inches, double power, double seconds) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSS);
    	desiredDistance = inches * encoderValue;
    	desiredPower = power;
    	time = seconds;
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	Robot.driveSS.rightEncoder.reset();
    	Robot.driveSS.navx.reset();
    	Robot.driveSS.leftEncoder.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currentAngle = Robot.driveSS.getYaw();
    	currentDisplacement = ((Robot.driveSS.rightEncoder.getDistance() / encoderValue) + Robot.driveSS.leftEncoder.getDistance() / encoderValue) / 2;
    	double error = desiredDistance - currentDisplacement;
    	if(currentDisplacement > (desiredDistance - pThreshold)){
    		Robot.driveSS.autoArcadeDrive(error * .003, currentAngle * .003);
    	} else {
    		Robot.driveSS.autoArcadeDrive(desiredPower, currentAngle * .003);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(currentDisplacement) > Math.abs(desiredDistance) - 2 && 
        		Math.abs(currentDisplacement) < desiredDistance + 2 || timer.get() > time;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
