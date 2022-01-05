package org.firstinspires.ftc.teamcode.robot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class lifter {
    private final DcMotor motor;
    public enum liftState{
        IDLE,
        LIFTING,
        LIFTED,
        DROPPING,
        INIT
    }
    public liftState state;
    public TouchSensor touch;
    private int targetLiftPos = 455; // 430 was old
    private int targetDropPos = -55;
    private double LiftPow = 0.5;
    private double DropPow = -0.3;
    private int currentTargetPos;
    public lifter(HardwareMap hw){
        this.motor = hw.get(DcMotor.class, "lifter");
        this.motor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.state = liftState.INIT;
        this.touch = hw.get(TouchSensor.class, "touch");
        currentTargetPos = 0;

    }
    public void liftToTop(){
        this.state = liftState.LIFTING;
        currentTargetPos = targetLiftPos;
        this.motor.setTargetPosition(currentTargetPos);
        this.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
    public void dropDown(){
        this.state = liftState.DROPPING;
        currentTargetPos = targetDropPos;
        this.motor.setTargetPosition(currentTargetPos);
        this.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }


    public void update(){
        if(state == liftState.LIFTING){
            if(getEncoderCount() == currentTargetPos){
                state = liftState.LIFTED;
            }
            else{
                this.motor.setTargetPosition(currentTargetPos);
                this.motor.setPower(LiftPow);
            }

        }
        else if(state == liftState.DROPPING){
            if(touch.getValue() == 1.0){
                state = liftState.IDLE;
            }
            else{
                this.motor.setTargetPosition(currentTargetPos);
                motor.setPower(DropPow);
            }
        }
        else if (state == liftState.IDLE){
            motor.setTargetPosition(targetDropPos);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setPower(-0.02);
        }

        else if (state == liftState.INIT){
            motor.setTargetPosition(-500);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setPower(-0.08);
            //stall();
            if(touch.getValue() == 1.0){
                targetDropPos = motor.getCurrentPosition();
                state = liftState.IDLE;
            }
        }
        else if (state == liftState.LIFTED){
            if(getEncoderCount() != currentTargetPos){
                this.motor.setTargetPosition(currentTargetPos);
                this.motor.setPower(LiftPow);
            }

        }




    }

    public int getEncoderCount(){
        return this.motor.getCurrentPosition();
    }
    public int getCurrentTargetPos(){return currentTargetPos;}
    public int getMotorCurrentPos(){return motor.getCurrentPosition();}
    public int getTargetLiftPos(){return targetLiftPos;}
    public int getTargetDropPos(){return targetDropPos;}
    public liftState getState(){return state;}
    public double getTouchVal(){return touch.getValue();}

    public void incTargVal(int increment){currentTargetPos += increment * 0.01;}
    public void decTargVal(int increment){currentTargetPos -= increment * 0.001;}

    //ONLY FOR DEBUG
    public void incLiftPos(int increment){
        this.targetLiftPos += increment;
    }
    public void decLiftPos(int increment){
        this.targetLiftPos -= increment;
    }
    public void incDropPos(int increment){
        this.targetDropPos += increment;
    }
    public void decDropPos(int increment){
        this.targetDropPos -= increment;
    }


}

