package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.eventloop.opmode.*;

@Autonomous(name = "RedNetAuto")
public class NetAuto extends LinearOpMode{
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
        while (opModeIsActive() && (slide.getCurrentPosition() < ceil && slide.getCurrentPosition() > floor)) {
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

        /*
        //red net zone side (complex w/ pre-load)
        forward(0.15,0.25);
        strafe_Left(1.5,0.25);
        turn_Left(0.25, 0.25);
        slide_Up(1,0.25);
        wrist.setPosition(0);
        wrist.setPosition(-0.15);
        claw.setPosition(0.7);
        backward(0.15, 0.25);
        turn_Right(0.25, 0.25);
        forward(1.25,0.25);
        strafe_Right(1.5,0.25);
        stop(2);
         */

        /*
        //red net zone side (simple park) -- draft 1
        strafe_Left(0.5, 0.75);
        forward(1.5,0.75);
        strafe_Right(0.5, 0.75);
        slide_Up(0.5,0.75); // too long, maybe half or third
        stop(2);
         */

        //edited version
        strafe_Left(0.5, 0.75);
        forward(1.3,0.75);
        stop(1);
        strafe_Right(0.3, 0.75);
        stop(1);
        turn_Right(0.4, 0.75);
        slide_Up(0.15,0.75); // too long, maybe half or third
        stop(2);
    }

}
