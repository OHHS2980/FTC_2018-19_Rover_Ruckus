package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="Test", group="Test")
public class AutoTest2018 extends LinearOpMode {
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

        //turns on color sensor light
        //robot.colorSensor.enableLed(true);
        //reads alpha value from color sensor (reflected light), and drives forward while alpha < 20 OR less than 3 seconds since program start
        //while(robot.colorSensor.alpha()<20 || runtime.milliseconds()<3000) {
        //    //calls the drive method from robot (BabushkaHardware)
        //    robot.drive(1,0,0);
        //}
    }

}
