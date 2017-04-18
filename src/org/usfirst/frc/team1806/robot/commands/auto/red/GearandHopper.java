package org.usfirst.frc.team1806.robot.commands.auto.red;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.commands.Wait;
import org.usfirst.frc.team1806.robot.commands.conveyor.StartConveyor;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.DriveToPosition;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.VisionDriveStraight;
import org.usfirst.frc.team1806.robot.commands.flywheel.StartFlywheel;
import org.usfirst.frc.team1806.robot.commands.gear.ExtendGear;
import org.usfirst.frc.team1806.robot.commands.hopper.RunHopper;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearandHopper extends CommandGroup {

    public GearandHopper() {
        addSequential(new DriveToPosition(12, 1, 0, 3));
        addSequential(new DriveToPosition(77, 1, -.40, 3));
        addSequential(new VisionDriveStraight(.25, Robot.driveSS.getVisionAngle(), 20));
        addSequential(new Wait(1));
        addSequential(new DriveToPosition(-60, -1, -0, 2)); // First go backwards
        addSequential(new DriveToPosition(-10, -1, -.9, 2)); // Drastic turn into hopper
        addSequential(new DriveToPosition(14, .6, 0, 2)); // Drastic turn into hopper
    	addSequential(new ExtendGear());
		addParallel(new StartFlywheel());
		addSequential(new Wait(1.2));
		addSequential(new StartConveyor());
		addSequential(new RunHopper());
    }
}
