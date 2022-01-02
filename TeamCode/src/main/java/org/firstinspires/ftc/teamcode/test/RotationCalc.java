package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.Ninjabot;
@Autonomous(name = "Rotation Test")
public class RotationCalc extends LinearOpMode {
    Ninjabot robot;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Ninjabot(DcMotor.RunMode.RUN_USING_ENCODER, hardwareMap);
        waitForStart();
        robot.turnLeftRots(3, 0.5);

    }
}
