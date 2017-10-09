package org.usfirst.frc.team1806.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SimpleLeft extends CommandGroup {

    public SimpleLeft() {
    	addSequential(new org.usfirst.frc.team1806.robot.commands.auto.motion.SimpleLeft());

    }
}
