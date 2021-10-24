package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Grasper {
    public Servo grasperServo;
    public double grasperPos;
    private final double GRASPER_CLOSE = 1.0;
    private final double GRASPER_OPEN = 0.0;

    public Grasper(HardwareMap hardwareMap){
        this.grasperServo = hardwareMap.get(Servo.class, "Grasper");
        grasperPos = this.grasperServo.getPosition();

    }
    public void closeGrasper(){
        grasperPos = GRASPER_CLOSE;
        grasperServo.setPosition(grasperPos);
    }
    public void openGrasper(){
        grasperPos = GRASPER_OPEN;
        grasperServo.setPosition(grasperPos);
    }
}
