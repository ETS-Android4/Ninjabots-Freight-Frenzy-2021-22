package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.Ninjabot;

@TeleOp(name = "Imu Right Turn")
public class imuRightTurn extends LinearOpMode {
    Ninjabot robot;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Ninjabot(DcMotor.RunMode.RUN_USING_ENCODER, hardwareMap);
        waitForStart();
        robot.turnRight(90, 0.4, telemetry);
        robot.stop(telemetry);

        telemetry.addData("Robot state:", robot.drivetrain.getState());
        telemetry.update();
        while (opModeIsActive()){
            telemetry.addData("IMU Ang:", robot.getImu().getAngle());
            telemetry.addData("Targ Degrees:", robot.getCurrentTargetDeg());
            telemetry.update();

        }


    }
}
