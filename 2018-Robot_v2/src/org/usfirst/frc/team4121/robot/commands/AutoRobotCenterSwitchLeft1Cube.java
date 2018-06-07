package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRobotCenterSwitchLeft1Cube extends CommandGroup {

    public AutoRobotCenterSwitchLeft1Cube() {
    	
    	addSequential(new ShiftDownCommand());
    	addSequential(new AutoDrive(20, -1, 0, 5)); 
    	addSequential(new AutoTurn (-90, 1));   	
    	addSequential(new AutoDrive(43, -1, -90, 5));
    	addSequential(new AutoTurn (0, 1));   
    	addSequential(new ElevatorToSwitchCommand());
    	addSequential(new AutoDrive(80, -1, 0, 10));
    	addSequential (new EjectCubeCommand(-.58));
    }
}
