package org.firstinspires.ftc.teamcode.robot;

import android.sax.StartElementListener;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robot.control.imu;
import org.firstinspires.ftc.teamcode.robot.vision.Camera;


public class Ninjabot {
    public drivetrain drivetrain;
    public TurnTable turnTable;
    public lifter lifter;
    public Cradle cradle;
    public intake intake;
    public Camera webcam;
    private org.firstinspires.ftc.teamcode.robot.control.imu imu;
    private double ticksPerRev = 537.6;
    private double degPerRot = 1;//75
    private final double RotPerInch = 0.0795774715459477;
    private boolean alrMoving = false;
    private double GateTolerance = 0.09;
    private double currentTargDeg = 0;
    private double robotCurrentPow = 0.0;

    public Ninjabot(DcMotor.RunMode runMode, HardwareMap hardwareMap){
        this.drivetrain = new drivetrain(runMode, hardwareMap);
        this.turnTable = new TurnTable(hardwareMap);
        this.cradle = new Cradle(hardwareMap);
        this.lifter = new lifter(hardwareMap);
        this.intake = new intake(hardwareMap);
        this.imu = new imu(hardwareMap);
        this.imu.init_imu();
        this.intake.stopIntake();
        this.cradle.closeSwivel();
        this.cradle.closeGate();
        this.webcam = new Camera(hardwareMap);
    }

    public void update(){
        this.lifter.update();
    }
    public void MoveTank(int inches, double speed, Telemetry telemetry){
        robotCurrentPow = speed;
        waitUntilMove(telemetry);
        this.drivetrain.resetMotors();
        this.imu.resetAngle();
        double rotations = (double) inches * RotPerInch;
        this.drivetrain.SetTargPos((int) (rotations * ticksPerRev));
        this.drivetrain.runToPos(speed);
    }
    public void StrafeLeft(int inches, double speed, Telemetry telemetry){
        waitUntilMove(telemetry);
        robotCurrentPow = speed;
        this.drivetrain.resetMotors();
        double rotations = (double) inches * RotPerInch;
        this.drivetrain.SetTargPos((int) (rotations * ticksPerRev));
        this.drivetrain.strafeLeftPos(speed);
    }
    public void StrafeRight(int inches, double speed, Telemetry telemetry){
        waitUntilMove(telemetry);
        robotCurrentPow = speed;
        this.drivetrain.resetMotors();
        double rotations = (double) inches * RotPerInch;
        this.drivetrain.SetTargPos((int) (rotations * ticksPerRev));
        this.drivetrain.strafeRightPos(speed);
    }
    public void turnLeft(int degrees, double speed, Telemetry telemetry){
        waitUntilMove(telemetry);
        imu.resetAngle();
        robotCurrentPow = speed;
        currentTargDeg = degrees;
        this.drivetrain.resetMotors();
        this.drivetrain.SetTargPos((int) (((double) degrees/degPerRot) * ticksPerRev));
        this.drivetrain.turnLeftPos(speed);
    }
    public void turnRight(int degrees, double speed, Telemetry telemetry){
        waitUntilMove(telemetry);
        imu.resetAngle();
        robotCurrentPow = speed;
        currentTargDeg = degrees;
        currentTargDeg *= -1;
        this.drivetrain.resetMotors();
        this.drivetrain.SetTargPos((int) (((double) degrees/degPerRot) * ticksPerRev));
        this.drivetrain.turnRightPos(speed);
    }
    public void stop(Telemetry tele){waitUntilMove(tele);}
    private void waitUntilMove(Telemetry telemetry){
        while(this.drivetrain.fr.getMode() == DcMotor.RunMode.RUN_TO_POSITION &&!this.drivetrain.isStoppedPos()){
            this.lifter.update();
            if(this.drivetrain.getState() == org.firstinspires.ftc.teamcode.robot.drivetrain.driveState.DRIVING){
                double correction = this.imu.checkDirection();
                this.drivetrain.adjustPow(correction, robotCurrentPow);
                telemetry.addData("1 imu heading", getImu().lastAngles.firstAngle);
                telemetry.addData("2 global heading", getImu().globalAngle);
                telemetry.addData("3 correction", getImu().checkDirection());
                telemetry.addData("STATE:", drivetrain.getState());

                if(drivetrain.fr.getPower() > 0.07){
                    int currentDelta = Math.abs(this.drivetrain.getTargetPos() - this.drivetrain.getCurrentPos());
                    if(currentDelta < 0.5 * Math.abs(this.drivetrain.getTargetPos())){
                        this.drivetrain.adjustPowAll(0.01);
                        telemetry.addData("MOVING INTO CURRENT DELTA", "TRUESHFDKL");
                    }

                }
                telemetry.addData("FL POW: ", drivetrain.getAllPow()[0]);
                telemetry.addData("FR POW: ", drivetrain.getAllPow()[1]);
                telemetry.addData("BR POW: ", drivetrain.getAllPow()[2]);
                telemetry.addData("BL POW: ", drivetrain.getAllPow()[3]);
                telemetry.update();


            }
            else if (this.drivetrain.getState() == org.firstinspires.ftc.teamcode.robot.drivetrain.driveState.TURNING_R || this.drivetrain.getState() == org.firstinspires.ftc.teamcode.robot.drivetrain.driveState.TURNING_L){
                if(this.imu.getAngle() <= currentTargDeg && this.drivetrain.getState() == org.firstinspires.ftc.teamcode.robot.drivetrain.driveState.TURNING_R && this.imu.getAngle() != 0){
                    this.drivetrain.setState(org.firstinspires.ftc.teamcode.robot.drivetrain.driveState.IDLE);
                    drivetrain.StopMotors();
                    telemetry.update();

                    return;
                }
                else if(this.imu.getAngle() >= currentTargDeg && this.drivetrain.getState() == org.firstinspires.ftc.teamcode.robot.drivetrain.driveState.TURNING_L && this.imu.getAngle() != 0){
                    this.drivetrain.setState(org.firstinspires.ftc.teamcode.robot.drivetrain.driveState.IDLE);
                    drivetrain.StopMotors();
                    telemetry.update();
                    return;
                }
                telemetry.update();

                if(drivetrain.fr.getPower() > 0.1 + 0.04){
                    double currentDelta = Math.abs(this.imu.getAngle() - currentTargDeg);
                    if(currentDelta < 0.6 * Math.abs(currentTargDeg)){
                        this.drivetrain.adjustPowAll(0.04);
                    }

                }
            }



        }
        this.drivetrain.setState(org.firstinspires.ftc.teamcode.robot.drivetrain.driveState.IDLE);
        drivetrain.StopMotors();

    }
    public void waitForLifter(){
        while(this.lifter.getState() == org.firstinspires.ftc.teamcode.robot.lifter.liftState.LIFTING && this.lifter.getMotorCurrentPos() != this.lifter.getCurrentTargetPos()){
            this.lifter.update();
        }
        while(this.lifter.getState() == org.firstinspires.ftc.teamcode.robot.lifter.liftState.DROPPING && this.lifter.getMotorCurrentPos() != this.lifter.getCurrentTargetPos()){
            this.lifter.update();
        }
    }
    public void waitForGate(){
        while(Math.abs(this.cradle.getTargetPos() - this.cradle.getGatePos()) > GateTolerance){
            this.lifter.update();
        }
    }
    public void waitForTT(){
        ElapsedTime time = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        time.reset();
        while(time.milliseconds() < 4300){
            this.lifter.update();
        }
    }
    public imu getImu(){return this.imu;}
    public double getCurrentTargetDeg(){return currentTargDeg;}


}
