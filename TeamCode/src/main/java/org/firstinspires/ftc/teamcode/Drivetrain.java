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
     * drive - A function to drive the robot in a certain direction
     * angle - A value from 0 to 360 degrees, 0 degrees is a heading straight foward relative to the robot, 90 degrees is straight left
     * power - A double from -1.0 to 1.0 specifying the power
     */
    public void drive(int angle, double power) {
        // Convert the angle into radians
        double theta = Math.toRadians(angle);
        // Rotate the angle 45 degrees over (PI / 4)
        theta -= (Math.PI / 4.0);

        double forward = Math.cos(theta) * power;
        double side = Math.sin(theta) * power;

        motors[0].setPower(forward);
        motors[4].setPower(forward);
        motors[1].setPower(side);
        motors[2].setPower(side); 
    }

    public void stop() {
        for (DcMotor motor : motors) {
            motor.setPower(0);
        }
    }

}
