package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.*;
import java.lang.Math.*;

public class Drivetrain {
    private DcMotor[] motors = new DcMotor[4];

    public Drivetrain(HardwareMap hardwareMap) {
        String[] motorMaps = {"frontLeft", "frontRight",
                               "backLeft", "backRight"};
        for (int i = 0; i < motors.length; i++) {
            motors[i] = hardwareMap.get(DcMotor.class, motorMaps[i]);
        }
    }

    /**
     * A function to drive the robot in a certain direction
     * @param angle The angle to drive the robot in radians, positive values are clockwise from the positive x-axis, e.g. +Pi/2 rad. is forward
     * @param power A value from -1.0 to 1.0 specifying the power
     */
    public void drive(double angle, double power) {
        // Rotate the angle PI/4 rad. clockwise (Anticlockwise is superior to Counterclockwise)
        double theta = angle - (Math.PI / 4.0);

        double forward = Math.cos(theta) * power;
        double side = Math.sin(theta) * power;

        motors[1].setPower(forward);
        motors[2].setPower(forward);
        motors[0].setPower(side);
        motors[4].setPower(side);
    }

    public void stop() {
        for (DcMotor motor : motors) {
            motor.setPower(0);
        }
    }

}
