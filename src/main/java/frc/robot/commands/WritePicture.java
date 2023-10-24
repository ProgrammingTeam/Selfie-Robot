// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.opencv.core.CvException;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class WritePicture extends InstantCommand {

  private static String loc = "/U/";
  private static String full_path;

  public WritePicture() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    try{ 
      System.out.println("Initiating WritePicture Sequence...");
      
      LocalDateTime current = LocalDateTime.now();
      DateTimeFormatter format = DateTimeFormatter.ofPattern("dd_MM_yyyy_HHmmss");
      String formatedDateTime = current.format(format);

      full_path = loc + formatedDateTime +".jpeg";

      System.out.println(full_path);


      CvSink Sink = CameraServer.getVideo("DeadEye");
      
      //CvSource stream = CameraServer.putVideo("bob", 640, 480);

      Mat matrix = new Mat();

      Sink.grabFrame(matrix, 1.0);
      Imgcodecs.imwrite(full_path, matrix);
    
      System.out.println("Successfully wrote to the file.");
  
     } catch (OutOfMemoryError | CvException e) {
      System.out.println("ERRRRRRRRROR");
     }
  }
}
