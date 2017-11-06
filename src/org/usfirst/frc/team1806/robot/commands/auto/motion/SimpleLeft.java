package org.usfirst.frc.team1806.robot.commands.auto.motion;

import org.usfirst.frc.team1806.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

/**
 *
 */
public class SimpleLeft extends Command {
	EncoderFollower left;
	EncoderFollower right;
	Trajectory trajectory;
	TankModifier modifier;
	Timer timer;

	double maxVelocity = 12;				///
	double maxAcceleration = 12;		/// REMEMBER THESE ARE ALL IN METERS
	double maxJerk = 60;				///	
	double drivebaseWidth = .762;
	double wheelDiam = .0889;
	
	double kP = 3.5;
	double kI = 0;
	double kD = 0;
	double accGain = 0;
	
	double l;
	double r;
	
    public SimpleLeft() {
        // Use requires() here to declare subsystem dependencies
         //eg. requires(chassis); //
    	requires(Robot.driveSS);
 

    }

    // Called just before this Command runs the first time
    protected void initialize() {
       	timer = new Timer();
    	Waypoint[] points = new Waypoint[]{
    		    new Waypoint(0, 0 ,0),     // This is the start out waypoint 
    		    new Waypoint(2.17, -.2, -45)	// 5m forward
    	};
    	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, maxVelocity, maxAcceleration, maxJerk);
    	trajectory = Pathfinder.generate(points, config);
    	//.762
    	TankModifier modifier = new TankModifier(trajectory).modify(drivebaseWidth);

    	// This 
    	left = new EncoderFollower(modifier.getLeftTrajectory());
    	right = new EncoderFollower(modifier.getRightTrajectory());
    	
    	// Configure encoders (Base Value, Encoder Clicks Per Rotation of Wheel, 
    	left.configureEncoder(Robot.driveSS.leftEncoder.get(), 250, wheelDiam);
    	right.configureEncoder(Robot.driveSS.rightEncoder.get(), 250, wheelDiam);
    	
    	// Configuring our PID for both of our encoders
    	// The first argument is the proportional gain. Usually this will be quite high
    	// The second argument is the integral gain. This is unused for motion profiling
    	// The third argument is the derivative gain. Tweak this if you are unhappy with the tracking of the trajectory
    	// The fourth argument is the velocity ratio. This is 1 over the maximum velocity you provided in the 
    	//trajectory configuration (it translates m/s to a -1 to 1 scale that your motors can read)
    	// The fifth argument is your acceleration gain. Tweak this if you want to get to a higher or lower speed quicker
    	left.configurePIDVA(kP, kI, kD, 1/ maxVelocity, accGain);
    	right.configurePIDVA(kP, kI, kD, 1/ maxVelocity, accGain);
    	timer.reset();
    	timer.start();

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	 l = left.calculate(Robot.driveSS.leftEncoder.get());
    	 r = right.calculate(Robot.driveSS.rightEncoder.get());
    	
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
    	System.out.println(l + " " + r);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(r) <= .1 && Math.abs(l) <= .1 && timer.get() > 2;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
