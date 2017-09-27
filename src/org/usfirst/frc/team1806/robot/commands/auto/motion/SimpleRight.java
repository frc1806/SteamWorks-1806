package org.usfirst.frc.team1806.robot.commands.auto.motion;

import org.usfirst.frc.team1806.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

/**
 *
 */
public class SimpleRight extends Command {
	EncoderFollower left;
	EncoderFollower right;
	Trajectory trajectory;
    public SimpleRight() {
        // Use requires() here to declare subsystem dependencies
         //eg. requires(chassis); //
    	requires(Robot.driveSS);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// 3 Waypoints
    	Waypoint[] points = new Waypoint[] {
        	new Waypoint(0, 0, 0),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
    	    new Waypoint(1.778, -2.3622, Pathfinder.d2r(-45)),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
    	};

    	// Create the Trajectory Configuration
    	//
    	// Arguments:
    	// Fit Method:          HERMITE_CUBIC or HERMITE_QUINTIC
    	// Sample Count:        SAMPLES_HIGH (100 000)
//    	                      SAMPLES_LOW  (10 000)
//    	                      SAMPLES_FAST (1 000)
    	// Time Step:           0.05 Seconds
    	// Max Velocity:        1.7 m/s
    	// Max Acceleration:    2.0 m/s/s
    	// Max Jerk:            60.0 m/s/s/s
    	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.3, 2.0, 60.0);

    	// Generate the trajectory
    	 trajectory = Pathfinder.generate(points, config);
        TankModifier modifier = new TankModifier(trajectory).modify(.762);
    	left = new EncoderFollower(modifier.getLeftTrajectory());
    	right = new EncoderFollower(modifier.getRightTrajectory());
    	
    	left.configureEncoder(Robot.driveSS.leftEncoder.get(), 258, 0.0889);
    	right.configureEncoder(Robot.driveSS.rightEncoder.get(), 258, 0.0889);
    	
    	left.configurePIDVA(.007, 0.0, 0.0, 1 / 1.3, 0);
    	right.configurePIDVA(0.007, 0.0, 0.0, 1 / 1.3, 0);
    	Robot.driveSS.navx.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftOutput = left.calculate(Robot.driveSS.leftEncoder.get());
    	double rightOutput = left.calculate(Robot.driveSS.rightEncoder.get());
    	
    	double gyroHeading = Robot.driveSS.navx.getAngle();
    	double desired_heading = left.getHeading();  // Should also be in degrees
    	
    	double angleDifference = desired_heading - gyroHeading;
    	double turn = 0.8 * (-1.0/80.0) * angleDifference;
    	
    	System.out.println("Desired Angle: "+ desired_heading + "   GyroHeading: " + gyroHeading);
    	Robot.driveSS.leftDrive(leftOutput + turn);
    	Robot.driveSS.rightDrive(rightOutput - turn);
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
