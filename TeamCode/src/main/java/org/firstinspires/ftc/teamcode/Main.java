package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Main extends OpMode {
    ViperSlide slide;
    Drivetrain drivetrain;

    @Override
    public void init() {
        slide = new ViperSlide(hardwareMap);
        drivetrain = new Drivetrain(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.y) {
            slide.up();
        } else if (gamepad1.a) {
            slide.down();
        } else {
            slide.stop();
        }

//        Calculate the value from the controller to give to the drivetrain
        double xComponent = gamepad1.left_stick_x;
        double yComponent = gamepad1.left_stick_y;
        double magnitude = Math.sqrt(Math.pow(xComponent, 2) + Math.pow(yComponent, 2));
        double angle = Math.atan2(yComponent, xComponent);
        drivetrain.drive(angle, magnitude);
        //        telemetry.add("Slide target: ", slide.getTarget());
    }
}
