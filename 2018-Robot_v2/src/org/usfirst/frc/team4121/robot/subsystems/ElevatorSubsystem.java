package org.usfirst.frc.team4121.robot.subsystems;

import org.usfirst.frc.team4121.robot.Robot;
import org.usfirst.frc.team4121.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ElevatorSubsystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.


//	public WPI_TalonSRX m_motor = new WPI_TalonSRX (RobotMap.ELEVATOR_MOTOR_MASTER);
//	public WPI_TalonSRX m_motor2_follower = new WPI_TalonSRX (RobotMap.ELEVATOR_MOTOR_SLAVE);  old code
	

	public TalonSRX m_motor = new TalonSRX (RobotMap.ELEVATOR_MOTOR_MASTER);
	public TalonSRX m_motor2_follower = new TalonSRX (RobotMap.ELEVATOR_MOTOR_SLAVE);

	public double targetPos = 0;
	public double oldTargetPos;
	public double inchesPerRev;
	public int encoderPulsesPerOutputRev; // number of motor encoders pulses per encoder output revolution
	
	public double cruiseVelocityUp ;
	public double cruiseVelocityDn ;
	public double accelUp ;
	public double accelDn ;
	public double targetPosSwitch ;
	public double targetPosScale ;
	public double bumpUp ;
	public double bumpDn ;



	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}


	public void runToScale() // run to scale height
	{
		/* go to scale height */
		oldTargetPos = targetPos;
		targetPos = targetPosScale;
		if (targetPos > oldTargetPos) {
			m_motor.configMotionCruiseVelocity((int) cruiseVelocityUp , RobotMap.kTimeoutMs);
			m_motor.configMotionAcceleration((int) accelUp, RobotMap.kTimeoutMs);
			m_motor.set(ControlMode.MotionMagic, targetPos);
		} 
		else {
			m_motor.configMotionCruiseVelocity((int) cruiseVelocityDn, RobotMap.kTimeoutMs);
			m_motor.configMotionAcceleration((int) accelDn, RobotMap.kTimeoutMs);
			m_motor.set(ControlMode.MotionMagic, targetPos);
		}
	}



	public void runToSwitch() // run to switch height
	{
		oldTargetPos = targetPos;
		targetPos = targetPosSwitch;
		if (targetPos > oldTargetPos) {
			m_motor.configMotionCruiseVelocity((int) cruiseVelocityUp, RobotMap.kTimeoutMs);
			m_motor.configMotionAcceleration((int) accelUp, RobotMap.kTimeoutMs);
			m_motor.set(ControlMode.MotionMagic, targetPos);
		} 
		else {
			m_motor.configMotionCruiseVelocity((int) cruiseVelocityDn, RobotMap.kTimeoutMs);
			m_motor.configMotionAcceleration((int) accelDn, RobotMap.kTimeoutMs);
			m_motor.set(ControlMode.MotionMagic, targetPos);
		}
	}
	
/* new functions here - bump up and bump down */

	public void bumpUp() // bump up bump up distance
	{
		if(targetPos+12>RobotMap.dPosScale)
		{
			//do nothinh because will go to high
		}
		else
		{
		targetPos += bumpUp ;
		m_motor.configMotionCruiseVelocity((int) cruiseVelocityUp, RobotMap.kTimeoutMs);
		m_motor.configMotionAcceleration((int) accelUp, RobotMap.kTimeoutMs);
		m_motor.set(ControlMode.MotionMagic, targetPos);
		}
	}

	public void bumpDown() // bump down height
	{
		if(targetPos-12 < 0)
		{
			//do nothing
		}
		else
		{
			targetPos += bumpDn ;
			m_motor.configMotionCruiseVelocity((int) cruiseVelocityDn, RobotMap.kTimeoutMs);
			m_motor.configMotionAcceleration((int) accelDn, RobotMap.kTimeoutMs);
			m_motor.set(ControlMode.MotionMagic, targetPos);
		}
		
	}

	public void goToHome() // go to home
	{
		targetPos = 0;
		m_motor.configMotionCruiseVelocity((int) cruiseVelocityDn, RobotMap.kTimeoutMs);
		m_motor.configMotionAcceleration((int) accelDn, RobotMap.kTimeoutMs);
		m_motor.set(ControlMode.MotionMagic, targetPos);
	}



}


	   
	 