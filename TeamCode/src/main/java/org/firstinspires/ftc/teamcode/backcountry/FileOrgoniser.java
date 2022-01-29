package org.firstinspires.ftc.teamcode.backcountry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.firstinspires.ftc.teamcode.backcountry.FTCUtilities.OpSleep;
import static org.firstinspires.ftc.teamcode.backcountry.FTCUtilities.telemetry;

//import static org.firstinspires.ftc.teamcode.backcountry.FTCUtilities.telemetry;

public class FileOrgoniser{
    private MotionFunctions MF;
    String[] OBMs = {"Intake", "RightLift", "LeftLift","Duck"};
    private OBM OBM1;
    private OBM OBM2;
    private OBM OBM3;
    private OBM OBM4;
    private CRServo Bucket = null;
    private Servo intakeRamp;

    public FileOrgoniser() {
        this.MF = new MotionFunctions();
        this.OBM1 = new OBM(OBMs[0]);
        this.OBM2 = new OBM(OBMs[1]);
        this.OBM3 = new OBM(OBMs[2]);
        this.OBM4 = new OBM(OBMs[3]);
        Bucket = FTCUtilities.getHardwareMap().get(CRServo.class, "bucket");
        intakeRamp = FTCUtilities.getHardwareMap().get(Servo.class , "intakeRamp");

    }

    // Straight "null", speed, null, distance, null, null
    // Rotate "null", speed, null, null, angle, null
    // // Strafe "null", speed, null, distance, null null
    // RunOBM, "name" speed, null, null, null, time
    // Servo "null", null, position, null, null, null

    // CommandType, nameOfOBM, speed, position, distance, angle, time
    public void startCloseBlue() {
        String[][] lineSegments = new String[][]{
                {"Straight","null","-.25","0","18","0","0"},
                {"Rotate","null",".275","0","0","65.0","0"},
                {"Straight","null","-.25","0","4","0","0"},
                {"RunOBM", "RightLift", ".5","0","0","0","17"},
                {"Straight","null",".25","0","3","0","0"},
                {"RunOBM", "RightLift", ".5","0","0","0","0"},
                {"Rotate","null",".275","0","0","-65.0","0"},
                {"Straight","null",".25","0","4","0","0"},
                {"Rotate","null",".275","0","0","-100.0","0"},
                {"Straight","null","-1.0","0","30","0","0"},
        };
        sort(lineSegments);
    }
    public void startFarBlue() {
        String[][] lineSegments = new String[][]{
                {"Rotate","null",".275","0","0","20.0","0"},
                {"Straight","null","-.25","0","1.0","0","0"},
                {"RunOBM", "Duck", ".75","0","0","0","5000"},
                {"Straight","null",".25","0","18","0","0"},
                {"Rotate","null",".275","0","0","150.0","0"},
                {"Straight","null","-.25","0","10","0","0"},
                {"RunOBM", "RightLift", ".5","0","0","0","17"},
                {"Straight","null",".25","0","3","0","0"},
                {"RunOBM", "RightLift", ".5","0","0","0","0"},
                {"Rotate","null",".275","0","0","-10.0","0"},
                {"Straight","null",".25","0","14","0","0"},

        };
        sort(lineSegments);
    }
    public void startCloseRed() {
        String[][] lineSegments = new String[][]{
                {"Straight","null","-.25","0","18","0","0"},
                {"Rotate","null",".275","0","0","-65.0","0"},
                {"Straight","null","-.25","0","4","0","0"},
                {"RunOBM", "RightLift", ".5","0","0","0","17"},
                {"Straight","null",".25","0","3","0","0"},
                {"RunOBM", "RightLift", ".5","0","0","0","0"},
                {"Rotate","null",".275","0","0","65.0","0"},
                {"Straight","null",".25","0","4","0","0"},
                {"Rotate","null",".275","0","0","100.0","0"},
                {"Straight","null","-1.0","0","30","0","0"},
        };
        sort(lineSegments);
    }
    public void startFarRed() {
        String[][] lineSegments = new String[][]{
                {"Straight","null","-.25","0","1","0","0"},
                {"Rotate","null",".275","0","0","-20.0","0"},
                {"RunOBM", "Duck", "-.75","0","0","0","5000"},
                {"Straight","null",".25","0","4","0","0"},
                {"Rotate","null",".275","0","0","75.0","0"},
                {"Straight","null","-.25","0","21","0","0"},
                {"Rotate","null",".275","0","0","85.0","0"},
                {"Straight","null","-.25","0","20","0","0"},
                {"RunOBM", "RightLift", ".5","0","0","0","17"},
                {"Straight","null",".25","0","3","0","0"},
                {"RunOBM", "RightLift", ".5","0","0","0","0"},
                {"Rotate","null",".275","0","0","-15.0","0"},
                {"Straight","null",".25","0","20","0","0"},
        };
        sort(lineSegments);
    }
    private void sort(String[][] lineSegments) {
        for (int i = 0; i < lineSegments.length; i++) {
            if (lineSegments[i][0] == "Straight") {
                MF.StraitMotion(lineSegments[i][2], lineSegments[i][4]);
            }
            if (lineSegments[i][0] == "Rotate") {
                MF.Rotate(lineSegments[i][2], lineSegments[i][5]);
                telemetry.addData(lineSegments[i][2],lineSegments[i][5]);
                telemetry.update();
            }
            if (lineSegments[i][0] == "Sleep") {
                OpSleep(500);
            }
            if (lineSegments[i][0] == "Servo") {
                double position = Double.parseDouble(lineSegments[i][3]);
                intakeRamp.setPosition(position);
                OpSleep(500);
            }
            if (lineSegments[i][0] == "CRServo") {
                double position = Double.parseDouble(lineSegments[i][3]);
                //.setPosition(position);
                OpSleep(500);
            }
            if (lineSegments[i][0] == "Strafe") {
                MF.Strafe(lineSegments[i][2], lineSegments[i][4]);
            }

            if (lineSegments[i][0] == "RunOBM") {
                //intake
                if (OBMs[3] == lineSegments[i][1]) {
                    double doubleSpeed = Double.parseDouble(lineSegments[i][2]);
                    int intTime=Integer.parseInt(lineSegments[i][6]);
                    telemetry.addData("power",doubleSpeed);
                    telemetry.addData("time",intTime);
                    OBM4.setPowerOBM(doubleSpeed);
                    FTCUtilities.OpSleep(intTime);
                    OBM4.setZero();
                }

                // Lift
                if (OBMs[1] == lineSegments[i][1]) {
                    double doubleSpeed = Double.parseDouble(lineSegments[i][2]);
                    int intDistance=Integer.parseInt(lineSegments[i][6]);
                    double inLeft;
                    boolean run = true;
                    inLeft= (int) OBM3.getInchesTravelled();
                    telemetry.addData("OBM3.getInchesTravelled()",inLeft= (int) OBM3.getInchesTravelled());
                    telemetry.update();
                    while(run) {
                        if (inLeft < intDistance) {
                            OBM2.setPowerOBM(doubleSpeed);
                            OBM3.setPowerOBM(-doubleSpeed);
                            telemetry.addData("intDistance",intDistance);
                            telemetry.addData("inLeft",inLeft);
                            telemetry.update();
                            inLeft= (int) OBM3.getInchesTravelled();
                        }else if (inLeft > intDistance) {
                            OBM2.setPowerOBM(-doubleSpeed);
                            OBM3.setPowerOBM(doubleSpeed);
                            telemetry.addData("intDistance",intDistance);
                            telemetry.addData("inLeft",inLeft);
                            telemetry.update();
                            inLeft= (int) OBM3.getInchesTravelled();
                        }else if (inLeft == intDistance) {
                            OBM2.setPowerOBM(0);
                            OBM3.setPowerOBM(0);
                            OBM2.setZero();
                            OBM3.setZero();
                            telemetry.addData("lift ramp", 0);
                            telemetry.update();
                            intakeRamp.setPosition(1.0);
                            OpSleep(1000);
                            telemetry.addData("setPosition",1.0);
                            telemetry.addData("intDistance",intDistance);
                            telemetry.addData("inLeft",inLeft);
                            telemetry.update();
                            inLeft = OBM3.getInchesTravelled();
                            run=false;
                        }
                    }
                }
            }
        }
    }
}
