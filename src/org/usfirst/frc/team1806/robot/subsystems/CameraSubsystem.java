package org.usfirst.frc.team1806.robot.subsystems;

import org.usfirst.frc.team1806.robot.Commands;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraSubsystem extends Subsystem {
	MjpegServer switcher;
	boolean whichCamera = false;
	UsbCamera boilerCamera, liftCamera;
	UsbCamera cameraAtMoment;
	public int CAMERA_WIDTH = 320;
	public int CAMERA_HEIGHT = 240;
	public int CAMERA_FPS = 25;

	public CameraSubsystem() {
		
	}
	public void init(){
//		boilerCamera = new UsbCamera("cam0", 0);
//		liftCamera = new UsbCamera("cam1", 1);
//		CameraServer.getInstance().startAutomaticCapture(boilerCamera);
//		CameraServer.getInstance().startAutomaticCapture(liftCamera);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

