package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LogicTestCommandGroup extends CommandGroup {
	
	public boolean test = true;
	
    public LogicTestCommandGroup() {
        
    	if(test) {
    		
    		addSequential(new AutoDrive(5, 1, 0, 5));
    		
    	}
    	
    }
}
