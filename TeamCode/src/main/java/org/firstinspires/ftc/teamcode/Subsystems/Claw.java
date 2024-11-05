package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.hardware.*;

// IMPLEMENT WRIST!!!!
public class Claw {
    private Servo claw;
    private Servo wrist;

    double wristPos = 0;
    double wristIncrement = 0.05;

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

    public void up() {
        if (wristPos + wristIncrement < 1.0) {
            wristPos += wristIncrement;
        } else {
            wristPos = 1.0;
        }
        wrist.setPosition(wristPos);
    }
    public void down() {
        if (wristPos - wristIncrement > 0) {
            wristPos -= wristIncrement;
        } else {
            wristPos = 0;
        }
        wrist.setPosition(wristPos);
    }
}
