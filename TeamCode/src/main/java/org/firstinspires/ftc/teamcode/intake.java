package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

//Control 0 - br
//Control 1 - bl
//Control 2 - fl
//Control 3 - fr
//Expansion 0 - intake - no encoder
//Expansion 1 - wobble
//Expansion 2 - flicker
//Expansion 3 - shooter - no encoder
//Control Servo 0 - wobble_gate
//Control Servo 1 - rack_pinion

@TeleOp(name = "intake test")
public class intake extends LinearOpMode {
    //all motors + servos


    private CRServo intake = null;


    @Override
    public void runOpMode() {


        intake = hardwareMap.get(CRServo.class, "intake");



        waitForStart();
        while(opModeIsActive()){

            if (gamepad1.a){
                intake.setPower(-1.0);}
            else {
                intake.setPower(0.0);
            }
            if (gamepad1.b){
                intake.setPower(1.0);
            }
            else {
                intake.setPower(0.0);
            }



        }

    }
}