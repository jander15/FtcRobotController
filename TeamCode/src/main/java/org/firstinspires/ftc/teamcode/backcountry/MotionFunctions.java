package org.firstinspires.ftc.teamcode.backcountry;

//import static org.firstinspires.ftc.teamcode.backcountry.FTCUtilities.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;

public class MotionFunctions {
    private Chassis chassis;

    public MotionFunctions(){
        this.chassis = new Chassis();
    }

    public void StraitMotion (String Speed,String Distance) {
        double doubleSpeed = Double.parseDouble(Speed);
        double doubleDistance = Double.parseDouble(Distance);
        chassis.StraitMotion(doubleSpeed,doubleDistance);
    }

    public void Rotate (String Speed,String Angle){
        double doubleSpeed = Double.parseDouble(Speed);
        double doubleAngle = Double.parseDouble(Angle);
        chassis.Rotate(doubleAngle,doubleSpeed);
    }

    public void Strafe (String Speed, String Distance){
        double doubleSpeed = Double.parseDouble(Speed);
        double doubleDistance = Double.parseDouble(Distance);
        chassis.Strafe(doubleSpeed, doubleDistance);
    }
}