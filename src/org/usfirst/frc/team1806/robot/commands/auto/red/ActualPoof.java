package org.usfirst.frc.team1806.robot.commands.auto.red;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.commands.Wait;
import org.usfirst.frc.team1806.robot.commands.conveyor.StartConveyor;
import org.usfirst.frc.team1806.robot.commands.conveyor.StopConveyor;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.BoilerTurnToAngle;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.DriveToPosition;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.TurnToAngle;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.VisionDriveStraight;
import org.usfirst.frc.team1806.robot.commands.flywheel.StartFlywheel;
import org.usfirst.frc.team1806.robot.commands.flywheel.StopFlywheel;
import org.usfirst.frc.team1806.robot.commands.gear.RectractGear;
import org.usfirst.frc.team1806.robot.commands.sequences.Shimmy;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ActualPoof extends CommandGroup {

    public ActualPoof() {
//        addSequential(new StartFlywheel(4300));
//    	addSequential(new RectractGear());
//        addSequential(new Wait(1));
//        addSequential(new DriveToPosition(-15, -.3, 0, 2));
//        addSequential(new StartConveyor());
//        addSequential(new DriveToPosition(-11, -.3, 0, 2));
//        addSequential(new StartFlywheel(4750));
//        addSequential(new DriveToPosition(-11, -.3, 0, 2));
//        addSequential(new Wait(1));
//        addSequential(new SetToLift());
//        addSequential(new Wait(.1));
//        addSequential(new StopConveyor());
////       ////
//       addSequential(new TurnToAngle(-90, .5, 2));
//       addSequential(new DriveToPosition(10, .3, 0, 4));
//       addSequential(new DriveToPosition(45, .5, -.2, 4));
//       addSequential(new VisionDriveStraight(.5, Robot.driveSS.getVisionAngle(), 25));
//       addSequential(new VisionDriveStraight(.25, Robot.driveSS.getVisionAngle(), 10));
//       addSequential(new Shimmy());
    	
    	addSequential(new RectractGear());
    	addSequential(new StartFlywheel(4350));
    	addSequential(new Wait(1));
    	addSequential(new DriveToPosition(-23, -.3, 0, .5));
    	addSequential(new StartConveyor());
    	addSequential(new Wait(2));
    	addSequential(new StopFlywheel());
    	addSequential(new StopConveyor());
    	addSequential(new TurnToAngle(-90, .5, 2));
    	addSequential(new DriveToPosition(20, .5, -.35, 2));
    	addSequential(new Wait(1));
    	addSequential(new VisionDriveStraight(.3, Robot.driveSS.getVisionAngle(), 40));
    	addSequential(new Shimmy());

    	
    }
}
