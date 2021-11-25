package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TurnTable {
    private double TurnTablePower;
    private final double POWER = 0.49;
    // Running with a rev Motor
    private DcMotor motor;
    public TurnTable(HardwareMap hardwareMap){
        this.motor = hardwareMap.get(DcMotor.class, "TurnTable");
        this.motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void setPower(double power){
        this.TurnTablePower = power;
        motor.setPower(this.TurnTablePower);
    }
    public void setPower(){
        motor.setPower(this.POWER);
    }
    public void stopTurnTable(){
        this.TurnTablePower = 0.0;
        this.setPower(this.TurnTablePower);
    }
}
