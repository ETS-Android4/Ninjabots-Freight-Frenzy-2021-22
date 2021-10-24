package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.hardware.bosch.NaiveAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.Ninjabot;
import org.firstinspires.ftc.teamcode.robot.drivetrain;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp - Preseason Test")

public class TeleOp extends OpMode {
    Ninjabot Ninjabot;
    @Override
    public void init() {
        Ninjabot = new Ninjabot(DcMotor.RunMode.RUN_USING_ENCODER, hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.left_stick_y == 0 && gamepad1.right_stick_y == 0 && gamepad1.right_trigger == 0 && gamepad1.left_trigger == 0){
            Ninjabot.drivetrain.StopMotors();
        }
        else if (gamepad1.left_stick_y !=0 || gamepad1.right_stick_y != 0){
            Ninjabot.drivetrain.SetPower(gamepad1.left_stick_y, gamepad1.right_stick_y);
        }

        else if (gamepad1.right_trigger > 0.2){
            Ninjabot.drivetrain.StrafeRight();
        }
        else if (gamepad1.left_trigger > 0.2){
            Ninjabot.drivetrain.StrafeLeft();
        }


        if (gamepad1.dpad_left){
            Ninjabot.grasper.closeGrasper();
        }
        else if (gamepad1.dpad_right){
            Ninjabot.grasper.openGrasper();
        }


        telemetry.addData("Br: ",Ninjabot.drivetrain.br.getCurrentPosition());
        telemetry.addData("Br: ",Ninjabot.drivetrain.bl.getCurrentPosition());
        telemetry.addData("Fr: ",Ninjabot.drivetrain.fr.getCurrentPosition());
        telemetry.addData("FL: ",Ninjabot.drivetrain.fl.getCurrentPosition());

    }
}
