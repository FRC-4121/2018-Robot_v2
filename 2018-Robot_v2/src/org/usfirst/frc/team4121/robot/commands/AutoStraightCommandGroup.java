package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoStraightCommandGroup extends CommandGroup {

    public AutoStraightCommandGroup() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addSequential(new BeginningMatchCommandGroup());
    	addSequential(new AutoDrive(110, -1, 0, 7)); 
    }
}
