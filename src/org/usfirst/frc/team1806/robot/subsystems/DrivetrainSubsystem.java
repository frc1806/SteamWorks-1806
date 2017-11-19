package org.usfirst.frc.team1806.robot.subsystems;


import java.util.ArrayList;
import java.util.HashSet;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;
import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.RobotMap;
import org.usfirst.frc.team1806.robot.States;
import org.usfirst.frc.team1806.robot.States.Driving;
import org.usfirst.frc.team1806.robot.States.Shifter;
import org.usfirst.frc.team1806.robot.States.Shifter;
import org.usfirst.frc.team1806.robot.commands.drivetrain.Drive;
import org.usfirst.frc.team1806.robot.commands.sequences.SeizureMode;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DrivetrainSubsystem extends Subsystem {
	public VoltageLimiter rightMotor1;
	public VoltageLimiter leftMotor1;
	public AHRS navx;
	States states;
	public DoubleSolenoid shifter;
	public Encoder leftEncoder;
	public Encoder rightEncoder;
	PIDSource drivePS;
	PIDOutput drivePO;
	public static PIDController drivePos;
	public boolean isShimmy = false;
	public boolean isBoiler = false;
	public boolean isSeizureMode = false;
	public boolean isVision= false;
	public double maxSpeed = 1;
	public double oldAngle = 0;
	public double currentAngle = 0;
	public double lastKnownAngle = 0;
	private HashSet<Integer> rightSidePDP = new HashSet<Integer>() {{
		add(13);
		add(14);
		add(15);
	}};
	private HashSet<Integer> leftSidePDP = new HashSet<Integer>() {{
		add(0);
		add(1);
		add(2);
	}};
	public DrivetrainSubsystem(){
		rightMotor1 = new VoltageLimiter(RobotMap.rightMotor, rightSidePDP);
		leftMotor1 = new VoltageLimiter(RobotMap.leftMotor, leftSidePDP);
		rightMotor1.setMaxAmp(50);
		leftMotor1.setMaxAmp(50);
		leftEncoder = new Encoder(0, 1);
		rightEncoder = new Encoder(2,3);
		rightEncoder.setReverseDirection(true);
		leftMotor1.setInverted(true);
		rightEncoder.reset();
		leftEncoder.reset();
		leftEncoder.setDistancePerPulse(24);
		rightEncoder.setDistancePerPulse(24); //TODO Fix these values
		navx = new AHRS(SPI.Port.kMXP);
		shifter = new DoubleSolenoid(RobotMap.shiftLow, RobotMap.shiftHigh);

		
		drivePS = new PIDSource() {
			
			@Override 
			public void setPIDSourceType(PIDSourceType pidSource) {
				// TODO Auto-generated method stub
				setPIDSourceType(PIDSourceType.kDisplacement);
			}
			
			@Override
			public double pidGet() {
				// TODO use two 
				return Robot.driveSS.rightEncoder.getDistance();
			}
			
			@Override
			public PIDSourceType getPIDSourceType() {
				// TODO Auto-generated method stub
				return PIDSourceType.kDisplacement;
			}
		};
		drivePO = new PIDOutput() {
			
			@Override
			public void pidWrite(double output) {
				// TODO Auto-generated method stub
				if(Math.abs(output) > maxSpeed){
					output = maxSpeed * Math.signum(output);
				}
				execute(output, getYaw() * .05);
			}
		};
		drivePos = new PIDController(.03, 0, 0, drivePS, drivePO);
		drivePos.setContinuous(false);
		drivePos.setOutputRange(-1, 1);
		drivePos.setAbsoluteTolerance(48);
	}
	
	
	
	
	public void driveToPositionDisable(){
		drivePos.reset();
		drivePos.disable();
	}
	public void driveToPositionEnable(){
		drivePos.reset();
		drivePos.enable();
	}
	public void driveToSetpoint(double pos){
		drivePos.setSetpoint(pos);
	}
	public boolean isDriveOnTarget(){
		return Math.abs(drivePos.getError()) < 48;
	}

	public void execute(double power, double turn){
		arcadeDrive(power, turn);
	}
	public void leftDrive(double speed){
		leftMotor1.set(speed);
	}
	public void rightDrive(double speed){
		rightMotor1.set(speed);
	}
	public void arcadeDrive(double power, double turn){
		leftDrive(power + turn); //verify signs
		rightDrive(power - turn);
	}
	public void autoArcadeDrive(double power, double turn){
		leftDrive((power ) + turn); //verify signs
		rightDrive((power) - turn);
	}
	public void shiftHigh(){
		Robot.states.shifterTracker = Shifter.HIGH;
		shifter.set(Value.kReverse);
	}
	public void shiftLow() {
		Robot.states.shifterTracker = Shifter.LOW;
		shifter.set(Value.kForward);
	}
	public boolean isHigh(){
		//home boi u lit
		return states.shifterTracker == Shifter.HIGH;
	}
	public boolean isLow(){
		//sad pepe
		return states.shifterTracker == Shifter.LOW;
	}
	//------------NAVX --------------------
	   public void resetYaw(){
	    	navx.zeroYaw();
	    }
	    
	    public void resetNavx(){
	    	navx.reset();
	    }
	    
	    public boolean isNavxConnected(){
	    	return navx.isConnected();
	    }

	    
	    public double getTrueAngle(){
	    	return navx.getAngle();
	    }
	    
	    
	    public double getYaw(){
	    	return navx.getYaw();
	    }
	    
	    public double getPitch(){
	    	return navx.getPitch();
	    }
	    
	    public double getRoll(){
	    	return navx.getRoll();
	    }
	    
	    public double getRotationalSpeed(){
	    	return navx.getRate();
	    }
	    
	    public double getQuaternion(){
	    	return navx.getQuaternionZ() * 180;
	    }
	    
	    public double getTilt(){
	    	return Math.sqrt(Math.pow(navx.getPitch(), 2) + Math.pow(navx.getRoll(), 2));
	    }
	    public double getVisionAngle(){
	    	oldAngle = currentAngle; 
	    	currentAngle = Robot.networkTable.getNumber("angleFromGoal", 0);
	    	return currentAngle;
	    }
	    public double getVisionDistance(){
	    	return Robot.networkTable.getDouble("distanceFromTarget");
	    }
	    public double getBoilerAngle(){
	    	return Robot.boilerTable.getDouble("angleFromGoal", 0 );
	    }
	    public double[] returnCenterY(){
	    	return Robot.boilerTable.getNumberArray("centerY");
	    }
	    public double getLastKnownAngle(){
	    	if(currentAngle == 0 && oldAngle !=0) {
	    		lastKnownAngle = oldAngle;
	    	} else {
	    		System.out.println("you really jacked up here chris");
	    	}
	    	return lastKnownAngle;
	    }
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Drive());
	}
}
