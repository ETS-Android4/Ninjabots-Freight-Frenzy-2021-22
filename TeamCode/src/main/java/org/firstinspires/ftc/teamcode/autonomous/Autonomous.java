package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.Ninjabot;
import org.firstinspires.ftc.teamcode.robot.vision.CameraPipeline;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Autonomous")
public class Autonomous extends LinearOpMode {
    Ninjabot robot;
    int level = -1;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Ninjabot(DcMotor.RunMode.RUN_USING_ENCODER, hardwareMap);
        robot.webcam.detect();
        while(level == -1){
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
                sleep(1);
                telemetry.addData("Rings: ", "Unknown");
            }
            telemetry.addData("Cam:", robot.webcam.getPosition());
            telemetry.update();

        }
        waitForStart();

        if(level == 1){
            robot.MoveTank(24, 0.5, telemetry);
            robot.stop(telemetry);
            robot.getImu().resetAngle();
            sleep(50);
            robot.turnLeft(112, 0.3, telemetry);
            robot.lifter.liftToTop();
            robot.waitForLifter();
            robot.MoveTank(-10, 0.3, telemetry);
            robot.stop(telemetry);
            robot.cradle.openGate();
            robot.waitForGate();
            telemetry.addData("Gate Opened:","True");
            telemetry.update();
            robot.MoveTank(16, 0.5, telemetry);
            robot.stop(telemetry);
            robot.cradle.closeGate();
            robot.lifter.dropDown();
            telemetry.addData("Lifter Down:","True");
            telemetry.update();
            robot.MoveTank(16, 0.4, telemetry);
            robot.stop(telemetry);
            robot.getImu().resetAngle();
            sleep(50);
            robot.turnRight(130, 0.3, telemetry);
            robot.MoveTank(-11, 0.2, telemetry);
            robot.stop(telemetry);
            robot.turnTable.setPower();
            robot.waitForTT();
            robot.turnTable.stopTurnTable();
            robot.MoveTank(12, 0.7, telemetry);
            robot.stop(telemetry);
            robot.getImu().resetAngle();
            sleep(50);

            robot.turnRight(62, 0.3, telemetry);

            robot.StrafeRight(30, 0.6, telemetry);
            robot.MoveTank(96, 1, telemetry);
            robot.stop(telemetry);
        }

    }
}

