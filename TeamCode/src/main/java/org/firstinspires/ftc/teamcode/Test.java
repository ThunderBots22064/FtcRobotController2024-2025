package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "_2324BLOCKCODE (Blocks to Java)")
public class Test extends LinearOpMode {

    double BL2;
    double FL2;
    double Arm_speed;
    double MAX_SPEED;
    double FR2;
    double BR2;
    double error_corection;

    /**
     * This function is executed when this OpMode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {

        DcMotor BL = hardwareMap.get(DcMotor.class, "BL");
        DcMotor hook = hardwareMap.get(DcMotor.class, "Hook");
        DcMotor BR = hardwareMap.get(DcMotor.class, "BR");
        DcMotor FL = hardwareMap.get(DcMotor.class, "FL");
        DcMotor FR = hardwareMap.get(DcMotor.class, "FR");

        FL.resetDeviceConfigurationForOpMode();
        FR.resetDeviceConfigurationForOpMode();
        BL.resetDeviceConfigurationForOpMode();
        BR.resetDeviceConfigurationForOpMode();

//        FL.setDirection(DcMotor.Direction.REVERSE);
        BR.setDirection(DcMotor.Direction.REVERSE);
//        BL.setDirection(DcMotor.Direction.REVERSE);


        // THIS IS TO SET MAX SPEED
        MAX_SPEED = 0.35;
        Arm_speed = 0.25;

        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                telemetry.addData("Raw_left_stick_y", Float.toString(gamepad1.left_stick_y));
                telemetry.addData("Raw_left_stick_y", Float.toString(gamepad1.left_stick_x));
                telemetry.addData("Raw_right_stick_y", Float.toString(gamepad1.right_stick_y));
                telemetry.addData("Raw_right_stick_x", Float.toString(gamepad1.right_stick_x));
                telemetry.update();
                // FROINT AND BACK
                BL2 += gamepad1.left_stick_y;
                BR2 += gamepad1.left_stick_y;
                FL2 += gamepad1.left_stick_y;
                FR2 += gamepad1.left_stick_y;
                // TURN LEFT AND RIGHT
                BL2 += gamepad1.left_trigger + gamepad1.right_trigger * -1;
                BR2 += gamepad1.right_trigger + gamepad1.left_trigger * -1;
                FL2 += gamepad1.left_trigger + gamepad1.right_trigger * -1;
                FR2 += gamepad1.right_trigger + gamepad1.left_trigger * -1;
                //  MOVE LEFT AND RIGHT
                BL2 += gamepad1.right_stick_x;
                BR2 += gamepad1.right_stick_x * -1;
                FL2 += gamepad1.right_stick_x * -1;
                FR2 += gamepad1.right_stick_x;
                // CHANGE MAX SPEED
                if (gamepad1.dpad_down) {
                    MAX_SPEED = MAX_SPEED * 100;
                    MAX_SPEED = MAX_SPEED - 10;
                    MAX_SPEED = MAX_SPEED / 100;
                    sleep(100);
                }
                if (gamepad1.dpad_up) {
                    MAX_SPEED = MAX_SPEED * 100;
                    MAX_SPEED = MAX_SPEED + 10;
                    MAX_SPEED = MAX_SPEED / 100;
                    sleep(100);
                }
                // this is for paper arm launcher
                if (gamepad1.dpad_left) {
                    error_corection = error_corection - 0.1;
                }
                if (gamepad1.dpad_right) {
                    error_corection = error_corection + 0.1;
                }
                // this controlls arm speed
                if (gamepad2.b) {
                    if (Arm_speed == 0.25) {
                        Arm_speed = 0.75;
                    } else {
                        Arm_speed = 0.25;
                    }
                    sleep(100);
                }
                // enabling debug mode
                // Printing stuff to the screens
                // True will actvate debug info
                hook.setPower(gamepad2.left_stick_y);
                BL.setPower(BL2 * MAX_SPEED);
                BR.setPower(BR2 * MAX_SPEED);
                FL.setPower(FL2 * MAX_SPEED);
                FR.setPower(FR2 * MAX_SPEED);
                // RESETS SO THAT THERE ISNT A INFINTE ADD LOOP
                BL2 = 0;
                BR2 = 0;
                FL2 = 0;
                FR2 = 0;
                telemetry.update();
            }
        }
    }

}