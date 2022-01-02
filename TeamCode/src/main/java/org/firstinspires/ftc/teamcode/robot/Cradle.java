package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Hardware;

public class Cradle {
    private Servo swivel;
    private Servo grasperGate;

    // Grasper Constants
    public double GRASPER_OPEN = 0.18;
    public double GRASPER_CLOSE = 0.0;
    private  double SWIVEL_BASE = 0.0;
    private final double SWIVEL_TARGET = 0.7;

    public Cradle(HardwareMap hw){
        this.grasperGate = hw.get(Servo.class, "grasper");
        this.swivel = hw.get(Servo.class, "swivel");
    }
    public void openGate(){
        this.grasperGate.setPosition(GRASPER_OPEN);
    }
    public void closeGate(){
        this.grasperGate.setPosition(GRASPER_CLOSE);
    }
    public void Swivel(){
        this.swivel.setPosition(SWIVEL_TARGET);
    }
    public void closeSwivel(){
        this.swivel.setPosition(SWIVEL_BASE);
    }
    public double getGatePos(){return grasperGate.getPosition();}
    public void incOpenPos(double inc){GRASPER_OPEN += inc;}
    public void decOpenPos(double inc){GRASPER_OPEN -= inc;}
    public void incClosePos(double inc){GRASPER_CLOSE += inc;}
    public void decClosePos(double inc){GRASPER_CLOSE -= inc;}

}
