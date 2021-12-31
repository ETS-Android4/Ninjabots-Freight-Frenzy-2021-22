package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class intake {
    private DcMotor intakeMotor;
    private int baseIntensity; // the value when something is in the cradle
    private final int lightThreshDiff = 15;
    public intake(HardwareMap hw){
        this.intakeMotor = hw.get(DcMotor.class, "intake");
        this.intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        baseIntensity = 0;
    }
    public void runIntake(){
        this.intakeMotor.setPower(1.0);
    }
    public void stopIntake(){
        this.intakeMotor.setPower(0.0);
    }
    public void reverseIntake(){
        this.intakeMotor.setPower(-1.0);
    }
    /*public void setBaseIntensity(){this.baseIntensity = color.alpha();}
    public boolean checkColor(){
        return Math.abs(color.alpha() - baseIntensity) < lightThreshDiff;
    }
    public int getColor(){return color.alpha();}*/
}
