package org.usfirst.frc.team1806.robot.commands.red;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.commands.Wait;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.DriveToPosition;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.RunDrive;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.TurnToAngle;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.VisionDriveStraight;
import org.usfirst.frc.team1806.robot.commands.sequences.Shimmy;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftSide extends CommandGroup {

    public LeftSide() {
      	addSequential(new DriveToPosition(85, .7, 2.5));
    	addSequential(new TurnToAngle(35, .6, 2));
    	addSequential(new VisionDriveStraight(.5, Robot.driveSS.getVisionAngle(), 20));
    	addSequential(new DriveToPosition(8, .5, 2));
		addSequential(new Shimmy());
		addSequential(new Wait(2));
		addSequential(new RunDrive(-.3, 0, .3));
		addSequential(new RunDrive(.3, 0, .3));
		addSequential(new Shimmy());
    }
}
