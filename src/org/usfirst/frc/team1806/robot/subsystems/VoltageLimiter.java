package org.usfirst.frc.team1806.robot.subsystems;

import java.util.List;
import java.util.Set;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.usfirst.frc.team1806.robot.Robot;
import org.usfirst.frc.team1806.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;

public class VoltageLimiter extends Talon{
	private double maxAmp = 180;
	private double limpAmp = 40;
	private double limpVoltage = 8.5;
	private double throttleMultiplier = .8;
	private double limpMultiplier = .4;
	private Set<Integer> pdpChannels; 
	public VoltageLimiter(int kPwmChannels, Set<Integer> pdpChannels) {
		super(kPwmChannels);
		this.pdpChannels = pdpChannels;
	}
	
	@Override
	public void set(double speed){
		   super.set(regulatedOutput(speed));
	}
	public double getTotalAmps() {
		double totalAmps = 0;
		for(Integer pdpChannel:pdpChannels){
			totalAmps += Robot.pdPowerDistributionPanel.getCurrent(pdpChannel);
		}
		return totalAmps;
	}
	public boolean isOver(){
		if(getTotalAmps() > maxAmp) {
			return true;
		} else {
			return false;
		}
		 
	}
	public boolean needToLimp(){
		return Robot.pdPowerDistributionPanel.getVoltage() < limpVoltage && getTotalAmps() > limpAmp;
	}
	public double regulatedOutput(double speed){
		if(needToLimp()){
			System.out.println("LIMPING ");
			return (speed * (limpAmp / getTotalAmps()) * limpMultiplier);
		} else if(isOver()){
			System.out.println("ITS OVER THE LIMIT");
			return (speed * (maxAmp / getTotalAmps()) * throttleMultiplier);
		} else {
			return speed;
		}
	}
	public double getMaxAmp(){
		return maxAmp;
	}
	public void setMaxAmp(double wantedAmp){
		maxAmp = wantedAmp;
	}
}
