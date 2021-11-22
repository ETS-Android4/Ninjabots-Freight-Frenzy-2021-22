package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ultraSonic {
    public enum State
    {
        ULTRA_LEFT_NOT_REACHED,
        ULTRA_LEFT_REACHED,
        ULTRA_RIGHT_NOT_REACHED,
        ULTRA_RIGHT_REACHED,
        IDLE
    }

    private final static double THRESHOLD = 100;
    private UltrasonicSensor ultraLeft;
    private UltrasonicSensor ultraRight;
    public State state;

    public ultraSonic(HardwareMap hw){
        this.ultraLeft = hw.get(UltrasonicSensor.class, "ultraLeft");
        this.ultraRight = hw.get(UltrasonicSensor.class, "ultraRight");
        this.state = State.IDLE;
    }
    private double calculateLeft(){
        return ultraLeft.getUltrasonicLevel();
    }
    private double calculateRight(){
        return ultraRight.getUltrasonicLevel();
    }
    public void compareLeftToThresh() {
        if (calculateLeft() < THRESHOLD){
            this.state = State.ULTRA_LEFT_REACHED;
        }
        else{
            this.state = State.ULTRA_LEFT_NOT_REACHED;
        }
    }

    public void compareRightToThresh(){
        if (calculateRight() < THRESHOLD){
            this.state = State.ULTRA_RIGHT_REACHED;
        }
        else{
            this.state = State.ULTRA_RIGHT_NOT_REACHED;
        }
    }
}
