package org.usfirst.frc.team1806.robot.commands.auto.red;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.commands.Wait;
import org.usfirst.frc.team1806.robot.commands.conveyor.StartConveyor;
import org.usfirst.frc.team1806.robot.commands.conveyor.StopConveyor;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.DriveToPosition;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.RunDrive;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.TurnToAngle;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.VisionDriveStraight;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.VisionTurnToAngle;
import org.usfirst.frc.team1806.robot.commands.flywheel.StartFlywheel;
import org.usfirst.frc.team1806.robot.commands.flywheel.StopFlywheel;
import org.usfirst.frc.team1806.robot.commands.gear.ExtendGear;
import org.usfirst.frc.team1806.robot.commands.gear.RectractGear;
import org.usfirst.frc.team1806.robot.commands.hopper.RunHopper;
import org.usfirst.frc.team1806.robot.commands.hopper.StopHopper;
import org.usfirst.frc.team1806.robot.commands.intake.StartIntake;
import org.usfirst.frc.team1806.robot.commands.sequences.Shimmy;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BoilerandGear extends CommandGroup {
	
    public BoilerandGear() {
    	addSequential(new RectractGear()); //TODO switch back
		addParallel(new StartFlywheel());
		addSequential(new Wait(1.2));
		addSequential(new StartConveyor());
		addSequential(new RunHopper());
		addSequential(new Wait(3));
		addSequential(new StopHopper());
		addSequential(new StopConveyor());
		addSequential(new StopFlywheel());
		addSequential(new RectractGear());
		addSequential(new TurnToAngle(-64, .75, 2));
		addSequential(new StartIntake());
		addSequential(new DriveToPosition(75, 1, 0 , 1.5));
		addSequential(new TurnToAngle(-30, .75, 2.5));
		addSequential(new VisionTurnToAngle(.5));
		addSequential(new VisionDriveStraight(.27, Robot.driveSS.getVisionAngle(), 30)); //13
		addSequential(new Shimmy());
		addSequential(new Wait(2));
		addSequential(new RunDrive(-.3, 0, .3));
		addSequential(new RunDrive(.3, 0, .3));
		addSequential(new Shimmy());
		addSequential(new Shimmy());
		addSequential(new Shimmy());
		addSequential(new Shimmy());
    }
}
