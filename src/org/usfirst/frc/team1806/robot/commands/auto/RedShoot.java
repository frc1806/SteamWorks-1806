package org.usfirst.frc.team1806.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RedShoot extends CommandGroup {

    public RedShoot() {
    	addSequential(new org.usfirst.frc.team1806.robot.commands.auto.motion.RedShoot());

    }
}
