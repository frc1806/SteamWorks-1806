package org.usfirst.frc.team1806.robot.commands.sequences;

import org.usfirst.frc.team1806.robot.commands.drivetrain.RunDrive;
import org.usfirst.frc.team1806.robot.commands.drivetrain.SeizureChange;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SeizureMode extends CommandGroup {
	public Timer seizureTime;
    public SeizureMode() {
    		addSequential(new SeizureChange(true));
        	addSequential(new RunDrive(-.5, .1));
        	addSequential(new RunDrive(.5, .1));
    		addSequential(new SeizureChange(false));
    	}
	}
