package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoScaleLeftSideCommandGroup extends CommandGroup {

    public AutoScaleLeftSideCommandGroup() {
        
    	addSequential(new BeginningMatchCommandGroup());
    	
    	addSequential(new ElevatorToSwitchCommand());
    	
    	addSequential(new AutoDrive(155, -1, 0, 10)); //drive to switch used to
    	
    	addSequential(new toScaleAutoCommand('L'));
    	
    	addSequential(new AutoDrive(100, -1, 0, 10)); //drive to switch used to
    	
    	addSequential(new AutoTurn(45, 1.5, 'L')); //turn; 30 degrees is a test value
    	
    	//addSequential(new AutoAngleMotorCommand());
    	
    	addSequential(new EjectCubeCommand('L'));
    	
    	//addSequential(new AutoAngleMotorDownCommand());
    	
    	//addSequential(new AutoDrive(10, -1, 0, 3)); //make sure the grabber won't hit the scale
    	
    	//addSequential(new ElevatorToHomeCommand());
    	
    	//addSequential(new AutoTurn(135, 1.5, 'L'));
    	
//    	addSequential(new AutoPickUpCubeCommandGroup());
    	
    }
}
