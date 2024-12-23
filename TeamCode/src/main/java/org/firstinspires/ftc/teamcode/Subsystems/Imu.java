package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

// This subsystem maintains the Inertial Measurement Unit or IMU for short
// It assists in getting the robots orientation
public class Imu {
    final private IMU imu;
    final private boolean safeMode;

    private final int cooldownTicks = 5;

    private int ticks = cooldownTicks;
    private double headingStore;

    /**
     * Creates an IMU
     * @param hardwareMap the hardware mapping object
     */
    public Imu(HardwareMap hardwareMap) {
        this(hardwareMap, false);
    }

    /**
     * Creates an IMU
     * @param hardwareMap the hardware mapping object
     * @param safeMode when enabled slows down the number of requests sent to the IMU
     */
    public Imu(HardwareMap hardwareMap, boolean safeMode) {
        imu = hardwareMap.get(IMU.class, "imu");

        imu.initialize(
                new IMU.Parameters(new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                        RevHubOrientationOnRobot.UsbFacingDirection.UP))
        );

        this.safeMode = safeMode;
    }

    /**
     * Gets the raw heading of the IMU
     * @return The heading between Pi and -Pi radians (Right hand rule applies, 0 rad. is directly forward)
     */
    public double getRawHeading() {
        if (!safeMode) {
            return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        }

        if (ticks < cooldownTicks) {
            ticks++;
        } else {
            ticks = 0;
            headingStore = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        }
        return headingStore;
    }

    /**
     * Gets a rotated version of the heading
     * @return A heading between 0 and 2*Pi radians, Pi/2 rad. is directly forward
     */
    public double getRotatedHeading() {
        double raw = getRawHeading();
        // Rotate the angle Pi/2 radians anticlockwise
        raw += Math.PI / 2.0;

        // If the value is negative then use then add 2Pi to get a value that is positive but representative of the same angle
        if (raw < 0) {
            raw += 2 * Math.PI;
        }

        return raw;
    }

    /**
     * Resets the IMU Heading/Yaw
     */
    public void resetHeading() {
        imu.resetYaw();
    }
}
