package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.hardware.*;

public class Claw {
    final private Servo claw;
    final private Servo wrist;

    public Claw (HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "claw");
        wrist = hardwareMap.get(Servo.class, "wrist");

        claw.setDirection(Servo.Direction.FORWARD);
        wrist.setDirection(Servo.Direction.FORWARD);
    }

    public void open() {
       claw.setPosition(1.0); //temp position
    }

    public void close() {
        claw.setPosition(0); //temp position
    }

    /**
     * Sets the position of the wrist as a value on its range
     * @param position a value between 0 and 1.00
     */
    public void setWrist(double position) {
        if (position > 1.00) {
            position = 1.00;
        } else if (position < 0) {
            position = 0;
        }
        wrist.setPosition(position);
    }
}