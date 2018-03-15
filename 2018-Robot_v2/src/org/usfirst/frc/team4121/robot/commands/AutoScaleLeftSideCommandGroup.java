package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoScaleLeftSideCommandGroup extends CommandGroup {

    public AutoScaleLeftSideCommandGroup() {
        
    	addSequential(new BeginningMatchCommandGroup());
    	
    	addSequential(new DriveToScaleCommand('L')); //drive appropriate distance depending on side assignment (left or right)
    	
    	addParallel(new AutoTurn(30, 5, 'L')); //turn; 30 degrees is a test value
    	
    	addSequential(new toScaleAutoCommand('L'));
    	
    	addSequential(new EjectCubeCommand('L'));
    	
    	//addParallel(new AutoDrive(10, -1, 0, 3)); //make sure the grabber won't hit the scale
    	
    	addSequential(new ElevatorToHomeCommand());
    	
    }
}
