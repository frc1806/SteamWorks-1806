package org.usfirst.frc.team1806.robot.subsystems;

import org.usfirst.frc.team1806.robot.Constants;
import org.usfirst.frc.team1806.robot.RobotMap;
import org.usfirst.frc.team1806.robot.commands.sequences.BallStuck;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class HopperSubsystem extends Subsystem {
	public CANTalon hopperMotor;
	//HAHA there isnt even a Hopper anymore jokes on you!
	double desiredOutputCurrent = 2.3;
	Timer timer = new Timer();
	public HopperSubsystem() {
		hopperMotor = new CANTalon(100);
	}
	
	public void setForwardPower(){
		hopperMotor.changeControlMode(TalonControlMode.PercentVbus);
		hopperMotor.set(-Constants.hopperSpeed);
	}
	
	public void setReverseMotor(){
		System.out.println("REVERSING MOTOR");
		hopperMotor.changeControlMode(TalonControlMode.PercentVbus);
		hopperMotor.set(.2);
	}
	public void setHopperSpeed(){
		if(returnCurrentDraw() > desiredOutputCurrent){
		
		} else {
			setForwardPower();
		}
	}
	public double returnCurrentDraw(){
		return hopperMotor.getOutputCurrent();
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

