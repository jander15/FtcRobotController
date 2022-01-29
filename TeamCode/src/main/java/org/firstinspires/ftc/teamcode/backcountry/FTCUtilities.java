package org.firstinspires.ftc.teamcode.backcountry;

import android.os.Environment;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class FTCUtilities {

    private static HardwareMap hardwareMap;
    static Telemetry telemetry;
    private static OpMode opMode;
    private static DcMotor.ZeroPowerBehavior ZeroPowerBehavior;

    public static HardwareMap getHardwareMap(){
        return hardwareMap;
    }

    public static void setHardWareMap(HardwareMap hw){
        FTCUtilities.hardwareMap = hw;
    }

    public static void setTelemetry(Telemetry t){
        FTCUtilities.telemetry = t;
    }

    public static Telemetry getTelemetry(){
        return telemetry;
    }

    public static DcMotor.ZeroPowerBehavior zero(){
        return ZeroPowerBehavior;
    }

    public static void setOpMode(OpMode o){
        FTCUtilities.opMode = o;
        hardwareMap = o.hardwareMap;
    }

    public static OpMode getOpMode(){
        return opMode;
    }

    public static boolean opModeIsActive() {
        if (opMode instanceof LinearOpMode) {
            return ((LinearOpMode) opMode).opModeIsActive();
        } else {
            return true;
        }
    }
    public static void getTelemetry(String caption, Object object){
        opMode.telemetry.addData(caption, object);
        opMode.telemetry.update();
    }

    public static BNO055IMU getIMU (String imuName){
        return hardwareMap.get(BNO055IMU.class, imuName);
    }

    public static void OpSleep(int ms){
        ((LinearOpMode) opMode).sleep(ms);
    }
}
