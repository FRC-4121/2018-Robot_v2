package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */

public class AutoLeftSideCommandGroup extends CommandGroup {

    public AutoLeftSideCommandGroup() {
        
    	addSequential(new BeginningMatchCommandGroup());
    	//addSequential(new OpenServoCommand());
    	addSequential(new ElevatorToSwitchCommand());
    	addSequential(new AutoDrive(95, -1, 0, 7)); //drive to switch used to
    	//addSequential(new AutoDrive(83, -1, 0, 7));
    	addSequential(new AutoTurn(45, 1.5, 'L', "Switch")); //used to be 98
    	addSequential(new EjectCubeCommand('L'));
    	
    }
    
}
