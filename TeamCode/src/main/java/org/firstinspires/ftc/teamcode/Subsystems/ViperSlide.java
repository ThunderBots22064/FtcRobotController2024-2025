package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class ViperSlide {
    private final DcMotor slide;
    private final TouchSensor floorLimit;

    // Define the floor and ceiling of ViperSlide movement
    private final int ceil = 300;
    private final int floor = 0;

    private final double speed = 0.20;
    private boolean homed = false;

    /**
     * Creates a ViperSlide
     * @param hardwareMap the hardware mapping object
     */
    public ViperSlide(HardwareMap hardwareMap) {
        slide = hardwareMap.get(DcMotor.class, "slide");

        slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slide.setDirection(DcMotorSimple.Direction.REVERSE);
        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        floorLimit = hardwareMap.get(TouchSensor.class, "floorLimit");
    }

    /**
     * Attempts to home the ViperSlide, must be called repeatedly.
     */
    public void home() {
        if (floorLimit.isPressed()) {
            slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            stop();

            homed = true;
        }
        if (!homed) {
            slide.setPower(-0.2);
        }
    }

    /**
     * Moves the ViperSlide up at its designated speed
     */
    public void up() {
        slide.setPower(speed);
    }

    /**
     * Moves the ViperSlide down at its designated speed
     */
    public void down() {
        if (slide.getCurrentPosition() < 15) {
            stop();
            return;
        }
        slide.setPower(-speed);
    }

    /**
     * Sets the position of the ViperSlide as a FRACTION of the total range
     * @param position a value representing the percentage of the total range to go to 0 is the floor while 1 is the ceiling
     */
  /*  public void setPosition(double position) {
        if (position > 1.00) {
            position = 1.00;
        } else if (position < 0) {
            position = 0;
        }
        int encoderVal = (int) ((ceil - floor) * position) + floor;
        slide.setTargetPosition(encoderVal);
    }
*/

    /**
     * Stops the ViperSlide
     */
    public void stop() {
        slide.setPower(0);
    }
}
