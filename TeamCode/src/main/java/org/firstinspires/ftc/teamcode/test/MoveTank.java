package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.Ninjabot;

@TeleOp(name = "Move Tank")
public class MoveTank extends LinearOpMode {
    private Ninjabot robot;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Ninjabot(DcMotor.RunMode.RUN_USING_ENCODER, hardwareMap);
        waitForStart();
        while (opModeIsActive()){
            robot.drivetrain.SetPower(1.0, 1.0);
            telemetry.addData("BR:", robot.drivetrain.br.getPower());
            telemetry.addData("BL:", robot.drivetrain.bl.getPower());
            telemetry.addData("FR:", robot.drivetrain.fr.getPower());
            telemetry.addData("FL:", robot.drivetrain.fl.getPower());
            telemetry.update();
        }
    }
}
