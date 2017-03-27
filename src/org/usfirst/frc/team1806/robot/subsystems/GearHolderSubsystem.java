package org.usfirst.frc.team1806.robot.subsystems;

import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.RobotMap;
import org.usfirst.frc.team1806.robot.States.GearHolder;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearHolderSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	DoubleSolenoid gearHolderL;
	public GearHolderSubsystem() {
		gearHolderL = new DoubleSolenoid(RobotMap.gearHolderIn, RobotMap.gearHolderOut);
	}
	public void extend(){
		Robot.states.gearTracker = GearHolder.OUT;
		gearHolderL.set(Value.kReverse);
	}
	public void retract(){
		Robot.states.gearTracker = GearHolder.IN;
		gearHolderL.set(Value.kForward);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

