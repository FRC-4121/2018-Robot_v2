package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRobotLeftScaleRight1Cube extends CommandGroup {

    public AutoRobotLeftScaleRight1Cube() {

    	addSequential(new BeginningMatchCommandGroup());
    	addSequential(new ShiftUpCommand());	
    	addSequential(new AutoDrive(197, -1, 0, 10)); //drive to switch  
    	addSequential(new ShiftDownCommand());
    	addSequential(new AutoTurn (90, 1));   	
    	addSequential(new AutoDrive(110, -1, 90, 10));
    	addSequential(new ElevatorToSwitchCommand());   	
    	addSequential(new AutoDrive(80, -1, 90, 10));   	
    	//addSequential(new ElevatorToScaleCommand());
    	addSequential(new AutoTurn (0, 1));   
    	addSequential(new AutoDrive(20, -1, 0, 2));
    	addSequential(new AutoTurn (-50, 2.0)); 
    	addSequential(new EjectCubeCommand(-0.75));
    	addSequential(new OpenArmsCommand());
    
    }
}
