package org.usfirst.frc.team6101.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BreachingArm {
	private static VictorSP armMotor;
	public static DigitalInput upperLimit;
	public static DigitalInput lowerLimit;
	
	public BreachingArm() {
		armMotor = new VictorSP(4);
		upperLimit = new DigitalInput(1);
		lowerLimit = new DigitalInput(0);
	}
	
	public void updateBreachingArm(double armInput) {
		double armSquared = 0.0;
		//armInput -= 0.5;
		//armInput *= 2;
		
		if ((armInput > 0.1)&& upperLimit.get()) {
			armSquared = armInput * armInput;
		} else if ((armInput < -0.1)  && lowerLimit.get()) {
			armSquared = - (armInput * armInput);
		} else {
			armSquared = 0.0;
		}
		
		
		armMotor.set(armSquared);
	}
	
	// Converts Joystick rang (0.0 - 1.0) to motor range (-1.0 - 1.0)
	private double scaleJoystickValues(double rawJoystickInput) {
		rawJoystickInput -= 0.5;
		rawJoystickInput *= 2;
		
		return rawJoystickInput;
	}
	}