package org.usfirst.frc.team1806.robot.commands.red;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.commands.Wait;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.DriveToPosition;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.RunDrive;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.TurnToAngle;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.VisionDriveStraight;
import org.usfirst.frc.team1806.robot.commands.gear.RectractGear;
import org.usfirst.frc.team1806.robot.commands.sequences.Shimmy;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSide extends CommandGroup {

    public RightSide() {
    	addSequential(new DriveToPosition(80, .6, 2.5));
    	addSequential(new RectractGear());
    	addSequential(new TurnToAngle(-45, .6, 2));
    	addSequential(new VisionDriveStraight(.5, Robot.driveSS.getVisionAngle(), 30));
    	addSequential(new DriveToPosition(8, .5, 2));
		addSequential(new Shimmy());
		addSequential(new Wait(2));
		addSequential(new RunDrive(-.3, 0, .7));
		addSequential(new RunDrive(.3, 0, .7));
		addSequential(new Shimmy());
    }
}
