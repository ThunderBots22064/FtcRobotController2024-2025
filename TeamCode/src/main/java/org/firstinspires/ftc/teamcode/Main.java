package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.*;
import org.firstinspires.ftc.teamcode.Utils.*;

@TeleOp
public class Main extends OpMode {
    ViperSlide slide;
    Drivetrain drivetrain;
    Imu imu;
//    Claw claw;

    boolean fieldOriented = false;
    OnPress orientSwitch = new OnPress();
    OnPress orientReset = new OnPress();

    double wristPosition = 0;

    @Override
    public void init() {
        slide = new ViperSlide(hardwareMap);
        drivetrain = new Drivetrain(hardwareMap, 0.60);
        imu = new Imu(hardwareMap);
//        intake = new Intake(hardwareMap);
    }

    @Override
    public void loop() {
        // Toggle field oriented modes only when both bumpers are pressed (Without repeatedly toggling)
        if (orientSwitch.pressed(gamepad1.left_bumper && gamepad1.right_bumper)) {
            fieldOriented = !fieldOriented;
        }

        // If 'B' has been pressed and the robot is in field-oriented mode reset the IMU
        if (orientReset.pressed(gamepad1.b) && fieldOriented) {
            imu.resetHeading();
        }

//        Calculate the value from the controller to give to the drivetrain
        double xComponent = deadzone(gamepad1.right_stick_x, 0.15);
        // Note that the y-value on joysticks is negative for forward values so it's reversed here
        double yComponent = deadzone(-gamepad1.left_stick_y, 0.15);
        double magnitude = Math.hypot(xComponent, yComponent);
        double angle = Math.atan2(yComponent, xComponent);
        double turn = deadzone(gamepad1.right_trigger, 0.1) - deadzone(gamepad1.left_trigger, 0.1);

        telemetry.addData("Raw Angle (DEG)", Math.toDegrees(angle));

        // Use the IMU to calculate the desired angle in field-oriented mode
        if (fieldOriented) {
            angle -= imu.getRawHeading();
        }

        telemetry.addData("Raw Heading (DEG)", Math.toDegrees(imu.getRawHeading()));
        telemetry.addData("Adjusted Angle (DEG)", Math.toDegrees(angle));

        boolean slowMode = gamepad1.y;

        drivetrain.drive(angle, magnitude, turn, slowMode);
        //        telemetry.add("Slide target: ", slide.getTarget());


        /* --- GAMEPAD 2 --- */
        double slideInput = deadzone(-gamepad2.right_stick_y, 0.1);
        if (slideInput > 0) {
            slide.up();
        } else if (slideInput < 0) {
            slide.down();
        } else {
            slide.stop();
        }
//        if (gamepad2.right_trigger > 0.5) {
//            intake.run(true);
//        } else if (gamepad2.left_trigger > 0.5) {
//            intake.run(false);
//        }
//
//        double wristInput = deadzone(-gamepad2.left_stick_y, 0.1);
//        if (wristInput > 0) {
//            wristPosition += 0.01;
//        } else if (wristInput < 0) {
//            wristPosition -= 0.01;
//        }
//        if (wristPosition > 1.00) {
//            wristPosition = 1.00;
//        } else if (wristPosition < 0) {
//            wristPosition = 0;
//        }
//        intake.setWrist(wristPosition);
        telemetry.update();
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
