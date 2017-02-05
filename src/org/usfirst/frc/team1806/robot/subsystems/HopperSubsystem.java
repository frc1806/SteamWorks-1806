package org.usfirst.frc.team1806.robot.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class HopperSubsystem extends Subsystem {
	CANTalon hopperMotor;
	public HopperSubsystem() {
		hopperMotor = new CANTalon(0);
	}
	public void setHopperSpeed(){
		hopperMotor.changeControlMode(TalonControlMode.PercentVbus);
		hopperMotor.set(.8);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

