package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AuotScaleLeftOppositeSide extends CommandGroup {

    public AuotScaleLeftOppositeSide() {
    	
    	addSequential(new BeginningMatchCommandGroup());
    	
    	addSequential(new ElevatorToSwitchCommand());

    	addSequential(new AutoDrive(155, -1, 0, 10)); //drive to switch 
    	
    	addSequential(new AutoTurn (-90, 5, 'L', "Scale"));
    	
    	addSequential(new AutoDrive(80, -1, -90, 10));

    	addSequential(new ElevatorToScaleCommand());
    	
    	addSequential(new AutoDrive(80, -1, -90, 10));
    	
    	addSequential(new AutoTurn (0, 5, 'L', "Scale"));
    	
    	addSequential(new AutoDrive(10, -1, 0, 5));
    	
    	addSequential(new EjectCubeCommand('L'));
    	

    }
}
