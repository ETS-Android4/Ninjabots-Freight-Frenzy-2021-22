package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

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

    public Ninjabot(DcMotor.RunMode runMode, HardwareMap hardwareMap){
        this.drivetrain = new drivetrain(runMode, hardwareMap);
        this.turnTable = new TurnTable(hardwareMap);
        this.cradle = new Cradle(hardwareMap);
        this.lifter = new lifter(hardwareMap);
        this.intake = new intake(hardwareMap);
        this.cradle.closeGate();
        this.intake.setBaseIntensity();
    }



}
