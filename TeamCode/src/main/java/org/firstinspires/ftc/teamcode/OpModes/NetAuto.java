package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.*;

import org.firstinspires.ftc.teamcode.Subsystems.*;

@Autonomous(name = "RedNetAuto")
public class NetAuto extends LinearOpMode{
    Drivetrain drivetrain;
    ViperSlide slide;
    Intake intake;

    private void sleep_sec(double seconds) {
        sleep((long) (seconds * 1000));
    }

    public void runOpMode(){
        drivetrain = new Drivetrain(hardwareMap, 0.75);
        slide = new ViperSlide(hardwareMap);
        intake = new Intake(hardwareMap);

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
//        strafe_Left(0.5, 0.75);
//        forward(1.3,0.75);
//        stop(1);
//        strafe_Right(0.3, 0.75);
//        stop(1);
//        turn_Right(0.4, 0.75);
//        slide_Up(0.15,0.75); // too long, maybe half or third
//        stop(2);

        drivetrain.drive(Math.PI, 1, 0);
        sleep_sec(0.5);

        drivetrain.drive((Math.PI / 2), 1, 0);
        sleep_sec(1.3);

        drivetrain.stop();
        sleep_sec(1);

        drivetrain.drive(0, 1, 0);
        sleep_sec(0.3);

        drivetrain.stop();
        sleep_sec(1);

        drivetrain.drive(0, 0, 1);
        sleep_sec(0.4);
        drivetrain.stop();

        slide.up();
        sleep_sec(0.10);
        slide.stop();
    }
}
