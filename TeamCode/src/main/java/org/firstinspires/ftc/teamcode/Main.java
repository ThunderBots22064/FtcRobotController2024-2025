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
    Hook hook;

    boolean fieldOriented = false;
    OnPress orientSwitch = new OnPress();
    OnPress orientReset = new OnPress();

    @Override
    public void init() {
        slide = new ViperSlide(hardwareMap);
        hook = new Hook(hardwareMap);
        drivetrain = new Drivetrain(hardwareMap);
        imu = new Imu(hardwareMap);
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
        double yComponent = -deadzone(gamepad1.left_stick_y, 0.15);
        double magnitude = Math.sqrt(Math.pow(xComponent, 2) + Math.pow(yComponent, 2));
        double angle = Math.atan2(yComponent, xComponent);
        double turn = deadzone(gamepad1.right_trigger, 0.1) - deadzone(gamepad1.left_trigger, 0.1);

        // Use the IMU to calculate the desired angle in field-oriented mode
        if (fieldOriented) {
            angle -= imu.getRawHeading();
        }

        drivetrain.drive(angle, magnitude, turn);
        //        telemetry.add("Slide target: ", slide.getTarget());


        /* --- GAMEPAD 2 --- */
        slide.run(deadzone(gamepad2.right_stick_y, 0.1));

        if (gamepad2.y) {
            hook.up();
        } else if (gamepad2.a) {
            hook.down();
        } else {
            hook.stop();
        }
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
