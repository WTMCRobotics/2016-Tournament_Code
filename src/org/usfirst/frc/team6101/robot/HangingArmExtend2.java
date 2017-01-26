package org.usfirst.frc.team6101.robot;

import edu.wpi.first.wpilibj.VictorSP;

public class HangingArmExtend2 {
	private static VictorSP armoutMotor;
	
	public HangingArmExtend2() {
		armoutMotor = new VictorSP(3);
	}
	public HangingArmExtend2(VictorSP armout) {
		armoutMotor = armout;
	}
	
	public void updateHangingArmExtend2(boolean b) {
		double armoutSquared = 0.0;
		if (b == true) {
			armoutSquared = -1;
		} else {
			armoutSquared = 0;
		}
		
	}
}
