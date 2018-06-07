package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRobotRightScaleLeft1Cube extends CommandGroup {

    public AutoRobotRightScaleLeft1Cube() {

    	addSequential(new AutoAngleMotorDownCommand());  
    	addSequential(new ClosedArmsCommand());
    	addSequential(new ShiftUpCommand());	
    	addSequential(new AutoDrive(197, -1, 0, 10)); //drive to switch  
    	addSequential(new ShiftDownCommand());
    	addSequential(new AutoTurn (-90, 1));   	
    	addSequential(new AutoDrive(127, -1, -90, 10));
    	addSequential(new ElevatorToSwitchCommand());   	
    	addSequential(new AutoDrive(80, -1, -90, 10));   	
    	addSequential(new AutoTurn (0, 1));   	
    	addSequential(new ElevatorToScaleCommand());
    	addSequential(new AutoDrive(16, -1, 0, 2));    //used to be 8	
    //	addSequential(new AutoElevatorToScale(4.0));
    	addSequential(new AutoTurn (47, 2.0)); 
    	addSequential(new EjectCubeCommand(-.65));
    	addSequential(new OpenArmsCommand());
    }
}
