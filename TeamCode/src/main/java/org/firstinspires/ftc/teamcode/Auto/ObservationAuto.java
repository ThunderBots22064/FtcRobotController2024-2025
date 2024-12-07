package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.eventloop.opmode.*;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.ViperSlide;

@Autonomous(name = "RedObservationAuto")
public class ObservationAuto extends LinearOpMode {
    Drivetrain drivetrain;
    ViperSlide slide;

    private void sleep_sec(double seconds) {
        sleep((long) (seconds * 1000));
    }

    public void runOpMode() {
        slide = new ViperSlide(hardwareMap);
        drivetrain = new Drivetrain(hardwareMap, 0.75);

        //red observation zone side (simple park)
        drivetrain.drive((Math.PI / 2), 1, 0);
        sleep_sec(0.1); // original time = 0.15s

        drivetrain.drive(0, 0, 1);
        sleep_sec(0.4);

        drivetrain.drive((Math.PI / 2), 1, 0);
        sleep_sec(0.5);

        drivetrain.stop();
    }
}
