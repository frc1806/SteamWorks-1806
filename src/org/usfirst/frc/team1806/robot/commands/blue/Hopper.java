package org.usfirst.frc.team1806.robot.commands.blue;

import org.usfirst.frc.team1806.robot.commands.conveyor.StartConveyor;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.DriveToPosition;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.TurnToAngle;
import org.usfirst.frc.team1806.robot.commands.flywheel.StartFlywheel;
import org.usfirst.frc.team1806.robot.commands.gear.ExtendGear;
import org.usfirst.frc.team1806.robot.commands.hopper.RunHopper;
import org.usfirst.frc.team1806.robot.commands.intake.StartIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Hopper extends CommandGroup {

    public Hopper() {
    	// HOPPER BLUE
    	addSequential(new ExtendGear());
    	addParallel(new StartFlywheel());
    	addSequential(new DriveToPosition(-104, .7, 0,2));
    	addSequential(new StartIntake());
    	addSequential(new TurnToAngle(30, .4, 1));
    	addSequential(new StartConveyor());
    	addSequential(new DriveToPosition(22, .5,0 ,.5));
    	addSequential(new RunHopper());
    }
}
