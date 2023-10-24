// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PIDSub;

public class setPositionA extends CommandBase {
  /** Creates a new setPositionA. */
  private PIDSub m_PID;
  private double poser;
  private  int m_position;


  public setPositionA(PIDSub SubPID, int place) {
    m_PID = SubPID;
    m_position = place;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(SubPID);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   // m_PID.setMotors(0); 
   m_PID.resetEncoder();
    System.out.println("setPositionA Initiated");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

        switch (m_position) {

      case 1: poser = 15;
              break;
      case 2: poser = -15;
              break;
     // default: PIDSpeed = 0.0;
      //        break;

    }
    m_PID.setPosition(poser);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //System.out.println("setPositionA Finished");
   return false;
  }
}
