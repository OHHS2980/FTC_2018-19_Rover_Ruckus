package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Drop + drive", group="Auto")

public class MakeThisWork extends LinearOpMode {
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
        robot.plowPosition(0.2);
        robot.wheelPosition(0.5);
        //robot.latchPosition(0);
        waitForStart();

        robot.lift(-1);
        while(runtime.milliseconds()<15000) {
            telemetry.addData("Auto:", "Forward");
            telemetry.update();
        }
        robot.lift(0);
        robot.latchPosition(1);
        while(runtime.milliseconds()<9000) {
            telemetry.addData("Auto:", "Forward");
            telemetry.update();
        }

        robot.drive(-1, 0, 0);
        while(runtime.milliseconds()<14000) {
            telemetry.addData("Auto", "Foward");
            telemetry.update();
        }
        robot.drive(0,0,0);

        robot.plowPosition(0.7);
        robot.wheelPosition(1);
        while(runtime.milliseconds()<15000)  {
            telemetry.addData("Auto:", "Forward");
            telemetry.update();
        }
        telemetry.addData("Auto:", "finished");
        telemetry.update();

    }
}
