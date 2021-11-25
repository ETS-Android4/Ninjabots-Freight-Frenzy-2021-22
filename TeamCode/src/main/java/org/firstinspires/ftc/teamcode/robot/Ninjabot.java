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
    public Grasper grasper;

    public Ninjabot(DcMotor.RunMode runMode, HardwareMap hardwareMap){
        this.drivetrain = new drivetrain(runMode, hardwareMap);
        this.turnTable = new TurnTable(hardwareMap);
        this.grasper = new Grasper(hardwareMap);
        this.lifter = new lifter(hardwareMap);
        this.grasper.closeGrasper();
    }



}
