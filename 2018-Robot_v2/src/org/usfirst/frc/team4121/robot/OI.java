package org.usfirst.frc.team4121.robot;

import org.usfirst.frc.team4121.robot.commands.AbortAutoDriveToCube;
import org.usfirst.frc.team4121.robot.commands.AngleMotorCommand;
import org.usfirst.frc.team4121.robot.commands.AngleMotorReverseCommand;
import org.usfirst.frc.team4121.robot.commands.AutoAngleMotorCommand;
import org.usfirst.frc.team4121.robot.commands.AutoPickUpCubeCommandGroup;
import org.usfirst.frc.team4121.robot.commands.BeginningMatchCommandGroup;
import org.usfirst.frc.team4121.robot.commands.ClimbCommand;
import org.usfirst.frc.team4121.robot.commands.ClimbReverseCommand;
import org.usfirst.frc.team4121.robot.commands.ClosedArmsCommand;
import org.usfirst.frc.team4121.robot.commands.EjectCubeCommandGroup;
import org.usfirst.frc.team4121.robot.commands.ElevatorToHomeCommand;
import org.usfirst.frc.team4121.robot.commands.ElevatorToPyramid;
import org.usfirst.frc.team4121.robot.commands.ElevatorToScaleCommand;
import org.usfirst.frc.team4121.robot.commands.ElevatorToSwitchCommand;
import org.usfirst.frc.team4121.robot.commands.OpenArmsCommand;
import org.usfirst.frc.team4121.robot.commands.ShiftDownCommand;
import org.usfirst.frc.team4121.robot.commands.ShiftUpCommand;
import org.usfirst.frc.team4121.robot.commands.StopClimbCommand;
import org.usfirst.frc.team4121.robot.commands.SwitchDriveCommand;
import org.usfirst.frc.team4121.robot.commands.TakeInCubeCommandGroup;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This is the main class that initializes and tells what buttons to do what.
 * 
 * @author Saliva Crustyman
 */
public class OI {
	
	//General Declarations of Objects
	public Joystick leftJoy, rightJoy;
	public XboxController xbox;
	public DigitalInput limitSwitchClimbBottomStop,limitSwitchClimbUpperStop;
	//public ADXRS450_Gyro MainGyro;
	public Encoder rightEncoder, leftEncoder;
	public Button climb, reverseClimb, servo, shiftUp, shiftDown, switchDrive;
	public Button elevatorHome, elevatorSwitch, elevatorScale, bumpUp, bumpDown, elevatorPyramid,autoDriveToCube, abortAutoDriveToCube, endAngleMotor;
	public Button takeInCube, spinWheelsOut, takeInCubeJoy, spinWheelsOutJoy, autoTesterEndAngleMotor;
	public Button openGrabber, closeGrabber, openGrabberJoy, closeGrabberJoy;
	public Button openServo, closeServo, beginMatch;
	
	public OI() {
	
		//Limit Switch Initializations
		limitSwitchClimbBottomStop = new DigitalInput(0);  
		limitSwitchClimbUpperStop = new DigitalInput(1);		
		
		//Initialize gyro
		//MainGyro = new ADXRS450_Gyro();
		
		//Define controllers , values can be moved on smart dashboard
		leftJoy = new Joystick(0);
		rightJoy = new Joystick(1);
		xbox = new XboxController(2);
		
		//Left joystick buttons
		shiftDown = new JoystickButton(leftJoy, 4);
		shiftUp = new JoystickButton(leftJoy, 5); //top left trigger
		openGrabberJoy = new JoystickButton (leftJoy, 3); //back button  
		closeGrabberJoy = new JoystickButton(leftJoy, 2); //start button 
		abortAutoDriveToCube = new JoystickButton(leftJoy, 1); 
		
		//Right joystick buttons
		//openServo = new JoystickButton(rightJoy, 10); 
		//closeServo = new JoystickButton(rightJoy, 11);
		beginMatch = new JoystickButton(rightJoy, 7);
		takeInCubeJoy = new JoystickButton(rightJoy, 3); //top right trigger
		spinWheelsOutJoy = new JoystickButton(rightJoy, 2);
		switchDrive = new JoystickButton(rightJoy, 4);
		autoDriveToCube = new JoystickButton(rightJoy, 1);
		endAngleMotor = new JoystickButton(rightJoy, 5);
		autoTesterEndAngleMotor = new JoystickButton(rightJoy, 7);
			
		//Xbox controller buttons
		elevatorHome = new JoystickButton(xbox, 1); //a button
		elevatorSwitch = new JoystickButton(xbox, 2); //b button
		elevatorPyramid = new JoystickButton(xbox, 3);
		elevatorScale = new JoystickButton (xbox, 4); //y button
		takeInCube = new JoystickButton(xbox, 5); //top right trigger
		spinWheelsOut = new JoystickButton(xbox, 6); //top left trigger
		openGrabber = new JoystickButton (xbox, 7); //back button
		closeGrabber = new JoystickButton(xbox, 8); //start button
		//bumpUp = new JoystickButton(xbox, 9); //left mini joystick
		//bumpDown = new JoystickButton(xbox, 10); //right mini joystick
		climb = new JoystickButton(xbox, 9);
		reverseClimb = new JoystickButton(xbox, 10);
		
		//define left joystick button commands
		shiftUp.whenActive(new ShiftUpCommand());
		shiftDown.whenActive(new ShiftDownCommand());
		openGrabberJoy.whenPressed(new OpenArmsCommand());
		closeGrabberJoy.whenPressed(new ClosedArmsCommand());
		abortAutoDriveToCube.whenPressed(new AbortAutoDriveToCube());
		
		//define right joystick button commands
		beginMatch.whenPressed(new BeginningMatchCommandGroup());
		takeInCubeJoy.whenPressed(new TakeInCubeCommandGroup());
		spinWheelsOutJoy.whenPressed(new EjectCubeCommandGroup());
		switchDrive.whenPressed(new SwitchDriveCommand());
		autoDriveToCube.whenPressed(new AutoPickUpCubeCommandGroup());
		endAngleMotor.whileHeld(new AngleMotorCommand());
		autoTesterEndAngleMotor.whenPressed(new AutoAngleMotorCommand());
		
		//openServo.whenPressed(new OpenServoCommand());
		//closeServo.whenPressed(new CloseServo());
		
		//define xbox button commands
		climb.whileHeld(new ClimbCommand());
		climb.whenReleased(new StopClimbCommand());
		reverseClimb.whileHeld(new ClimbReverseCommand());
		reverseClimb.whenReleased(new StopClimbCommand());
		elevatorSwitch.whenPressed(new ElevatorToSwitchCommand());
		elevatorScale.whenPressed(new ElevatorToScaleCommand());
		elevatorHome.whenPressed(new ElevatorToHomeCommand());
		elevatorPyramid.whenPressed(new ElevatorToPyramid());
		takeInCube.whenPressed(new TakeInCubeCommandGroup());
		spinWheelsOut.whenPressed(new EjectCubeCommandGroup());
		openGrabber.whenPressed(new OpenArmsCommand());
		closeGrabber.whenPressed(new ClosedArmsCommand());
		
		//bumpUp.whenPressed(new BumpElevatorUpCommand());
		//bumpDown.whenPressed(new BumpElevatorDownCommand());
		
	}
}
