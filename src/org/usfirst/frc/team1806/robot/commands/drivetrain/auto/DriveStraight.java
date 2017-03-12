package org.usfirst.frc.team1806.robot.commands.drivetrain.auto;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.commands.drivetrain.Drive;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

    public DriveStraight(double power, double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSS);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSS.navx.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		//Robot.driveSS.arcadeDrive(desiredPower, -currentAngle * kP);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
