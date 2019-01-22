package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Latch Test", group="Auto")
@Disabled
public class LatchTest extends LinearOpMode {
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

        robot.latchPosition(0);

        while (runtime.milliseconds() < 5000) {
            telemetry.addData("Auto:", "forward");
            telemetry.update();
        }

        robot.latchPosition(1);

        while (runtime.milliseconds() < 10000) {
            telemetry.addData("Auto:", "forward");
            telemetry.update();
        }

        robot.latchPosition(-1);

        telemetry.addData("Auto:", "finished");
        telemetry.update();

    }
}