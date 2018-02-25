package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideNoTurnCommandGroup extends CommandGroup {

    public AutoRightSideNoTurnCommandGroup() {
    	//
    	addSequential(new BeginningMatchCommandGroup());
    	//addSequential(new ShiftUpCommand());
    	addSequential(new ElevatorToSwitchCommand());
    	addSequential(new AutoDrive(90, -1, 0, 7)); //drive to switch
    	addSequential(new EjectCubeCommand('R'));
    }
}
