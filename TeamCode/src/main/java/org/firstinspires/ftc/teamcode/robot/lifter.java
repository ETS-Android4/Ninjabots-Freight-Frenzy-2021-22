package org.firstinspires.ftc.teamcode.robot;

import android.hardware.camera2.params.TonemapCurve;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class lifter {

    // Old Code
    /*
    private DcMotor motor;
    private enum liftState{
        IDLE,
        LIFTING,
        LIFTED,
        DROPPING
    }
    private int[] checkpoint = new int[]{160, 200, 220, 255};
    private int[] checkpointDown = new int[]{-160, -190, -215, -235};
    private double[] checkpointPower = new double[]{0.6, 0.2, 0.09, 0.03};
    private double[] checkpointDropPower = new double[]{-0.6, -0.16, -0.1, -0.03};
    private int checkpointIndex = 0;
    public liftState state;
    public TouchSensor touch;
    private int liftedEncoder;
    public lifter(HardwareMap hw){
        this.motor = hw.get(DcMotor.class, "lifter");
        this.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.state = liftState.IDLE;
        this.touch = hw.get(TouchSensor.class, "touch");
    }
    public void liftToTop(){
        this.state = liftState.LIFTING;
        this.checkpointIndex = 0;

    }
    public void dropDown(){
        this.state = liftState.DROPPING;
        this.checkpointIndex = 0;
        this.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void stall(){
        this.motor.setPower(0.01);
    }
    public void update(){
        if(touch.isPressed() && state != liftState.DROPPING){
            this.state = liftState.LIFTED;
            this.liftedEncoder = motor.getCurrentPosition();
        }
        else if(state == liftState.LIFTING){
            for (int i = 0; i < checkpoint.length; i++) {
                if(this.motor.getCurrentPosition() > checkpoint[i]){
                    this.checkpointIndex = i;
                }
            }
            motor.setPower(checkpointPower[checkpointIndex]);
        }
        else if(state == liftState.DROPPING){
            if(this.motor.getCurrentPosition() < checkpointDown[3]){
                this.state = liftState.IDLE;
            }
            for (int i = 0; i < checkpoint.length; i++) {
                if(this.motor.getCurrentPosition() < checkpointDown[i]){
                    this.checkpointIndex = i;
                }
            }
            motor.setPower(checkpointDropPower[checkpointIndex]);
        }
        else if (state == liftState.IDLE){
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        if (state == liftState.LIFTED){
            stall();
            //state = liftState.LIFTING;
        }




    }

    public double getCheckpointPower() {
        return checkpointPower[checkpointIndex];
    }

    public int getCheckpointIndex() {
        return checkpointIndex;
    }
    public int getEncoderCount(){
        return this.motor.getCurrentPosition();
    }
    */
    private DcMotor motor;
    private TouchSensor touch;
    private int targetMotorPosition = 300;
    private int basePosition;
    private int topPosition;
    private final double STALL_POWER = 0.2;
    private final double LIFT_SPEED =  0.4;
    private final double DROP_SPEED =  -0.3;

    public enum State{
        IDLE,
        LIFTING,
        DROPPING,
        LIFTED
    }
    public State lifterState;
    public lifter(HardwareMap hw){
        motor = hw.get(DcMotor.class, "lifter");
        //touch = hw.get(TouchSensor.class, "touch");
        this.lifterState = State.IDLE;
        this.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.topPosition = 0;
        this.basePosition = motor.getCurrentPosition();
    }
    public void liftArm(){
        this.lifterState = State.LIFTING;
    }
    public void dropArm(){
        this.lifterState = State.DROPPING;
    }
    public void stall(){
        this.motor.setTargetPosition(topPosition);
        this.motor.setPower(STALL_POWER);
    }
    public void update(){
        switch (lifterState){
            case LIFTING:
                this.motor.setTargetPosition(targetMotorPosition);
                this.motor.setPower(LIFT_SPEED);
            case IDLE:
                this.motor.setPower(0.0);
            case DROPPING:
                this.motor.setTargetPosition(basePosition);
                this.motor.setPower(DROP_SPEED);
            case LIFTED:
                this.stall();
        }
        if(lifterState == State.LIFTING && Math.abs(targetMotorPosition - this.motor.getCurrentPosition()) <= 10){
            this.lifterState = State.LIFTED;
        }
        if(lifterState == State.DROPPING && Math.abs(basePosition - this.motor.getCurrentPosition()) <= 10){
            this.lifterState = State.IDLE;
        }

    }

    public int getEncoderCount(){return motor.getCurrentPosition();}
    public double getMotorPower(){return motor.getPower();}

}
