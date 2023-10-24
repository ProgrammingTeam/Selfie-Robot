// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.SwerveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ZeroHeadingCmd extends InstantCommand {
  
  private SwerveSubsystem z_subsystem;

  public ZeroHeadingCmd(SwerveSubsystem z_sub) {
    // Use addRequirements() here to declare subsystem dependencies.
    z_subsystem = z_sub;
    addRequirements(z_sub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    z_subsystem.zeroHeading();

  }
}
