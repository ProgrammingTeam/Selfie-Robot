package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.SwerveJoystickCmd;
import frc.robot.commands.WritePicture;
import frc.robot.commands.ZeroHeadingCmd;
import frc.robot.commands.setPositionA;
import frc.robot.subsystems.PIDSub;
import frc.robot.subsystems.PlayAudio;
import frc.robot.subsystems.SwerveSubsystem;

public class RobotContainer {

        // Create instance of each Subsystem:
        private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
        private final PlayAudio m_PlayAudio = new PlayAudio();
        private final PIDSub m_PID = new PIDSub();
        // Create instance of each Controller/Joystick:
        private final Joystick driverJoytick = new Joystick(OIConstants.kDriverControllerPort);
        private final CommandXboxController m_driverController = new CommandXboxController(
                        OIConstants.kDriverControllerPort);

        public RobotContainer() {

                // Define Controller/Joystick mapping within RobotContainer and call
                // "configureButtonBindings()":
                swerveSubsystem.setDefaultCommand(new SwerveJoystickCmd(
                                swerveSubsystem,
                                () -> driverJoytick.getRawAxis(OIConstants.kDriverYAxis),
                                () -> driverJoytick.getRawAxis(OIConstants.kDriverXAxis),
                                () -> driverJoytick.getRawAxis(OIConstants.kDriverRotAxis),
                                () -> !driverJoytick.getRawButton(OIConstants.kDriverFieldOrientedButtonIdx)));

                configureButtonBindings();
        }

        // Define Controller/Joystick button actions:
        private void configureButtonBindings() {

                new JoystickButton(driverJoytick, 5).onTrue(new ZeroHeadingCmd(swerveSubsystem));
                // PID Comands Bellow
                new JoystickButton(driverJoytick, 3).onTrue(new setPositionA(m_PID, 1));
                // m_driverController.b().onFalse(new setPositionA(m_PID, 0));
                new JoystickButton(driverJoytick, 4).onTrue(new setPositionA(m_PID, 2));
                // m_driverController.a().onFalse(new setPositionA(m_PID, 0));
                new JoystickButton(driverJoytick, 1).onTrue(new WritePicture());

                m_driverController.a().whileTrue(m_PlayAudio.PressA());
        }

        // Run AutonomousCommand:

}