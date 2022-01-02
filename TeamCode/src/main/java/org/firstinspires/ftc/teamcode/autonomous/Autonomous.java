package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.Ninjabot;
import org.firstinspires.ftc.teamcode.robot.vision.CameraPipeline;

public class Autonomous extends LinearOpMode {
    Ninjabot robot;
    int level = -1;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Ninjabot(DcMotor.RunMode.RUN_USING_ENCODER, hardwareMap);
        robot.webcam.detect();
        if(robot.webcam.getPosition() == CameraPipeline.DuckPosition.ONE){
            telemetry.addData("Rings: ", "ONE");
            level = 1;
        }
        else if (robot.webcam.getPosition() == CameraPipeline.DuckPosition.TWO){
            telemetry.addData("Rings: ", "TWO");
            level = 2;
        }
        else if (robot.webcam.getPosition() == CameraPipeline.DuckPosition.THREE) {
            telemetry.addData("Rings: ", "THREE");
            level = 3;
        }
        telemetry.update();
        waitForStart();

        //Start Auto
        //PLAN:
        /*
        * 1. Go forward
        * 2. Turn Robot Left - 120 degrees left
        * 3. Lift Up Lifter while turning
        * 4. Back Up a bit
        * 5. Open Lifter to drop on top - how much we lift is based on how much we do
        * 6. Forward + Lower Cradle
        * 7. Turn 150 to the right
        * 8. Back Up a bit TBD
        * 9. Hits TurnTable, start it for some amount of time, longer is ok
        * 9.60 degree right turn
        * 10. Strafe a teeny amount into the wall
        * 11. Go straight for 84 inches
        * */

        robot.MoveTank(3, 0.5);

    }
}
