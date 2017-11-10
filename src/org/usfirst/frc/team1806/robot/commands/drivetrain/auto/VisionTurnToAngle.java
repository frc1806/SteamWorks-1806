package org.usfirst.frc.team1806.robot.commands.drivetrain.auto;

import org.usfirst.frc.team1806.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VisionTurnToAngle extends Command {
    double targetAngle;
    
    //PID constants
    double kP = 0.06;
    double kD = 0.725;
    
    //variables for PID outputs
    double Pout;
    double Kout;
    
    //variables for chekcing positions later
    double currentPos;
    double error;
    double leftPower, rightPower;
    boolean isClockwise;
    double motorBaseSpeed;
    double rate;    
    double timeoutTaker;
    Timer timeOutTaker;
    public VisionTurnToAngle(double angle, double power, double timeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSS);
    	Robot.driveSS.navx.reset();
       	if(angle > 0) {
    		//turning clockwise
    		isClockwise = true;
    	}
    	else {
    		//turning counter clockwise
    		isClockwise = false;
    	}

       	requires(Robot.driveSS);
    	timeOutTaker = new Timer();
    	
    	//sets arguments to fields
    	timeoutTaker = timeout;
    	targetAngle = angle;
       	motorBaseSpeed = power;

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSS.navx.reset();
    	timeOutTaker.start();
    	leftPower = motorBaseSpeed;
       	rightPower = motorBaseSpeed;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	
    	//determining current state of motion
    	currentPos = Robot.driveSS.navx.getYaw();
    	rate = Robot.driveSS.navx.getRate();
    	    	
    	//setting PID outputs
    	
    	//TODO reverse left and right
    	
    	if(isClockwise) {
    		//finds distance to target
    		error = targetAngle - currentPos;

        	Pout = kP * error;
    		//Kout = rate * kD;
    		
	    	//initiates PID control
    		/*if(error < 20) {
    			System.out.println("PID kicking in");
    			leftPower = Pout;
    			rightPower = Pout ;
    		}
*/
    		System.out.println("leftPower: " + leftPower);
   			System.out.println("rightPower: " + rightPower);
    		System.out.println("error: " + error);
   			Robot.driveSS.leftDrive(leftPower);
        	Robot.driveSS.rightDrive(-rightPower);
    	}
    	else {
    		//finds distance to target
    		error = currentPos - targetAngle;

        	Pout = kP * error;
    		Kout = -rate * kD; 

	    	//initiates PID control
    		/*
			if(error < 20) {
				System.out.println("PID kicking in");
				leftPower = Pout ;
				rightPower = Pout ;
			}
*/
    		System.out.println("EX: error: " + error);
			System.out.println("EX: current : " + currentPos);
			System.out.println("EX: targetAngle: " + targetAngle);
    		
    		Robot.driveSS.leftDrive(-leftPower);
        	Robot.driveSS.rightDrive(rightPower);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	return (error < 2) || timeOutTaker.get() > timeoutTaker || Robot.driveSS.getVisionAngle() != 0;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println(currentPos);
    	Robot.driveSS.navx.zeroYaw();
    	Robot.driveSS.leftDrive(0);
    	Robot.driveSS.rightDrive(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
