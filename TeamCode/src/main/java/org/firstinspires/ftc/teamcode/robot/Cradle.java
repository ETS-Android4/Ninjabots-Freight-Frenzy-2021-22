package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Cradle {
    private Servo swivel;
    private Servo gate;

    // Grasper Constants
    public double GATE_OPEN = 0.25;//Mid Position is 0.18
    public double GATE_CLOSE = 0.0;
    private  double SWIVEL_BASE = 0.0;
    private final double SWIVEL_TARGET = 0.7;
    private double currentTarget;

    public Cradle(HardwareMap hw){
        this.gate = hw.get(Servo.class, "grasper");
        this.swivel = hw.get(Servo.class, "swivel");
        this.gate.setPosition(0.18);//Base
        currentTarget = 0;
    }
    public void openGate(){
        currentTarget = GATE_OPEN;
        this.gate.setPosition(GATE_OPEN);
    }
    public void closeGate(){
        currentTarget = GATE_CLOSE;
        this.gate.setPosition(GATE_CLOSE);
    }
    public void Swivel(){
        this.swivel.setPosition(SWIVEL_TARGET);
    }
    public void closeSwivel(){
        this.swivel.setPosition(SWIVEL_BASE);
    }
    public double getGatePos(){return gate.getPosition();}
    public void incOpenPos(double inc){
        GATE_OPEN += inc;}
    public void decOpenPos(double inc){
        GATE_OPEN -= inc;}
    public void incClosePos(double inc){
        GATE_CLOSE += inc;}
    public void decClosePos(double inc){
        GATE_CLOSE -= inc;}
    public double getTargetPos(){return currentTarget;}

}
