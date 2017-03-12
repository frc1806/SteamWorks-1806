package org.usfirst.frc.team1806.robot.commands.drivetrain;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.States.Shifter;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShiftLow extends Command {

    public ShiftLow() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSS);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		Robot.states.shifterTracker = Shifter.LOW;
		System.out.println("shift low");
    	Robot.driveSS.shifter.set(Value.kReverse);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {


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
