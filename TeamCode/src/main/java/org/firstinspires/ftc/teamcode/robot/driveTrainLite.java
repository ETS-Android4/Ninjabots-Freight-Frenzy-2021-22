package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class driveTrainLite{
    public DcMotor r;
    public DcMotor l;
    public double lpow, rpow;
    public driveTrainLite(DcMotor.RunMode mode, HardwareMap hardwareMap){
        this.l = hardwareMap.get(DcMotor.class, "l");
        this.r = hardwareMap.get(DcMotor.class, "r");

        this.l.setMode(mode);
        this.r.setMode(mode);

        this.r.setDirection(DcMotorSimple.Direction.REVERSE);

        //Init Power for Motors
        this.rpow = 0.0;
        this.lpow = 0.0;

    }



    public void SetPower(){
        this.l.setPower(this.lpow);
        this.r.setPower(this.rpow);
    }
    public void SetPower(double g1power, double g2power) {
        this.rpow = g2power;
        this.lpow = g1power;
    }

    public void StopMotors(){
        this.rpow = 0;
        this.lpow = 0;


    }

}
