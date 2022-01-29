package org.firstinspires.ftc.teamcode.backcountry;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.backcountry.sensors.IMU;

import static java.lang.Math.abs;
import static org.firstinspires.ftc.teamcode.backcountry.FTCUtilities.telemetry;
//import static org.firstinspires.ftc.teamcode.backcountry.FTCUtilities.telemetry;

public class Chassis{

    private DriveUnit RightFront;
    private DriveUnit LeftFront;
    private DriveUnit RightBack;
    private DriveUnit LeftBack;
    private DriveUnit motor;
    private org.firstinspires.ftc.teamcode.backcountry.sensors.IMU IMU;

    private double runTime;

    public Chassis(){
        this.RightFront= new DriveUnit(1,4.7,"RightFront",true);
        this.LeftFront= new DriveUnit(1,4.7,"LeftFront",true);
        this.RightBack= new DriveUnit(1,4.7,"RightBack",true);
        this.LeftBack= new DriveUnit(1,4.7,"LeftBack",true);
        this.IMU = new IMU(FTCUtilities.getIMU("imu"));
    }

    public void init(){
        RightFront.init();
        LeftFront.init();
        LeftBack.init();
        RightBack.init();
    }

    public void StraitMotion(double Speed,double Distance){
        boolean run = true;
        RightFront.zeroDistance();
        LeftFront.zeroDistance();
        RightBack.zeroDistance();
        LeftBack.zeroDistance();

        while(run&&FTCUtilities.opModeIsActive()){
            RightBack.setPower(Speed);
            LeftBack.setPower(-Speed);
            RightFront.setPower(Speed);
            LeftFront.setPower(-Speed);
            double TravledRight = RightFront.getInchesTravelled();
            double TravledLeft = LeftFront.getInchesTravelled();
            telemetry.addData("speed From Chassis",Speed);
            telemetry.addData("TravledRight",TravledRight);
            telemetry.addData("TravledLeft",TravledLeft);
            telemetry.update();
            if((TravledRight > Distance) || (TravledLeft > Distance)){
                run = false;
            }
        }

        RightBack.setPower(0);
        LeftBack.setPower(0);
        RightFront.setPower(0);
        LeftFront.setPower(0);
    }

    public void Rotate(double angle, double speed){
        IMU.resetAngle();
        if (angle < 0)
        {   // turn right.
            RightFront.setPower(-speed);
            LeftFront.setPower(-speed);
            RightBack.setPower(-speed);
            LeftBack.setPower(-speed);
        }
        else if (angle > 0)
        {   // turn left.
            RightFront.setPower(speed);
            LeftFront.setPower(speed);
            RightBack.setPower(speed);
            LeftBack.setPower(speed);
        }
        else return;

        if (angle < 0)
        {
            // On right turn we have to get off zero first.
            while (FTCUtilities.opModeIsActive() && IMU.getAngle() == 0) {
                telemetry.addData("IMU.getAngle() when == 0",IMU.getAngle());
                telemetry.update();
            }

            while (FTCUtilities.opModeIsActive() && IMU.getAngle() > angle) {
                telemetry.addData("IMU.getAngle() when > 0",IMU.getAngle());
                telemetry.update();
            }
        }
        else    // left turn.
            while (FTCUtilities.opModeIsActive() && IMU.getAngle() < angle) {
                telemetry.addData("IMU.getAngle() when < 0",IMU.getAngle());
                telemetry.update();
            }

        RightFront.setPower(0);
        LeftFront.setPower(0);
        RightBack.setPower(0);
        LeftBack.setPower(0);

        IMU.resetAngle();
    }

    public void Strafe(double speed,double distence){
        RightBack.zeroDistance();
        LeftBack.zeroDistance();
        RightFront.zeroDistance();
        LeftFront.zeroDistance();

        boolean run = true;
        while(run  && FTCUtilities.opModeIsActive()){
            RightBack.setPower(speed);
            LeftBack.setPower(speed);
            RightFront.setPower(-speed);
            LeftFront.setPower(-speed);

            double TravledRight = RightFront.getInchesTravelled();
            double TravledLeft = LeftFront.getInchesTravelled();

            if(TravledRight > distence || TravledLeft > distence){
                run = false;
            }
        }

        RightBack.setPower(0);
        LeftBack.setPower(0);
        RightFront.setPower(0);
        LeftFront.setPower(0);
    }
}

