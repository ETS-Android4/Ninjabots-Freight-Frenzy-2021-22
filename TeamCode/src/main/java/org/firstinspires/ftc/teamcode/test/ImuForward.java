package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.Ninjabot;

@TeleOp(name = "Imu Drive Forward")
public class ImuForward extends LinearOpMode {
    Ninjabot robot;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Ninjabot(DcMotor.RunMode.RUN_USING_ENCODER, hardwareMap);
        waitForStart();
        robot.MoveTank(48, 0.5, telemetry);
        robot.stop(telemetry);

        telemetry.addData("Robot state:", robot.drivetrain.getState());
        telemetry.update();



    }
}
