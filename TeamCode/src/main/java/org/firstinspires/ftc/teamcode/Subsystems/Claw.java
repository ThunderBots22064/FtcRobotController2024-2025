package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.hardware.*;

public class Claw {
    private Servo claw;
    public Claw (HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "claw");
    }

    public void open() {
       claw.setPosition(0); //temp position
    }

    public void close() {
        claw.setPosition(1); //temp position
    }
}
