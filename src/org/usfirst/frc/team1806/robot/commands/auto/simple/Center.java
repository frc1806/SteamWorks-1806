package org.usfirst.frc.team1806.robot.commands.auto.simple;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.commands.Wait;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.DriveToPosition;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.RunDrive;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.VisionDriveStraight;
import org.usfirst.frc.team1806.robot.commands.gear.ExtendGear;
import org.usfirst.frc.team1806.robot.commands.gear.RectractGear;
import org.usfirst.frc.team1806.robot.commands.sequences.Shimmy;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Center extends CommandGroup {

    public Center() {
    	addSequential(new RectractGear());
    	addSequential(new DriveToPosition(30, .5,0, 2));
    	addSequential(new VisionDriveStraight(.5, Robot.driveSS.getVisionAngle(), 25));
    	addSequential(new DriveToPosition(10, .5,0, 2));
    	addSequential(new Shimmy());
		addSequential(new Wait(5));
		addSequential(new RunDrive(-.3, 0, .7));
		addSequential(new RunDrive(.3, 0, .7));
		addSequential(new Shimmy());
    }
}
