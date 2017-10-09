package org.usfirst.frc.team1806.robot.commands.auto;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.VisionDriveStraight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SimpleRight extends CommandGroup {

    public SimpleRight() {
    	addSequential(new org.usfirst.frc.team1806.robot.commands.auto.motion.SimpleRight());
    	addSequential(new VisionDriveStraight(.25, Robot.driveSS.getVisionAngle(), 30));
    }
}
