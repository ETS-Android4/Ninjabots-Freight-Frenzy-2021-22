package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.Ninjabot;
import org.firstinspires.ftc.teamcode.robot.vision.CameraPipeline;

@Autonomous(name = "Auto Test")
public class AutoTest extends LinearOpMode {
    Ninjabot robot;
    int level = -1;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Ninjabot(DcMotor.RunMode.RUN_USING_ENCODER, hardwareMap);
        sleep(1000);
        robot.webcam.detect();
        while(robot.webcam.getPosition() == CameraPipeline.DuckPosition.UNKNOWN){
            if (robot.webcam.getPosition() == CameraPipeline.DuckPosition.ONE) {
                telemetry.addData("Rings: ", "ONE");
                level = 1;
            } else if (robot.webcam.getPosition() == CameraPipeline.DuckPosition.TWO) {
                telemetry.addData("Rings: ", "TWO");
                level = 2;
            } else if (robot.webcam.getPosition() == CameraPipeline.DuckPosition.THREE) {
                telemetry.addData("Rings: ", "THREE");
                level = 3;
            }
            else{
                robot.webcam.detect();
                telemetry.addData("Rings: ", "Unknown");
            }
            telemetry.update();

        }

        waitForStart();
        robot.MoveTank(1, 0.5);
        robot.MoveTank(-1, 0.5);
        robot.StrafeRight(1, 0.5);
        robot.StrafeLeft(1, 0.5);
        robot.turnLeft(90, 0.5);
    }
}
