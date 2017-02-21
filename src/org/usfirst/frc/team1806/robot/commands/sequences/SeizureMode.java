package org.usfirst.frc.team1806.robot.commands.sequences;

import org.usfirst.frc.team1806.robot.commands.SetSeizeMode;
import org.usfirst.frc.team1806.robot.commands.drivetrain.RunDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SeizureMode extends CommandGroup {

    public SeizureMode() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new RunDrive(.5, 1));
    	addSequential(new RunDrive(-.5, 1));
    	addSequential(new SetSeizeMode());
    }
}
