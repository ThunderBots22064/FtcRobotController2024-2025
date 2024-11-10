package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.*;

import java.util.function.Consumer;

public class ViperSlide {
    final private DcMotor slide;

    // Define the floor and ceiling of ViperSlide movement
    final int ceil = 300;
    final int floor = 0;

    private int target = 0;

    public ViperSlide(HardwareMap hardwareMap) {
        slide = hardwareMap.get(DcMotor.class, "slide");
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide.setTargetPosition(0);
        slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slide.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void up() {
        if (slide.isBusy()) {
            return;
        }
        target += 10;
        if (target > ceil) {
            target = ceil;
        }
        slide.setTargetPosition(target);
    }

    public void down() {
        if (slide.isBusy()) {
            return;
        }
        target -= 10;
        if (target > ceil) {
            target = ceil;
        }
        slide.setTargetPosition(target);
    }

    /**
     * Sets the position of the ViperSlide as a FRACTION of the total range
     * @param position a value representing the percentage of the total range to go to 0 is the floor while 1 is the ceiling
     */
    public void setPosition(double position) {
        if (position > 1.00) {
            position = 1.00;
        } else if (position < 0) {
            position = 0;
        }
        int encoderVal = (int) ((ceil - floor) * position) + floor;
        slide.setTargetPosition(encoderVal);
    }

    public void stop() {
        slide.setPower(0);
    }
}
