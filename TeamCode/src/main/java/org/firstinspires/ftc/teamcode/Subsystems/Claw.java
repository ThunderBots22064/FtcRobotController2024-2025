package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.hardware.*;

// IMPLEMENT WRIST!!!!
public class Claw {
    private Servo claw;
    private Servo wrist;
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
}
