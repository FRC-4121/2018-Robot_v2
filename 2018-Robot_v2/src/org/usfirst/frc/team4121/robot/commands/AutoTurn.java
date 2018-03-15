package org.usfirst.frc.team4121.robot.commands;

import org.usfirst.frc.team4121.robot.Robot;
import org.usfirst.frc.team4121.robot.RobotMap;
import org.usfirst.frc.team4121.robot.extraClasses.PIDControl;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoTurn extends Command {

	double targetAngle;
	double startTime;
	double stopTime;
	double angleError;
	double angleCorrection;
	double motorOutput;
	private char robotSide;

	PIDControl pidControl;

	//	private PIDController pid;
	//	private PIDOutput pidOutput;
	//	
	private Timer timer = new Timer();


	//Class constructor
	public AutoTurn(double angle, double time, char side) { //change in smartdashboard

		targetAngle = angle;
		stopTime = time;
		robotSide = side;

		requires(Robot.driveTrain);

		//set up PID controller
		pidControl = new PIDControl(RobotMap.kP_Turn, RobotMap.kI_Turn, RobotMap.kD_Turn);

		//    	pidOutput = new PIDOutput() {
		//    		
		//    		@Override
		//    		public void pidWrite(double d) {
		//    			//Robot.driveTrain.autoDrive(-d*0.6, d*0.6);
		//    		}
		//    	};
		//    	
		//    	pid = new PIDController(0.05, 0.0, 0.0, Robot.oi.MainGyro, pidOutput);
		//    	pid.setAbsoluteTolerance(RobotMap.ANGLE_TOLERANCE);
		//    	pid.setContinuous();
		//    	pid.setSetpoint(angle);
	}


	// Called just before this Command runs the first time
	protected void initialize() {

		//    	pid.reset();
		//        pid.enable();
		timer.start();
		startTime = timer.get();
		angleError = 0;
		angleCorrection = 0;


	}


	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (robotSide == 'N' || robotSide == RobotMap.AUTO_SWITCH_POSITION)
		{

			angleCorrection = pidControl.Run(Robot.driveAngle.getDouble(0), targetAngle);
			//angleCorrection= RobotMap.kD_Straight*(Robot.oi.MainGyro.getAngle()-targetAngle);
			motorOutput = angleCorrection * RobotMap.AUTO_TURN_SPEED;
			//    	if (motorOutput > 1.0)
			//    	{
			//    		motorOutput = 1.0;
			//    	}
			//    	else if (motorOutput < 0.0)
			//    	{
			//    		motorOutput = 0.0;
			//    	}
			Robot.driveTrain.autoDrive(motorOutput, -motorOutput);  
			//SmartDashboard.putString("Drive Angle:", Double.toString(Robot.oi.MainGyro.getAngle()));
		}


	}


	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		//Declare return flag
		boolean thereYet = false;

		//Check elapsed time
		if(stopTime<=timer.get()-startTime)
		{

			//Too much time has elapsed.  Stop this command
			//pid.disable();
			thereYet = true;

		}
		else
		{

			angleError = Robot.driveAngle.getDouble(0) - targetAngle;
			if (Math.abs(angleError) <= RobotMap.TURN_ANGLE_TOLERANCE)
			{
				thereYet = true;

			}

		}

		//Put PID status on dashboard
		//SmartDashboard.putString("Angle Reached Yet:", Boolean.toString(pid.onTarget()));

		//Return the flag
		return thereYet;

	}


	// Called once after isFinished returns true
	protected void end() {

		//pid.disable();
		Robot.driveTrain.autoStop(); //maybe don't need depends on robot

	}


	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {

		//pid.disable();

	}
}
