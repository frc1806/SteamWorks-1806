package org.usfirst.frc.team1806.robot.commands.auto;

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
public class SimpleRight extends CommandGroup {

    public SimpleRight() {
    	addSequential(new org.usfirst.frc.team1806.robot.commands.auto.motion.SimpleRight());
		addSequential(new VisionDriveStraight(.5, Robot.driveSS.getVisionAngle(), 30)); //13
		addSequential(new Shimmy());
    	addSequential(new Shimmy());
		addSequential(new Wait(2));
		addSequential(new RunDrive(-.3, 0, .7));
		addSequential(new RunDrive(.3, 0, .7));
		addSequential(new Shimmy());

    }
}
