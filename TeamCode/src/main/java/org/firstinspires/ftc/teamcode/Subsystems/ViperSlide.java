package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.*;

public class ViperSlide {
    final private DcMotor slide;

    private int target = 0;

    // Define the floor and ceiling of ViperSlide movement
    final int ceil = 300;
    final int floor = 0;

    public ViperSlide(HardwareMap hardwareMap) {
        slide = hardwareMap.get(DcMotor.class, "slide");
        slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slide.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public int getTarget() {
        return target;
    }

    public void run(double power) {
        if (slide.getCurrentPosition() >= ceil || slide.getCurrentPosition() <= floor) {
            stop();
            return;
        }
        slide.setPower(power);
    }

    public void stop() {
        slide.setPower(0);
    }
}
