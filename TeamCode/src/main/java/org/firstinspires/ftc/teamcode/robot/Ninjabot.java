package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Ninjabot {
    public drivetrain drivetrain;
    public TurnTable turnTable;
    public Grasper grasper;
    public Ninjabot(DcMotor.RunMode runMode, HardwareMap hardwareMap){
        this.drivetrain = new drivetrain(runMode, hardwareMap);
        //this.turnTable = new TurnTable(hardwareMap);
        //this.grasper = new Grasper(hardwareMap);
    }
    //public void init(){
    //    grasper.grasperServo.setPosition(0.0);
    //}
    //public void runTurnTable(double inputPower){
    //    this.turnTable.setPower(inputPower);
    //}
    //public void stopTurnTable(){
    //    this.turnTable.stopTurnTable();
    //}

}
