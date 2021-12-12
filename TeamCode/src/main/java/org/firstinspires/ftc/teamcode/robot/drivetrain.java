package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

import fi.iki.elonen.NanoHTTPD;


public class drivetrain{
    public double crservopower;
    public double servpos;
    public DcMotor br;
    public DcMotor bl;
    public DcMotor fr;
    public DcMotor fl;
    public double frpow, brpow, flpow, blpow;
    private final double frontpow;
    private final double backpow;
    public final double roundPow = 0.4;
    private final double leftOffset = 1.0;
    public drivetrain(DcMotor.RunMode mode, HardwareMap hardwareMap){
        this.fl = hardwareMap.get(DcMotor.class, "fl");
        this.fr = hardwareMap.get(DcMotor.class, "fr");
        this.bl = hardwareMap.get(DcMotor.class, "bl");
        this.br = hardwareMap.get(DcMotor.class, "br");

        this.bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        this.bl.setMode(mode);
        this.br.setMode(mode);
        this.fl.setMode(mode);
        this.fr.setMode(mode);

        this.bl.setDirection(DcMotorSimple.Direction.REVERSE);
        this.br.setDirection(DcMotorSimple.Direction.REVERSE);
        this.fr.setDirection(DcMotorSimple.Direction.REVERSE);
        this.fl.setDirection(DcMotorSimple.Direction.REVERSE);

        //Init Power for Motors
        this.frpow = 0.0;
        this.brpow = 0.0;
        this.flpow = 0.0;
        this.blpow = 0.0;

        //Init Default Power
        this.frontpow = 0.7;
        this.backpow = -0.7;

        // Servo Power + Position
        this.crservopower = 0.0;
        this.servpos = 0.5;
    }
    public void StrafeLeft(){
        this.blpow = this.frontpow;
        this.flpow = this.backpow;
        this.frpow = this.frontpow;
        this.brpow = this.backpow;
        SetPower();
    }

    public void StrafeRight(){
        this.blpow = this.backpow;
        this.flpow = this.frontpow;
        this.frpow = this.backpow;
        this.brpow = this.frontpow;
        SetPower();
    }

    private void SetPower(){
        this.fl.setPower(Math.round(this.flpow/roundPow)*roundPow * leftOffset);
        this.br.setPower(Math.round(this.brpow/roundPow)*roundPow);
        this.bl.setPower(Math.round(this.blpow/roundPow)*roundPow * leftOffset);
        this.fr.setPower(Math.round(this.frpow/roundPow)*roundPow);
    }
    public void SetPower(double g1power, double g2power) {
        this.blpow = g1power;
        this.flpow = g1power;
        this.frpow = g2power;
        this.brpow = g2power;
        SetPower();
    }

    public void StopMotors(){
        this.blpow = 0;
        this.flpow = 0;
        this.frpow = 0;
        this.brpow = 0;
        SetPower();


    }


}
