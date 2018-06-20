package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRobotRightScaleRight1Cube extends CommandGroup {

    public AutoRobotRightScaleRight1Cube() {

    	addSequential(new BeginningMatchCommandGroup());
    	//addSequential(new ShiftUpCommand());	
    	addSequential(new ElevatorToSwitchCommand());
    	addSequential(new AutoDrive(143, -1, 0, 10)); 
    	addSequential(new ElevatorToScaleCommand());
    	addSequential(new AutoDrive(110, -1, 0, 10)); //used to be 100
    	addSequential(new AutoTurn(-50, 1.5));
    	addSequential(new EjectCubeCommand(-.75));
    	addSequential(new OpenArmsCommand());
    
    }
}
