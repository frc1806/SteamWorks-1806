package org.usfirst.frc.team1806.robot.commands.auto.red;

import org.usfirst.frc.team1806.robot.Constants;
import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.commands.ArcadeForSeconds;
import org.usfirst.frc.team1806.robot.commands.Wait;
import org.usfirst.frc.team1806.robot.commands.boiler.BoilerToggle;
import org.usfirst.frc.team1806.robot.commands.conveyor.StartConveyor;
import org.usfirst.frc.team1806.robot.commands.conveyor.StopConveyor;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.BoilerTurnToAngle;
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
import org.usfirst.frc.team1806.robot.commands.sequences.Shimmy;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Poof extends CommandGroup {

    public Poof() {
    	//TODO, make it work????
    	addSequential(new RectractGear());
    	addSequential(new DriveToPosition(77, .5,0, 2));
    	addSequential(new Shimmy());
		addSequential(new Wait(1));
//		////////////
		addSequential(new DriveToPosition(-33, -.5, .32, 2));
		addSequential(new DriveToPosition(20, .7, .12, 1.5));
		addSequential(new DriveToPosition(20, .5, .07, 1.5));
		addSequential(new DriveToPosition(34, .5, .03, 1.5));
		addSequential(new BoilerTurnToAngle(1));
		addParallel(new StartFlywheel(5550));
		addSequential(new Wait(1));
		addSequential(new StartConveyor());
    }
}



//addSequential(new DriveToPosition(30, .5, 0, 2));
//addSequential(new VisionDriveStraight(.5, Robot.driveSS.getVisionAngle(), 20));
//addSequential(new DriveToPosition(23, .5, 0, 2));




