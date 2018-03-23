
package org.usfirst.frc.team4121.robot.commands;

import org.usfirst.frc.team4121.robot.Robot;
import org.usfirst.frc.team4121.robot.RobotMap;
import org.usfirst.frc.team4121.robot.extraClasses.PIDControl;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GrandTheftAutoCommand extends Command {

	public char robotStartSide;

	double targetDistance; 
	double direction; //-1 = Reverse, +1 = Forward (reverse is for gear forward is for shooting)
	double targetAngle;  //drive angle
	double stopTime;  //timeout time
	double angleCorrection, angleError;
	double startTime;
	
	PIDControl pidControl;

	public Timer timer = new Timer();
	
	public int leftEncoderStart;
	public int rightEncoderStart;
	
	//These boolean flags are in charge of what step of our we are own (like addSequential) 
	boolean drivingToScale = true;
	boolean liftCubeToScale = false;
	boolean angleGrabberUp = false;
	boolean ejectCube = false;
	boolean backUp = false;
	boolean angleGrabberDown = false;
	boolean lowerElevToGround = false;
	boolean turnAround = false;
	boolean grabCube2 = false;
	boolean turnToScale = false;
	//lift to scale again
	//angle up, eject, back up, angle down again

	public GrandTheftAutoCommand(char side) {
		
		requires(Robot.driveTrain);
		
		requires(Robot.elevator);
		
		requires(Robot.end);
		
		robotStartSide = side;
		
		pidControl = new PIDControl(RobotMap.kP_Straight, RobotMap.kI_Straight, RobotMap.kD_Straight);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
		Robot.distanceTraveled = 0.0;
        timer.start();
        startTime = timer.get();
        leftEncoderStart = Robot.driveTrain.getLeftEncoderPosition();
        rightEncoderStart = Robot.driveTrain.getRightEncoderPosition();
        angleCorrection = 0;
        angleError = 0;
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		if(robotStartSide == 'L') { //run this on the left side

			switch (RobotMap.AUTO_SWITCH_AND_SCALE) {

				case "LL": 
					
					//Drive to Scale
					if(drivingToScale) {
						
						//set variables for driving
						targetDistance = 260; //distance to the scale, in this case
						direction = 1;
						targetAngle = 0;
						
						//Drive action
						angleError = Robot.driveAngle.getDouble(0)-targetAngle;
				    	angleCorrection = RobotMap.kP_Straight*angleError;
				    	Robot.driveTrain.autoDrive(direction*RobotMap.AUTO_DRIVE_SPEED + angleCorrection, direction*RobotMap.AUTO_DRIVE_SPEED - angleCorrection);
				    	 
				    	//Check elapsed time
				    	stopTime = 10;
				    	if(stopTime <= timer.get()-startTime) {
				    		
				    		//Too much time has elapsed. Stop doing stuff. 
				    		drivingToScale = false;
				    		
				    	} else {
				    		
				    		int totalRotationsRight = Math.abs((Robot.driveTrain.getRightEncoderPosition() - rightEncoderStart)) / 4096;
				    		int totalRotationsLeft = Math.abs((Robot.driveTrain.getLeftEncoderPosition() - leftEncoderStart)) / 4096;
				    		Robot.distanceTraveled = (6*Math.PI*((totalRotationsRight+totalRotationsLeft)/2))/ RobotMap.DRIVE_GEAR_RATIO;
				    		
				    		if (targetDistance <= Robot.distanceTraveled) {
				    			
				    			//Robot has reached its destination.  Stop this command and switch to the next part of the command
				    			drivingToScale = false;
				    			liftCubeToScale = true;
				    			
				    		}
				    		
				    	}
						
					}
					
					//Lift cube to scale height
					if(liftCubeToScale) {
						
						
						
						
						
					}
					
					//Angle motor approp
					if(angleGrabberUp) {
						
						
					}
					
					//Eject
					if(ejectCube) {
						
						
						
					}
					
					//Back up??
					if(backUp) {
						
						
					}
					
					//reverse angle motor
					if(angleGrabberDown) {
						
						
					}
					
					//Lower elevator
					if(lowerElevToGround) {
						
						
						
					}

					//Turn around
					if(turnAround) {
						
						
						
					}
					
					//Grab new cube
					if(grabCube2) {
						
						
						
					}
					
					//Turn around
					//Lift new cube to scale height
					//Angle motor here
					//Eject
					//Back up??
					//Lower angle
					//Lower elevator to ground
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					System.out.println("filler");

				case "LR":

					System.out.println("filler2");

				case "RL":

					System.out.println("filler3");

				case "RR":	

					System.out.println("filler4");

			}

		} else { //run this on the right side

			switch (RobotMap.AUTO_SWITCH_AND_SCALE) {

				case "LL": 

					System.out.println("filler");

				case "LR":

					System.out.println("filler2");

				case "RL":

					System.out.println("filler3");

				case "RR":	

					System.out.println("filler4");

			}

		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
