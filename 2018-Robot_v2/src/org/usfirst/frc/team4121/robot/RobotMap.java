package org.usfirst.frc.team4121.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @author Hen "traitor" Bayden thanks ben for showing up 
 */
public class RobotMap {
	
	//Motor Controller Constants
	public static final int LEFT_MOTOR_MASTER = 0;
	public static final int LEFT_MOTOR_SLAVE = 15;
	public static final int RIGHT_MOTOR_MASTER = 4;
	public static final int RIGHT_MOTOR_SLAVE = 3;
	public static final int CLIMBER1 = 2;
	public static final int ENDMOTOR1 = 1;
	public static final int ENDMOTOR2 = 8; 
	public static final int ELEVATOR_MOTOR_MASTER = 6;
	public static final int ELEVATOR_MOTOR_SLAVE = 7;
	
	public static final int kXboxPort = 2;
	public static final int A = 1;
	public static final int B = 2;
	public static final int Y = 3;
	public static final int PADUP = 4;
	public static final int PADDOWN = 5;
	public static final int LT = 6;
	public static final int RT = 7;
	public static final int RB = 8;
	
	
	
	//Motor Speeds
	public static final double END_EFFECTOR_SPEED = .5; 
	public static final double DRIVE_SPEED = 0.8;
	public static double AUTO_DRIVE_SPEED = 0.5;//changed from .8
	public static double AUTO_TURN_SPEED = 0.6;
	public static double SHOOTER_SPEED = -.6;//can change later depending on speed
	public static double CLIMBER_SPEED = .6;//can change later depending on speed
	public static double CLIMBER_REVERSE_SPEED = -.6;
	
	
	//Miscellaneous
	public static int DIRECTION_MULTIPLIER = 1;
	public static final int COMPRESSOR = 0;
	public static double STRAIGHT_ANGLE_TOLERANCE = .5;
	public static double TURN_ANGLE_TOLERANCE = .1;
	public static char AUTO_SWITCH_POSITION;
	
	
	//PID values
	public static double kP_Straight = 0.017;
	public static double kP_Turn = 0.05;
	public static double kI_Straight = 0.0;
	public static double kI_Turn = 0.0;
	public static double kD_Straight = 0.0;
	public static double kD_Turn = 0.0;
	
	//Camera Image Values
	public static final int IMG_WIDTH = 320;
	public static final int IMG_HEIGHT = 240;

	
	//encoder stuff
	public static boolean kSensorPhase = false;
	public static boolean kMotorInvert = false;
	public static final int kPIDLoopIdx = 0;
	public static final int kTimeoutMs = 10;
	public static int LEFT_STARTING_POSITION = 0;
	public static int RIGHT_STARTING_POSITION = 0;
	public static int DRIVE_GEAR_RATIO = 5;

	//stuff for elevator
		/* Elevator target positions */
		public static final double dPosSwitch = 30 ;
		public static final double dPosScale = 84;
		public static final double dPosBumpUp = 12 ;
		public static final double dPosBumpDown = -12 ;

		public static final double dFudgeFactor = 1.135 ;  // actual distance/programmed distance

		/* Elevator drive ratios */
		public static final int kMotorGearRatio = 5 ;
		public static final int kEncoderRatio = 1 ; //ratio of encoder revs to output revs
		public static final int kMotorSprocketTeeth = 12 ;
		public static final int kDrumShaftSprocketTeeth = 12 ;
		public static final double kWinchDrumDia = 0.5 ;

		/* Motor encoder info */
		public static final int kEncoderPPR = 4096 ;  // encoder pulses per revolution

		/* PID constants */
		public static final double kf = 0.5 ;
		public static final double kp = 0.25 ;
		public static final double ki = 0 ;
		public static final double kd = 7 ;

		/* Motion magic motion parameters */
		/* Test values only - for real try to increase 4x by competition */

		public static final double kCruiseSpeed = 50.0 ; // inches per sec
		public static final double kAcceleration = 30.0 ; // inches/s^2

		public static final double kCruiseSpeedUp = 80.0 ; // inches per sec  
		public static final double kAccelerationUp = 50.0 ; // inches/s^2

		/* speed and acceleration down need to be set to keep tension on the cable */
		/* g = 386.4 in/s^ */

		public static final double kCruiseSpeedDown = 20.0 ; // inches per sec
		public static final double kAccelerationDown = 10.0 ; // inches/s^2

		/* motor IDS */
		public static final int kMotorPort = 0;
		public static final int kMotor2Port = 1 ;

		public static final int kMaxMotorCurrent = 40 ;
		public static final int kPeakMotorCurrent = 60 ;

}
