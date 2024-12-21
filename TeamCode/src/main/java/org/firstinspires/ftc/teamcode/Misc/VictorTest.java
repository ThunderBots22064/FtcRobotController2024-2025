package org.firstinspires.ftc.teamcode.Misc;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

@Autonomous(name = "Victor")
public class VictorTest extends LinearOpMode {
    private void sleep_sec(double seconds) {
        sleep((long) (seconds * 1000));
    }
    public void runOpMode(){
        Drivetrain drivetrain = new Drivetrain(hardwareMap, 0.5);
        waitForStart();
        while (opModeIsActive()) {
            drivetrain.drive(Math.PI / 2, 1, 0);
            sleep_sec(2000);
            drivetrain.drive(Math.PI, 1, 0);
            sleep_sec(2500);
            drivetrain.drive(Math.PI / 2, 1, 0);
            sleep_sec(500);
            drivetrain.drive(0,1,0);
            sleep_sec(2500);
    }

}
}