package org.usfirst.frc.team1806.robot.commands.intake;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.States.IntakeStates;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StartIntake extends Command {

    public StartIntake() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeSS);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.states.intakeStatesTracker = IntakeStates.INTAKE;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intakeSS.setIntaking();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeSS.stopIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intakeSS.stopIntake();
    }
}
