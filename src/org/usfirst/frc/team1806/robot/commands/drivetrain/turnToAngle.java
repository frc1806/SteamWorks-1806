package org.usfirst.frc.team1806.robot.commands.drivetrain;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.States;
import org.usfirst.frc.team1806.robot.States.Driving;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class turnToAngle extends Command {
    int targetAngle;
    double kP = 0.03;
    double kI, kD;
    double cDist, ccDist;
    double currentPos;
    double error;
    double leftPower, rightPower;
    double initialPos;
    boolean isClockwise;
    public turnToAngle(int angle, double power, double timeout) {
    	requires(Robot.driveSS);
    	targetAngle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSS.navx.reset();
    	initialPos = Robot.driveSS.navx.getYaw();
    	cDist = normalize(targetAngle - initialPos);
    	ccDist = normalize(initialPos - targetAngle);
    	
    	
    	
    	leftPower = 1.0;
       	rightPower = 1.0;

       	if(ccDist > cDist) {
    		//turning clockwise
    		isClockwise = true;
    	}
    	else {
    		//turning counter clockwise
    		isClockwise = false;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentPos = Robot.driveSS.navx.getYaw();
    	
    	if(isClockwise) {
    		error = normalize(targetAngle - currentPos);
    		
    		if(error < 20) {
    			leftPower = kP * error;
    			rightPower = kP * error;
    		}
    		
    		rightPower = makeNeg(rightPower);
    		System.out.println(error);
    	}
    	else {
    		error = normalize(currentPos - targetAngle);
    		
    		if(error < 20) {
    			leftPower = kP * error;
    			rightPower = kP * error;
    		}

    		leftPower = makeNeg(leftPower);

    		System.out.println(error);
    	}
    	
    	Robot.driveSS.leftDrive(leftPower);
    	Robot.driveSS.rightDrive(rightPower);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(error - targetAngle) < 5;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    protected double normalize(double angle) {
    	while(angle < -180 || angle > 180) {
    		if (angle < -180) {    		
				angle += 360;
			}
    		if (angle > 180) {    		
				angle -= 360;
			}
    	}
    	return angle;
    }
    private double makeNeg(double x) {
    	if (x > 0) x *= -1;
    	return x;
    }
}
