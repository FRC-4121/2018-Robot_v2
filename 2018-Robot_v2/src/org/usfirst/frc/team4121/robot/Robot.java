package org.usfirst.frc.team4121.robot;

import org.usfirst.frc.team4121.robot.commands.AutoRobotCenterSwitchLeft;
import org.usfirst.frc.team4121.robot.commands.AutoRobotCenterSwitchRight;
import org.usfirst.frc.team4121.robot.commands.AutoRobotLeftScaleLeft1Cube;
import org.usfirst.frc.team4121.robot.commands.AutoRobotLeftScaleLeft2Cubes;
import org.usfirst.frc.team4121.robot.commands.AutoRobotLeftScaleRight1Cube;
import org.usfirst.frc.team4121.robot.commands.AutoRobotLeftSwitchLeft1Cube;
import org.usfirst.frc.team4121.robot.commands.AutoRobotRightScaleLeft1Cube;
import org.usfirst.frc.team4121.robot.commands.AutoRobotRightScaleRight1Cube;
import org.usfirst.frc.team4121.robot.commands.AutoRobotRightScaleRight2Cubes;
import org.usfirst.frc.team4121.robot.commands.AutoRobotRightSwitchRight1Cube;
import org.usfirst.frc.team4121.robot.commands.AutoStraightCommandGroup;
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
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * 
 * @author Jonas Muhlenkamp
 * 
 * 
 * ROBOT PLACEMENT:  edge of bumper 78 inches from center line left and right
 * 					 edge of bumper on center line for center start
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

	//Commands
	private Command autonomousCommand;

	//encoder math values
	public static double distanceTraveled;
	public static double angleTraveled;
	public static double leftDistance;
	public static double rightDistance;

	//2018 Game specific variables
	public static String gameData = null;
	public Timer timer = new Timer();
	public double startTime;
	public double stopTime;
	private boolean autoCommandStarted = false;
	public static String myTarget;
	public static String mySide;
	public static double numberOfCubes;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */

	@Override
	public void robotInit() {

		//Initialize NetworkTables
		dataTableInstance = NetworkTableInstance.getDefault();
		visionTable = dataTableInstance.getTable("vision");
		navxTable = dataTableInstance.getTable("navx");


		//Initialize NetworkTable entries
		robotStop = visionTable.getEntry("RobotStop");
		cubeAngle = visionTable.getEntry("CubeAngle");
		cubeDistance = visionTable.getEntry("CubeDistance");
		cubeOffset = visionTable.getEntry("CubeOffset");
		cubePercentWidth = visionTable.getEntry("CubePercentWidth");
		writeVideo = visionTable.getEntry("WriteVideo");
		cubeHeight = visionTable.getEntry("CubeHeight");
		driveAngle = navxTable.getEntry("DriveAngle");
		yVelocity = navxTable.getEntry("YVelocity");
		xVelocity = navxTable.getEntry("XVelocity");
		yDisplacement = navxTable.getEntry("YDisplacement");
		xDisplacement = navxTable.getEntry("XDisplacement");
		zeroGyro = navxTable.getEntry("ZeroGyro");


		//Initialize NetworkTable values
		robotStop.setDouble(0.0);


		//Initialize subsystems		
		driveTrain = new DriveTrainSubsystem();
		shifter = new ShifterSubsystem();
		climber = new ClimberSubsystem();
		end = new EndEffector();
		elevator = new ElevatorSubsystem();
		oi = new OI();

		//Initialize variables
		distanceTraveled = 0.0;
		angleTraveled = 0.0;
		mySide = "";
		myTarget = "";
		numberOfCubes = 1.0;

		//Initialize Smartdashboard entries
		SmartDashboard.putString("Target", myTarget);
		SmartDashboard.putString("Side", mySide);
		SmartDashboard.putNumber("Cubes", numberOfCubes);

		
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

		//SmartDashboard.("Target");
		//SmartDashboard.clearPersistent("Side");
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

		//Set auto flags
		autoCommandStarted = false;


		//Calibrate the main gyro
		zeroGyro.setDouble(1.0);


		//Reset encoders
		RobotMap.LEFT_STARTING_POSITION = Robot.driveTrain.getLeftEncoderPosition();
		RobotMap.RIGHT_STARTING_POSITION = Robot.driveTrain.getRightEncoderPosition();


		//Get game related data from SmartDashboard
		mySide = SmartDashboard.getString("Side", "LEFT");
		myTarget = SmartDashboard.getString("Target", "SCALE");
		numberOfCubes = SmartDashboard.getNumber("Cubes", 2.0);
		
		autonomousCommand = new AutoStraightCommandGroup();

		//Start game data timer
		timer.start();
		startTime = timer.get();

	}


	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {

		//in case the game data is not properly set, we give the field 10 seconds, then default to AutoStraightCommandGroup
		stopTime = 10;

		//		SmartDashboard.putString("Test Target", myTarget);
		//		SmartDashboard.putString("Test Side", mySide);
		//		SmartDashboard.putNumber("Test Number Of Cubes", numberOfCubes);

		if(stopTime <= timer.get() - startTime && gameData == null) { //timer check code

			//drive forward if it's been 10 seconds w/o game data
			if (!autoCommandStarted) {
				autonomousCommand = new AutoStraightCommandGroup();
				autonomousCommand.start();
				autoCommandStarted = true;
			}

		} else { //if timer hasn't stopped yet, grab the data

			String testGameData = DriverStation.getInstance().getGameSpecificMessage();

			//if the data contains useful stuff, use it!
			if(testGameData != null && !testGameData.isEmpty()) {

				//get individual strings
				gameData = testGameData;
				RobotMap.AUTO_SWITCH_POSITION = gameData.charAt(0);
				RobotMap.AUTO_SCALE_POSITION = gameData.charAt(1);
				RobotMap.AUTO_SWITCH_AND_SCALE = gameData.substring(2);

				//determine which command to run
				if (mySide.toUpperCase().equals("LEFT"))
				{

					if (myTarget.toUpperCase().equals("SWITCH"))
					{

						if (RobotMap.AUTO_SWITCH_POSITION == 'L')
						{

							if (numberOfCubes == 1.0)
							{
								autonomousCommand = new AutoRobotLeftSwitchLeft1Cube();
							}
							else
							{
								autonomousCommand = new AutoRobotLeftSwitchLeft1Cube();
							}

						}
						else
						{
							autonomousCommand = new AutoStraightCommandGroup();							
						}

					}
					
					else
					{

						if (RobotMap.AUTO_SCALE_POSITION == 'L')
						{

							if (numberOfCubes == 1.0)
							{
								autonomousCommand = new AutoRobotLeftScaleLeft1Cube();
							}
							else
							{
								autonomousCommand = new AutoRobotLeftScaleLeft2Cubes();
							}

						}
						else
						{

							if (numberOfCubes == 1.0)
							{
								autonomousCommand = new AutoRobotLeftScaleRight1Cube();
							}
							else
							{
								autonomousCommand = new AutoRobotLeftScaleRight1Cube();
							}

						}

					}

				}
				else if(mySide.toUpperCase().equals("CENTER"))
				{
					if(RobotMap.AUTO_SWITCH_POSITION == 'L')
					{
						autonomousCommand = new AutoRobotCenterSwitchLeft();														
					
					}
					else
					{
						autonomousCommand = new AutoRobotCenterSwitchRight();														
						
					}
				}
				
				
				else if(mySide.toUpperCase().equals("RIGHT"))
				{

					if (myTarget.toUpperCase().equals("SWITCH"))
					{

						if (RobotMap.AUTO_SWITCH_POSITION == 'L')
						{
							autonomousCommand = new AutoStraightCommandGroup();														
						}
						else
						{

							if (numberOfCubes == 1.0)
							{
								autonomousCommand = new AutoRobotRightSwitchRight1Cube();
							}
							else
							{
								autonomousCommand = new AutoRobotRightSwitchRight1Cube();
							}

						}

					}
					
					else 
					{

						if (RobotMap.AUTO_SCALE_POSITION == 'L')
						{

							if (numberOfCubes == 1.0)
							{
								autonomousCommand = new AutoRobotRightScaleLeft1Cube();
							}
							else
							{
								autonomousCommand = new AutoRobotRightScaleLeft1Cube();
							}

						}
						else
						{

							if (numberOfCubes == 1.0)
							{
								autonomousCommand = new AutoRobotRightScaleRight1Cube();
							}
							else
							{
								autonomousCommand = new AutoRobotRightScaleRight2Cubes();
							}

						}

					}

				}
				else //this is for going straight
				{
					autonomousCommand = new AutoStraightCommandGroup();
				}

			}

		}



		//Start the selected command
		if(!autoCommandStarted && gameData != null) 
		{

			autonomousCommand.start();
			autoCommandStarted = true;

		}


		//Start autonomous scheduler
		Scheduler.getInstance().run();


		//Put key values on smart dashboard
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

		SmartDashboard.putNumber("Master Current", Robot.elevator.m_motor.getOutputCurrent());
		SmartDashboard.putNumber("Slave Current", Robot.elevator.m_motor2_follower.getOutputCurrent());
		SmartDashboard.putNumber("Master Output", Robot.elevator.m_motor.getMotorOutputPercent());
		SmartDashboard.putNumber("Slave Output", Robot.elevator.m_motor2_follower.getMotorOutputPercent());



	}


	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {	

	}
}
