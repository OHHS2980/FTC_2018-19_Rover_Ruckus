package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleOp", group="Test")

public class OurTeleOp extends LinearOpMode{
    private BabushkaHardware2018 robot = new BabushkaHardware2018();
    private ElapsedTime runtime = new ElapsedTime();
    private boolean latchOn = false;
    private boolean latchLast = latchOn;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();
        robot.init(hardwareMap);
        robot.liftL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        gamepad1.setJoystickDeadzone(0.05f);

        while(opModeIsActive()) {
            //mechanum wheel drive
            //right stick x controls strafe, left stick x controls turning, left stick y controls forward/back
            robot.drive(gamepad1.left_stick_y,-gamepad1.left_stick_x,-gamepad1.right_stick_x);
            //robot.liftLimit.setState(true);
            //lifter
            if(gamepad1.left_bumper) {
                robot.lift(1);
            }
            else if(gamepad1.left_trigger>0.5f) {
                robot.lift(-1);
            }
            else {
                robot.lift(0);
            }

            //whomper
            if(gamepad1.right_bumper) {
                robot.whomp(1);
            }
            else if(gamepad1.right_trigger>0.5f) {
                robot.whomp(-1);
            }
            else {
                robot.whomp(0);
            }

            //toggle latch
            if(gamepad1.x&&latchLast==false) {
                //close latch
                if(latchOn) {
                    robot.latchPosition(1);
                    latchOn = false;
                }
                //open latch
                else {
                    robot.latchPosition(0);
                    latchOn = true;
                }
            }
            latchLast = gamepad1.x;

            if(gamepad1.y) {
                //raises plow
                robot.plowPosition(0.2);
            }
            else if (gamepad1.b) {
                //lowers plow compleatly
                robot.plowPosition(0.77);
            }
                //lowers plow partly
            else if(gamepad1.a) {
                robot.plowPosition(0.7);
            }

            //start intake
            if(gamepad1.dpad_down) {
                robot.wheelPosition(1);
            }
            //start outtake
            else if(gamepad1.dpad_up) {
                robot.wheelPosition(0);
            }
            //stop in/outtake
            else {
                robot.wheelPosition(0.5);
            }

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Wheels", "left front (%.2f), right front (%.2f), right front (%.2f), right back (%.2f)",
                    robot.lfDrive.getPower(), robot.rfDrive.getPower(), robot.lbDrive.getPower(), robot.rbDrive.getPower());
            telemetry.addData("Motors", "Whomp L (%.2f), Whomp R (%.2f), Lift L (%.2f), Lift R (%.2f)",
                    robot.whompL.getPower(),robot.whompR.getPower(),robot.liftL.getPower(),robot.liftR.getPower());
            telemetry.addData("controls", "Left Y: (%.2f), Left X: (%.2f), Right X: (%.2f)",
                    gamepad1.left_stick_y,gamepad1.left_stick_x,gamepad1.right_stick_x);
            telemetry.addData("Encoder pos", "Enc L: (%d), Enc R: (%d)", robot.liftL.getCurrentPosition(),robot.liftR.getCurrentPosition());
            telemetry.addData("Lift Limit: ", robot.liftLimit.getState());
            telemetry.update();
        }
    }
}
