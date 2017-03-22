package org.usfirst.frc.team1806.robot.commands.drivetrain.auto;

import org.usfirst.frc.team1806.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class NewDriveToPosition extends Command {
	Timer timeOutTimer;
	//defines variables that this command needs
	double motorSpeed;
	AHRS navx;
    Timer PIDTimer;
	int initialPosLeft, initialPosRight, initialPosAvg;
	double encoderAvg;
	int targetPos;
	boolean isTimerStarted = false;
	double timeoutTaker;
	//496 clicks to a rotation, hypothetically.
    public NewDriveToPosition(int target, double power, double timeout) {
    	timeoutTaker = timeout;
    	navx = new AHRS(Port.kMXP);
    	requires(Robot.driveSS);
    	motorSpeed = power;
    	targetPos = target * 24;
    	PIDTimer = new Timer();
    	timeOutTimer = new Timer();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSS.leftEncoder.reset();
    	Robot.driveSS.rightEncoder.reset();
    	initialPosLeft = Robot.driveSS.leftEncoder.get();
    	initialPosRight = Robot.driveSS.rightEncoder.get();
    	
    	isTimerStarted = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftRate = Robot.driveSS.leftEncoder.getRate();
    	double rightRate = Robot.driveSS.rightEncoder.getRate();
    	//System.out.println("LeftRate: " + leftRate);
    	//System.out.println("LeftRate: " + leftRate);
    	double leftPosition = Robot.driveSS.leftEncoder.get();
    	double rightPosition = Robot.driveSS.rightEncoder.get();
    	
    	double leftError = targetPos - leftPosition;
    	double rightError = targetPos - rightPosition;
    	
    	//Proportional Stuff
    	double kA = 0.007;
    	double kP = 0.00225;
    	double leftP = kP * leftError;
    	double rightP = kP * rightError;
    	
    	//Derivative Stuff (When you overshoot this slows you up.)
    	double kD = -0.000015;
    	double leftD = kD * leftRate;
    	double rightD = kD * rightRate;
    	
    	//Integral Stuff (When you undershoot this speeds you up.)
    	double kI = 0.007; //0.05
    	double leftI = kI * PIDTimer.get() * leftError;
    	double rightI = kI * PIDTimer.get() * rightError;
    	double rightOutput;
    	double leftOutput;
    	double angleError = rightPosition - leftPosition;
    	double angleCorrection = angleError * kA;
    	
    	
    
    	if(leftError < 360 || rightError < 360) 
    		{
	    	if(!isTimerStarted) {
	    		isTimerStarted = true;
	    		PIDTimer.start();
	    	}
	    	//Motor Output Stuff
//	    	System.out.println("Hey, we're ramping down");
	    	leftOutput  = leftP + leftD + leftI - angleCorrection;
	    	rightOutput = rightP + leftD + leftI + angleCorrection;
    	}
    	else {
    		leftOutput =  motorSpeed - angleCorrection;
        	rightOutput = motorSpeed + angleCorrection;    		
    	}
    	if(leftOutput > motorSpeed) leftOutput = motorSpeed;
    	if(rightOutput > motorSpeed) rightOutput = motorSpeed;
    	
    	Robot.driveSS.leftMotor1.set(leftOutput);
    	Robot.driveSS.rightMotor1.set(rightOutput);
    	
//    	System.out.println("left " + leftOutput);
//    	System.out.println("right " + rightOutput);
    	//System.out.println("leftPos " + leftPosition);
    	//System.out.println("rightPos " + rightPosition);
    	//System.out.println("timer " + PIDTimer.get());
    	System.out.println("Power left " + leftOutput);
    	System.out.println("Power right " + rightOutput);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
         return Math.abs(Math.abs(Robot.driveSS.rightEncoder.get() - initialPosRight) - targetPos) <= 30 &&
        		Math.abs(Math.abs(Robot.driveSS.leftEncoder.get() - initialPosLeft) - targetPos) <= 30 || 
        		timeOutTimer.get() >= timeoutTaker ;
    }

    // Called once after isFinished returns true
    protected void end() {
    	PIDTimer.stop();
    	PIDTimer.reset();
    	Robot.driveSS.leftDrive(0);
    	Robot.driveSS.rightDrive(0);
    	//System.out.println("Initial encoder left " + initialPosLeft);
    	//System.out.println("Initial encoder right " + initialPosRight);
    	System.out.println("finished");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}