package org.usfirst.frc.team6101.robot;
import edu.wpi.first.wpilibj.VictorSP;

public class Drivetrain {
	private static VictorSP leftMotor;
	private static VictorSP rightMotor;
	private static VictorSP lefttwoMotor;
	private static VictorSP righttwoMotor;
	
	public Drivetrain() {
		leftMotor = new VictorSP(0);
		rightMotor = new VictorSP(1);
		lefttwoMotor = new VictorSP(3);
		righttwoMotor = new VictorSP(2);
	}
	
	public Drivetrain(VictorSP left, VictorSP right, VictorSP lefttwo, VictorSP righttwo) {
		leftMotor = left;
		rightMotor = right;
		lefttwoMotor = lefttwo;
		righttwoMotor = righttwo;
	}
	
	public void updateDrivetrain(double leftInput, double rightInput) {
		double leftSquared = 0.0;
		double rightSquared = 0.0;
		if (leftInput < 0) {
			leftSquared = - (leftInput * leftInput);
		} else if (leftInput > 0) {
			leftSquared = leftInput * leftInput;
		} else {
			leftSquared = 0.0;
		}
		if (rightInput < 0) {
			rightSquared = - (rightInput * rightInput);
		} else if (rightInput > 0) {
			rightSquared = rightInput * rightInput;
		} else {
			rightSquared = 0.0;
		}
		leftMotor.set(leftSquared);
		rightMotor.set(rightSquared);
		lefttwoMotor.set(rightSquared);
		righttwoMotor.set(leftSquared);
	}
}