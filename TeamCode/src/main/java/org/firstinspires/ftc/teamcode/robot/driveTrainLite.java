/*
package org.firstinspires.ftc.teamcode.robot;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class driveTrainLite {
    private DcMotor l;
    private DcMotor r;
    public driveTrainLite(DcMotor.RunMode mode, HardwareMap hardwareMap){
        this.l = hardwareMap.get(DcMotor.class, "l");
        this.r = hardwareMap.get(DcMotor.class, "r");

        this.l.setMode(mode);
        this.r.setMode(mode);

        //this.br.setDirection(DcMotorSimple.Direction.REVERSE);
        //this.fr.setDirection(DcMotorSimple.Direction.REVERSE);

    }


    private void SetPower(){
        l.setPower();
    }
    public void SetPower(double g1power, double g2power) {
        SetPower();
    }

    public void StopMotors(){
        SetPower();


    }
}
*/