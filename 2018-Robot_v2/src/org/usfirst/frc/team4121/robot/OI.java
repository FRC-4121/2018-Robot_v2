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
import org.usfirst.frc.team4121.robot.commands.StopClimbCommand;
import org.usfirst.frc.team4121.robot.commands.SwitchDriveCommand;
import org.usfirst.frc.team4121.robot.commands.ClosedArmsCommand;
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
	//public XboxController xbox;
	//public DigitalInput limitSwitchEnd, limitSwitchClimb;
	public ADXRS450_Gyro MainGyro;
	public Encoder rightEncoder, leftEncoder;
	public Button shoot, feed, climb, reverseClimb, servo, shiftUp, shiftDown, gear, boiler, switchDrive, increaseShootSpeed, decreaseShootSpeed;
	public Button elevatorHome, elevatorSwitch, elevatorScale, practiceEncoder;
	public Button practiceEncoders;
	public Button openGrabber, closeGrabber;
	//public static final int kXboxPort = 2;
	// public static final Button elevatorHome = new Button (A);
	
	public OI() {
	
		//Limit Switch
		//limitSwitchEnd = new DigitalInput(4);  
		//limitSwitchClimb = new DigitalInput(5);
		
		//Gyro
		MainGyro = new ADXRS450_Gyro();
				
		//Joysticks
		leftJoy = new Joystick(1);
		rightJoy = new Joystick(0);
		//xbox = new XboxController(kXboxPort); //can change later
		
//		//Buttons
		
//		shoot = new JoystickButton(rightJoy, 1);
		shiftDown = new JoystickButton(rightJoy, 4);
		//shiftUp = new JoystickButton(rightJoy, 5);
		//switchDrive = new JoystickButton(leftJoy, 2);
//		openGrabber = new JoystickButton (leftJoy, 4);
//		closeGrabber = new JoystickButton(leftJoy, 5);

		
		//elevatorSwitch = new JoystickButton(leftJoy, 3);
		//climb = new JoystickButton(leftJoy, 4);
		//reverseClimb = new JoystickButton (leftJoy, 5);
//		
//		gear = new JoystickButton(leftJoy, 2);
//		boiler = new JoystickButton(leftJoy, 3);

// 
////		
//		climb.whileHeld(new ClimbCommand());
//		climb.whenReleased(new StopClimbCommand () );
//		reverseClimb.whileHeld(new ClimbReverseCommand());
//		reverseClimb.whenReleased(new StopClimbCommand());
	//	practiceEncoders.whenPressed(new PraticeEncoders());
	//	shiftUp.whenActive(new ShiftUpCommand());
		shiftDown.whenActive(new ShiftDownCommand());
		//switchDrive.whenPressed(new SwitchDriveCommand());
		//openGrabber.whenPressed(new OpenArmsCommand());
	//	closeGrabber.whenPressed(new ClosedArmsCommand());
		//elevatorSwitch.whenPressed(new ElevatorToSwitchCommand());
		
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
