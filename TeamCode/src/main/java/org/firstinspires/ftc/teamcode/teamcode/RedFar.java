package org.firstinspires.ftc.teamcode.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.backcountry.FTCUtilities;
import org.firstinspires.ftc.teamcode.backcountry.FileOrgoniser;

// Control hub terminal commands
// Control hub IP 192.168.43.1:5555

//Commands
// Connect is "adb connect 192.168.43.1:5555"
// Disconnect is "adb disconnect"

@Autonomous(name="RedFar", group="Linear Opmode")

public class RedFar extends LinearOpMode {
    private FileOrgoniser file;
    private ElapsedTime runtime = new ElapsedTime();

    public void runOpMode() {
        FTCUtilities.setOpMode(this);
        file = new FileOrgoniser();
        runtime.reset();

        FTCUtilities.setTelemetry(telemetry);
        waitForStart();
        FTCUtilities.OpSleep(1000);
        file.startFarRed();

    }
}

