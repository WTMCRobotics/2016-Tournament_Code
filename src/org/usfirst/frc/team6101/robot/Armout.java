package org.usfirst.frc.team6101.robot;

import edu.wpi.first.wpilibj.VictorSP;
public class Armout {
	private static VictorSP armoutMotor;
	
	public Armout() {
		armoutMotor = new VictorSP(3);
	}
	public Armout(VictorSP armout) {
		armoutMotor = armout;
	}
	
	public void updateArmout(double b) {
		double armoutSquared = 0.0;
		if (b > 0) {
			armoutSquared = b * b;
		} else if (b < 0) {
			armoutSquared = - (b * b);
		} else {
			armoutSquared = 0.0;
		}
	}
}