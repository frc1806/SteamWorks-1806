package org.usfirst.frc.team1806.robot.commands.sequences;

import org.usfirst.frc.team1806.robot.commands.drivetrain.SeizureChange;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.RunDrive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SeizureMode extends CommandGroup {
	public Timer seizureTime;
    public SeizureMode() {
    		addSequential(new SeizureChange(true));
        	addSequential(new RunDrive(-.3,0, .1));
        	addSequential(new RunDrive(.3,0, .1));
    		addSequential(new SeizureChange(false));
    	}
	}
