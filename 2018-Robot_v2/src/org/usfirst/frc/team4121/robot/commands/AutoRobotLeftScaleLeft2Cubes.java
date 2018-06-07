package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRobotLeftScaleLeft2Cubes extends CommandGroup {

    public AutoRobotLeftScaleLeft2Cubes() {
    	
    	addSequential(new BeginningMatchCommandGroup());
    	addSequential(new ElevatorToSwitchCommand());
    	addSequential(new AutoDrive(155, -1, 0, 5)); 
    	addSequential(new ElevatorToScaleCommand());
    	addSequential(new AutoDrive(110, -1, 0, 5)); //used to be 100
    	addSequential(new AutoTurn(50, 1.5));
    	addSequential(new EjectCubeCommand(-.58));
    	addSequential(new OpenArmsCommand());
    	//addSequential(new AutoDrive(10, 1, -130, 3));
    	addSequential(new AutoElevatorToHome(3.0));
    	addSequential(new AutoTurn(145, 1.5));
    	addSequential(new AutoPickUpCubeCommandGroup());
    	addSequential(new AutoDrive(10, 1, 0, 2)); 

    }
}
