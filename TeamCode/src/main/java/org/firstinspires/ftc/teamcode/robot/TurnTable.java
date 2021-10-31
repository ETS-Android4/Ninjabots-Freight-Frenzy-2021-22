package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TurnTable {
    private double TurnTablePower = 0.19;
    // Running with a goBilda 6000rpm Motor
    private DcMotor motor;
    public TurnTable(HardwareMap hardwareMap){
        this.motor = hardwareMap.get(DcMotor.class, "TurnTable");
    }
    public void setPower(double power){
        this.TurnTablePower = power;
        motor.setPower(this.TurnTablePower);
    }
    public void setPower(){
        motor.setPower(this.TurnTablePower);
    }
    public void stopTurnTable(){
        this.TurnTablePower = 0.0;
        this.setPower();
    }
}
