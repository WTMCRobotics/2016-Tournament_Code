package org.usfirst.frc.team6101.robot;

import edu.wpi.first.wpilibj.VictorSP;

public class HangingArm {
	private static VictorSP armtwoMotor;
	
	public HangingArm() {
		armtwoMotor = new VictorSP(4);
	}
	public HangingArm(VictorSP armtwo) {
		armtwoMotor = armtwo;
	}
	
	public void updateHangingArm(double armtwoInput) {
		double armtwoSquared = 0.0;
		if (armtwoInput > 0) {
			armtwoSquared = armtwoInput * armtwoInput;
		} else if (armtwoInput < 0) {
			armtwoSquared = - (armtwoInput * armtwoInput);
		} else {
			armtwoSquared = 0.0;
		}
		armtwoMotor.set(armtwoSquared);
	}
}