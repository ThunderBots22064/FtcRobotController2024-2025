package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.ViperSlide;

@Autonomous(name = "Autotest")
public class Autotest extends LinearOpMode{
    private Claw claw;
    private Drivetrain drivetrain;
    private ViperSlide slide;


    public void config(HardwareMap hardwareMap){
        claw = new Claw(hardwareMap);
        drivetrain = new Drivetrain(hardwareMap, 0.25);
        slide = new ViperSlide(hardwareMap);
    }

    private void sleep_sec(double seconds) {
        sleep((long) (seconds * 1000));
    }

    public void runOpMode(){
        config(hardwareMap);

        //red observation zone side (simple park)
        drivetrain.drive(0, 1, 0);
        sleep_sec(1);
        drivetrain.stop();
        sleep_sec(5);

        //red net zone side (complex w/ pre-load)
        drivetrain.drive((Math.PI / 2), 1.0, 0);
        sleep_sec(0.15);

        drivetrain.drive((Math.PI), 1, 0);
        sleep_sec(1.5);

        drivetrain.drive(0, 0, -1);
        sleep_sec(0.25);

//        slide.setPosition(1);

        claw.setWrist(0);
        claw.setWrist(-0.15);
        claw.setWrist(0.7);

        drivetrain.drive((Math.PI * 3.0/2), 1, 0);
        sleep_sec(0.15);

        drivetrain.drive(0, 0, 1);
        sleep_sec(0.25);

        drivetrain.drive((Math.PI / 2), 1, 0);
        sleep_sec(1.25);

        drivetrain.drive(0, 1, 0);
        sleep_sec(1.5);

        drivetrain.stop();
        sleep_sec(2);

        //red net zone side (simple park)
        drivetrain.drive((Math.PI), 1, 0);
        sleep_sec(0.5);

        drivetrain.drive((Math.PI / 2), 1, 0);
        sleep_sec(1.5);

        drivetrain.drive(0, 1, 0);
        sleep_sec(0.5);

//        slide.setPosition(1);
        drivetrain.stop();
        sleep_sec(2);

        /*
        drivetrain.drive((Math.PI / 2), 1, 0);
        sleep_sec(1);

        drive.drive(0, 1, 0);
        sleep_sec(0.5);

        drivetrain.stop();
        sleep_sec(2);
         */
    }

}
