package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Ninjabot {
    public drivetrain drivetrain;
    public ultraSonic ultraSonic;
    public TurnTable turnTable;

    public Ninjabot(DcMotor.RunMode runMode, HardwareMap hardwareMap){
        this.drivetrain = new drivetrain(runMode, hardwareMap);
        this.turnTable = new TurnTable(hardwareMap);
        //this.ultraSonic = new ultraSonic(hardwareMap);
    }
    /*public void ultraRightStrafe(){
        if(! (ultraSonic.state == org.firstinspires.ftc.teamcode.robot.ultraSonic.State.IDLE)){
            drivetrain.StrafeRight();
        }
        if(ultraSonic.state == org.firstinspires.ftc.teamcode.robot.ultraSonic.State.ULTRA_RIGHT_REACHED){
            drivetrain.StopMotors();
        }
    }*/
    public void ultraLeftStrafe(){

    }


}
