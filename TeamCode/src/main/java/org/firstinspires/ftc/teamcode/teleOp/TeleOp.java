package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.drivetrain;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp - Preseason Test")

public class TeleOp extends OpMode {
    drivetrain Ninjabot;
    @Override
    public void init() {
        Ninjabot = new drivetrain(DcMotor.RunMode.RUN_USING_ENCODER, hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.left_stick_y == 0 && gamepad1.right_stick_y == 0 && gamepad1.right_trigger == 0 && gamepad1.left_trigger == 0){
            Ninjabot.StopMotors();
        }
        else if (gamepad1.left_stick_y !=0 || gamepad1.right_stick_y != 0){
            Ninjabot.SetPower(gamepad1.left_stick_y, gamepad1.right_stick_y);
        }

        else if (gamepad1.right_trigger > 0.2){
            Ninjabot.StrafeRight();
        }
        else if (gamepad1.left_trigger > 0.2){
            Ninjabot.StrafeLeft();
        }

        if (gamepad1.dpad_left){
            Ninjabot.SetServoPosition(0.0);
        }
        else if (gamepad1.dpad_right){
            Ninjabot.SetServoPosition(1.0);
        }
        else{
            Ninjabot.SetServoPosition(Ninjabot.servpos);
        }

        if (gamepad1.right_bumper){
            Ninjabot.SetCrServoPower(1.0);
        }
        else if (gamepad1.left_bumper){
            Ninjabot.SetCrServoPower(-1.0);
        }
        else{
            Ninjabot.SetCrServoPower(0.0);
        }
        telemetry.addData("Br: ",Ninjabot.br.getCurrentPosition());
        telemetry.addData("Br: ",Ninjabot.bl.getCurrentPosition());
        telemetry.addData("Fr: ",Ninjabot.fr.getCurrentPosition());
        telemetry.addData("FL: ",Ninjabot.fl.getCurrentPosition());

    }
}
