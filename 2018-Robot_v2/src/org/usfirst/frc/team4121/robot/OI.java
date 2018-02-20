package org.usfirst.frc.team4121.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4121.robot.commands.ClimbCommand;
import org.usfirst.frc.team4121.robot.commands.ClimbReverseCommand;
import org.usfirst.frc.team4121.robot.commands.PraticeEncoders;
import org.usfirst.frc.team4121.robot.commands.ShiftDownCommand;
import org.usfirst.frc.team4121.robot.commands.ShiftUpCommand;
import org.usfirst.frc.team4121.robot.commands.SpinWheelsInCommand;
import org.usfirst.frc.team4121.robot.commands.SpinWheelsOutCommand;
import org.usfirst.frc.team4121.robot.commands.StopClimbCommand;
import org.usfirst.frc.team4121.robot.commands.SwitchDriveCommand;
import org.usfirst.frc.team4121.robot.commands.TakeInCubeCommandGroup;
import org.usfirst.frc.team4121.robot.commands.ClosedArmsCommand;
import org.usfirst.frc.team4121.robot.commands.EjectCubeCommandGroup;
import org.usfirst.frc.team4121.robot.commands.ElevatorToHomeCommand;
import org.usfirst.frc.team4121.robot.commands.ElevatorToScaleCommand;
import org.usfirst.frc.team4121.robot.commands.ElevatorToSwitchCommand;
import org.usfirst.frc.team4121.robot.commands.OpenArmsCommand;
import org.usfirst.frc.team4121.robot.subsystems.DriveTrainSubsystem;


/**
 * This is the main class that initializes and tells what buttons to do what.
 * 
 * @author Saliva Crustyman
 */
public class OI {
	
	//Initializations
	public Joystick leftJoy, rightJoy;
	public XboxController xbox;
	public DigitalInput limitSwitchClimbBottomStop,limitSwitchClimbUpperStop;
	public ADXRS450_Gyro MainGyro;
	public Encoder rightEncoder, leftEncoder;
	public Button shoot, feed, climb, reverseClimb, servo, shiftUp, shiftDown, gear, boiler, switchDrive, increaseShootSpeed, decreaseShootSpeed;
	public Button elevatorHome, elevatorSwitch, elevatorScale, practiceEncoder;
	public Button takeInCube, spinWheelsOut;
	public Button openGrabber, closeGrabber;
	//public static final int kXboxPort = 2;
	// public static final Button elevatorHome = new Button (A);
	
	public OI() {
	
		//Limit Switch
		limitSwitchClimbBottomStop = new DigitalInput(0);  
		limitSwitchClimbUpperStop = new DigitalInput(1);
		//limitSwitchClimb = new DigitalInput(5);
		
		
		//Define gyro
		MainGyro = new ADXRS450_Gyro();
		
		
		//Define controllers
		leftJoy = new Joystick(2);
		rightJoy = new Joystick(0);
		xbox = new XboxController(1);
		
		
		//Left joystick buttons
		elevatorHome = new JoystickButton (leftJoy, 1);
		elevatorScale = new JoystickButton (leftJoy, 2);
		elevatorSwitch = new JoystickButton(leftJoy, 3);		
		shiftDown = new JoystickButton(leftJoy, 4);
		shiftUp = new JoystickButton(leftJoy, 5);
		

		//Right joytick buttons
		climb = new JoystickButton(rightJoy, 3);
		reverseClimb = new JoystickButton(rightJoy, 2);
		//spinWheelsOut = new JoystickButton(rightJoy, 2);
		//takeInCube = new JoystickButton(rightJoy, 3);
		//openGrabber = new JoystickButton (rightJoy, 4);
		//closeGrabber = new JoystickButton(rightJoy, 5);
		//spinWheelsOut = new JoystickButton(rightJoy, 6);
		
		
			
		//Xbox controller buttons
		
//        elevatorHome = new JoystickButton(xbox, 1); //a button
//		elevatorSwitch = new JoystickButton(xbox, 2); //b button	
//		elevatorScale = new JoystickButton (xbox, 3); //x button
		spinWheelsOut = new JoystickButton(xbox, 1); //y  button
		openGrabber = new JoystickButton (xbox, 2); //top left trigger
		takeInCube = new JoystickButton(xbox, 3); //top right trigger
		closeGrabber = new JoystickButton(xbox, 4);
		
		
		//Define commands for buttons
		shiftUp.whenActive(new ShiftUpCommand());
		shiftDown.whenActive(new ShiftDownCommand());
		openGrabber.whenPressed(new OpenArmsCommand());
		closeGrabber.whenPressed(new ClosedArmsCommand());
		elevatorSwitch.whenPressed(new ElevatorToSwitchCommand());
		elevatorScale.whenPressed(new ElevatorToScaleCommand());
		elevatorHome.whenPressed(new ElevatorToHomeCommand());
		takeInCube.whenPressed(new TakeInCubeCommandGroup());
		spinWheelsOut.whenPressed(new EjectCubeCommandGroup());
		climb.whileHeld(new ClimbCommand());
		climb.whenReleased(new StopClimbCommand () );
		reverseClimb.whileHeld(new ClimbReverseCommand());
		reverseClimb.whenReleased(new StopClimbCommand());
		
		
		//		shoot = new JoystickButton(rightJoy, 1);
//		switchDrive = new JoystickButton(leftJoy, 2);
		
		//climb = new JoystickButton(leftJoy, 3);
		//reverseClimb = new JoystickButton (leftJoy, 5);
//		
//		gear = new JoystickButton(leftJoy, 2);
//		boiler = new JoystickButton(leftJoy, 3);

// 
////		

	//	practiceEncoders.whenPressed(new PraticeEncoders());
		//switchDrive.whenPressed(new SwitchDriveCommand());
//		takeInCube.whenPressed(new TakeInCubeCommandGroup());
//		spinWheelsOut.whileHeld(new SpinWheelsOutCommand());
		
		
		
		//xbox buttons
//		if(xbox.getAButtonPressed())
//		{
//			SmartDashboard.putString("A Button Pressed", "true");
//			 new ElevatorToHomeCommand();
//		}
//		if(xbox.getBButtonPressed())
//		{
//			SmartDashboard.putString("B Button Pressed", "true");
//			new ElevatorToSwitchCommand();
//		
//		}
//		if (xbox.getYButtonPressed())
//		{
//			SmartDashboard.putString("Y Button Pressed", "true");
//			new ElevatorToScaleCommand();
//			
//		}
		
//		//Commands
//		//servo.whileHeld(new OpenGateCommand());
//		//servo.whenReleased(new CloseGateCommand());
//		//feed.whileHeld(new FeedCommand());
//		//climb.whileHeld(new ClimbCommand());
//		//climb.whenReleased(new StopClimbCommand());
//		//gear.whenPressed(new FindGearTargetCommand());
//		//boiler.whenPressed(new FindBoilerTargetCommand());
////		switchDrive.whenPressed(new SwitchDriveCommand());
//		
//		//switchDrivexbox.whenPressed(new SwitchDriveCommand());
//		
//		//decreaseShootSpeed.whenPressed(new DecreaseShootSpeedCommand());
//		//increaseShootSpeed.whenPressed(new IncreaseShootSpeedCommand());
		
	}
}
