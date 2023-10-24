package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class SwerveSubsystem extends SubsystemBase {

    // Define each SwerveModule:
    private final SwerveModule frontLeft = new SwerveModule(
            DriveConstants.kFrontLeftDriveMotorPort,
            DriveConstants.kFrontLeftTurningMotorPort,
            DriveConstants.kFrontLeftDriveEncoderReversed,
            DriveConstants.kFrontLeftTurningEncoderReversed,
            DriveConstants.kFrontLeftDriveAbsoluteEncoderPort,
            DriveConstants.kFrontLeftDriveAbsoluteEncoderOffsetRad,
            DriveConstants.kFrontLeftDriveAbsoluteEncoderReversed);

    private final SwerveModule frontRight = new SwerveModule(
            DriveConstants.kFrontRightDriveMotorPort,
            DriveConstants.kFrontRightTurningMotorPort,
            DriveConstants.kFrontRightDriveEncoderReversed,
            DriveConstants.kFrontRightTurningEncoderReversed,
            DriveConstants.kFrontRightDriveAbsoluteEncoderPort,
            DriveConstants.kFrontRightDriveAbsoluteEncoderOffsetRad,
            DriveConstants.kFrontRightDriveAbsoluteEncoderReversed);

    private final SwerveModule backLeft = new SwerveModule(
            DriveConstants.kBackLeftDriveMotorPort,
            DriveConstants.kBackLeftTurningMotorPort,
            DriveConstants.kBackLeftDriveEncoderReversed,
            DriveConstants.kBackLeftTurningEncoderReversed,
            DriveConstants.kBackLeftDriveAbsoluteEncoderPort,
            DriveConstants.kBackLeftDriveAbsoluteEncoderOffsetRad,
            DriveConstants.kBackLeftDriveAbsoluteEncoderReversed);

    private final SwerveModule backRight = new SwerveModule(
            DriveConstants.kBackRightDriveMotorPort,
            DriveConstants.kBackRightTurningMotorPort,
            DriveConstants.kBackRightDriveEncoderReversed,
            DriveConstants.kBackRightTurningEncoderReversed,
            DriveConstants.kBackRightDriveAbsoluteEncoderPort,
            DriveConstants.kBackRightDriveAbsoluteEncoderOffsetRad,
            DriveConstants.kBackRightDriveAbsoluteEncoderReversed);


    // Define gyro used for orientation tracking:
    private final AHRS gyro = new AHRS(SPI.Port.kMXP);


    public SwerveSubsystem() {

    // Create Thread in SwerveSubsystem to delay resetting gyro at startup.
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                zeroHeading();
            } catch (Exception e) {
            }
        }).start();
    }

    // Method to reset chassis orientation.
    public void zeroHeading() {
        gyro.reset();
    }

    // Method to gyro heading between 0 and 360 degrees.
    public double getHeading() {
        return Math.IEEEremainder(-gyro.getAngle(), 360);
    }

    // Translate gyro heading into a point on unit circle.
    public Rotation2d getRotation2d() {
        return Rotation2d.fromDegrees(getHeading());
    }

    @Override
    public void periodic() {

        SmartDashboard.putNumber("Robot Heading", getHeading());
        SmartDashboard.putNumber("Absolute Encoder Value - FL", frontLeft.getAbsoluteEncoderRad());
        SmartDashboard.putNumber("Absolute Encoder Value - FR", frontRight.getAbsoluteEncoderRad());
        SmartDashboard.putNumber("Absolute Encoder Value - RL", backLeft.getAbsoluteEncoderRad());
        SmartDashboard.putNumber("Absolute Encoder Value - RR", backRight.getAbsoluteEncoderRad());
    }

    public void stopModules() {
      
        frontLeft.stop();
        frontRight.stop();
        backLeft.stop();
        backRight.stop();
    }

    public void setModuleStates(SwerveModuleState[] desiredStates) {

        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, DriveConstants.kPhysicalMaxSpeedMetersPerSecond);
        frontLeft.setDesiredState(desiredStates[0]);
        frontRight.setDesiredState(desiredStates[1]);
        backLeft.setDesiredState(desiredStates[2]);
        backRight.setDesiredState(desiredStates[3]);
    }
}
