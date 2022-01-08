package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.Ninjabot;
import org.firstinspires.ftc.teamcode.robot.lifter;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp")

public class TeleOp extends OpMode {
    Ninjabot Ninjabot;
    private double deadZoneVal = 0.3;
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
            //Ninjabot.intake.runIntake();
        }
        else if (gamepad1.right_trigger != 0){
            //Ninjabot.intake.stopIntake();
            Ninjabot.drivetrain.StrafeRight();
        }

        //Turn Table
        if(gamepad1.dpad_left){
            Ninjabot.turnTable.setPower();
        }
        else if (gamepad1.dpad_right){
            Ninjabot.turnTable.stopTurnTable();
        }
        if(gamepad2.dpad_left){
            Ninjabot.intake.runIntake();
        }
        else if (gamepad2.dpad_right){
            Ninjabot.intake.stopIntake();
        }

        if(gamepad2.dpad_up){
            Ninjabot.lifter.liftToTop();
        }
        else if (gamepad2.dpad_down){
            Ninjabot.lifter.dropDown();
        }

        if(gamepad2.right_trigger > deadZoneVal){
            Ninjabot.cradle.closeGate();
            Ninjabot.intake.stopIntake();
        }
        else if (gamepad2.left_trigger > deadZoneVal){
            Ninjabot.cradle.openGate();
        }

        if(gamepad2.a){
            Ninjabot.grasper.liftTo1();
        }
        else if (gamepad2.b){
            Ninjabot.grasper.liftTo2();
        }
        else if (gamepad2.x){
            Ninjabot.grasper.dropDown();
        }
        if(gamepad2.right_trigger > 0.3){
            Ninjabot.grasper.openGate();
        }
        else if (gamepad2.left_trigger > 0.3){
            Ninjabot.grasper.closeGate();
        }


        Ninjabot.update();
        telemetry.addData("FL: ", Ninjabot.drivetrain.fl.getCurrentPosition());
        telemetry.addData("FR: ", Ninjabot.drivetrain.fr.getCurrentPosition());
        telemetry.addData("BL: ", Ninjabot.drivetrain.bl.getCurrentPosition());
        telemetry.addData("BR: ", Ninjabot.drivetrain.br.getCurrentPosition());
        telemetry.addData("State of Lifter: ", Ninjabot.lifter.state);
        telemetry.addData("Lifter Encoder Count: ", Ninjabot.lifter.getEncoderCount());
        telemetry.addData("Touch Condition: ", Ninjabot.lifter.touch.isPressed());

        telemetry.update();

    }

}
