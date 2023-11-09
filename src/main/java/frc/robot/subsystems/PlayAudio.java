// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import java.io.File;
import java.io.IOException;
import java.lang.System.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PlayAudio extends SubsystemBase {

  Long currentFrame;
  Clip clip;

  // current status of clip
  String status;

  AudioInputStream audioInputStream;
  static String filePath;

  public PlayAudio() {
    // create AudioInputStream object
    try {
      filePath = "src/main/java/frc/robot/SnapSound.wav";

      audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
      // create clip reference
      clip = AudioSystem.getClip();

      // open audioInputStream to the clip
      clip.open(audioInputStream);
      play();
    } catch (Exception e) {
    }
  }

  public void play() {
    // start the clip
    clip.start();

    status = "play";
  }

  public void stop() {
    currentFrame = 0L;
    clip.stop();
    clip.close();
  }

  public CommandBase PressA() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          new PlayAudio();
          System.out.println("!!!Did you know that PlayAudio actually ran!!!");
        });
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}
