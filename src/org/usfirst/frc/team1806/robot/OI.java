package org.usfirst.frc.team1806.robot;

import org.usfirst.frc.team1806.robot.utils.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;

import org.usfirst.frc.team1806.robot.commands.ExampleCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//ds is for driver ;)
	XboxController dc = new XboxController(0);
	XboxController oc = new XboxController(1);
	public double dlsY, drsX, dRT, dLT;
	boolean dB, dY, dRB, dLB, dStart, dPOVUp, dPOVDown, dPOVLeft, dPOVRight;
	public boolean dBack, dA, dX;

	double olsY, orsY, oRT, oLT;
	boolean oA, oB, oX, oY, oRB, oLB, oStart, oBack, oRsClick;
	public boolean oPOVUp, oPOVDown;
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
	}
	public void stopRumble(){
		dc.setRumble(RumbleType.kLeftRumble, 0);
		dc.setRumble(RumbleType.kRightRumble, 0);
		
		oc.setRumble(RumbleType.kLeftRumble, 0);
		oc.setRumble(RumbleType.kRightRumble, 0);
	}
}
