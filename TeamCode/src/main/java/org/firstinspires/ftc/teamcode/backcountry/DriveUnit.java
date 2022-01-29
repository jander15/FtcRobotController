package org.firstinspires.ftc.teamcode.backcountry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import static org.firstinspires.ftc.teamcode.backcountry.FTCUtilities.getHardwareMap;
import static org.firstinspires.ftc.teamcode.backcountry.FTCUtilities.telemetry;

public class DriveUnit {
    private double gearRatio;
    private double wheelDiameter;
    private DcMotor motor = null;
    private boolean direction;
    private String diviceName;

    public DriveUnit(double gearRatio, double wheelDiameter, String diviceName, boolean direction) {
        this.gearRatio = gearRatio;
        this.wheelDiameter = wheelDiameter;
        this.direction = direction;
        this.diviceName = diviceName;
        init();
    }

    public void init(){
        motor = FTCUtilities.getHardwareMap().get(DcMotor.class,diviceName);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setPower(double power){
        if(!direction){power=-power;}
        motor.setPower(power);
    }

    public void zeroDistance(){
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

//    this method should return the distance travelled in inches
    public double getInchesTravelled(){
        double ticksPerRevolution=537.6;

        int ticks = motor.getCurrentPosition();
        double revolutions = ticks/ticksPerRevolution;
        double distance = revolutions*wheelDiameter*Math.PI;
        return Math.abs(distance);

    }

}
