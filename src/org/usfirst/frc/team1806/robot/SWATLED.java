package org.usfirst.frc.team1806.robot;

import com.mindsensors.CANLight;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

public class SWATLED {
	CANLight light;
	DriverStation ds;
	public SWATLED(int CANPort, Alliance ds){
		light = new CANLight(CANPort);
		light.reset();
	}
	public void colorReset(){
		light.writeRegister(0, 1.5, 0, 255, 0);
		light.writeRegister(1, 1.5, 0, 150, 0);
		light.writeRegister(2, 1.5, 0, 100, 0);
		light.fade(0, 2);
	}
	public void showColor(int r, int g, int b){
		light.free();
		light.writeRegister(0, 1, r, g, b);
		light.showRegister(0);
	}
	public void flashColor(int oR, int oG, int oB, int lR, 
			int lG, int lB){
		// We cycle through two colors that the method provides
		light.free();
		light.writeRegister(0, .2, oR, oG, oB);
		light.writeRegister(1, .2, lR, lG, lB);
		light.cycle(0, 1);
	}
	public void defaultState() {
		teamColor();
	}
	public void teamColor(){
		if (ds.getAlliance() == DriverStation.Alliance.Red) {
			light.showRGB(255, 0, 0);
        } else if (ds.getAlliance() == DriverStation.Alliance.Blue) {
        	light.showRGB(0, 0, 255);
        } else if (ds.getAlliance() == DriverStation.Alliance.Invalid) {
        	light.showRGB(255, 200, 0); // yellow
        }
	}
	public void rainbowFade(){
		light.free();
		light.writeRegister(0, .2, 255,0,0);
		light.writeRegister(1, .2, 255,165,0);
		light.writeRegister(2, .2, 255,255,0);
		light.writeRegister(3, .2, 0,50,0);
		light.writeRegister(4, .2, 0,0,255);
		light.writeRegister(5, .2, 0,125,125);
		light.fade(0, 5);
	}
	public void firstFade(){
		light.writeRegister(1, 1.0, 255,   0,   0); // red
		light.writeRegister(2, 1.0, 255, 255, 255); // white
		light.writeRegister(3, 1.0,   0,   0, 255); // blue
		light.fade(1, 3);
	}
}
