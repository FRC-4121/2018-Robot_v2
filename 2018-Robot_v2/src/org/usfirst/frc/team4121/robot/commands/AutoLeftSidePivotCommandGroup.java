package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */

public class AutoLeftSidePivotCommandGroup extends CommandGroup {

    public AutoLeftSidePivotCommandGroup() {
        // Add Commands here:
    	addSequential(new BeginningMatchCommandGroup());
    	addSequential(new toSwitchAutoCommand('L'));
    	//addSequential(new AutoDrive(130, -1, 0, 7)); //drive to switch used to
    	addSequential(new AutoDrive(83, -1, 0, 7));
    	addSequential(new AutoTurn(9, 3, 'L')); //used to be 98
    	addSequential(new EjectCubeCommand('L'));

        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
