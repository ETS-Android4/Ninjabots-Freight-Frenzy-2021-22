package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.Ninjabot;
import org.firstinspires.ftc.teamcode.robot.lifter;

@TeleOp(name = "Cycle Test")
public class CycleTest extends LinearOpMode {
    Ninjabot ninjabot;
    @Override
    public void runOpMode() throws InterruptedException {
        ninjabot = new Ninjabot(DcMotor.RunMode.RUN_USING_ENCODER, hardwareMap);
        ninjabot.cradle.openGate();
        waitForStart();
        while (opModeIsActive()){
            if(gamepad1.dpad_left){
                ninjabot.intake.runIntake();
            }
            else if (gamepad1.dpad_right){
                ninjabot.intake.stopIntake();
            }
            if(gamepad1.left_trigger > 0.5){
                ninjabot.cradle.openGate();
            }
            else if (gamepad1.right_trigger > 0.5){
                ninjabot.cradle.closeGate();
                ninjabot.intake.stopIntake();
            }
            if(ninjabot.cradle.getGatePos() == ninjabot.cradle.GRASPER_CLOSE && ninjabot.lifter.getState() == lifter.liftState.IDLE){
                ninjabot.lifter.liftToTop();
            }
            if(gamepad1.dpad_down){
                ninjabot.lifter.dropDown();
            }
            else if (gamepad1.dpad_up){
                ninjabot.lifter.liftToTop();
            }
            ninjabot.lifter.update();

        }
    }
}
