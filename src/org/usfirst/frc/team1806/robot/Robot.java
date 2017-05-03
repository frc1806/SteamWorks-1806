
package org.usfirst.frc.team1806.robot;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.logging.Logger;


import org.usfirst.frc.team1806.robot.States.GearHolder;
import org.usfirst.frc.team1806.robot.States.IntakeStates;
import org.usfirst.frc.team1806.robot.commands.ExampleCommand;
import org.usfirst.frc.team1806.robot.commands.Wait;
import org.usfirst.frc.team1806.robot.commands.auto.blue.BoilerToGear;
import org.usfirst.frc.team1806.robot.commands.auto.red.ActualPoof;
import org.usfirst.frc.team1806.robot.commands.auto.red.BoilerToCenter;
import org.usfirst.frc.team1806.robot.commands.auto.red.BoilerToLeft;
import org.usfirst.frc.team1806.robot.commands.auto.red.BoilerandGear;
import org.usfirst.frc.team1806.robot.commands.auto.red.GearandHopper;
import org.usfirst.frc.team1806.robot.commands.auto.red.Hopper;
import org.usfirst.frc.team1806.robot.commands.auto.red.Poof;
import org.usfirst.frc.team1806.robot.commands.auto.simple.Center;
import org.usfirst.frc.team1806.robot.commands.auto.simple.LeftSide;
import org.usfirst.frc.team1806.robot.commands.auto.simple.RightSide;
import org.usfirst.frc.team1806.robot.commands.conveyor.StartConveyor;
import org.usfirst.frc.team1806.robot.commands.conveyor.StopConveyor;
import org.usfirst.frc.team1806.robot.commands.drivetrain.Drive;
import org.usfirst.frc.team1806.robot.commands.drivetrain.shiftHigh;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.DriveAngle;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.DriveToPosition;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.RunDrive;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.TurnToAngle;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.VisionDriveStraight;
import org.usfirst.frc.team1806.robot.commands.flywheel.RunFlywheelTime;
import org.usfirst.frc.team1806.robot.commands.flywheel.StartFlywheel;
import org.usfirst.frc.team1806.robot.commands.flywheel.StopFlywheel;
import org.usfirst.frc.team1806.robot.commands.gear.ExtendGear;
import org.usfirst.frc.team1806.robot.commands.gear.RectractGear;
import org.usfirst.frc.team1806.robot.commands.hopper.RunHopper;
import org.usfirst.frc.team1806.robot.commands.hopper.StopHopper;
import org.usfirst.frc.team1806.robot.commands.intake.StartIntake;
import org.usfirst.frc.team1806.robot.commands.intake.StopIntake;
import org.usfirst.frc.team1806.robot.commands.sequences.SeizureMode;
import org.usfirst.frc.team1806.robot.commands.sequences.Shimmy;
import org.usfirst.frc.team1806.robot.subsystems.CameraSubsystem;
import org.usfirst.frc.team1806.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team1806.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team1806.robot.subsystems.FlywheelSubsystem;
import org.usfirst.frc.team1806.robot.subsystems.GearHolderSubsystem;
import org.usfirst.frc.team1806.robot.subsystems.HopperSubsystem;
import org.usfirst.frc.team1806.robot.subsystems.IntakeSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static SmartDashboardUpdater ss;
	public static DrivetrainSubsystem driveSS;
	public static FlywheelSubsystem flywheelSS;
	public static HopperSubsystem hopperSS;
	public static IntakeSubsystem intakeSS;
	public static ClimberSubsystem climberSS;
	public static GearHolderSubsystem gearSS;
	public static NetworkTable networkTable;
	public static NetworkTable boilerTable;
	public static OI oi;
	public static PowerDistributionPanel pdPowerDistributionPanel;
	public static States states;
	public UsbCamera camera;
	//MjpegServer cameraServer = new MjpegServer("camera", 5000);
	Command autonomousCommand;
	SendableChooser chooser = new SendableChooser<>();
	Compressor c = new Compressor();
	public static CameraSubsystem cameraSS;
	public static Timer matchTimer = new Timer();
	DataLogger logger;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		pdPowerDistributionPanel = new PowerDistributionPanel();
		states = new States();
		cameraSS = new CameraSubsystem();
		climberSS = new ClimberSubsystem();
		driveSS = new DrivetrainSubsystem();
		flywheelSS = new FlywheelSubsystem();
		hopperSS = new HopperSubsystem();
		intakeSS = new IntakeSubsystem();
		gearSS = new GearHolderSubsystem();
		states.resetStates();
		ss = new SmartDashboardUpdater();
		oi = new OI();
		
		boilerTable = NetworkTable.getTable("BoilerTracker");
		networkTable = NetworkTable.getTable("LiftTracker");
		Robot.driveSS.navx.reset();
		ss.updateValues();

		chooser.addDefault("Default Wait 1", new Wait(2));
		///
		chooser.addObject("MAIN MAIN MAIN Red: Shoot 10 + Right Gear", new BoilerandGear());
		chooser.addObject("Red: Hopper", new Hopper());
		chooser.addObject("Red: Boiler and Center Gear", new BoilerToCenter());
		chooser.addObject("Red: Boiler and Left Gear", new BoilerToLeft());
		chooser.addObject("Red: Gear and Hopper", new GearandHopper());
		///
		chooser.addObject("Center", new Center());
		chooser.addObject("Left", new LeftSide());
		chooser.addObject("Right", new RightSide());
		///
		chooser.addObject("MAIN MAIN MAIN Blue: Shoot 10 + Right Gear", new BoilerToGear());
		chooser.addObject("Blue: EXPERIMENTAL Hopper ", new org.usfirst.frc.team1806.robot.commands.auto.blue.Hopper());
		chooser.addObject("Blue: Shoot 10 + Center", new org.usfirst.frc.team1806.robot.commands.auto.blue.BoilerToCenter());
		chooser.addObject("Blue: EXPERIMENTAL Gear + Hopper Shoot", new org.usfirst.frc.team1806.robot.commands.auto.blue.GearandHopper());
		chooser.addObject("Blue: EXPERIMENTAL Boiler + Left Side", new org.usfirst.frc.team1806.robot.commands.auto.blue.BoilerToLeft());
		///
		chooser.addObject("Red Poof", new Poof());
		chooser.addObject("Red NEW Poof", new ActualPoof());
		chooser.addObject("Blue Poof", new org.usfirst.frc.team1806.robot.commands.auto.blue.Poof());
		chooser.addObject("Blue NEW Poof", new org.usfirst.frc.team1806.robot.commands.auto.blue.ActualPoof());
		SmartDashboard.putData("Chooser", chooser);
		Robot.cameraSS.init();
		Robot.driveSS.navx.reset();
	}	
	
		

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		ss.updateValues();
		oi.stopRumble();
		cameraSS.reset();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		oi.update();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
		autonomousCommand = (Command) chooser.getSelected();
		autonomousCommand.start();
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		ss.updateValues();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		ss.updateValues();
		matchTimer.reset();
		matchTimer.start();
		cameraSS.camera0.setExposureAuto();
		cameraSS.camera1.setExposureAuto();
		logger = new DataLogger();
		logger.addTimestamp();
		Robot.states.resetStates();
		Robot.driveSS.navx.reset();
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		ss.updateValues();
		Scheduler.getInstance().run();
		oi.update();
		logger.writeNewTeleopCycle();
		c.setClosedLoopControl(true);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
