
package org.usfirst.frc.team1806.robot;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
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
import org.usfirst.frc.team1806.robot.commands.conveyor.StartConveyor;
import org.usfirst.frc.team1806.robot.commands.conveyor.StopConveyor;
import org.usfirst.frc.team1806.robot.commands.drivetrain.Drive;
import org.usfirst.frc.team1806.robot.commands.drivetrain.RunDrive;
import org.usfirst.frc.team1806.robot.commands.drivetrain.TurnToAngle;
import org.usfirst.frc.team1806.robot.commands.drivetrain.shiftHigh;
import org.usfirst.frc.team1806.robot.commands.flywheel.RunFlywheelTime;
import org.usfirst.frc.team1806.robot.commands.flywheel.StartFlywheel;
import org.usfirst.frc.team1806.robot.commands.flywheel.StopFlywheel;
import org.usfirst.frc.team1806.robot.commands.hopper.RunHopper;
import org.usfirst.frc.team1806.robot.commands.hopper.StopHopper;
import org.usfirst.frc.team1806.robot.commands.intake.StartIntake;
import org.usfirst.frc.team1806.robot.commands.intake.StopIntake;
import org.usfirst.frc.team1806.robot.commands.sequences.SeizureMode;
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
	//MjpegServer cameraServer = new MjpegServer("camera", 5000);
	CommandGroup autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		pdPowerDistributionPanel = new PowerDistributionPanel();
		states = new States();
		climberSS = new ClimberSubsystem();
		driveSS = new DrivetrainSubsystem();
		flywheelSS = new FlywheelSubsystem();
		hopperSS = new HopperSubsystem();
		intakeSS = new IntakeSubsystem();
		gearSS = new GearHolderSubsystem();
		states.resetStates();
		ss = new SmartDashboardUpdater();
		oi = new OI();
		
		autonomousCommand = new CommandGroup();
		Robot.driveSS.navx.reset();
		ss.updateValues();
		//UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		//camera.setResolution(640, 480);
		//camera.setFPS(60);        
		autonomousCommand.addParallel(new StartFlywheel());
		autonomousCommand.addSequential(new Wait(1));

		autonomousCommand.addParallel(new StartConveyor());
		autonomousCommand.addParallel(new RunHopper());

		autonomousCommand.addSequential(new Wait(4));
		
		autonomousCommand.addParallel(new StopConveyor());
		autonomousCommand.addParallel(new StopHopper());
		autonomousCommand.addParallel(new StopFlywheel());
		
		autonomousCommand.addSequential(new TurnToAngle(-120, .4, 0));
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
		//new SeizureMode().start();
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
		Robot.gearSS.extend();
		Robot.oi.smartDashboardUpdater.updateValues();
		Robot.states.resetStates();
		//Robot.driveSS.shifter.set(Value.kForward);
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
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
