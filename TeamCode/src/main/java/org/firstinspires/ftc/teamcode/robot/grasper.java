package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class grasper {
    private DcMotor grasperLifter;
    private Servo grasper;
    public enum GrasperState{
        DISABLED,
        IDLE,
        LIFTING_1,
        LIFTING_2,
        DROPPING,
        REACHED_1,
        REACHED_2
    }
    private int target1Lift;
    private int target2Lift; // Figure this out
    private int basePos = 0;
    private int currentTargetPos = 0;
    private GrasperState state;
    private double LiftPow = 0.5;
    private double DropPow = -0.3;
    public double GRASPER_OPEN = 0.25;
    public double GRASPER_CLOSE = 0.0;
    private double currentGrasperTarget = GRASPER_CLOSE;



    public grasper(HardwareMap hw){
        this.grasperLifter = hw.get(DcMotor.class, "Garm");
        this.grasper = hw.get(Servo.class, "Claw");
        this.grasperLifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.state = GrasperState.DISABLED;
        this.grasper.setPosition(GRASPER_CLOSE);
        //Direction?
    }

    public void liftTo1(){
        this.state = GrasperState.LIFTING_1;
        currentTargetPos = target1Lift;
        this.grasperLifter.setTargetPosition(currentTargetPos);
        this.grasperLifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
    public void liftTo2(){
        this.state = GrasperState.LIFTING_2;
        currentTargetPos = target2Lift;
        this.grasperLifter.setTargetPosition(currentTargetPos);
        this.grasperLifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
    public void dropDown(){
        this.state = GrasperState.DROPPING;
        currentTargetPos = basePos;
        this.grasperLifter.setTargetPosition(currentTargetPos);
        this.grasperLifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public void openGate(){
        currentGrasperTarget = GRASPER_OPEN;
        this.grasper.setPosition(GRASPER_OPEN);
    }
    public void closeGate(){
        currentGrasperTarget = GRASPER_CLOSE;
        this.grasper.setPosition(GRASPER_CLOSE);
    }

    public void update(){
        if(state == GrasperState.LIFTING_1){
            if(getEncoderCount() == currentTargetPos){
                state = GrasperState.REACHED_1;
            }
            else{
                this.grasperLifter.setTargetPosition(currentTargetPos);
                this.grasperLifter.setPower(LiftPow);
            }

        }
        else if(state == GrasperState.LIFTING_2){
            if(getEncoderCount() == currentTargetPos){
                state = GrasperState.REACHED_2;
            }
            else{
                this.grasperLifter.setTargetPosition(currentTargetPos);
                this.grasperLifter.setPower(LiftPow);
            }

        }

        else if(state == GrasperState.DROPPING){
            if(getEncoderCount() == basePos){
                state = GrasperState.IDLE;
            }
            else{
                this.grasperLifter.setTargetPosition(currentTargetPos);
                grasperLifter.setPower(DropPow);
            }
        }
        else if (state == GrasperState.IDLE){
            grasperLifter.setTargetPosition(basePos);
            grasperLifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            grasperLifter.setPower(0.1);
        }

        else if (state == GrasperState.REACHED_1 || state == GrasperState.REACHED_2){
            if(getEncoderCount() != currentTargetPos){
                this.grasperLifter.setTargetPosition(currentTargetPos);
                this.grasperLifter.setPower(LiftPow);
            }

        }

    }
    public int getEncoderCount(){
        return this.grasperLifter.getCurrentPosition();
    }

}
