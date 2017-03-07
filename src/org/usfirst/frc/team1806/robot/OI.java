package org.usfirst.frc.team1806.robot;

import org.usfirst.frc.team1806.robot.utils.CommandLatch;
import org.usfirst.frc.team1806.robot.utils.Latch;
import org.usfirst.frc.team1806.robot.utils.XboxController;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import java.nio.file.StandardOpenOption;

import org.usfirst.frc.team1806.robot.States.Climber;
import org.usfirst.frc.team1806.robot.States.Conveyor;
import org.usfirst.frc.team1806.robot.States.Driving;
import org.usfirst.frc.team1806.robot.States.Shifter;
import org.usfirst.frc.team1806.robot.States.GearHolder;
import org.usfirst.frc.team1806.robot.States.Hopper;
import org.usfirst.frc.team1806.robot.States.IntakeStates;
import org.usfirst.frc.team1806.robot.States.ShootSpeed;
import org.usfirst.frc.team1806.robot.commands.ExampleCommand;
import org.usfirst.frc.team1806.robot.commands.climber.RunClimberAtSpeed;
import org.usfirst.frc.team1806.robot.commands.climber.StopClimber;
import org.usfirst.frc.team1806.robot.commands.conveyor.StartConveyor;
import org.usfirst.frc.team1806.robot.commands.conveyor.StartConveyor;
import org.usfirst.frc.team1806.robot.commands.conveyor.StopConveyor;
import org.usfirst.frc.team1806.robot.commands.drivetrain.Creep;
import org.usfirst.frc.team1806.robot.commands.drivetrain.Drive;
import org.usfirst.frc.team1806.robot.commands.drivetrain.RunDrive;
import org.usfirst.frc.team1806.robot.commands.drivetrain.ShiftLow;
import org.usfirst.frc.team1806.robot.commands.drivetrain.shiftHigh;
import org.usfirst.frc.team1806.robot.commands.flywheel.StartFlywheel;
import org.usfirst.frc.team1806.robot.commands.flywheel.StopFlywheel;
import org.usfirst.frc.team1806.robot.commands.gear.ExtendGear;
import org.usfirst.frc.team1806.robot.commands.gear.RectractGear;
import org.usfirst.frc.team1806.robot.commands.hopper.RunHopper;
import org.usfirst.frc.team1806.robot.commands.hopper.StopHopper;
import org.usfirst.frc.team1806.robot.commands.intake.StartIntake;
import org.usfirst.frc.team1806.robot.commands.intake.StopIntake;
import org.usfirst.frc.team1806.robot.commands.sequences.SeizureMode;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
	SmartDashboardUpdater smartDashboardUpdater = new SmartDashboardUpdater();
	// controller init
	Constants constants = new Constants();
	XboxController dc = new XboxController(0);
	XboxController oc = new XboxController(1);
	Joystick stick = new Joystick(0);
	Button dAB = new JoystickButton(stick, 1);
	States states = new States();
	public double dlsY, drsX, dRT, dLT;
	boolean dB, dY, dRB, dLB, dStart, dPOVUp, dPOVDown, dPOVLeft, dPOVRight, dLClick;
	public boolean dBack, dA, dX;

	double olsY, orsY, oRT, oLT;
	boolean oA, oB, oX, oY, oRB, oLB, oStart, oBack, oRsClick;
	public boolean oPOVUp, oPOVDown;
	double maxCurrent = 0;
	Latch intakeLatch = new Latch();
	Latch shooterLatch = new Latch();			// This is making the latchs to update the states
	Latch conveyorLatch = new Latch();
	Latch gearHolderLatch = new Latch();
	Latch shifterLatch = new Latch();
	CommandLatch seizureLatch = new CommandLatch();
	public SeizureMode seizure = new SeizureMode();
	public boolean seizureBoolean = false;
	public void update(){
		updateButtons();
		updateStates();
		updateCommands();
		if(Robot.states.drivingTracker == Driving.DRIVING && !Robot.driveSS.isSeizureMode){
			new Drive().start(); //make the dude drive a wee bit
		} else if(Robot.states.drivingTracker == Driving.CREEP){
			new Creep().start();
		} else if(Robot.states.drivingTracker == Driving.SEIZURE){
			new SeizureMode().start();
		}
		smartDashboardUpdater.updateValues();
	}
	public void updateStates(){
		if(shifterLatch.update(dLB)){
			states.shifterTracker = Shifter.HIGH;
		} else {
			states.shifterTracker = Shifter.LOW;
		}
		if(gearHolderLatch.update(dBack)){
			states.gearTracker = GearHolder.OUT;
		} else {
			states.gearTracker = GearHolder.IN;
		}
		if(intakeLatch.update(dX)){
			states.intakeStatesTracker = IntakeStates.INTAKE;
		} else {
			states.intakeStatesTracker = IntakeStates.STOPPED;
		}
		if(shooterLatch.update(dY)){
			states.shootSpeedTracker = ShootSpeed.RUNNING;
		} else {
			states.shootSpeedTracker = ShootSpeed.STOPPED;
		}
		
		if(dRB){
			states.conveyorTracker = Conveyor.RUNNING;
			states.hopperTracker = Hopper.RUNNING;
		} else {
			states.hopperTracker = Hopper.STOPPED;
			states.conveyorTracker = Conveyor.STOPPED;
		}
		//System.out.println(Robot.driveSS.leftMotor1.get());
		if(dLClick){
			Robot.states.drivingTracker = Driving.CREEP;
		} else {
			Robot.states.drivingTracker = Driving.DRIVING;
		}
		if(dLT > .15){
			// TODO check this .15 value and make sure that's actually right
			states.climberTracker = Climber.RUNNINGATSPEED;
		}
		/*
		if(dB){
			constants.camCoder += 25;
		} else if(dA){
			constants.camCoder -= 25;
		} */
		
		if(seizureLatch.update(dA)){
			Robot.states.drivingTracker = Driving.SEIZURE;
		} else {
			Robot.states.drivingTracker = Driving.DRIVING;
		}		
		
	}
	public void updateCommands(){
		//This will be where the commands actually execute from the states
		//dAB.whenActive(seizure);
		if(states.shootSpeedTracker == ShootSpeed.RUNNING){
			Robot.flywheelSS.setToShootingSpeed();
		} else {
			Robot.flywheelSS.stopFlyWheel();
		}
		if(states.gearTracker == GearHolder.IN){
			new RectractGear().start();
		} else if(states.gearTracker == GearHolder.OUT){
			new ExtendGear().start();
		}
		if(states.conveyorTracker == Conveyor.RUNNING && states.hopperTracker == Hopper.RUNNING){
			new StartConveyor().start();
			new RunHopper().start();
		} else if(states.conveyorTracker == Conveyor.STOPPED && states.hopperTracker == Hopper.STOPPED ){
			new StopConveyor().start();
			new StopHopper().start();
		}
		
		if(states.climberTracker == Climber.RUNNINGATSPEED){
			new RunClimberAtSpeed(dLT).start();
		} else {
			new StopClimber().start();
		}
		if(states.shifterTracker == Shifter.HIGH){
			Robot.states.shifterTracker = Shifter.HIGH;
			Robot.driveSS.shifter.set(Value.kForward);
		} else if(states.shifterTracker == Shifter.LOW) {
			Robot.states.shifterTracker = Shifter.LOW;
			Robot.driveSS.shifter.set(Value.kReverse);
		}
		if(states.intakeStatesTracker == IntakeStates.INTAKE && states.gearTracker == GearHolder.OUT){
			Robot.intakeSS.intakeMotor.set(.8);
			Robot.states.intakeStatesTracker = IntakeStates.INTAKE;
			System.out.println(Robot.intakeSS.intakeMotor.get());
		} else{
			Robot.intakeSS.intakeMotor.set(0);
			Robot.states.intakeStatesTracker = IntakeStates.STOPPED;
		}
		if(maxCurrent < Robot.hopperSS.returnCurrentDraw()){
			maxCurrent = Robot.hopperSS.returnCurrentDraw();
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
