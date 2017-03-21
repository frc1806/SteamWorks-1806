
package org.usfirst.frc.team1806.robot;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1806.robot.States.GearHolder;
import org.usfirst.frc.team1806.robot.States.IntakeStates;
import org.usfirst.frc.team1806.robot.commands.ExampleCommand;
import org.usfirst.frc.team1806.robot.commands.Wait;
import org.usfirst.frc.team1806.robot.commands.blue.BoilerToGear;
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
import org.usfirst.frc.team1806.robot.commands.hopper.RunHopper;
import org.usfirst.frc.team1806.robot.commands.hopper.StopHopper;
import org.usfirst.frc.team1806.robot.commands.intake.StartIntake;
import org.usfirst.frc.team1806.robot.commands.intake.StopIntake;
import org.usfirst.frc.team1806.robot.commands.red.BoilerandGear;
import org.usfirst.frc.team1806.robot.commands.red.Center;
import org.usfirst.frc.team1806.robot.commands.red.Hopper;
import org.usfirst.frc.team1806.robot.commands.red.LeftSide;
import org.usfirst.frc.team1806.robot.commands.red.RightSide;
import org.usfirst.frc.team1806.robot.commands.sequences.SeizureMode;
import org.usfirst.frc.team1806.robot.commands.sequences.Shimmy;
import org.usfirst.frc.team1806.robot.subsystems.CameraSwitcher;
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
	public static CameraSwitcher cameraS;
	public static NetworkTable networkTable;
	public static OI oi;
	public static PowerDistributionPanel pdPowerDistributionPanel;
	public static States states;
	public UsbCamera camera;
	//MjpegServer cameraServer = new MjpegServer("camera", 5000);
	Command autonomousCommand;
	SendableChooser chooser = new SendableChooser<>();
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		pdPowerDistributionPanel = new PowerDistributionPanel();
		states = new States();
		cameraS = new CameraSwitcher();
		climberSS = new ClimberSubsystem();
		driveSS = new DrivetrainSubsystem();
		flywheelSS = new FlywheelSubsystem();
		hopperSS = new HopperSubsystem();
		intakeSS = new IntakeSubsystem();
		gearSS = new GearHolderSubsystem();
		states.resetStates();
		ss = new SmartDashboardUpdater();
		oi = new OI();
		
		networkTable = NetworkTable.getTable("LiftTracker");
		networkTable.putDouble("ayylmao", 86950346);
		Robot.driveSS.navx.reset();
		ss.updateValues();
				
	    camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(640, 480);
		camera.setFPS(30);
		camera.setExposureManual(7); //7
		
		chooser.addDefault("Default Wait 1", new Wait(2));
		chooser.addObject("Red: Shoot 10 + Gear", new BoilerandGear());
		chooser.addObject("Red: Center", new Center());
		chooser.addObject("Red: Left", new LeftSide());
		chooser.addObject("Red: Right", new RightSide());
		chooser.addObject("Red: Hopper", new Hopper());
		///
		chooser.addObject("Blue: Shoot 10 + Gear", new BoilerToGear());
		chooser.addObject("Blue: Center", new org.usfirst.frc.team1806.robot.commands.blue.Center());
		chooser.addObject("Blue: Left", new org.usfirst.frc.team1806.robot.commands.blue.LeftSide());
		chooser.addObject("Blue: Right", new org.usfirst.frc.team1806.robot.commands.blue.RightSide());
		SmartDashboard.putData("Chooser", chooser);
		
	}	
	
		

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		ss.updateValues();
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
		Robot.states.resetStates();
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		oi.update();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
