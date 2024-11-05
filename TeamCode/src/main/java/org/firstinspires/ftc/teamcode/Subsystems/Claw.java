package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.hardware.*;

// IMPLEMENT WRIST!!!!
public class Claw {
    private Servo claw;
    private Servo wrist;
    public Claw (HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "claw");
        wrist = hardwareMap.get(Servo.class, "wrist");
    }

    public void open() {
       claw.setPosition(0); //temp position
    }

    public void close() {
        claw.setPosition(1); //temp position
    }
}
