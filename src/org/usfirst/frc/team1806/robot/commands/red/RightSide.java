package org.usfirst.frc.team1806.robot.commands.red;

import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.DriveToPosition;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSide extends CommandGroup {

    public RightSide() {
    	addSequential(new DriveToPosition(90, .6, 2));
    	addSequential(new TurnToAngle(-60, .6, 2));
    }
}
