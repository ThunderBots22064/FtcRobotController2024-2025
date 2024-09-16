package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "_2324BLOCKCODE (Blocks to Java)")
public class Test extends LinearOpMode {

    //motar speeds
    double BL_Power;
    double FL_Power;
    double FR_Power;
    double BR_Power;
    double ARM_Power;
    // THIS IS TO SET MAX SPEED
    final double BL_Max_Power = 0.80;
    final double BR_Max_Power = 0.80;
    final double FL_Max_Power = 0.80;
    final double FR_Max_Power = 0.80;
    // this is for error correction
    double Fl_Error_Correction;
    double FR_Error_Correction;
    double Bl_Error_Correction;
    double BR_Error_Correction;

    public void Partal_reset_mem(){
        BL_Power = 0;
        FL_Power = 0;
        FR_Power = 0;
        BR_Power = 0;
    }

    /**
     * This function is executed when this OpMode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {

        // Setting up motars
        DcMotor BL = hardwareMap.get(DcMotor.class, "BL");
        DcMotor hook = hardwareMap.get(DcMotor.class, "Hook");
        DcMotor BR = hardwareMap.get(DcMotor.class, "BR");
        DcMotor FL = hardwareMap.get(DcMotor.class, "FL");
        DcMotor FR = hardwareMap.get(DcMotor.class, "FR");

        // reset the motar config (might do something idk)
        FL.resetDeviceConfigurationForOpMode();
        FR.resetDeviceConfigurationForOpMode();
        BL.resetDeviceConfigurationForOpMode();
        BR.resetDeviceConfigurationForOpMode();

        // sets this motar direction to reverse the movement so 1 and -1 reveser speed
        BR.setDirection(DcMotor.Direction.REVERSE); // doesnt work unless its like this
        //Witch one is reversed depends on rotation that the motar is placed in (rotate 180 around the motartr's power cable)




        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                //debug information
                telemetry.addData("Raw_left_stick_y", Float.toString(gamepad1.left_stick_y));
                telemetry.addData("Raw_left_stick_y", Float.toString(gamepad1.left_stick_x));
                telemetry.addData("\nbreak", "\n");
                telemetry.addData("Raw_right_stick_y", Float.toString(gamepad1.right_stick_y));
                telemetry.addData("Raw_right_stick_x", Float.toString(gamepad1.right_stick_x));
                telemetry.addData("\nbreak", "\n");
                telemetry.addData("FL_Max_Power", FL_Max_Power);
                telemetry.addData("FR_Max_Power", FR_Max_Power);
                telemetry.addData("FL_Max_Power", BL_Max_Power);
                telemetry.addData("FL_Max_Power", BR_Max_Power);
                telemetry.addData("\nbreak", "\n");
                telemetry.addData("FL_Max_Power", FL_Max_Power);
                telemetry.addData("FR_Max_Power", FR_Max_Power);
                telemetry.addData("FL_Max_Power", BL_Max_Power);
                telemetry.addData("FL_Max_Power", BR_Max_Power);



                // Controlls front  and back movment
                BL_Power += gamepad1.left_stick_y;
                BR_Power += gamepad1.left_stick_y;
                FL_Power += gamepad1.left_stick_y;
                FR_Power += gamepad1.left_stick_y;
                // For turning left and right
                BL_Power += gamepad1.left_trigger + gamepad1.right_trigger * -1;
                BR_Power += gamepad1.right_trigger + gamepad1.left_trigger * -1;
                FL_Power += gamepad1.left_trigger + gamepad1.right_trigger * -1;
                FR_Power += gamepad1.right_trigger + gamepad1.left_trigger * -1;
                //  For moving left and right is also compatle with forword and backword to allow moving in all directions
                BL_Power += gamepad1.right_stick_x;
                BR_Power += gamepad1.right_stick_x * -1;
                FL_Power += gamepad1.right_stick_x * -1;
                FR_Power += gamepad1.right_stick_x;






                hook.setPower(gamepad2.left_stick_y);
                BL.setPower(BL_Power * BL_Max_Power);
                BR.setPower(BR_Power * BR_Max_Power);
                FL.setPower(FL_Power * FL_Max_Power);
                FR.setPower(FR_Power * FR_Max_Power);
                // RESETS SO THAT THERE ISNT A INFINTE ADD LOOP
                Partal_reset_mem();
                telemetry.update();
            }
        }
    }

}