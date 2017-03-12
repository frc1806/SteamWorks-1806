package org.usfirst.frc.team1806.robot.commands.sequences;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.commands.drivetrain.ShimmyChange;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.RunDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Shimmy extends CommandGroup {

    public Shimmy() {
    	addSequential(new ShimmyChange(true));
    	addSequential(new RunDrive(.3, .15, .3));
    	addSequential(new RunDrive(.3, -.2, .3));
    	addSequential(new ShimmyChange(false));
    }
}
