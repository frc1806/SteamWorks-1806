package org.usfirst.frc.team1806.robot.commands.drivetrain;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.States;
import org.usfirst.frc.team1806.robot.States.Conveyor;
import org.usfirst.frc.team1806.robot.States.Shifter;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class shiftHigh extends Command {

    public shiftHigh() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveSS);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		Robot.states.shifterTracker = Shifter.HIGH;
		Robot.driveSS.shifter.set(Value.kForward);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		
    	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}