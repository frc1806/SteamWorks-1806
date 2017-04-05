package org.usfirst.frc.team1806.robot.subsystems;

import org.usfirst.frc.team1806.robot.Commands;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraSubsystem extends Subsystem {
	MjpegServer switcher;
	boolean whichCamera = false;
	UsbCamera camera0, camera1;
	UsbCamera cameraAtMoment;
	public int CAMERA_WIDTH = 320;
	public int CAMERA_HEIGHT = 240;
	public int CAMERA_FPS = 15;

	public CameraSubsystem() {
		
	}
	public void init(){
		camera0 = new UsbCamera("cam0", 0);
		camera1 = new UsbCamera("cam1", 1);
		switcher = new MjpegServer("switcher", 5801);
		camera0.setResolution(CAMERA_WIDTH, CAMERA_HEIGHT);
		camera0.setFPS(CAMERA_FPS);
		camera0.setExposureManual(30); //3
		camera1.setResolution(CAMERA_WIDTH, CAMERA_HEIGHT);
		camera1.setFPS(CAMERA_FPS);
		camera1.setExposureManual(7); //3
		cameraAtMoment = camera0;
		cameraAtMoment.setPixelFormat(PixelFormat.kMJPEG);
		switcher.setSource(cameraAtMoment);	
	}
	public void update(){
		cameraAtMoment = ((cameraAtMoment == camera0) ? camera1 : camera0);
		switcher.setSource(cameraAtMoment);
		System.out.println("Setting the camera" + switcher.getSource().getDescription());
	}
	public void setToBoilerCamera(){
		
	}
	public void setToLiftCamera(){
		
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

