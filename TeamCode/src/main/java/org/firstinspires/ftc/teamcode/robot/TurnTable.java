package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TurnTable {
    private double TurnTablePower;
    private final double POWER = 0.48;
    // Running with a rev Motor
    private DcMotor motor;
    public TurnTable(HardwareMap hardwareMap){
        this.motor = hardwareMap.get(DcMotor.class, "intake");//shoule be turntable, just for testing
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
