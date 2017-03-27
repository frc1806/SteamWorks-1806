package org.usfirst.frc.team1806.robot.commands.sequences;

import org.usfirst.frc.team1806.robot.commands.drivetrain.ShimmyChange;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.DriveLeft;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.DriveRight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Shimmy12 extends CommandGroup {

    public Shimmy12() {
    	addSequential(new ShimmyChange(true));
	     addSequential(new DriveLeft(.5, .3, 2));
	     addSequential(new DriveRight(.3, .3, 2));
	     addSequential(new DriveRight(.5, .3, 2));
	     addSequential(new DriveLeft(.5, .3, 2));
	     addSequential(new DriveLeft(.5, .3, 2));
	     addSequential(new DriveRight(.3, .3, 2));
	     addSequential(new DriveRight(.5, .3, 2));
	     addSequential(new DriveLeft(.5, .3, 2));
	     addSequential(new DriveLeft(.5, .3, 2));
	     addSequential(new DriveRight(.3, .3, 2));
	     addSequential(new DriveLeft(.5, .3, 2));
	     addSequential(new DriveRight(.3, .3, 2));
	     addSequential(new DriveRight(.5, .3, 2));
	     addSequential(new DriveLeft(.5, .3, 2));
	     addSequential(new DriveLeft(.5, .3, 2));
	     addSequential(new DriveRight(.3, .3, 2));
	     addSequential(new DriveRight(.5, .3, 2));
	     addSequential(new DriveLeft(.5, .3, 2));
	     addSequential(new DriveLeft(.5, .3, 2));
	     addSequential(new DriveRight(.3, .3, 2));
	     addSequential(new DriveRight(.5, .3, 2));
	     addSequential(new DriveLeft(.5, .3, 2));
	     addSequential(new DriveLeft(.5, .3, 2));
	     addSequential(new DriveRight(.3, .3, 2));
	     addSequential(new ShimmyChange(false));
    }
}
