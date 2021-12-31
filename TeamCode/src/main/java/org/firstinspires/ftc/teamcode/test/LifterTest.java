package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.Ninjabot;

@TeleOp(name = "Lifter Test")
public class LifterTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Ninjabot ninjabot = new Ninjabot(DcMotor.RunMode.RUN_USING_ENCODER, hardwareMap);
        waitForStart();
        while(opModeIsActive()){
            if(gamepad1.x){
                ninjabot.lifter.incTargVal(5);
                telemetry.addData("X: ", "true");
            }
            else if (gamepad1.a){
                ninjabot.lifter.decTargVal(5);
            }


            if(gamepad1.dpad_up){
                ninjabot.lifter.liftToTop();
            }
            else if (gamepad1.dpad_down){
                ninjabot.lifter.dropDown();
            }
            ninjabot.lifter.update();
            telemetry.addData("State: ", ninjabot.lifter.getState());
            telemetry.addData("Current Target: ", ninjabot.lifter.getCurrentTargetPos());
            telemetry.addData("Lift: ", ninjabot.lifter.getTargetLiftPos());
            telemetry.addData("Drop: ", ninjabot.lifter.getTargetDropPos());
            telemetry.addData("Touch: ", ninjabot.lifter.getTouchVal());
            telemetry.addData("Current Motor Position: ", ninjabot.lifter.getMotorCurrentPos());
            telemetry.update();
        }
    }
}
