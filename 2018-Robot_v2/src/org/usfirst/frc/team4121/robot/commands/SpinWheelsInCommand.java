package org.usfirst.frc.team4121.robot.commands;

import org.usfirst.frc.team4121.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpinWheelsInCommand extends Command {
	private Timer armTimer = new Timer();

    public SpinWheelsInCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.end);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	armTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.end.endeffector(.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
     	if(5<=armTimer.get()) //change time, once reaches five seconds command should stop
    	{
    		return true;
    	}
  
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
