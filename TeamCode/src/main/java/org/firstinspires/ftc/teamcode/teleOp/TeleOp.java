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
        Ninjabot.drivetrain.SetPower(0.1, 0.1);
        if (gamepad1.left_stick_y == 0 && gamepad1.right_stick_y == 0 && gamepad1.right_trigger < 0.1 && gamepad1.left_trigger < 0.1 && false){
            Ninjabot.drivetrain.StopMotors();
        }
        else if (gamepad1.left_stick_y !=0 || gamepad1.right_stick_y != 0){
            Ninjabot.drivetrain.SetPower(-gamepad1.left_stick_y, -gamepad1.right_stick_y);
        }

        /*if(gamepad1.left_trigger != 0){
            //turn
            Ninjabot.drivetrain.SetPower(-0.2, 0.8);
        }
        else if (gamepad1.right_trigger != 0){
            Ninjabot.drivetrain.SetPower(0.8, -0.3);

        }*/

        if (gamepad1.dpad_left){
            Ninjabot.grasper.closeGrasper();
        }
        else if (gamepad1.dpad_right){
            Ninjabot.grasper.openGrasper();
        }
        /*if (gamepad1.dpad_left){
            Ninjabot.runTurnTable(0.195);
        }
        else{
            Ninjabot.stopTurnTable();
        }
        */


    }
}
