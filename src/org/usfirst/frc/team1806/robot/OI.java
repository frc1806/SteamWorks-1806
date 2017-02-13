package org.usfirst.frc.team1806.robot;

import org.usfirst.frc.team1806.robot.utils.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;

import org.usfirst.frc.team1806.robot.States.Climber;
import org.usfirst.frc.team1806.robot.States.Conveyor;
import org.usfirst.frc.team1806.robot.States.ShootSpeed;
import org.usfirst.frc.team1806.robot.commands.ExampleCommand;
import org.usfirst.frc.team1806.robot.commands.climber.RunClimberAtSpeed;
import org.usfirst.frc.team1806.robot.commands.conveyor.MoveConveyor;
import org.usfirst.frc.team1806.robot.commands.conveyor.StartConveyor;
import org.usfirst.frc.team1806.robot.commands.conveyor.StopConveyor;
import org.usfirst.frc.team1806.robot.commands.flywheel.StartFlywheel;
import org.usfirst.frc.team1806.robot.commands.flywheel.StopFlywheel;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//ds is for driver ;)
	XboxController dc = new XboxController(0);
	XboxController oc = new XboxController(1);
	States states = new States();
	public double dlsY, drsX, dRT, dLT;
	boolean dB, dY, dRB, dLB, dStart, dPOVUp, dPOVDown, dPOVLeft, dPOVRight;
	public boolean dBack, dA, dX;

	double olsY, orsY, oRT, oLT;
	boolean oA, oB, oX, oY, oRB, oLB, oStart, oBack, oRsClick;
	public boolean oPOVUp, oPOVDown;
	public void updateStates(){
		// This is where the states get updated to run the commands u know
		if(dY){
			states.shootSpeedTracker = ShootSpeed.ATSPEED;
		} else {
			states.shootSpeedTracker = ShootSpeed.STOPPED;
		}
		
		if(dRB){
			states.conveyorTracker = Conveyor.RUNNING;
		} else {
			states.conveyorTracker = Conveyor.STOPPED;
		}
		
		if(dLT > .15){
			// TODO check this .15 value and make sure that's actually right
			states.climberTracker = Climber.RUNNINGATSPEED;
		}
		
	}
	public void updateCommands(){
		//This will be where the commands actually execute from the states
		if(states.shootSpeedTracker == ShootSpeed.ATSPEED){
			new StartFlywheel().start();
		} else if(states.shootSpeedTracker == ShootSpeed.STOPPED){
			new StopFlywheel().start();
		}
		if(states.conveyorTracker == Conveyor.RUNNING && states.shootSpeedTracker == ShootSpeed.ATSPEED){
			new StartConveyor().start();
		} else if(states.conveyorTracker == Conveyor.STOPPED){
			new StopConveyor().start();
		}
		if(states.climberTracker == Climber.RUNNINGATSPEED){
			new RunClimberAtSpeed(dLT);
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
