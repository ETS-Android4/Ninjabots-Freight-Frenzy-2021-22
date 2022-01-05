package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

import fi.iki.elonen.NanoHTTPD;


public class drivetrain{
    public enum driveState{
        IDLE,
        TELEOP,
        DRIVING,
        STRAFING,
        TURNING_R,
        TURNING_L
    }
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
    private DcMotor.RunMode mode;
    public driveState state;
    public drivetrain(DcMotor.RunMode mode, HardwareMap hardwareMap){
        this.fl = hardwareMap.get(DcMotor.class, "fl");
        this.fr = hardwareMap.get(DcMotor.class, "fr");
        this.bl = hardwareMap.get(DcMotor.class, "bl");
        this.br = hardwareMap.get(DcMotor.class, "br");

        this.bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        this.state = driveState.IDLE;
        this.mode = mode;
        setMode();
        this.bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //this.bl.setDirection(DcMotorSimple.Direction.REVERSE);
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
    public void setMode(){
        this.bl.setMode(mode);
        this.br.setMode(mode);
        this.fl.setMode(mode);
        this.fr.setMode(mode);

    }
    public void resetMotors(){
        this.bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void StrafeLeft(){
        setMode();
        setState(driveState.TELEOP);

        this.blpow = this.frontpow;
        this.flpow = this.backpow;
        this.frpow = this.frontpow;
        this.brpow = this.backpow;
        SetPower();
    }

    public void StrafeRight(){
        setMode();
        setState(driveState.TELEOP);

        this.blpow = this.backpow;
        this.flpow = this.frontpow;
        this.frpow = this.backpow;
        this.brpow = this.frontpow;
        SetPower();
    }

    private void SetPower(){
        setMode();
        setState(driveState.TELEOP);

        this.fl.setPower(Math.round(this.flpow/roundPow)*roundPow * leftOffset);
        this.br.setPower(Math.round(this.brpow/roundPow)*roundPow);
        this.bl.setPower(Math.round(this.blpow/roundPow)*roundPow * leftOffset);
        this.fr.setPower(Math.round(this.frpow/roundPow)*roundPow);
    }
    public void SetPower(double g1power, double g2power) {
        setMode();
        setState(driveState.TELEOP);
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
        setState(driveState.IDLE);
        resetMotors();


    }
    public void SetTargPos(int targ){
        if(bl.getMode() == DcMotor.RunMode.RUN_TO_POSITION ||
           br.getMode() == DcMotor.RunMode.RUN_TO_POSITION ||
           fl.getMode() == DcMotor.RunMode.RUN_TO_POSITION ||
           fr.getMode() == DcMotor.RunMode.RUN_TO_POSITION){
            return;
        }
        else{
            bl.setTargetPosition(targ);
            br.setTargetPosition(targ);
            fl.setTargetPosition(targ);
            fr.setTargetPosition(targ);
        }
    }
    public void runToPos(double power){
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        setState(driveState.DRIVING);

        bl.setPower(power);
        br.setPower(power);
        fl.setPower(power);
        fr.setPower(power);

    }
    public void strafeLeftPos(double power){
        br.setTargetPosition(-br.getTargetPosition());
        fl.setTargetPosition(-fl.getTargetPosition());

        setState(driveState.STRAFING);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        bl.setPower(power);
        br.setPower(-power);
        fl.setPower(-power);
        fr.setPower(power);

    }
    public void strafeRightPos(double power){

        bl.setTargetPosition(-bl.getTargetPosition());
        fr.setTargetPosition(-fr.getTargetPosition());

        setState(driveState.STRAFING);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        bl.setPower(-power);
        br.setPower(power);
        fl.setPower(power);
        fr.setPower(-power);

    }
    public void turnLeftPos(double power){
        // pass in the target encoders
        bl.setTargetPosition(-bl.getTargetPosition());
        fl.setTargetPosition(-fl.getTargetPosition());

        setState(driveState.TURNING_L);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        bl.setPower(-power);
        br.setPower(power);
        fl.setPower(-power);
        fr.setPower(power);
    }
    public void turnRightPos(double power){
        br.setTargetPosition(-br.getTargetPosition());
        fr.setTargetPosition(-fr.getTargetPosition());

        setState(driveState.TURNING_R);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        bl.setPower(power);
        br.setPower(-power);
        fl.setPower(power);
        fr.setPower(-power);
    }
    public int getCurrentPos(){return fr.getCurrentPosition();}
    public int getTargetPos(){return fr.getTargetPosition();}
    public void setState(driveState newState){this.state = newState;}
    public driveState getState(){return this.state;}
    private double getPower(){return fr.getPower();}
    public void adjustPow(double correction, double currentPow){
        fr.setPower(fr.getPower() + correction);
        br.setPower(br.getPower() + correction);
        fl.setPower(fl.getPower() - correction);
        bl.setPower(bl.getPower() - correction);

    }
    public double[] getAllPow(){
        return new double[]{fl.getPower(), fr.getPower(), br.getPower(), bl.getPower()};
    }
    public boolean isStoppedPos(){
        return !br.isBusy() || !fr.isBusy() || !bl.isBusy() || !fl.isBusy();
    }
    public void adjustPowAll(double inc){
        if(br.getPower() < 0){
            br.setPower(br.getPower() + inc);
        }
        else{
            br.setPower(br.getPower() - inc);
        }

        if(fr.getPower() < 0){
            fr.setPower(fr.getPower() + inc);
        }
        else{
            fr.setPower(fr.getPower() - inc);
        }

        if(fl.getPower() < 0){
            fl.setPower(fl.getPower() + inc);
        }
        else{
            fl.setPower(fl.getPower() - inc);
        }

        if(bl.getPower() < 0){
            bl.setPower(bl.getPower() + inc);
        }
        else{
            bl.setPower(bl.getPower() - inc);
        }
    }
    public void floorPow(double minPow){
        if(br.getPower() < 0){
            br.setPower(Math.min(-minPow, br.getPower()));
        }
        else{
            br.setPower(Math.max(minPow, br.getPower()));
        }

        if(fr.getPower() < 0){
            fr.setPower(Math.min(-minPow, fr.getPower()));
        }
        else{
            fr.setPower(Math.max(minPow, fr.getPower()));
        }

        if(fl.getPower() < 0){
            fl.setPower(Math.min(-minPow, fl.getPower()));
        }
        else{
            fl.setPower(Math.max(minPow, fl.getPower()));
        }

        if(bl.getPower() < 0){
            bl.setPower(Math.min(-minPow, bl.getPower()));
        }
        else{
            bl.setPower(Math.max(minPow, bl.getPower()));
        }
    }
}
