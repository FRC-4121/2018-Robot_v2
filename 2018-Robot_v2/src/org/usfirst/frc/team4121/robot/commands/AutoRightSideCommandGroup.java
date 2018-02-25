package org.usfirst.frc.team4121.robot.commands;

import org.usfirst.frc.team4121.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSideCommandGroup extends CommandGroup {

    public AutoRightSideCommandGroup() {
        // Add Commands here:
    	
    	//
    	addSequential(new BeginningMatchCommandGroup());
    	//addSequential(new ShiftUpCommand());
    	addSequential(new ElevatorToSwitchCommand());
    	addSequential(new AutoDrive(147, -1, 0, 7)); //drive to switch
    	addSequential(new AutoTurn(-90, 3, 'R'));
    	addSequential(new EjectCubeCommand('R'));
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
