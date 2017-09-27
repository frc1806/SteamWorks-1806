package org.usfirst.frc.team1806.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SimpleRight extends CommandGroup {

    public SimpleRight() {
    	addSequential(new org.usfirst.frc.team1806.robot.commands.auto.motion.SimpleRight());
    }
}
