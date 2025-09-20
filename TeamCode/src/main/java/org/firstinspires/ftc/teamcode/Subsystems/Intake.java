package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.hardware.*;

public class Intake {
    final private CRServo intake;
    final private Servo wrist;

    private final double wristIncrement = 0.10;

    /**
     * Creates an Intake
     * @param hardwareMap the hardware mapping object
     */
    public Intake (HardwareMap hardwareMap) {
        intake = hardwareMap.get(CRServo.class, "intake");
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
     * Stops the intake system
     */
    public void stop() {
        intake.setPower(0.0);
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

    /**
     * Moves the wrist up by wristIncrement
     */
    public void wristUp() {
        double target = wrist.getPosition();

        if (Double.isNaN(target)) {
            target = 0;
        }

        target += wristIncrement;

        if (target > 1.0) {
            target = 1.0;
        } else if (target < 0) {
            target = 0;
        }

        wrist.setPosition(target);
    }

    /**
     * Moves the wrist down by wristIncrement
     */
    public void wristDown() {
        double target = wrist.getPosition();

        if (Double.isNaN(target)) {
            target = 0;
        }

        target -= wristIncrement;

        if (target > 1.0) {
            target = 1.0;
        } else if (target < 0) {
            target = 0;
        }

        wrist.setPosition(target);
    }

    /**
     * A getter for the wrist of the intake
     * @return the position of the wrist on [0.0, 1.0]
     */
    public double getWrist() {
        return wrist.getPosition();
    }
}