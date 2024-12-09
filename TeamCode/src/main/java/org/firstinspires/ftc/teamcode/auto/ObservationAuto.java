package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.eventloop.opmode.*;

@Autonomous(name = "RedObservationAuto")
public class ObservationAuto extends LinearOpMode{
    private DcMotor FR;
    private DcMotor FL;
    private DcMotor BR;
    private DcMotor BL;
    private DcMotor slide;
    private DcMotor hook;
    private Servo claw;
    private Servo wrist;
    final int ceil = 300;
    final int floor = 0;

    public final static DcMotorSimple.Direction FL_DIR = DcMotorSimple.Direction.FORWARD;
    public final static DcMotorSimple.Direction FR_DIR = DcMotorSimple.Direction.FORWARD;
    public final static DcMotorSimple.Direction BL_DIR = DcMotorSimple.Direction.REVERSE;
    public final static DcMotorSimple.Direction BR_DIR = DcMotorSimple.Direction.FORWARD;

    public void config(HardwareMap hardwareMap){
        FR = hardwareMap.get(DcMotor.class, "frontRight");
        FL = hardwareMap.get(DcMotor.class, "frontLeft");
        BR = hardwareMap.get(DcMotor.class, "backRight");
        BL = hardwareMap.get(DcMotor.class, "backLeft");
        slide = hardwareMap.get(DcMotor.class, "slide");
        hook = hardwareMap.get(DcMotor.class, "hook");
        claw = hardwareMap.get(Servo.class, "claw");
        wrist = hardwareMap.get(Servo.class, "wrist");
    }

    //Stop function to stop power to wheels for inputted time (in seconds)
    private void stop(int time__in_seconds_) {
        BL.setPower(0);
        BR.setPower(0);
        FL.setPower(0);
        FR.setPower(0);
        sleep(time__in_seconds_ * 1000);
    }
    // Forward function to move wheels forward for inputted time and with inputted power
    private void forward(double time__in_seconds_, double power) {
        BL.setPower(power);
        BR.setPower(power);
        FL.setPower(power);
        FR.setPower(power);
        sleep((long) (time__in_seconds_ * 1000));
    }
    // Backward function to move wheels backward for inputted time and with inputted power
    private void backward(double time__in_seconds_, double power){
        BL.setPower(-power);
        BR.setPower(-power);
        FR.setPower(-power);
        FL.setPower(-power);
        sleep((long) (time__in_seconds_ * 1000));
    }
    // Turn left function to turn left for inputted time and with inputted power
    private void turn_Left(double time__in_seconds_, double power) {
        BL.setPower(-power);
        BR.setPower(power);
        FR.setPower(power);
        FL.setPower(-power);
        sleep((long) (time__in_seconds_ * 1000));
    }
    // Turn right function to turn right for inputted time and with inputted power
    private void turn_Right(double time__in_seconds_, double power){
        BL.setPower(power);
        BR.setPower(-power);
        FR.setPower(-power);
        FL.setPower(power);
        sleep((long) (time__in_seconds_ * 1000));
    }

    private void strafe_Right(double time__in_seconds_, double power){
        BL.setPower(-power);
        BR.setPower(power);
        FR.setPower(-power);
        FL.setPower(power);
        sleep((long) (time__in_seconds_ * 1000));
    }

    private void strafe_Left(double time__in_seconds_, double power){
        BL.setPower(power);
        BR.setPower(-power);
        FR.setPower(power);
        FL.setPower(-power);
        sleep((long) (time__in_seconds_ * 1000));
    }

    private void slide_Up(double time__in_seconds_, double power){
        while (slide.getCurrentPosition() < ceil || slide.getCurrentPosition() > floor) {
            slide.setPower(power);
            sleep((long) (time__in_seconds_ * 1000));
        }
        slide.setPower(0);
        sleep( 2000);
    }

    public void runOpMode(){
        config(hardwareMap);
        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        FL.setDirection(FL_DIR);
        FR.setDirection(FR_DIR);
        BL.setDirection(BL_DIR);
        BR.setDirection(BR_DIR);

        slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slide.setDirection(DcMotorSimple.Direction.FORWARD);


        //red observation zone side (simple park)
        forward(0.1, 0.75); // original time = 0.15s
        turn_Right(0.4, 0.75);
        forward(0.5,0.75);
        stop(2);
    }

}
