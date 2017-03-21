package org.usfirst.frc.team1806.robot.commands.red;

import org.usfirst.frc.team1806.robot.commands.Wait;
import org.usfirst.frc.team1806.robot.commands.conveyor.StartConveyor;
import org.usfirst.frc.team1806.robot.commands.conveyor.StopConveyor;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.DriveToPosition;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.TurnToAngle;
import org.usfirst.frc.team1806.robot.commands.flywheel.StartFlywheel;
import org.usfirst.frc.team1806.robot.commands.flywheel.StopFlywheel;
import org.usfirst.frc.team1806.robot.commands.hopper.RunHopper;
import org.usfirst.frc.team1806.robot.commands.hopper.StopHopper;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Hopper extends CommandGroup {

    public Hopper() {
    	addParallel(new StartFlywheel());
    	addSequential(new DriveToPosition(-104, .7, 2));
    	addSequential(new TurnToAngle(-30, .4, 1));
    	addParallel(new StartConveyor());
    	addSequential(new DriveToPosition(14, .5, .5));
    	addSequential(new RunHopper());
    	addSequential(new Wait(9));
    	addSequential(new StopHopper());
    	addSequential(new StopConveyor());
    	addSequential(new StopFlywheel());
    }
}
