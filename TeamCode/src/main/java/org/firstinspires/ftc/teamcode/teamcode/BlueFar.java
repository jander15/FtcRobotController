package org.firstinspires.ftc.teamcode.teamcode;

import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.backcountry.Chassis;
import org.firstinspires.ftc.teamcode.backcountry.DriveUnit;
import org.firstinspires.ftc.teamcode.backcountry.FTCUtilities;
import org.firstinspires.ftc.teamcode.backcountry.FileOrgoniser;
import org.firstinspires.ftc.teamcode.backcountry.MotionFunctions;

import java.io.FileNotFoundException;
import java.util.Locale;

// Control hub terminal commands
// Control hub IP 192.168.43.1:5555

//Commands
// Connect is "adb connect 192.168.43.1:5555"
// Disconnect is "adb disconnect"

@Autonomous(name="BlueFar", group="Linear Opmode")

public class BlueFar extends LinearOpMode {
    private FileOrgoniser file;
    private ElapsedTime runtime = new ElapsedTime();


    public void runOpMode() {
        FTCUtilities.setOpMode(this);
        file = new FileOrgoniser();
        runtime.reset();

        FTCUtilities.setTelemetry(telemetry);
        waitForStart();
        file.startFarBlue();
    }
}

