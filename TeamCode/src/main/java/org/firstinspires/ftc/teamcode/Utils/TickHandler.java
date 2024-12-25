package org.firstinspires.ftc.teamcode.Utils;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class TickHandler {
    private final double maxVector;
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

    public void handle(double input) {

    }
}
