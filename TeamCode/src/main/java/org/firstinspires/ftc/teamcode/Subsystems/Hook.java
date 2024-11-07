package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.*;

public class Hook {
    final private DcMotor hook;

    private int target = 0;

    // Define the floor and ceiling of the hook
    final int ceil = 200;
    final int floor = 0;

    public Hook(HardwareMap hardwareMap) {
        hook = hardwareMap.get(DcMotor.class, "hook");
        hook.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hook.setTargetPosition(0);
        hook.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hook.setDirection(DcMotorSimple.Direction.FORWARD);
        hook.setPower(0.4);
    }

    public int getTarget() {
        return target;
    }

    public void up() {
        target += 10;
        if (target > ceil) {
            target = ceil;
        }
        hook.setTargetPosition(target);
    }

    public void down() {
        target -= 10;
        if (target < floor) {
            target = floor;
        }
        hook.setTargetPosition(target);
    }

    public void stop() {
        hook.setPower(0);
    }


}
