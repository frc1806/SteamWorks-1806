package org.usfirst.frc.team1806.robot.commands.drivetrain;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.VisionDriveStraight;
import org.usfirst.frc.team1806.robot.commands.sequences.Shimmy;
import org.usfirst.frc.team1806.robot.commands.sequences.Shimmy12;
import org.usfirst.frc.team1806.robot.commands.sequences.Shimmy5;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VisionandShimmy extends Command {
	boolean ayy;
    public VisionandShimmy(boolean should12) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSS);
    	ayy = should12;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.driveSS.isVision = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
			new VisionDriveStraight(.4, Robot.driveSS.getVisionAngle(), 36).start();
			System.out.println("fucking ");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(ayy){
    		System.out.println("starting shimmy");
    		new Shimmy12().start();
    	} else {
    		new Shimmy5().start();
    	}
    	Robot.driveSS.isVision = false;
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	if(ayy){
    		new Shimmy12().start();
    	} else {
    		new Shimmy5().start();
    	}
    	    }
}
