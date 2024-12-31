package org.firstinspires.ftc.teamcode.Utils;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class TickHandler {
    private final double maxVector;

//    Sadly generics didn't work, hopefully a way can be explored where the Supplier and Consumer
//    function based on a generic i.e. int or double, the only problem is getting the calculations
//    at the end involving multiplication to work.
    private final Consumer<Double> output;
    private final Supplier<Double> position;

    /**
     * Constructor for a Tick Handler to manage subsystems that require specific positions 
     * but must be controlled continuously
     * @param maxVector the number of units (ticks or just like servo values) "into the future"
     *                  that the position will be set when the input is at max
     * @param output the function (from the subsystem) taking in the actual position value
     * @param position a function which returns the position of the hardware, i.e. encoder value or
     *                 position of servo
     */
    public TickHandler(double maxVector, Consumer<Double> output, Supplier<Double> position) {
        this.maxVector = maxVector;
        this.output = output;
        this.position = position;
    }

    /**
     * Handles an input to generate the proper continuous output
     * @param input A value between -1.0 and 1.0 specifying the total magnitude (percentage) of the
     *              maxVector amount to use when targeting
     */
    public void handle(double input) {
        double vector = maxVector * input;
        double target = position.get() + vector;

        output.accept(target);
    }
}
