package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot.vision.Camera;


public class Ninjabot {
    public drivetrain drivetrain;
    public TurnTable turnTable;
    public lifter lifter;
    public Cradle cradle;
    public intake intake;
    public Camera webcam;
    private double ticksPerRev = 537.6;
    private double degPerRot = 75;
    private final double RotPerInch = 0.0795774715459477;
    private boolean alrMoving = false;

    public Ninjabot(DcMotor.RunMode runMode, HardwareMap hardwareMap){
        this.drivetrain = new drivetrain(runMode, hardwareMap);
        this.turnTable = new TurnTable(hardwareMap);
        this.cradle = new Cradle(hardwareMap);
        this.lifter = new lifter(hardwareMap);
        this.intake = new intake(hardwareMap);
        this.intake.stopIntake();
        this.cradle.closeSwivel();
        //this.intake.setBaseIntensity();
        this.cradle.closeGate();
        this.webcam = new Camera(hardwareMap);
    }

    public void update(){
        this.lifter.update();
    }
    public void MoveTank(int inches, double speed){
        waitUntilMove();
        this.drivetrain.resetMotors();
        double rotations = (double) inches * RotPerInch;
        this.drivetrain.SetTargPos((int) (rotations * ticksPerRev));
        this.drivetrain.runToPos(speed);
    }
    public void StrafeLeft(int inches, double speed){
        waitUntilMove();
        this.drivetrain.resetMotors();
        double rotations = (double) inches * RotPerInch;
        this.drivetrain.SetTargPos((int) (rotations * ticksPerRev));
        this.drivetrain.strafeLeftPos(speed);
    }
    public void StrafeRight(int inches, double speed){
        waitUntilMove();
        this.drivetrain.resetMotors();
        double rotations = (double) inches * RotPerInch;
        this.drivetrain.SetTargPos((int) (rotations * ticksPerRev));
        this.drivetrain.strafeRightPos(speed);
    }
    public void turnLeft(int degrees, double speed){
        waitUntilMove();
        this.drivetrain.resetMotors();
        this.drivetrain.SetTargPos((int) (((double) degrees/degPerRot) * ticksPerRev));
        this.drivetrain.turnLeftPos(speed);
    }
    public void turnRight(int degrees, double speed){
        waitUntilMove();
        this.drivetrain.resetMotors();
        this.drivetrain.SetTargPos((int) (((double)degrees/degPerRot) * ticksPerRev));
        this.drivetrain.turnRightPos(speed);
    }

    private void waitUntilMove(){
        while(this.drivetrain.fr.getMode() == DcMotor.RunMode.RUN_TO_POSITION && this.drivetrain.getCurrentPos() != this.drivetrain.getTargetPos()){

        }
    }

}
