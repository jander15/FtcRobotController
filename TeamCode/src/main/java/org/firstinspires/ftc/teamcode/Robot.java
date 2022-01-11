package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {
    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    private Chassis chassis;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        chassis = new Chassis(hardwareMap,telemetry);
    }

    public void driveStraight(double distance){
        chassis.driveStraight(distance);
    }

    public void pointTurn(double angle){
        chassis.pointTurn(angle);
    }
}
