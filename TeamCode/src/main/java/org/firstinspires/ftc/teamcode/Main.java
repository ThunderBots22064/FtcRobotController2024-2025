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
        double xComponent = deadzone(gamepad1.left_stick_x, 0.15);
        // Note that the y-value on joysticks is negative for forward values so it's reversed here
        double yComponent = -deadzone(gamepad1.left_stick_y, 0.15);
        double magnitude = Math.sqrt(Math.pow(xComponent, 2) + Math.pow(yComponent, 2));
        double angle = Math.atan2(yComponent, xComponent);
        double turn = deadzone(gamepad1.right_trigger, 0.1) - deadzone(gamepad1.left_trigger, 0.1);
        drivetrain.drive(angle, magnitude, turn);
        //        telemetry.add("Slide target: ", slide.getTarget());
    }

    /**
     * Creates a deadzone around a value, usually used to prevent tiny error in inputs from causing outputs
     * @param val The base value
     * @param deadzone The minimum value that the value must be larger than to not fall to 0
     * @return A value that is either greater than deadzone or equal to 0
     */
    private static double deadzone(double val, double deadzone) {
        if (Math.abs(val) <= deadzone) {
            return 0.0;
        }
        return val;
    }
}
