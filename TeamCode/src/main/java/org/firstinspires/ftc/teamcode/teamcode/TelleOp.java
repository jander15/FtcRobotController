package org.firstinspires.ftc.teamcode.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.lang.Math;
@TeleOp(name="TelleOp", group="Iterative Opmode")
public class TelleOp extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor RightFront = null;
    private DcMotor LeftFront = null;
    private DcMotor RightBack = null;
    private DcMotor LeftBack = null;
    private DcMotor Intake = null;
    private DcMotor RightLift = null;
    private DcMotor LeftLift = null;
    private DcMotor Duck = null;
    private CRServo bucket = null;
    private Servo intakeRamp = null;



    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        RightFront  = hardwareMap.get(DcMotor.class, "RightFront");
        LeftFront = hardwareMap.get(DcMotor.class, "LeftFront");
        RightBack  = hardwareMap.get(DcMotor.class, "RightBack");
        LeftBack = hardwareMap.get(DcMotor.class, "LeftBack");
        Duck = hardwareMap.get(DcMotor.class, "Duck");
        Intake = hardwareMap.get(DcMotor.class, "Intake");
        RightLift = hardwareMap.get(DcMotor.class, "RightLift");
        LeftLift = hardwareMap.get(DcMotor.class, "LeftLift");
        bucket = hardwareMap.get(CRServo.class , "bucket");
        intakeRamp = hardwareMap.get(Servo.class , "intakeRamp");

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");

        LeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }

    @Override
    public void init_loop() {
        LeftLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RightLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void start() {
        runtime.reset();
    }

    public double getInchesTravelled(DcMotor motor){
        double ticksPerRevolution=537.6;
        int ticks = motor.getCurrentPosition();
        double revolutions = ticks/ticksPerRevolution;
        double distance = revolutions*1.886*Math.PI;
        return (distance);

    }

    @Override
    public void loop() {
        double drive = gamepad1.right_stick_y;
        double turn = gamepad1.right_stick_x;

        double lift = gamepad2.right_stick_y;
        double intake = gamepad2.left_stick_y;

        boolean positionZeroLift = gamepad2.a;
        boolean positionMidLift = gamepad2.b;
        boolean positionTopLift = gamepad2.x;
        boolean positionCapLift = gamepad2.y;

        boolean setZeroEncoder = gamepad2.dpad_up;

        boolean intakeRampUp = gamepad1.left_bumper;
        boolean intakeRampDown = gamepad1.right_bumper;

        double Duck1 = gamepad1.left_trigger;
        double Duck2 = gamepad1.right_trigger;

        Duck.setPower(-Duck1);
        Duck.setPower(Duck2);

        if(intakeRampUp){
            intakeRamp.setPosition(1);
        }else if (intakeRampDown){
            intakeRamp.setPosition(-.75);
        }

        int inRight;
        int inLeft;

        double frontLeft = -turn + drive;
        double frontRight = turn + drive;
        double backLeft = -turn + drive;
        double backRight = turn + drive;

        double positionZero = 0;
        double positionMid = 8;
        double positionTop = 13;
        double positionCap = 15;

        if(setZeroEncoder){
            LeftLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            LeftLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            RightLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RightLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        boolean run = false;
        inRight= (int) Math.abs(getInchesTravelled(RightLift));
        inLeft= (int) getInchesTravelled(LeftLift);
        if(positionZeroLift){
            run = true;
            while(run) {
                if (inLeft < positionZero) {
                    telemetry.addData("inLeft < positionZero",inLeft < positionZero);
                    telemetry.addData("inches traveled right",String.valueOf(inRight));
                    telemetry.addData("inches traveled left",String.valueOf(inLeft));
                    telemetry.update();
                    LeftLift.setPower(-.5);
                    RightLift.setPower(.5);
                    inRight= (int) Math.abs(getInchesTravelled(RightLift));
                    inLeft= (int) getInchesTravelled(LeftLift);
                }else if(inLeft > positionZero){
                    telemetry.addData("inLeft > positionZero",inLeft > positionZero);
                    telemetry.addData("inches traveled right",String.valueOf(inRight));
                    telemetry.addData("inches traveled left",String.valueOf(inLeft));
                    telemetry.update();
                    LeftLift.setPower(.5);
                    RightLift.setPower(-.5);
                    inRight= (int) Math.abs(getInchesTravelled(RightLift));
                    inLeft= (int) getInchesTravelled(LeftLift);
                }else if(inLeft == positionZero){
                    run=false;
                    LeftLift.setPower(0);
                    RightLift.setPower(0);
                    inRight= (int) Math.abs(getInchesTravelled(RightLift));
                    inLeft= (int) getInchesTravelled(LeftLift);
                }else{
                    telemetry.addData("error", "never running if statement in zero position");
                    telemetry.update();
                    inRight= (int) Math.abs(getInchesTravelled(RightLift));
                    inLeft= (int) getInchesTravelled(LeftLift);
                }
            }
        }else if(positionMidLift){
            run = true;
            while(run) {
                if (inLeft < positionMid) {
                    telemetry.addData("inLeft < positionMid",inLeft < positionMid);
                    telemetry.addData("inches traveled right",String.valueOf(inRight));
                    telemetry.addData("inches traveled left",String.valueOf(inLeft));
                    telemetry.update();
                    LeftLift.setPower(-.5);
                    RightLift.setPower(.5);
                    inRight= (int) Math.abs(getInchesTravelled(RightLift));
                    inLeft= (int) getInchesTravelled(LeftLift);
                }else if(inLeft > positionMid){
                    telemetry.addData("inLeft > positionMid",inLeft > positionMid);
                    telemetry.addData("inches traveled right",String.valueOf(inRight));
                    telemetry.addData("inches traveled left",String.valueOf(inLeft));
                    telemetry.update();
                    LeftLift.setPower(.5);
                    RightLift.setPower(-.5);
                    inRight= (int) Math.abs(getInchesTravelled(RightLift));
                    inLeft= (int) getInchesTravelled(LeftLift);
                }else if(inLeft == positionMid){
                    run=false;
                    LeftLift.setPower(0);
                    RightLift.setPower(0);
                    inRight= (int) Math.abs(getInchesTravelled(RightLift));
                    inLeft= (int) getInchesTravelled(LeftLift);
                }else{
                    telemetry.addData("error", "never running if statement in mid position");
                    telemetry.update();
                    inRight= (int) Math.abs(getInchesTravelled(RightLift));
                    inLeft= (int) getInchesTravelled(LeftLift);
                }
            }
        }else if(positionTopLift){
            run = true;
            while(run) {
                if (inLeft < positionTop) {
                    telemetry.addData("inLeft < positionTop",inLeft < positionTop);
                    telemetry.addData("inches traveled right",String.valueOf(inRight));
                    telemetry.addData("inches traveled left",String.valueOf(inLeft));
                    telemetry.update();
                    LeftLift.setPower(-.5);
                    RightLift.setPower(.5);
                    inRight= (int) Math.abs(getInchesTravelled(RightLift));
                    inLeft= (int) getInchesTravelled(LeftLift);
                }else if(inLeft > positionTop){
                    telemetry.addData("inLeft > positionTop",inLeft > positionTop);
                    telemetry.addData("inches traveled right",String.valueOf(inRight));
                    telemetry.addData("inches traveled left",String.valueOf(inLeft));
                    telemetry.update();
                    LeftLift.setPower(.5);
                    RightLift.setPower(-.5);
                    inRight= (int) Math.abs(getInchesTravelled(RightLift));
                    inLeft= (int) getInchesTravelled(LeftLift);
                }else if(inLeft == positionTop){
                    run=false;
                    LeftLift.setPower(0);
                    RightLift.setPower(0);
                    inRight= (int) Math.abs(getInchesTravelled(RightLift));
                    inLeft= (int) getInchesTravelled(LeftLift);
                }else{
                    telemetry.addData("error", "never running if statement in top position");
                    telemetry.update();
                    inRight= (int) Math.abs(getInchesTravelled(RightLift));
                    inLeft= (int) getInchesTravelled(LeftLift);
                }
            }
        }else if(positionCapLift){
            run = true;
            while(run) {
                if (inLeft < positionCap) {
                    telemetry.addData("inLeft < positionCap",inLeft < positionCap);
                    telemetry.addData("inches traveled right",String.valueOf(inRight));
                    telemetry.addData("inches traveled left",String.valueOf(inLeft));
                    telemetry.update();
                    LeftLift.setPower(-.5);
                    RightLift.setPower(.5);
                    inRight= (int) Math.abs(getInchesTravelled(RightLift));
                    inLeft= (int) getInchesTravelled(LeftLift);
                } else if (inLeft > positionCap) {
                    telemetry.addData("inLeft > positionCap",inLeft > positionCap);
                    telemetry.addData("inches traveled right",String.valueOf(inRight));
                    telemetry.addData("inches traveled left",String.valueOf(inLeft));
                    telemetry.update();
                    LeftLift.setPower(.5);
                    RightLift.setPower(-.5);
                    inRight= (int) Math.abs(getInchesTravelled(RightLift));
                    inLeft= (int) getInchesTravelled(LeftLift);
                } else if (inLeft == positionCap) {
                    run = false;
                    LeftLift.setPower(0);
                    RightLift.setPower(0);
                    inRight= (int) Math.abs(getInchesTravelled(RightLift));
                    inLeft= (int) getInchesTravelled(LeftLift);
                } else {
                    telemetry.addData("error", "never running if statement in cap position");
                    telemetry.update();
                    inRight= (int) Math.abs(getInchesTravelled(RightLift));
                    inLeft= (int) getInchesTravelled(LeftLift);
                }
            }
        }

        telemetry.addData("inches traveled right",String.valueOf(inRight));
        telemetry.addData("inches traveled left",String.valueOf(inLeft));
        telemetry.update();

        Intake.setPower(intake);

        LeftLift.setPower(lift);
        RightLift.setPower(-lift);

        LeftLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        RightBack.setPower(-backRight);
        RightFront.setPower(-frontRight);
        LeftBack.setPower(backLeft);
        LeftFront.setPower(frontLeft);


    }
    @Override
    public void stop() {
    }

}
