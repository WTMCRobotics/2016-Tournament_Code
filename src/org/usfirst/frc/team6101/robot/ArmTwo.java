package org.usfirst.frc.team6101.robot;

import edu.wpi.first.wpilibj.VictorSP;

public class ArmTwo {
private static VictorSP armMotor;
	
	public ArmTwo() {
		armMotor = new VictorSP(2);
	}
	public ArmTwo(VictorSP arm) {
		armMotor = arm;
	}
	
	public void updateArm(double armInput) {
		double armSquared = 0.0;
		if (armInput > 0) {
			armSquared = armInput * armInput;
		} else if (armInput < 0) {
			armSquared = - (armInput * armInput);
		} else {
			armSquared = 0.0;
		}
	}
}
