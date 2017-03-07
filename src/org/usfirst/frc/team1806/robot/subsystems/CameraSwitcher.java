package org.usfirst.frc.team1806.robot.subsystems;

import org.usfirst.frc.team1806.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraSwitcher extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void updateCamera(){
		
	}
	public String cameraString(){
		return Robot.states.cameraTracker.toString();
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

