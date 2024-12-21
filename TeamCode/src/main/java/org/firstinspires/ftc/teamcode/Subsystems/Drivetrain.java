package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivetrain {
    private DcMotor[] motors = new DcMotor[4];
    private double speed = 1.0;

    /**
     * Constructor for the drivetrain subsystem
     * @param hardwareMap The hardware map to get motors from
     */
    public Drivetrain(HardwareMap hardwareMap) {
        this(hardwareMap, 1.0);
    }

    /**
     * Constructor for the drivetrain subsystem
     * @param hardwareMap The hardware map to get motors from
     * @param speed The max speed (unless otherwise specified) to use for driving
     */
    public Drivetrain(HardwareMap hardwareMap, double speed) {
        String[] motorMaps = {"frontLeft", "frontRight",
                "backLeft", "backRight"};

        Direction[] motorDirs = {Direction.FORWARD, Direction.FORWARD,
                Direction.FORWARD, Direction.REVERSE};

        for (int i = 0; i < motors.length; i++) {
            motors[i] = hardwareMap.get(DcMotor.class, motorMaps[i]);
            motors[i].setDirection(motorDirs[i]);
            motors[i].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        this.speed = speed;
    }

    /**
     * A function to drive the robot in a certain direction (capped at the subsystem's speed)
     * @param angle The angle to drive the robot in radians, positive values are clockwise from the positive x-axis, e.g. +Pi/2 rad. is forward
     * @param magnitude A value from 0 to 1.0 representing the magnitude of the vector of drive
     * @param turn A value between -1.0 and 1.0 representing the turn power, -1.0 is full anticlockwise while 1.0 is full clockwise
     */
    public void drive(double angle, double magnitude, double turn) {
        drive(angle, magnitude, turn, this.speed);
    }

    /**
     * A function to drive the robot in a certain direction (capped at the subsystem's speed)
     * @param angle The angle to drive the robot in radians, positive values are clockwise from the positive x-axis, e.g. +Pi/2 rad. is forward
     * @param magnitude A value from 0 to 1.0 representing the magnitude of the vector of drive
     * @param turn A value between -1.0 and 1.0 representing the turn power, -1.0 is full anticlockwise while 1.0 is full clockwise
     * @param slow If true the robot will drive at half of the subsystem's speed
     */
    public void drive(double angle, double magnitude, double turn, boolean slow) {
        // Ternary operator is used here so if slow is true speed is multiplied by 0.5 (1/2).
        double calculatedSpeed = this.speed * (slow ? 0.5 : 1);
        drive(angle, magnitude, turn, calculatedSpeed);
    }

    /**
     * A function to drive the robot in a certain direction
     * @param angle The angle to drive the robot in radians, positive values are clockwise from the positive x-axis, e.g. +Pi/2 rad. is forward
     * @param magnitude A value from 0 to 1.0 representing the magnitude of the vector of drive
     * @param turn A value between -1.0 and 1.0 representing the turn power, -1.0 is full anticlockwise while 1.0 is full clockwise
     * @param speed The maximum possible motor speed (i.e. 0.40 is 40% of max speed)
     */
    public void drive(double angle, double magnitude, double turn, double speed) {
        // Turn values to apply to the left and right side of the robot
        double lTurn = turn;
        double rTurn = -turn;

        // Rotate the angle PI/4 rad. clockwise (Anticlockwise is superior to Counterclockwise)
        double theta = angle - (Math.PI / 4.0);

        // Find the forward and side components of power
        /*
         Note: Although these both could be multiplied by magnitude (hypotenuse) to find their true values
         (these trigonometric functions are ratios with the hypotenuse in the denominator)
         they will be divided by the maximum value which would immediately cancel this value i.e. ((cos / hyp) * hyp) / hyp
         Additionally, the magnitude is assumed to be 1 with these functions so this is unneccesary
         Also additionally, the magnitude will be multiplied after they have been scaled to the proper motor outputs
         */

        double forward = Math.cos(theta);
        double side = Math.sin(theta);

        double max = Math.max(Math.abs(forward), Math.abs(side));
        /*
        * To get the most out of the motors a maximum value must be reached
        * The possible values for forward and side follow values of cos and sin on a unit circle
        * That being said motors can be set to greater powers
        * Take for example a Pi/4 angle.
        * The sin and cos of Pi/4 is sqrt(2)/2
        * Assuming the magnitude is 1 the value doesn't change
        * But the motors can be set to values greater than sqrt(2)/2
        * So they are thus divided by max to scale them to their maximum possible values
        * Finally, multiplying by magnitude allows for variation in control
        * */

        double[] drivePowers = new double[4];

        drivePowers[0] = speed * (magnitude * (forward / max) + lTurn);
        drivePowers[3] = speed * (magnitude * (forward / max) + rTurn);
        drivePowers[1] = speed * (magnitude * (side / max) + rTurn);
        drivePowers[2] = speed * (magnitude * (side / max) + lTurn);

        // Set the motors to clamped values between -1.0 and 1.0 - it's not pretty but each motor needs to be set to a different value
        for (int i = 0; i < motors.length; i++) {
            motors[i].setPower( clamp(-1.0, 1.0, drivePowers[i]) );
        }
    }

    public void stop() {
        for (DcMotor motor : motors) {
            motor.setPower(0);
        }
    }

    /**
     * A method to clamp values within a range
     * @param minimum The minimum value
     * @param maximum The maximum value
     * @param val The value to clamp
     * @return A value between minimum and maximum inclusive
     */
    private double clamp(double minimum, double maximum, double val) {
        return Math.min(Math.max(val, minimum), maximum);
    }
}
