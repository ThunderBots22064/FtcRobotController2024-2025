package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class ViperSlide {
    final private DcMotor slide;
    final private TouchSensor floorLimit;

    // Define the floor and ceiling of ViperSlide movement
    final int ceil = 300;
    final int floor = 0;

    private double speed = 0.20;

    public ViperSlide(HardwareMap hardwareMap) {
        slide = hardwareMap.get(DcMotor.class, "slide");
        slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slide.setDirection(DcMotorSimple.Direction.REVERSE);
        floorLimit = hardwareMap.get(TouchSensor.class, "floorLimit");
    }

    public void up() {
        slide.setPower(speed);
    }

    public void down() {
        if (floorLimit.isPressed()) {
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
    public void stop() {
        slide.setPower(0);
    }
}
