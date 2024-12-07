package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.hardware.*;

public class Intake {
    final private CRServo intake;
    final private Servo wrist;

    /**
     * Creates an Intake
     * @param hardwareMap the hardware mapping object
     */
    public Intake (HardwareMap hardwareMap) {
        intake = hardwareMap.get(CRServo.class, "claw");
        wrist = hardwareMap.get(Servo.class, "wrist");

        intake.setDirection(DcMotor.Direction.FORWARD);
        wrist.setDirection(Servo.Direction.FORWARD);
    }

    /**
     * Runs the intake system
     * @param in if true intakes, otherwise outtakes
     */
    public void run(boolean in) {
        if (in) {
            intake.setPower(1.0);
        } else {
            intake.setPower(-1.0);
        }
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