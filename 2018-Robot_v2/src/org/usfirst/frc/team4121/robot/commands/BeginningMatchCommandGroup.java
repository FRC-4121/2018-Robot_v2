package org.usfirst.frc.team4121.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BeginningMatchCommandGroup extends CommandGroup {

    public BeginningMatchCommandGroup() {
        // Add Commands here:
        addSequential(new TakeInCubeCommandGroup());
        addSequential(new OpenServoCommand());
    }
}
