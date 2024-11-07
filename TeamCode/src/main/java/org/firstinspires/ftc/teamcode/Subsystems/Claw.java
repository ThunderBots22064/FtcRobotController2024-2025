package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.hardware.*;

// IMPLEMENT WRIST!!!!
public class Claw {
    final private Servo claw;
    final private CRServo wrist;

    public Claw (HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "claw");
        wrist = hardwareMap.get(CRServo.class, "wrist");

        claw.setDirection(Servo.Direction.FORWARD);
        wrist.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void open() {
       claw.setPosition(1.0); //temp position
    }

    public void close() {
        claw.setPosition(0); //temp position
    }

    public void tilt(double speed) {
        if (speed > 1.0) {
            speed = 1.0;
        } else if (speed < -1.0) {
            speed = -1.0;
        }
        wrist.setPower(speed);
    }
}