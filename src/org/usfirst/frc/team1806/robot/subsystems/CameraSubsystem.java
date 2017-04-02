package org.usfirst.frc.team1806.robot.subsystems;

import org.usfirst.frc.team1806.robot.Commands;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraSubsystem extends Subsystem {
	MjpegServer switcher;
	UsbCamera camera0, camera1;
	UsbCamera cameraAtMoment;
	public CameraSubsystem() {
		camera0 = new UsbCamera("cam0", 0);
		camera1 = new UsbCamera("cam1", 1);
		switcher = new MjpegServer("switcher", 5801);

		camera0.setResolution(640, 480);
		camera0.setResolution(640, 480);
		camera0.setFPS(30);
		camera0.setExposureManual(7); //3
		
		camera1.setResolution(640, 480);
		
		camera1.setResolution(640, 480);
		camera1.setFPS(30);
		camera1.setExposureManual(50); //3
		
	}
	public void update(){
		cameraAtMoment = ((cameraAtMoment == camera0) ? camera1 : camera0);
		switcher.setSource(cameraAtMoment);
		switcher.getListenAddress();
		System.out.println(switcher.getListenAddress());
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

