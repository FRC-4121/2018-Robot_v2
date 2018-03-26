package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoStraightCommandGroup extends CommandGroup {

    public AutoStraightCommandGroup() {
       
    	addSequential(new BeginningMatchCommandGroup());
    	addSequential(new AutoDrive(220, -1, 0, 7)); 
    	
    }
    
}
