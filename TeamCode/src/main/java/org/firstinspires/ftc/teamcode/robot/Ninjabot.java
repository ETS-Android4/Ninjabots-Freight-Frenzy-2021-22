package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot.vision.Camera;

/*
* Control Hub --
* 0 - TurnTable
* 1 -
* 2 -
* 3 -
*
*
*
*
* Expansion Hub --
*
*
* */

public class Ninjabot {
    public drivetrain drivetrain;
    public TurnTable turnTable;
    public lifter lifter;
    public Cradle cradle;
    public intake intake;
    public Camera webcam;

    public Ninjabot(DcMotor.RunMode runMode, HardwareMap hardwareMap){
        this.drivetrain = new drivetrain(runMode, hardwareMap);
        this.turnTable = new TurnTable(hardwareMap);
        this.cradle = new Cradle(hardwareMap);
        this.lifter = new lifter(hardwareMap);
        this.intake = new intake(hardwareMap);
        this.cradle.closeSwivel();
        this.intake.setBaseIntensity();
        this.cradle.closeGate();
        this.webcam = new Camera(hardwareMap);
    }



}
