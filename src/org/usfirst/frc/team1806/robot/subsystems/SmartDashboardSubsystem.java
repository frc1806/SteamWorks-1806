package org.usfirst.frc.team1806.robot.subsystems;

import org.usfirst.frc.team1806.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SmartDashboardSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	SmartDashboard s;
	public SmartDashboardSubsystem(){
		s = new SmartDashboard();
	}
	@SuppressWarnings("deprecation")
	public void updateSmartDashboard(){
		s.putDouble("Left Motor Speed", Robot.driveSS.leftMotor2.getRaw());
		s.putDouble("Right Motor Speed", Robot.driveSS.rightMotor2.getRaw());
		
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

