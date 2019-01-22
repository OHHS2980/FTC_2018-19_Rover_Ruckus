package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Crater", group="Auto")

public class AutoForward2 extends LinearOpMode {
    BabushkaHardware2018 robot = new BabushkaHardware2018();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        robot.plowPosition(0.2);
        robot.drive(-1,0,0);
        while(runtime.milliseconds()<4000) {
            telemetry.addData("Auto:", "Forward");
            telemetry.update();
        }
        robot.drive(0,0,0);
        robot.plowPosition(0.7);
        robot.wheelPosition(1);
        telemetry.addData("Auto:", "finished");
        telemetry.update();
    }
}
