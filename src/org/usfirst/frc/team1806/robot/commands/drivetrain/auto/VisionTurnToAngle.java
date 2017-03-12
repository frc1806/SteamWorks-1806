package org.usfirst.frc.team1806.robot.commands.drivetrain.auto;

import org.usfirst.frc.team1806.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VisionTurnToAngle extends Command {
	double currentAngle;
	double pOut;
    public VisionTurnToAngle() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSS);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	currentAngle = Robot.driveSS.getVisionAngle();
    	if(Math.abs(currentAngle) > 2.5){
    		pOut = currentAngle * .025;
    		Robot.driveSS.rightDrive(pOut);
    		Robot.driveSS.leftDrive(-pOut);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(currentAngle) < 2.5;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
