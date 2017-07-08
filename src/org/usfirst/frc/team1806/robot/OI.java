package org.usfirst.frc.team1806.robot;

import org.usfirst.frc.team1806.robot.utils.CommandLatch;
import org.usfirst.frc.team1806.robot.utils.Latch;
import org.usfirst.frc.team1806.robot.utils.XboxController;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

import java.nio.file.StandardOpenOption;

import org.omg.CORBA.SystemException;
import org.usfirst.frc.team1806.robot.Commands.ClimberRequest;
import org.usfirst.frc.team1806.robot.Commands.ConveyorRequest;
import org.usfirst.frc.team1806.robot.Commands.DrivingRequest;
import org.usfirst.frc.team1806.robot.Commands.GearHolderRequest;
import org.usfirst.frc.team1806.robot.Commands.HopperRequest;
import org.usfirst.frc.team1806.robot.Commands.IntakeStatesRequest;
import org.usfirst.frc.team1806.robot.Commands.ShifterRequest;
import org.usfirst.frc.team1806.robot.Commands.ShootSpeedRequest;
import org.usfirst.frc.team1806.robot.States.Climber;
import org.usfirst.frc.team1806.robot.States.Conveyor;
import org.usfirst.frc.team1806.robot.States.Driving;
import org.usfirst.frc.team1806.robot.States.Shifter;
import org.usfirst.frc.team1806.robot.States.GearHolder;
import org.usfirst.frc.team1806.robot.States.Hopper;
import org.usfirst.frc.team1806.robot.States.IntakeStates;
import org.usfirst.frc.team1806.robot.States.ShootSpeed;
import org.usfirst.frc.team1806.robot.commands.ExampleCommand;
import org.usfirst.frc.team1806.robot.commands.VibrateForSeconds;
import org.usfirst.frc.team1806.robot.commands.VisionTeleOp;
import org.usfirst.frc.team1806.robot.commands.climber.RunClimberAtSpeed;
import org.usfirst.frc.team1806.robot.commands.climber.StopClimber;
import org.usfirst.frc.team1806.robot.commands.conveyor.StartConveyor;
import org.usfirst.frc.team1806.robot.commands.conveyor.StartConveyor;
import org.usfirst.frc.team1806.robot.commands.conveyor.StopConveyor;
import org.usfirst.frc.team1806.robot.commands.drivetrain.Creep;
import org.usfirst.frc.team1806.robot.commands.drivetrain.Drive;
import org.usfirst.frc.team1806.robot.commands.drivetrain.InverseDrive;
import org.usfirst.frc.team1806.robot.commands.drivetrain.ShiftLow;
import org.usfirst.frc.team1806.robot.commands.drivetrain.shiftHigh;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.BoilerTurnToAngle;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.RunDrive;
import org.usfirst.frc.team1806.robot.commands.drivetrain.auto.VisionDriveStraight;
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

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
	int bits;
	SmartDashboardUpdater smartDashboardUpdater = new SmartDashboardUpdater();
	// controller init
	Constants constants = new Constants();
	XboxController dc = new XboxController(0);
	XboxController oc = new XboxController(1);
	DigitalInput prox = new DigitalInput(4);
	Joystick stick = new Joystick(0);
	States states = new States();
	public double dlsY, drsX, dRT, dLT;
	boolean dB, dY, dRB, dLB, dStart, dPOVUp, dPOVDown, dPOVLeft, dPOVRight, dLClick, dRClick;
	public boolean dBack, dA, dX;
	
	double olsY, orsY, oRT, oLT;
	boolean oA, oB, oX, oY, oRB, oLB, oStart, oBack, oRsClick;
	public boolean oPOVUp, oPOVDown;
	public static boolean isInverse = false;
	double maxCurrent = 0;
	Commands requestCommands = new Commands();
	
	public Latch intakeLatch = new Latch();
	public Latch conveyorLatch = new Latch();
	public Latch gearHolderLatch = new Latch();
	public Latch shifterLatch = new Latch();
	public Latch shooterLatch = new Latch();			// This is making the latchs to update the states
	public Latch inverseLatch = new Latch();
	
	CommandLatch liftTracker = new CommandLatch();
	CommandLatch seizureLatch = new CommandLatch();
	CommandLatch bumpLatch = new CommandLatch();
	CommandLatch cameraLatch = new CommandLatch();
	CommandLatch proxLatch = new CommandLatch();
	public boolean seizureBoolean = false;
	public void update(){
		updateButtons();
		updateStates();
		updateCommands();
		
		if(requestCommands.drivingRequestTracker == DrivingRequest.DRIVING){
			new Drive().start(); //make the dude drive a wee bit
			Robot.driveSS.isSeizureMode = false;
			Robot.driveSS.isShimmy = false;
			Robot.driveSS.isVision = false;
		} else if(requestCommands.drivingRequestTracker == DrivingRequest.CREEP){
			if(Robot.states.drivingTracker != States.Driving.CREEP){
				new Creep().start();
			}
		} else if(requestCommands.drivingRequestTracker == DrivingRequest.SEIZURE){
			if(!Robot.driveSS.isSeizureMode){
				new SeizureMode().start();
			}
		} else if(requestCommands.drivingRequestTracker == DrivingRequest.SHIMMY){
			if(!Robot.driveSS.isShimmy){
				new Shimmy().start();
			} 
		} else if(requestCommands.drivingRequestTracker == DrivingRequest.VISION && dY){
			if(!Robot.driveSS.isVision){
				new VisionTeleOp(.3, Robot.driveSS.getVisionAngle(), 36).start();
			}
		} else if(requestCommands.drivingRequestTracker == DrivingRequest.VISION && dRClick){
				if(!Robot.driveSS.isVision){
					new BoilerTurnToAngle(100).start();
					System.out.println("turning");
				}
		} else if(requestCommands.drivingRequestTracker == DrivingRequest.INVERSE){
			if(Robot.states.drivingTracker != States.Driving.INVERSE){
				new InverseDrive().start();
			}
		}
		if(requestCommands.gearRequestTracker == GearHolderRequest.IN){
			new ExtendGear().start();
		} else {
			new RectractGear().start();
		}
	}
	public void updateStates(){
		System.out.println(prox.get());
		if(proxLatch.update(prox.get())){
			new VibrateForSeconds(2).start();
		}
		
		if(cameraLatch.update(dc.getPOV() == 0)){
			Robot.cameraSS.update();
		}
		if(shifterLatch.update(dLB)){
			requestCommands.shiferRequestTracker = ShifterRequest.HIGH;
		} else {
			requestCommands.shiferRequestTracker = ShifterRequest.LOW;
		}
		
		if(gearHolderLatch.update(dBack)){
			requestCommands.gearRequestTracker = GearHolderRequest.IN;
		} else {
			requestCommands.gearRequestTracker = GearHolderRequest.OUT;
		}
		
		if(dLT > .15){
			requestCommands.intakeRequestTracker = IntakeStatesRequest.INTAKE;
		} else {
			requestCommands.intakeRequestTracker = IntakeStatesRequest.STOPPED;
		}
		
		if(bumpLatch.update(oPOVUp)){
			constants.camCoder += 25;
		} else if(bumpLatch.update(oPOVDown)){
			constants.camCoder -= 25;
		}
		//-------------------- DRIVE STATES ---------------------------//
		if(dA){
			requestCommands.drivingRequestTracker = DrivingRequest.SEIZURE;
		} else if(dRB){
			requestCommands.drivingRequestTracker = DrivingRequest.SHIMMY;
		}else if(dY){
			requestCommands.drivingRequestTracker = DrivingRequest.VISION;
		}else if(dRT > .15){
			System.out.println("CREEPING");
			requestCommands.drivingRequestTracker = DrivingRequest.CREEP;
		} else if(dRClick){
			requestCommands.drivingRequestTracker = DrivingRequest.VISION;
		} else if(inverseLatch.update(dX)){
			requestCommands.drivingRequestTracker = DrivingRequest.INVERSE;
		} else {
			requestCommands.drivingRequestTracker = DrivingRequest.DRIVING;
		}		
		//-------------------- OPERATOR ---------------------------//
		

		if(oB){
			requestCommands.conveyorRequestTracker = ConveyorRequest.RUNNING;
			requestCommands.hopperRequestTracker = HopperRequest.RUNNING;
		} else {
			requestCommands.hopperRequestTracker = HopperRequest.STOPPED;
			requestCommands.conveyorRequestTracker = ConveyorRequest.STOPPED;
		}

		if(oLT > .15){
			requestCommands.climberRequestTracker = ClimberRequest.RUNNINGATSPEED;
		} else {
			requestCommands.climberRequestTracker = ClimberRequest.STOPPED;
		}
		
		
		if(shooterLatch.update(oStart)){
			requestCommands.shootSpeedRequestTracker = ShootSpeedRequest.RUNNING;
		} else {
			requestCommands.shootSpeedRequestTracker = ShootSpeedRequest.STOPPED;
		}
	}
	public void updateCommands(){

		//This will be where the commands actually execute from the states
		if(requestCommands.shootSpeedRequestTracker == ShootSpeedRequest.RUNNING){
				//TODO Fix this
				Robot.flywheelSS.setShooterRPM(constants.camCoder);
		} else if(requestCommands.shootSpeedRequestTracker == ShootSpeedRequest.STOPPED) {
			new StopFlywheel().start();
		}
		if(requestCommands.gearRequestTracker == GearHolderRequest.IN){
			new RectractGear().start();
		} else if(requestCommands.gearRequestTracker == GearHolderRequest.OUT){
			new ExtendGear().start();
		}
//		
		if(requestCommands.conveyorRequestTracker == ConveyorRequest.RUNNING && requestCommands.hopperRequestTracker == HopperRequest.RUNNING){
			new StartConveyor().start();
			new RunHopper().start();
		} else if(requestCommands.conveyorRequestTracker == ConveyorRequest.STOPPED && requestCommands.hopperRequestTracker == HopperRequest.STOPPED){
			new StopConveyor().start();
			new StopHopper().start();
		}
		
		
		if(requestCommands.climberRequestTracker == ClimberRequest.RUNNINGATSPEED){
			new RunClimberAtSpeed(oLT).start();
		} else if(oA){
			new RunClimberAtSpeed(.35).start(); //TODO: Change this back
		} else if(oX){
			new RunClimberAtSpeed(.15).start();
		}else {
			new StopClimber().start();
		}
		if(requestCommands.shiferRequestTracker == ShifterRequest.HIGH){
			Robot.driveSS.shiftHigh();
		} else if(requestCommands.shiferRequestTracker == ShifterRequest.LOW) {
			Robot.driveSS.shiftLow();
		}
		if(requestCommands.intakeRequestTracker == IntakeStatesRequest.INTAKE ){
			if(Robot.states.intakeStatesTracker != IntakeStates.INTAKE){
				new StartIntake().start();
			}
		} else{
			new StopIntake().start();
		}

	}
	public void updateButtons(){
		//so this is pretty much where the buttons on the xbox controller get updated in the code
			// driver buttons
			dlsY = dc.getLeftJoyY();
			drsX = dc.getRightJoyX();
			dRT = dc.getRightTrigger();
			dLT = dc.getLeftTrigger();

			dA = dc.getButtonA();
			dB = dc.getButtonB();
			dX = dc.getButtonX();
			dY = dc.getButtonY();
			dRB = dc.getButtonRB();
			dLB = dc.getButtonLB();
			dStart = dc.getButtonStart();
			dBack = dc.getButtonBack();
			dPOVUp = dc.getPOVUp();
			dPOVDown = dc.getPOVDown();
			dPOVLeft = dc.getPOVLeft();
			dPOVRight = dc.getPOVRight();
			dLClick = dc.getButtonLS();
			dRClick = dc.getButtonRS();
			// operator buttons
			olsY = oc.getLeftJoyY();
			orsY = oc.getRightJoyY();
			oRT = oc.getRightTrigger();
			oLT = oc.getLeftTrigger();

			oA = oc.getButtonA();
			oB = oc.getButtonB();
			oX = oc.getButtonX();
			oY = oc.getButtonY();
			oRB = oc.getButtonRB();
			oLB = oc.getButtonLB();
			oStart = oc.getButtonStart();
			oBack = oc.getButtonBack();
			oRsClick = oc.getButtonRS();
			oPOVUp = oc.getPOVUp();
			oPOVDown = oc.getPOVDown();

		}
	public void setDriverRumble(){
		dc.setRumble(RumbleType.kLeftRumble, 1);
		dc.setRumble(RumbleType.kRightRumble, 1);
		//egg head vibrator
	}
	public void setOperatorRumble(){
		dc.setRumble(RumbleType.kLeftRumble, 1);
		dc.setRumble(RumbleType.kRightRumble, 1);
	}
	public void stopRumble(){
		dc.setRumble(RumbleType.kLeftRumble, 0);
		dc.setRumble(RumbleType.kRightRumble, 0);
		
		oc.setRumble(RumbleType.kLeftRumble, 0);
		oc.setRumble(RumbleType.kRightRumble, 0);
	}
}
