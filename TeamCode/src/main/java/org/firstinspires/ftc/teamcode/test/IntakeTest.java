package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.Ninjabot;
import org.firstinspires.ftc.teamcode.robot.intake;

@TeleOp(name = "Intake Test")
public class IntakeTest extends LinearOpMode {
    private intake robot;
    @Override
    public void runOpMode() throws InterruptedException {
        this.robot = new intake(hardwareMap);
        waitForStart();
        while (opModeIsActive()){
            if(gamepad1.a){
                robot.runIntake();
            }
            else if (gamepad1.b){
                robot.stopIntake();
            }
        }
    }
}
