package org.firstinspires.ftc.teamcode.backcountry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

// On Bot Mechanic
public class OBM {

    private String deviceName;
    private HardwareMap hardwareMap;
    private DcMotor motor;
    public OBM(String deviceName) {
        this.deviceName = deviceName;
        init();
    }

    public void init(){
        motor = FTCUtilities.getHardwareMap().get(DcMotor.class,deviceName);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public double getInchesTravelled(){
        double ticksPerRevolution=537.6;
        int ticks = motor.getCurrentPosition();
        double revolutions = ticks/ticksPerRevolution;
        double distance = revolutions*1.886*Math.PI;
        return (distance);

    }

    public void setPowerOBM(double power){
        motor.setPower(power);
    }
    public void setZero(){
        motor.setPower(0);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
}
