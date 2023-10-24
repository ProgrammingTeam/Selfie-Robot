// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlayAudio extends CommandBase {
  /** Creates a new PlayAudio. */
  //AudioPlayer audioPlayerTest = new AudioPlayer();
  
  public class AudioPlayer
	{
		// to store current position
		Long currentFrame;
		Clip clip;
		
		// current status of clip
		String status;
		
		AudioInputStream audioInputStream;
		static final String filePath = "C:/Users/Spencer Czarnezki/Downloads/Underwater.mp3";

		// constructor to initialize streams and clip
		public AudioPlayer()
		//banana
			throws UnsupportedAudioFileException,
			IOException, LineUnavailableException
		{
			System.out.println("Running");
			// create AudioInputStream object
			File holder = new File(filePath);
			audioInputStream =
					AudioSystem.getAudioInputStream(holder);
			
			// create clip reference
			clip = AudioSystem.getClip();
			
			// open audioInputStream to the clip
			clip.open(audioInputStream);
			clip.start();
		}
		public void play()
		{
			//start the clip
			clip.start();
			
			status = "play";
		System.out.println("Playing");
		}
   
  	}

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
	System.out.println("mmmmmmmmmmmmmmmmmm");
	String filePath = "C:/Users/Spencer Czarnezki/Downloads/Underwater.mp3";
	InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
	try {
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
		AudioFormat audioFormat = audioStream.getFormat();
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		Clip audioClip = (Clip) AudioSystem.getLine(info);
		//audioClip.addLineListener(null);
		audioClip.open(audioStream);
		audioClip.start();
	} catch (UnsupportedAudioFileException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (LineUnavailableException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}
