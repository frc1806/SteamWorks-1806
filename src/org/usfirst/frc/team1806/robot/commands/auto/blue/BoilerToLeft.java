package org.usfirst.frc.team1806.robot.commands.auto.blue;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.commands.Wait;
import org.usfirst.frc.team1806.robot.commands.conveyor.StartConveyor;
import org.usfirst.frc.team1806.robot.commands.conveyor.StopConveyor;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.DriveToPosition;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.TurnToAngle;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.VisionDriveStraight;
import org.usfirst.frc.team1806.robot.commands.flywheel.StartFlywheel;
import org.usfirst.frc.team1806.robot.commands.flywheel.StopFlywheel;
import org.usfirst.frc.team1806.robot.commands.gear.ExtendGear;
import org.usfirst.frc.team1806.robot.commands.gear.RectractGear;
import org.usfirst.frc.team1806.robot.commands.hopper.RunHopper;
import org.usfirst.frc.team1806.robot.commands.hopper.StopHopper;
import org.usfirst.frc.team1806.robot.commands.sequences.Shimmy;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BoilerToLeft extends CommandGroup {

    public BoilerToLeft() {
    	addSequential(new ExtendGear());
		addParallel(new StartFlywheel());
		addSequential(new Wait(1.2));
		addSequential(new StartConveyor());
		addSequential(new RunHopper());
		addSequential(new Wait(3));
		addSequential(new StopHopper());
		addSequential(new StopConveyor());
		addSequential(new StopFlywheel());
		addSequential(new RectractGear());
		addSequential(new DriveToPosition(-158, -1,.15 , 5));
    	addSequential(new TurnToAngle(55, .6, 2));
		addSequential(new VisionDriveStraight(.27, Robot.driveSS.getVisionAngle(), 26));
		addSequential(new Shimmy());
    }
}
