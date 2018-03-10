package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoScaleRightSideCommandGroup extends CommandGroup {

    public AutoScaleRightSideCommandGroup() {
    	
    	//addSequential(new BeginningMatchCommandGroup());
    	
    	addSequential(new DriveToScaleCommand('R')); //drive appropriate distance depending on side assignment (left or right)
    	
    	addParallel(new AutoTurn(-30, 5, 'R')); //turn; 30 degrees is a test value
    	
    	//addSequential(new toScaleAutoCommand('R'));
    	
    	//addSequential(new EjectCubeCommand('R'));
    	
    	//addParallel(new AutoDrive(10, -1, 0, 3)); //make sure that the grabber won't hit the scale
    	
    	//addSequential(new ElevatorToHomeCommand());

    }
}
