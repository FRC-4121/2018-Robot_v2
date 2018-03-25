package org.usfirst.frc.team4121.robot;

import org.usfirst.frc.team4121.robot.commands.AutoLeftSideCommandGroup;
import org.usfirst.frc.team4121.robot.commands.AutoLeftSidePivotCommandGroup;
import org.usfirst.frc.team4121.robot.commands.AutoRightSideNoTurnCommandGroup;
import org.usfirst.frc.team4121.robot.commands.AutoScaleLeftSideCommandGroup;
import org.usfirst.frc.team4121.robot.commands.AutoScaleRightSideCommandGroup;
import org.usfirst.frc.team4121.robot.commands.AutoStopCommand;
import org.usfirst.frc.team4121.robot.commands.AutoStraightCommandGroup;
import org.usfirst.frc.team4121.robot.commands.LogicTestCommandGroup;
import org.usfirst.frc.team4121.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team4121.robot.subsystems.DriveTrainSubsystem;
import org.usfirst.frc.team4121.robot.subsystems.ElevatorSubsystem;
import org.usfirst.frc.team4121.robot.subsystems.EndEffector;
import org.usfirst.frc.team4121.robot.subsystems.ShifterSubsystem;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * 
 * @author Saliva Crustyman
 */
public class Robot extends IterativeRobot {
	
	//Camera Stuff
	public static UsbCamera cam;
	public static CameraServer server;
	
	//Network tables
	
	public static NetworkTableInstance dataTableInstance;
	public static NetworkTable visionTable;
	public static NetworkTable navxTable;
	public static NetworkTableEntry robotStop;
	public static NetworkTableEntry cubeHeight;
	public static NetworkTableEntry cubeAngle;
	public static NetworkTableEntry cubeDistance;
	public static NetworkTableEntry cubeOffset;
	public static NetworkTableEntry cubePercentWidth;
	public static NetworkTableEntry driveAngle;
	public static NetworkTableEntry yVelocity;
	public static NetworkTableEntry xVelocity;
	public static NetworkTableEntry yDisplacement;
	public static NetworkTableEntry xDisplacement;
	public static NetworkTableEntry zeroGyro;
	public static NetworkTableEntry writeVideo;
	
	//Subsystems
	public static DriveTrainSubsystem driveTrain;
	public static ShifterSubsystem shifter;
	public static ClimberSubsystem climber;
	public static EndEffector end;
	public static ElevatorSubsystem elevator;
	
	//Sensors and inputs
	public static OI oi;

	//SmartDashboard chooser
	private SendableChooser<Command> chooser;
	
	//Commands
	private Command autonomousCommand;

	//encoder math values
	public static double distanceTraveled;
	public static double angleTraveled;
	public static double leftDistance;
	public static double rightDistance;
		
	//2018 Game Data
	public static String gameData;
	public static String switchTargetPosition;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		//Network tables
		dataTableInstance = NetworkTableInstance.getDefault();
		visionTable = dataTableInstance.getTable("vision");
		navxTable = dataTableInstance.getTable("navx");
		
		//NetworkTable Entries
		robotStop = visionTable.getEntry("RobotStop");
		cubeAngle = visionTable.getEntry("CubeAngle");
		cubeDistance = visionTable.getEntry("CubeDistance");
		cubeOffset = visionTable.getEntry("CubeOffset");
		cubePercentWidth = visionTable.getEntry("CubePercentWidth");
		writeVideo = visionTable.getEntry("WriteVideo");
		//cubeHeight = visionTable.getEntry("cubeHeight");
		driveAngle = navxTable.getEntry("DriveAngle");
		yVelocity = navxTable.getEntry("YVelocity");
		xVelocity = navxTable.getEntry("XVelocity");
		yDisplacement = navxTable.getEntry("YDisplacement");
		xDisplacement = navxTable.getEntry("XDisplacement");
		zeroGyro = navxTable.getEntry("ZeroGyro");
		

		robotStop.setDouble(0.0);
		zeroGyro.setDouble(1.0);		
		
		//Initialize subsystems		
		driveTrain = new DriveTrainSubsystem();
		shifter = new ShifterSubsystem();
		climber = new ClimberSubsystem();
		end = new EndEffector();
		elevator = new ElevatorSubsystem();
		oi = new OI();
		
		//Camera Server setup
//		server = CameraServer.getInstance();
//		cam = new UsbCamera("Main Camera", 0);
//		server.addCamera(cam);
//		cam.setResolution(160, 120);
//		server.startAutomaticCapture(cam);
		
		//Initialize dashboard choosers
		//!!Update this to reflect any new auto code!!
		chooser = new SendableChooser<>();
		chooser.addDefault("Straight", new AutoStraightCommandGroup());
		chooser.addObject("Left Turn", new AutoLeftSideCommandGroup());
		chooser.addObject("Left Straight (ish)", new AutoLeftSidePivotCommandGroup());
		chooser.addObject("Right Straight", new AutoRightSideNoTurnCommandGroup());
		chooser.addObject("Do nothing", new AutoStopCommand());
		chooser.addObject("Logic Tester", new LogicTestCommandGroup());
		chooser.addObject("Left Scale", new AutoScaleLeftSideCommandGroup());
		chooser.addObject("Right Scale", new AutoScaleRightSideCommandGroup());
		SmartDashboard.putData("Auto Mode:", chooser);
		
		//Initialize variables
		distanceTraveled = 0.0;
		angleTraveled = 0.0;
		
		//Ensure servos are closed
		Robot.end.closeServos();
		
	}
	

	void Disabled() {
		
		while(isDisabled()) {
			
		}
		
	}

	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		
		//robotStop.setDouble(1.0);

	}

	
	@Override
	public void disabledPeriodic() {
		
		//Start scheduler
		Scheduler.getInstance().run();
		
	}

	
	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	
	@Override
	public void autonomousInit() {
		
		//Calibrate the main gyro
		//Robot.oi.MainGyro.calibrate();
		//Robot.oi.MainGyro.reset();
		zeroGyro.setDouble(1.0);
		
		//grab gameData
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		RobotMap.AUTO_SWITCH_POSITION = gameData.charAt(0);
		RobotMap.AUTO_SCALE_POSITION = gameData.charAt(1);
		RobotMap.AUTO_SWITCH_AND_SCALE = gameData.substring(2);

		//Get selected autonomous command
		autonomousCommand = chooser.getSelected();
		
		//Reset encoders
		RobotMap.LEFT_STARTING_POSITION = Robot.driveTrain.getLeftEncoderPosition();
		RobotMap.RIGHT_STARTING_POSITION = Robot.driveTrain.getRightEncoderPosition();
		
		//start video
		writeVideo.setDouble(1.0);

		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}
	
	
	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
		//Start autonomous scheduler
		Scheduler.getInstance().run();
		
		//Put key values on smart dashboard
		
		//SmartDashboard.putString("Left Drive Distance: ", Double.toString(Robot.oi.leftEncoder.getDistance()));
		//SmartDashboard.putString("Right Drive Distance: ", Double.toString(Robot.oi.rightEncoder.getDistance()));
		SmartDashboard.putString("Gear Position: ", shifter.gearPosition());
	}
	
	@Override
	public void teleopInit() {
		
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null){
			autonomousCommand.cancel();
		}
		Scheduler.getInstance().removeAll();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		//Start scheduler
		Scheduler.getInstance().run();
 
		//Put key values on the SmartDashboard
		SmartDashboard.putString("Gear Postion: ", shifter.gearPosition());
		SmartDashboard.putString("Drive Direction:", Integer.toString(RobotMap.DIRECTION_MULTIPLIER));		
		//SmartDashboard.putNumber("Gyro Reading:", oi.MainGyro.getAngle());
		//SmartDashboard.putNumber("Master Current", Robot.elevator.m_motor.getOutputCurrent());
		//SmartDashboard.putNumber("Slave Current", Robot.elevator.m_motor2_follower.getOutputCurrent());
		//SmartDashboard.putNumber("Master Output", Robot.elevator.m_motor.getMotorOutputPercent());
		//SmartDashboard.putNumber("Slave Output", Robot.elevator.m_motor2_follower.getMotorOutputPercent());
		SmartDashboard.putNumber("Elevator Current Position", Robot.elevator.targetPos);
		//SmartDashboard.putString("Right Drive Distance (in inches): ", Double.toString(Robot.oi.rightEncoder.getDistance()));
		//SmartDashboard.putString("Left Drive Distance (in inches): ", Double.toString(Robot.oi.leftEncoder.getDistance()));		
		//SmartDashboard.putString("Limit Switch: ", Boolean.toString(Robot.oi.limitSwitch.get()));
		//SmartDashboard.putString("Drive Angle: ", Double.toString(Robot.oi.MainGyro.getAngle())); //gyro on the pi
	
	}
	

	/**
	 * This function is called periodically during test mode
	 */
	
	@Override
	public void testPeriodic() {	
		
	}
}
