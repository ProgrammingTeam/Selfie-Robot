// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PIDSub extends SubsystemBase {
 
private final CANSparkMax PIDTester =  new CANSparkMax(Constants.PIDTestController, MotorType.kBrushless);
private final SparkMaxPIDController m_PIDController = PIDTester.getPIDController();
private final RelativeEncoder Garbage = PIDTester.getEncoder();
private final double wheelSize = 0.75;
private final double kRot2feet = (2 * wheelSize * Math.PI) / 12 * 12;
public double kP, kI, kD;
public double kIz, kFF, kMaxOutput, kMinOutput;
double setPoint;

  /** Creates a new PIDSub. */
  public PIDSub() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
      // PID coefficients
      kP = 0.3; 
      kI = 1e-5;
      kD = 1e-4;  //0.5; 
      kIz = 0; 
      kFF = 0; 
      kMaxOutput = 0.3; 
      kMinOutput = -0.3;
  
      // set PID coefficients
      m_PIDController.setP(kP);
      m_PIDController.setI(kI);
      m_PIDController.setD(kD);
      m_PIDController.setIZone(kIz);
      m_PIDController.setFF(kFF);
      m_PIDController.setOutputRange(kMinOutput, kMaxOutput);
  
      // display PID coefficients on SmartDashboard
      SmartDashboard.putNumber("arm encoder rotations", Garbage.getPosition());

      SmartDashboard.putNumber("P Gain", kP);
      SmartDashboard.putNumber("I Gain", kI);
      SmartDashboard.putNumber("D Gain", kD);
      SmartDashboard.putNumber("I Zone", kIz);
      SmartDashboard.putNumber("Feed Forward", kFF);
      SmartDashboard.putNumber("Max Output", kMaxOutput);
      SmartDashboard.putNumber("Min Output", kMinOutput);
      SmartDashboard.putNumber("Set Rotations", 0);   
  }
  
  public void resetEncoder(){
    Garbage.setPosition(0);
  }

  public void setPosition(double setPoint) {
    System.out.println("setPosition Initiated");
        // read PID coefficients from SmartDashboard
        double p = SmartDashboard.getNumber("P Gain", 0);
        double i = SmartDashboard.getNumber("I Gain", 0);
        double d = SmartDashboard.getNumber("D Gain", 0);
        double iz = SmartDashboard.getNumber("I Zone", 0);
        double ff = SmartDashboard.getNumber("Feed Forward", 0);
        double max = SmartDashboard.getNumber("Max Output", 0);
        double min = SmartDashboard.getNumber("Min Output", 0);
        double Rotations = SmartDashboard.getNumber("Set Rotations", 0);
    
        // if PID coefficients on SmartDashboard have changed, write new values to controller
        if((p != kP)) { m_PIDController.setP(p); kP = p; }
        if((i != kI)) { m_PIDController.setI(i); kI = i; }
        if((d != kD)) { m_PIDController.setD(d); kD = d; }
        if((iz != kIz)) { m_PIDController.setIZone(iz); kIz = iz; }
        if((ff != kFF)) { m_PIDController.setFF(ff); kFF = ff; }
        if((max != kMaxOutput) || (min != kMinOutput)) { 
          m_PIDController.setOutputRange(min, max); 
          kMinOutput = min; kMaxOutput = max;} 
    System.out.println("Setpoint = "+setPoint);
    System.out.println("Garbage Position = "+Garbage.getPosition());

    m_PIDController.setReference(setPoint,CANSparkMax.ControlType.kPosition);
    }
}
