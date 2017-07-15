package org.usfirst.frc.team1806.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardUpdater {
	public void updateValues(){
		SmartDashboard.putString("Shooter Value", Robot.states.shootSpeedTracker.toString());
		SmartDashboard.putString("Driving Value", Robot.states.drivingTracker.toString());
		SmartDashboard.putString("Climber Value", Robot.states.climberTracker.toString());
		SmartDashboard.putString("Intake Value", Robot.states.intakeStatesTracker.toString());
		SmartDashboard.putString("Shifter Value", Robot.states.shifterTracker.toString());
		SmartDashboard.putString("Conveyor Value", Robot.states.conveyorTracker.toString());
		SmartDashboard.putString("Gear Value", Robot.states.gearTracker.toString());
		SmartDashboard.putDouble("Flywheel F", Robot.flywheelSS.flyWheel.getF());
		SmartDashboard.putDouble("Flywheel p", Robot.flywheelSS.flyWheel.getP());
		SmartDashboard.putDouble("Flywheel I", Robot.flywheelSS.flyWheel.getI());
		SmartDashboard.putDouble("Flywheel D", Robot.flywheelSS.flyWheel.getD());
		SmartDashboard.putDouble("Flywheel Power ", Robot.flywheelSS.getPower());
		SmartDashboard.putDouble("Left Motor Speed", Robot.driveSS.leftMotor1.get());
		SmartDashboard.putDouble("Right Motor Speed", Robot.driveSS.rightMotor1.get());
		SmartDashboard.putDouble("flyWheelSpeed", Robot.flywheelSS.flyWheel.getSpeed());
		SmartDashboard.putDouble("Current Fly Wheel Set Speed: ", Robot.oi.constants.camCoder);
		SmartDashboard.putDouble("Current Yaw: ", Robot.driveSS.navx.getYaw());
		SmartDashboard.putBoolean("Current Connection State ", Robot.driveSS.navx.isConnected());
		SmartDashboard.putInt("Left Encoder: ", Robot.driveSS.leftEncoder.get());
		SmartDashboard.putInt("Right Encoder: ", Robot.driveSS.rightEncoder.get());
		SmartDashboard.putBoolean("Is Seizuring: ", Robot.driveSS.isSeizureMode);
		SmartDashboard.putBoolean("Is Shimmy: ", Robot.driveSS.isShimmy);
		SmartDashboard.putBoolean("Is Aimed", Math.abs(Robot.flywheelSS.flyWheel.getSpeed()) < Constants.maxShooterRange && Math.abs(Robot.driveSS.getBoilerAngle()) > Constants.minShooterRange);
		
	}
}
