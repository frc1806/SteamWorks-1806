package org.usfirst.frc.team1806.robot.commands.red;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.commands.Wait;
import org.usfirst.frc.team1806.robot.commands.conveyor.StartConveyor;
import org.usfirst.frc.team1806.robot.commands.conveyor.StopConveyor;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.DriveToPosition;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.RunDrive;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.TurnToAngle;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.VisionDriveStraight;
import org.usfirst.frc.team1806.robot.commands.flywheel.StartFlywheel;
import org.usfirst.frc.team1806.robot.commands.flywheel.StopFlywheel;
import org.usfirst.frc.team1806.robot.commands.hopper.RunHopper;
import org.usfirst.frc.team1806.robot.commands.hopper.StopHopper;
import org.usfirst.frc.team1806.robot.commands.sequences.Shimmy;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BoilerandGear extends CommandGroup {
	
    public BoilerandGear() {
		addSequential(new StartFlywheel());
		addSequential(new Wait(1.5));
		addParallel(new StartConveyor());
		addParallel(new RunHopper());
		addSequential(new Wait(4));
		addSequential(new StopHopper());
		addSequential(new StopConveyor());
		addSequential(new StopFlywheel());
		
		addSequential(new TurnToAngle(-60, .7, 2));
		addSequential(new DriveToPosition(46, .9, 2));
		addSequential(new TurnToAngle(-70, .7, 2.5));
		addSequential(new VisionDriveStraight(.5, Robot.driveSS.getVisionAngle(), 42));
		addSequential(new Shimmy());
		addSequential(new RunDrive(-.3, 0, .3));
		addSequential(new Shimmy());
    }
}
