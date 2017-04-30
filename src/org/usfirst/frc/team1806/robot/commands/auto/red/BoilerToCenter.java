package org.usfirst.frc.team1806.robot.commands.auto.red;

import org.usfirst.frc.team1806.robot.Constants;
import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.commands.Wait;
import org.usfirst.frc.team1806.robot.commands.conveyor.StartConveyor;
import org.usfirst.frc.team1806.robot.commands.conveyor.StopConveyor;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.DriveToPosition;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.RunDrive;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.TurnToAngle;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.VisionDriveStraight;
import org.usfirst.frc.team1806.robot.commands.flywheel.StartFlywheel;
import org.usfirst.frc.team1806.robot.commands.flywheel.StopFlywheel;
import org.usfirst.frc.team1806.robot.commands.gear.ExtendGear;
import org.usfirst.frc.team1806.robot.commands.gear.RectractGear;
import org.usfirst.frc.team1806.robot.commands.hopper.RunHopper;
import org.usfirst.frc.team1806.robot.commands.hopper.StopHopper;
import org.usfirst.frc.team1806.robot.commands.intake.StartIntake;
import org.usfirst.frc.team1806.robot.commands.sequences.Shimmy;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BoilerToCenter extends CommandGroup {

    public BoilerToCenter() {
    	addSequential(new ExtendGear());
		addParallel(new StartFlywheel(Constants.camCoder));
		addSequential(new Wait(1.2));
		addSequential(new StartConveyor());
		addSequential(new RunHopper());
		addSequential(new Wait(3));
		addSequential(new StopHopper());
		addSequential(new StopConveyor());
		addSequential(new StopFlywheel());
		addSequential(new RectractGear());
		addSequential(new TurnToAngle(2.5, .25, .3));
		addSequential(new DriveToPosition(-53, -.5,0, 1.5));
		addSequential(new TurnToAngle(-90, .7, 2.5));
    	addSequential(new RectractGear());
    	addSequential(new DriveToPosition(30, .5,0 ,2));
    	addSequential(new VisionDriveStraight(.5, Robot.driveSS.getVisionAngle(), 25));
    	addSequential(new DriveToPosition(10, .5,0 ,2));
    	addSequential(new Shimmy());
		addSequential(new Wait(5));
		addSequential(new RunDrive(-.3, 0, .7));
		addSequential(new RunDrive(.3, 0, .7));
		addSequential(new Shimmy());
    }
}
