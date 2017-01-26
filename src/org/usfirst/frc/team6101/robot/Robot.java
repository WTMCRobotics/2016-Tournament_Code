package org.usfirst.frc.team6101.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.buttons.*;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	RobotDrive myRobot;
	Joystick stick;
	Joystick stick2;
	Button button2;
	Button button3;
	VictorSP liftMotor;
	VictorSP leftMotor;
	VictorSP rightMotor;
	VictorSP armMotor; 
	VictorSP armoutMotor;
	VictorSP armtwoMotor;
	VictorSP lefttwoMotor;
	VictorSP righttwoMotor;
	Drivetrain drive;
	BreachingArm arm;
	//HangingArm armtwo;
	Timer timer;
	int autoLoopCounter;
	int autoMode;
	boolean autoTimerStarted;
	CameraServer camera = CameraServer.getInstance();
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	stick = new Joystick(0);
    	stick2 = new Joystick(1);
    	button2 = new JoystickButton(stick2, 2);
    	button3 = new JoystickButton(stick2, 3);
    	leftMotor = new VictorSP(0);
		rightMotor = new VictorSP(1);
		lefttwoMotor = new VictorSP(3);
		righttwoMotor = new VictorSP(2);
		//armMotor = new VictorSP(4);
    	drive = new Drivetrain(leftMotor, rightMotor, lefttwoMotor, righttwoMotor);
    	arm = new BreachingArm();
    	//armtwo = new HangingArm(armtwoMotor);
    	timer = new Timer();
    	
    	//default to 0 for safety
    	autoMode = 0;
    	
    	camera.setQuality(50);
    	camera.startAutomaticCapture();
    }

	/**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() {
    	autoLoopCounter = 0;
    	autoMode = 1;
    	autoTimerStarted = false;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    /*	if(autoLoopCounter < 100) //Check if we've completed 100 loops (approximately 2 seconds)
		{
			myRobot.drive(-0.5, 0.0); 	// drive forward half speed
			autoLoopCounter++;
			} else {
			myRobot.drive(0.0, 0.0); 	// stop robot
		}*/
    	
    	// Default to all motors off
    	double armSquared = 0.0;
    	double leftSquared = 0.0;
    	double rightSquared = 0.0;
    	
    	if(autoMode == 1) {	//low bar, arm down, go forward
    		if(arm.lowerLimit.get()) {	//if switch not pressed, lower arm
    			armSquared = -1.0;
    		} else {
    			armSquared = 0.0;
    		}
    		
    		if(!arm.lowerLimit.get()) {	//if switch is pressed, arm is down, turn on drive motors
    			double desiredMotorSpeed = .55;
				double driftFactor = 0.05;
				
				leftSquared = -desiredMotorSpeed;
				rightSquared = desiredMotorSpeed + driftFactor;
    		} else {
    			leftSquared = 0.0;
    			rightSquared = 0.0;
    		}
    	}
    	
    	if(autoMode == 2) {	// drive straight, rock wall, rough terrain
				if(!autoTimerStarted) {	//start timer if not started
					timer.reset();
					timer.start();
					autoTimerStarted = true;
				} else {	//if timer started, drive
					if(timer.get() <= 5.0) {
						double desiredMotorSpeed = .95;
						double driftFactor = 0.05;
						
						leftSquared = -desiredMotorSpeed;
						rightSquared = desiredMotorSpeed + driftFactor;
					}
				}
    	}
    	
    	if(autoMode == 3) { //Autonomous low bar, untested
    		if(arm.lowerLimit.get()) {	//if switch not pressed, lower arm
    			armSquared = -1.0;
    		} else {
    			armSquared = 0.0;
    		}
    		
    		if(!arm.lowerLimit.get()) {	//if switch is pressed, arm is down, turn on drive motors
    			// start timer
    			if(!autoTimerStarted) {	//if timer is not started, start it
    				timer.reset();
    		    	timer.start();
    		    	autoTimerStarted = true;
    			}
    			
    			SmartDashboard.putNumber("Timer", timer.get());
    			
    			if(timer.get() <= 3.0) {	//if less than 4 seconds, go forward
    				double desiredMotorSpeed = .55;
    				double driftFactor = 0.05;
    				
    				leftSquared = -desiredMotorSpeed;
    				rightSquared = desiredMotorSpeed + driftFactor;
    			} else if(timer.get() >= 3.0 && timer.get() <= 6.0) {	//if between 4.0 and 8.0 seconds, reverse
    				double desiredMotorSpeed = -.60;
    				double driftFactor = -0.05;
    				
    				leftSquared = -desiredMotorSpeed;
    				rightSquared = desiredMotorSpeed + driftFactor;
    			} else {	//if more than max time, stop
    				leftSquared = 0.0;
        			rightSquared = 0.0;
    			}
    			
    		} else {
    			leftSquared = 0.0;
    			rightSquared = 0.0;
    		}
    	}
    	
    	// update arm and drivetrain with desired values
    	arm.updateBreachingArm(armSquared);
    	drive.updateDrivetrain(rightSquared, leftSquared);
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit(){
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
       
        
        drive.updateDrivetrain(-stick.getRawAxis(1), stick.getRawAxis(5));
        arm.updateBreachingArm(stick2.getRawAxis(1));
        //armtwo.updateHangingArm(stick2.getRawAxis(3));
       
    
    	}
    }