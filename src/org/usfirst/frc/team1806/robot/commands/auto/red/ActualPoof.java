package org.usfirst.frc.team1806.robot.commands.auto.red;

import org.usfirst.frc.team1806.robot.commands.Wait;
import org.usfirst.frc.team1806.robot.commands.conveyor.StartConveyor;
import org.usfirst.frc.team1806.robot.commands.conveyor.StopConveyor;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.BoilerTurnToAngle;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.DriveToPosition;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.TurnToAngle;
import org.usfirst.frc.team1806.robot.commands.flywheel.StartFlywheel;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ActualPoof extends CommandGroup {

    public ActualPoof() {
       addSequential(new StartFlywheel());
       addSequential(new Wait(1));
       addSequential(new DriveToPosition(-15, -.3, 0, 2));
       addSequential(new StartConveyor());
       addSequential(new DriveToPosition(-27, -.3, 0, 2));
       addSequential(new BoilerTurnToAngle(1));
       addSequential(new Wait(.1));
       addSequential(new StopConveyor());
//       ////
       addSequential(new TurnToAngle(-90, .5, 2));
       addSequential(new DriveToPosition(10, .3, 0, 4));
       addSequential(new DriveToPosition(45, .5, -.2, 4));
    }
}
