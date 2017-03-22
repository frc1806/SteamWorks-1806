package org.usfirst.frc.team1806.robot.commands.red;

import org.usfirst.frc.team1806.robot.commands.Wait;
import org.usfirst.frc.team1806.robot.commands.conveyor.StartConveyor;
import org.usfirst.frc.team1806.robot.commands.conveyor.StopConveyor;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.DriveToPosition;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.TurnToAngle;
import org.usfirst.frc.team1806.robot.commands.flywheel.StartFlywheel;
import org.usfirst.frc.team1806.robot.commands.flywheel.StopFlywheel;
import org.usfirst.frc.team1806.robot.commands.gear.ExtendGear;
import org.usfirst.frc.team1806.robot.commands.hopper.RunHopper;
import org.usfirst.frc.team1806.robot.commands.hopper.StopHopper;
import org.usfirst.frc.team1806.robot.commands.intake.StartIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Hopper extends CommandGroup {

    public Hopper() {
    	addSequential(new ExtendGear());
    	addParallel(new StartFlywheel());
    	addSequential(new DriveToPosition(-104, .7, 2));
    	addSequential(new StartIntake());
    	addSequential(new TurnToAngle(-30, .4, 1));
    	addSequential(new StartConveyor());
    	addSequential(new DriveToPosition(22, .5, .5));
    	addSequential(new RunHopper());
    }
}
