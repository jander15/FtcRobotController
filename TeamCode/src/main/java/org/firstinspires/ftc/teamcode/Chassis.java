package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Chassis {
    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    private DcMotor leftFront = null;
    private DcMotor leftRear = null;
    private DcMotor rightFront = null;
    private DcMotor rightRear = null;
    private static final double TICKS_PER_INCH=43;
    private static final double TICKS_PER_ROTATION = TICKS_PER_INCH*30*Math.PI;

    public Chassis(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;

        leftFront = hardwareMap.get(DcMotor.class, "front_left_motor");
        leftRear = hardwareMap.get(DcMotor.class, "back_left_motor");
        rightFront = hardwareMap.get(DcMotor.class, "front_right_motor");
        rightRear = hardwareMap.get(DcMotor.class, "back_right_motor");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftRear.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        rightRear.setDirection(DcMotor.Direction.FORWARD );

    }
    public void driveStraight(double distance){
        double motorPower=0.2;
        double ticksToRun = distance*TICKS_PER_INCH;
        int ticksSoFar = 0;
        int startPos = leftFront.getCurrentPosition();
        leftFront.setPower(motorPower);
        rightFront.setPower(motorPower);
        leftRear.setPower(motorPower);
        rightRear.setPower(motorPower);
        while (ticksSoFar < ticksToRun){
            ticksSoFar = Math.abs(leftFront.getCurrentPosition()-startPos);
        }
        motorPower=0;
        leftFront.setPower(motorPower);
        rightFront.setPower(motorPower);
        leftRear.setPower(motorPower);
        rightRear.setPower(motorPower);

    }

    public void pointTurn(double angle){
        double motorPower=0.8;
        double ticksToRun = angle/360*TICKS_PER_ROTATION;
        int ticksSoFar = 0;
        int startPos = leftFront.getCurrentPosition();
        leftFront.setPower(motorPower);
        rightFront.setPower(-motorPower);
        leftRear.setPower(motorPower);
        rightRear.setPower(-motorPower);
        while (ticksSoFar < ticksToRun){
            ticksSoFar = Math.abs(leftFront.getCurrentPosition()-startPos);
        }
        motorPower=0;
        leftFront.setPower(motorPower);
        rightFront.setPower(motorPower);
        leftRear.setPower(motorPower);
        rightRear.setPower(motorPower);


    }
}
