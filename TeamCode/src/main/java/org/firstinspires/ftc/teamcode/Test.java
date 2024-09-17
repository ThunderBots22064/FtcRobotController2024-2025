package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.VoltageSensor;

@TeleOp(name = "Java_Code(Josh)")
public class Test extends LinearOpMode {


    //motar speeds
    double BL_Power;
    double FL_Power;
    double FR_Power;
    double BR_Power;
    double HOOK_Power;
    // THIS IS TO SET MAX SPEED becacsue the motars are bad this is to acount it.
    final double BL_Max_Power = 0.100;
    final double BR_Max_Power = 0.80;
    final double FL_Max_Power = 0.80;
    final double FR_Max_Power = 0.80;
    // this is for error correction
    double BL_Error_Correction = .20;
    double BR_Error_Correction = .0;
    double FL_Error_Correction = .0;
    double FR_Error_Correction = .0;


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

        //Voltage sensor
//        VoltageSensor myControlHubVoltageSensor;
//        myControlHubVoltageSensor = hardwareMap.get(VoltageSensor.class, "Control Hub");
//        double presentVoltage;
        // Setting up motars
        DcMotor BL = hardwareMap.get(DcMotor.class, "BL");
        DcMotor Hook = hardwareMap.get(DcMotor.class, "Hook");
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
                telemetry.addData("BL_Max_Power", BL_Max_Power);
                telemetry.addData("BR_Max_Power", BR_Max_Power);
                telemetry.addData("\nbreak", "\n");
                telemetry.addData("FL_Error_Correction", FL_Error_Correction);
                telemetry.addData("FR_Error_Correction", FR_Error_Correction);
                telemetry.addData("BL_Error_Correction", BL_Error_Correction);
                telemetry.addData("BR_Error_Correction", BR_Error_Correction);



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




                //Motar logic with error correction
                BL_Power = BL_Power + (BL_Power * BL_Error_Correction);
                if (BL_Power > BL_Max_Power){
                    BL_Power = BL_Max_Power;
                }

                BR_Power = BR_Power + (BR_Power * BR_Error_Correction);
                if (BR_Power > BR_Max_Power){
                    BR_Power = BR_Max_Power;
                }

                FL_Power = FL_Power + (FL_Power * FL_Error_Correction);
                if (FL_Power > FL_Max_Power){
                    FL_Power = FL_Max_Power;
                }

                FR_Power = FR_Power + (FR_Power * FR_Error_Correction);
                if (FR_Power > FR_Max_Power){
                    FR_Power = FR_Max_Power;
                }

                Hook.setPower(HOOK_Power);
                BL.setPower(BL_Power);
                BR.setPower(BR_Power);
                FL.setPower(FL_Power);
                FR.setPower(FR_Power);
                // RESETS SO THAT THERE is not a infinte add loop
                Partal_reset_mem();
                telemetry.update();
            }
        }
    }

}