package org.firstinspires.ftc.teamcode.Utils;

import java.util.function.Consumer;

public class TickHandler {
    private final double maxVector;
    private final Consumer output;

    /**
     * Constructor for a Tick Handler to manage subsystems that require specific positions 
     * but must be controlled continuously
     * @param maxVector the number of units (ticks or just like servo values) "into the future"
     *                  that the position will be set when the input is at max
     * @param output the function (from the subsystem) taking in the actual position value
     */
    public TickHandler(double maxVector, Consumer output) {
        this.maxVector = maxVector;
        this.output = output;
    }
}
