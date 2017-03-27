package org.usfirst.frc.team1806.robot.commands.sequences;

import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.DriveLeft;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.DriveRight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Shimmy5 extends CommandGroup {

    public Shimmy5() {
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
    }
}
