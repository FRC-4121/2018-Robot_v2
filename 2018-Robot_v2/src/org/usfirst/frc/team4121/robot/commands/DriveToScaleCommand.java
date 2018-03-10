package org.usfirst.frc.team4121.robot.commands;

import org.usfirst.frc.team4121.robot.Robot;
import org.usfirst.frc.team4121.robot.RobotMap;
import org.usfirst.frc.team4121.robot.extraClasses.PIDControl;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToScaleCommand extends Command {
	
	char robotStartPos;
	double direction = 1;
	double targetAngle;
	double angleCorrection;
	double distance;
	
	
	PIDControl pidControl;
	
    public DriveToScaleCommand(char pos) {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	
    	robotStartPos = pos;  
    	
    	pidControl = new PIDControl(RobotMap.kP_Straight, RobotMap.kI_Straight, RobotMap.kD_Straight);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(robotStartPos == RobotMap.AUTO_SCALE_POSITION) {
        	
    		distance = 260; //distance to drop into scale

    	} else {
    		
    		distance = 160;//otherwise cross the auto line and get into a position to attack the scale
    		
    	}
    	
		angleCorrection = pidControl.Run(Robot.oi.MainGyro.getAngle(), targetAngle);
		Robot.driveTrain.autoDrive(direction*RobotMap.AUTO_DRIVE_SPEED - angleCorrection, direction*RobotMap.AUTO_DRIVE_SPEED + angleCorrection);    	    	

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
