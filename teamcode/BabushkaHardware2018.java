package org.firstinspires.ftc.teamcode; //The java library package we are working with

import com.qualcomm.robotcore.hardware.DcMotor;//allows us to work with DC motors
import com.qualcomm.robotcore.hardware.HardwareMap;//Tells processor where all the robot parts are
import com.qualcomm.robotcore.util.ElapsedTime;//Tells robot about different phases of the match
import com.qualcomm.robotcore.hardware.*;//
import com.qualcomm.robotcore.util.Range;//Gives us the range of numbers that get sent to all robot parts

//Program that deals with all hardware items on the robot
public class BabushkaHardware2018 {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    //DC motors
    public DcMotor lfDrive = null; //The left front motor of the drive train
    public DcMotor rfDrive = null; //The right front motor of the drive train

    public DcMotor rbDrive = null; //The right back motor of the drive train
    public DcMotor lbDrive = null; //The left back motor of tyhe drive train

    public DcMotor whompL = null; //The left motor of the whomper
    public DcMotor whompR = null; //The right motor of the whomper

    public DcMotor liftL = null; //The left lift motor
    public DcMotor liftR = null; //The right lift motor

    //Servos
    public Servo plowL = null; //The left plow linear servo
    public Servo plowR = null; //The right plow linear servo
    public double plowPos;
    public Servo plowWheelL = null; //The left plow wheel servo
    public Servo plowWheelR = null; //The right plow wheel servo
    //public CRServo plowWheelL = null;

    public Servo latch = null; //The servo for latching onto the lander
    public double latchPos;

    //Sensors
    //ColorSensor colorSensor; //The color sensor
    public DigitalChannel liftLimit = null;

    HardwareMap hardwareMap = null;//Why is there such a long space here? //Establishes the hardware map of the robot

    public BabushkaHardware2018() { //initialization of our robot hardware
    }

    public void init(HardwareMap ahwMap) { //THAT NAME IS TRASH YA FOOL
        // Save reference to Hardware map
        hardwareMap = ahwMap;

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        lfDrive = hardwareMap.get(DcMotor.class, "lfDrive"); //Get the left front drive motor from the hardware map
        rfDrive = hardwareMap.get(DcMotor.class, "rfDrive"); //Get the right front drive motor from the hardware map

        rbDrive = hardwareMap.get(DcMotor.class, "rbDrive"); //Get the right back drive motor from the hardware map
        lbDrive = hardwareMap.get(DcMotor.class, "lbDrive"); //Get the left back drive motor from the hardware map

        whompL = hardwareMap.get(DcMotor.class, "whompL"); //get the left whomper motor from hardware map
        whompR = hardwareMap.get(DcMotor.class, "whompR"); // get the right whomper from the hardware map

        liftL = hardwareMap.get(DcMotor.class, "liftL"); // get the left lifter motor from the hardware map
        liftR = hardwareMap.get(DcMotor.class, "liftR"); // get the right lifter motor from the hardware map

        //servos
        plowL = hardwareMap.get(Servo.class, "plowL"); //get the left linear servo from the hardware map
        plowR = hardwareMap.get(Servo.class, "plowR"); // get the right linear servo from the hardware map
        plowPosition(0);
        plowPos = plowL.getPosition();
        plowWheelL = hardwareMap.get(Servo.class, "plowWheelL"); // get the left plow wheel from the hardware map
        plowWheelR = hardwareMap.get(Servo.class, "plowWheelR"); // get the right plow wheel from the hardware map
        wheelPosition(0.5);
        latch = hardwareMap.get(Servo.class, "latch"); // get the latch (grapple) from the hardware map
        latchPos = latch.getPosition();
        //sensors
        //colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor"); // get the color sensor from the hardware map
        liftLimit = hardwareMap.get(DigitalChannel.class, "liftLimit");     //  Use generic form of device mapping
        liftLimit.setMode(DigitalChannel.Mode.INPUT);

        //set motor directions
        lfDrive.setDirection(DcMotor.Direction.FORWARD); // The left front drive is forward
        rfDrive.setDirection(DcMotor.Direction.REVERSE); // The right front drive is reverse

        lbDrive.setDirection(DcMotor.Direction.FORWARD); // The left back drive is forward
        rbDrive.setDirection(DcMotor.Direction.REVERSE); // The right back drive is reverse

        liftR.setDirection(DcMotor.Direction.FORWARD); // The right lifter motor is set forward
        liftL.setDirection(DcMotor.Direction.REVERSE); // The left lifter motor is set reverse
        /*liftR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftL.setMode(DcMotor.RunMode.RUN_TO_POSITION);*/

        whompR.setDirection(DcMotor.Direction.FORWARD); // The right whomper motor is set
        whompL.setDirection(DcMotor.Direction.REVERSE); // The left whomper motor is set reverse
    }
    /*
    public void drive(double forward, double turn, double strafe) { // Asking for how to drive
        double r = Math.hypot(strafe, forward); // Math hypotenuse, double right is strafe or forward?
        double robotAngle = Math.atan2(forward, -strafe) - Math.PI / 4; // What angle the robot is going to drive at
        double rightX = -turn; // How to turn
        double v1 = Range.clip(r * Math.cos(robotAngle) + rightX, -1.0, 1.0); // How fast to go forwards?
        double v2 = Range.clip(r * Math.sin(robotAngle) - rightX, -1.0, 1.0); // How fast to go backwards?
        double v3 = Range.clip(r * Math.sin(robotAngle) + rightX, -1.0, 1.0); // ?
        double v4 = Range.clip(r * Math.cos(robotAngle) - rightX, -1.0, 1.0); // ?

        lfDrive.setPower(v1); // Setting the power of the left front drive
        rfDrive.setPower(v3); // Setting the power of the right front drive
        lbDrive.setPower(v2); // Setting the power of the left back drive
        rbDrive.setPower(v4); // Setting the power of the right back drive
    }*/
    /*
    void drive(double y, double x, double rot) { //sets the motor speeds given an x, y and rotation value
        double theta = Math.atan(x/y) - 3.14159/4;  //finds the angle of the joystick and turns it by pi/4 radians or 45 degrees
        rot = .5*rot;  //scales rotation factor
        double magnitude = Math.sqrt(x*x+y*y);  //finds the magnitude of the joystick input by the Pythagorean theorem
        magnitude = magnitude*(100/127) - rot; // subtracts rot from the magnitude to make room for it and scales the magnitude
        double newX = Math.cos(theta)*magnitude; //finds the input to one set of wheels
        double newY = Math.sin(theta)*magnitude; //finds the input to the other set of wheels
        //from here on is just setting motor values
        rfDrive.setPower(rot + newX);//fr
        lbDrive.setPower(rot - newX);//bl
        lfDrive.setPower(rot - newY);//fl
        rbDrive.setPower(rot + newY);//br
    }*/
    //Y1: forward, X2: rotation, X1: strafing
    void drive(double Y1, double X2, double X1) {
        rfDrive.setPower(Range.clip(Y1 - X2 - X1,-1,1));
        rbDrive.setPower(Range.clip(Y1 - X2 + X1,-1,1));
        lfDrive.setPower(Range.clip(Y1 + X2 + X1,-1,1));
        lbDrive.setPower(Range.clip(Y1 + X2 - X1,-1,1));
    }

    public void lift(double power) { //  Setting the speed of the lifer
        liftL.setPower(power); //Setting the power of the left lifter motor
        liftR.setPower(power); // Setting the power of the right lifter motor
    }
    public void whomp(double power) { //  Setting the speed of the whomper
        whompR.setPower(power); //Setting the power of the right whomper motor
        whompL.setPower(power); // Setting the power of the left whomper motor
    }
    public void latchPosition(double pos) {
        latch.setPosition(pos);
        latchPos = pos;
    }
    public void plowPosition(double pos){
        plowL.setPosition(pos);
        plowR.setPosition(pos);
        plowPos = pos;
    }
    public void wheelPosition(double pos) {
        plowWheelL.setPosition(pos);
        plowWheelR.setPosition(1-pos);
    }
    public void liftPosition(int pos){
        liftL.setTargetPosition(pos);
        liftR.setTargetPosition(pos);
    }
}
