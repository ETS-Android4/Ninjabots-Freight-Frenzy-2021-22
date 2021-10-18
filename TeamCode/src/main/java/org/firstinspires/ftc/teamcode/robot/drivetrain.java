package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class drivetrain{
    public double crservopower;
    public double servpos;
    public DcMotor br;
    public DcMotor bl;
    public DcMotor fr;
    public DcMotor fl;
    //private DcMotor fl, fr, bl, br;
    private double frpow, brpow, flpow, blpow;
    private double frontpow, backpow;
    private Servo servo;
    private CRServo crservo;
    public drivetrain(DcMotor.RunMode mode, HardwareMap hardwareMap){
        this.fl = hardwareMap.get(DcMotor.class, "fl");
        this.fr = hardwareMap.get(DcMotor.class, "fr");
        this.bl = hardwareMap.get(DcMotor.class, "bl");
        this.br = hardwareMap.get(DcMotor.class, "br");
        this.servo = hardwareMap.get(Servo.class, "servo");
        this.crservo = hardwareMap.get(CRServo.class, "crservo");

        this.bl.setMode(mode);
        this.br.setMode(mode);
        this.fl.setMode(mode);
        this.fr.setMode(mode);

        //this.br.setDirection(DcMotorSimple.Direction.REVERSE);
        this.fr.setDirection(DcMotorSimple.Direction.REVERSE);

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
        this.blpow = this.backpow;
        this.flpow = this.frontpow;
        this.frpow = this.backpow;
        this.brpow = this.frontpow;
        SetPower();
    }

    public void StrafeRight(){
        this.blpow = this.frontpow;
        this.flpow = this.backpow;
        this.frpow = this.frontpow;
        this.brpow = this.backpow;
        SetPower();
    }

    private void SetPower(){
        this.fl.setPower(this.flpow);
        this.br.setPower(this.brpow * 0.95238095238);
        this.bl.setPower(this.blpow);
        this.fr.setPower(this.frpow);
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
    public void SetServoPosition(double servpos){
        this.servpos = servpos;
        servo.setPosition(servpos);
    }
    public void SetCrServoPower(double crservopower){
        this.crservopower = crservopower;
        crservo.setPower(crservopower);
    }
}
