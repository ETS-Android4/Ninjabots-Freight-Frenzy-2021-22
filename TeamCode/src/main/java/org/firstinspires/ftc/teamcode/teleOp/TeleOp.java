package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.Ninjabot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp")

public class TeleOp extends OpMode {
    Ninjabot Ninjabot;
    private double deadZoneVal = 0.4;
    @Override
    public void init() {
        Ninjabot = new Ninjabot(DcMotor.RunMode.RUN_USING_ENCODER, hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.left_stick_y >= -deadZoneVal && gamepad1.right_stick_y >= -deadZoneVal && gamepad1.left_stick_y <= deadZoneVal && gamepad1.right_stick_y <= deadZoneVal && gamepad1.right_trigger < 0.1 && gamepad1.left_trigger < 0.1){
            Ninjabot.drivetrain.StopMotors();
        }
        else if (gamepad1.left_stick_y !=0 || gamepad1.right_stick_y != 0){
            Ninjabot.drivetrain.SetPower(-gamepad1.left_stick_y, -gamepad1.right_stick_y);
        }

        if(gamepad1.left_trigger != 0){
            Ninjabot.drivetrain.StrafeLeft();
        }
        else if (gamepad1.right_trigger != 0){
            Ninjabot.drivetrain.StrafeRight();

        }

        //Turn Table
        if(gamepad1.x){
            Ninjabot.turnTable.setPower();
        }
        else if (gamepad1.b){
            Ninjabot.turnTable.stopTurnTable();
        }
        if(gamepad1.dpad_up){
            Ninjabot.lifter.liftArm();
        }
        else if (gamepad1.dpad_down){
            Ninjabot.lifter.dropArm();
        }

        if(gamepad1.left_bumper){
            Ninjabot.cradle.closeGate();
            telemetry.addData("Grasper opening.", "true");
        }
        else if (gamepad1.right_bumper){
            Ninjabot.cradle.openGate();
            telemetry.addData("Grasper closing.", "true");
        }

        Ninjabot.lifter.update();
        telemetry.addData("FL: ", Ninjabot.drivetrain.fl.getCurrentPosition());
        telemetry.addData("FR: ", Ninjabot.drivetrain.fr.getCurrentPosition());
        telemetry.addData("BL: ", Ninjabot.drivetrain.bl.getCurrentPosition());
        telemetry.addData("BR: ", Ninjabot.drivetrain.br.getCurrentPosition());
        telemetry.addData("State of Lifter: ", Ninjabot.lifter.lifterState);
        telemetry.addData("Lifter Encoder Count: ", Ninjabot.lifter.getEncoderCount());
        telemetry.addData("Lifter Power: ", Ninjabot.lifter.getMotorPower());
        //telemetry.addData("Touch Condition: ", Ninjabot.lifter.touch.isPressed());

        telemetry.update();

    }

}
