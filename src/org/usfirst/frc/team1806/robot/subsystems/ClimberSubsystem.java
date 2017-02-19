package org.usfirst.frc.team1806.robot.subsystems;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.RobotMap;
import org.usfirst.frc.team1806.robot.States.Climber;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimberSubsystem extends Subsystem {
	public Talon climber, climber1;
	
	public ClimberSubsystem(){
		climber = new Talon(RobotMap.climber);
		climber1 = new Talon(RobotMap.climber1);

	}
	
	public void climbAtSpeed(double power){
		Robot.states.climberTracker = Climber.RUNNINGATSPEED;
		climber.set(power);
		climber1.set(power);

	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

