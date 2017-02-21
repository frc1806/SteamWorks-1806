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
	double max = 0; 
	public ClimberSubsystem(){
		climber = new Talon(RobotMap.climber);

	}
	
	public void climbAtSpeed(double power){
		if(Robot.pdPowerDistributionPanel.getCurrent(12) > max || Robot.pdPowerDistributionPanel.getCurrent(3) > max){
			max = Robot.pdPowerDistributionPanel.getCurrent(12);
		}
		Robot.states.climberTracker = Climber.RUNNINGATSPEED;
		climber.set(-power);
		if(Robot.pdPowerDistributionPanel.getCurrent(12) > 37.5|| Robot.pdPowerDistributionPanel.getCurrent(3) > 37.5){
			climber.set(0);
		}
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

