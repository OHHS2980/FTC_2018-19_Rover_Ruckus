package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Drive enc", group="Auto")
@Disabled
public class Encoderthingyy extends LinearOpMode {
    BabushkaHardware2018 robot = new BabushkaHardware2018();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);
        robot.liftR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.liftR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.liftL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.liftL.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        robot.liftL.setTargetPosition(500);
        robot.liftR.setTargetPosition(500);
        while(robot.liftL.getCurrentPosition()<490) {
            telemetry.addData("Auto:", "Forward");
            telemetry.update();
        }
        robot.latchPosition(1);
        while(runtime.milliseconds()<3000) {
            telemetry.addData("Auto:", "Forward");
            telemetry.update();
        }

        robot.drive(-1, 0, 0);
        while(runtime.milliseconds()<5000) {
            telemetry.addData("Auto", "Foward");
            telemetry.update();
        }
        robot.drive(1,0,0);
        while(runtime.milliseconds()<3000) {
            telemetry.addData("Auto:", "Forward");
            telemetry.update();
        }
        robot.plowPosition(0.8);
        telemetry.addData("Auto:", "finished");
        telemetry.update();

    }
}
