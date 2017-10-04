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
	TankModifier modifier;
    public SimpleRight() {
        // Use requires() here to declare subsystem dependencies
         //eg. requires(chassis); //
    	requires(Robot.driveSS);

    }

    // Called just before this Command runs the first time
    protected void initialize() {

    	Waypoint[] points = new Waypoint[]{
    		    new Waypoint(-5, 10, Pathfinder.d2r(-55)),      // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
    		    new Waypoint(0, 0, 0)                           // Waypoint @ x=0, y=0,   exit angle=0 radians
    	};
    	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2, 60);
    	trajectory = Pathfinder.generate(points, config);
    	//.762
    	TankModifier modifier = new TankModifier(trajectory).modify(0.762);

    	left = new EncoderFollower(modifier.getLeftTrajectory());
    	right = new EncoderFollower(modifier.getRightTrajectory());
    	
    	left.configureEncoder(Robot.driveSS.leftEncoder.get(), 250, 0.889);
    	right.configureEncoder(Robot.driveSS.rightEncoder.get(), 250, 0.889);
    	
    	left.configurePIDVA(1, 0, 0, 1/ 1.7, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double l = left.calculate(Robot.driveSS.leftEncoder.get());
    	double r = right.calculate(Robot.driveSS.rightEncoder.get());
    	
    	double gyroHeading = Robot.driveSS.navx.getAngle();
    	double desiredHeading = Pathfinder.r2d(left.getHeading());
    	
    	double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);
    	double turn = .8 * (-1/80) * angleDifference;
    	Robot.driveSS.leftDrive(l + turn);
    	Robot.driveSS.rightDrive(r - turn);
    	for (int i = 0; i < trajectory.length(); i++) {
    	    Trajectory.Segment seg = trajectory.get(i);
    	    
    	    System.out.printf("%f,%f,%f,%f,%f,%f,%f,%f\n", 
    	        seg.dt, seg.x, seg.y, seg.position, seg.velocity, 
    	            seg.acceleration, seg.jerk, seg.heading);
    	}
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
