package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.drivetrain;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp - Preseason Test")

public class TeleOp extends OpMode {
    drivetrain Ninjabot;
    @Override
    public void init() {
        Ninjabot = new drivetrain(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void loop() {
        if (gamepad1.left_stick_y == 0 && gamepad1.right_stick_y == 0){
            Ninjabot.StopMotors();
        }
        else{
            Ninjabot.SetPower(gamepad1.left_stick_y, gamepad1.right_stick_y);
        }

        if (gamepad1.right_trigger > 0.2){
            Ninjabot.StrafeRight();
        }
        else if (gamepad1.left_trigger > 0.2){
            Ninjabot.StrafeLeft();
        }
    }
}
