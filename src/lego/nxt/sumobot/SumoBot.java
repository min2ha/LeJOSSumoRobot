package lego.nxt.sumobot;

import lejos.nxt.Battery;
import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.objectdetection.RangeFeatureDetector;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lego.nxt.sumobot.behavior.BatteryLowBehavior;
import lego.nxt.sumobot.behavior.DriveForwardBehavior;
import lego.nxt.sumobot.behavior.EnemyDetectionBehavior;
import lego.nxt.sumobot.behavior.ObstacleBehavior;
import lego.nxt.sumobot.behavior.SonarEnemyDetection;
//import lejos.hardware.motor.NXTRegulatedMotor;
import lego.nxt.sumobot.objectdetection.ObjectDetect;

public class SumoBot {
	
	
	
	private RegulatedMotor left = Motor.A;
    private RegulatedMotor right = Motor.C;
    
    private UltrasonicSensor sonarFront;// = new UltrasonicSensor(SensorPort.S1);
	//private UltrasonicSensor sonarLeft = new UltrasonicSensor(SensorPort.S2);
    
    private TouchSensor touch;
    
    private LightSensor lightSensor;

    private final Arbitrator arbitrator;

    /*
     * getTouch().fetchSample
     * 
     * The second argument allows you to specify an offset into the array 
     * at which to start storing the data. 
     * In general in Java it is good practice to do this, 
     * it basically makes up for the lack of pointers in Java compared to C 
     * and makes some operations easier/more efficient. 
     * 
     * Think of for example having 3 single axis accelerometers 
     * that you wish to store the values in 
     * locations 0, 1, 2 of an array 
     * to simulate a 3 axis device. 
     * 
     * The offset allows for this without having to copy data about.
     * 
     * 
     * */
    
    public SumoBot() {
    	
    	//left = new Motor(A);
    	
    	//setPower(int aPower)
    	//value between 0 and 100
    	
    	//acceleration?
    	//setAcceleration(int acceleration)
    	//
    	//Lets you control how fast the motor speed will change from one speed to another. 
    	//The acceleration is set in degrees per second per second. 
    	//Use small values (try 200 or 500) to have your robot smoothly accelerate up to speed.
    	
    	//left = new NXTRegulatedMotor(MotorPort.A);
        //right = new NXTRegulatedMotor(MotorPort.B);
        
        //touch = new TouchSensor(SensorPort.S1);

        lightSensor = new LightSensor(SensorPort.S4);
        sonarFront = new UltrasonicSensor(SensorPort.S1);
        
        
        //Push/Attack behaviour!
        
        Behavior forwardToEnemy = new DriveForwardBehavior(this);        
        Behavior hitWhiteLine = new ObstacleBehavior(this);
        //Behavior lowBattery = new BatteryLowBehavior(this, Battery.getVoltage());
        Behavior enemyDetectionBehavior = new EnemyDetectionBehavior(this);
        Behavior sonarEnemyDetection = new SonarEnemyDetection(this);
        
        arbitrator = new Arbitrator(
        		new Behavior[]{
        				enemyDetectionBehavior,
        				//sonarEnemyDetection
        				forwardToEnemy,
        				hitWhiteLine 
        				});
        //, lowBattery});
        //forward, 
        
        
        arbitrator.start();
    }
    
    
	public static void main(String[] args) {
		
		
		/*
		ObjectDetect listener = new ObjectDetect();
		UltrasonicSensor us = new UltrasonicSensor(SensorPort.S4);
		RangeFeatureDetector fd = new RangeFeatureDetector(us, MAX_DETECT, 500);
		fd.addListener(listener);
		Button.ENTER.waitForPressAndRelease();
		*/
		UltrasonicSensor uSensorFront = new UltrasonicSensor(SensorPort.S2);
		UltrasonicSensor uSensorLeft = new UltrasonicSensor(SensorPort.S1);
		
		// Object to communicate with sound sensorplugged into port 2:
		//Accelerometer accelerometer = SensorSelector.createAccelerometer(SensorPort.S1);
		//LightSensor smuxLiSensor = new LightSensor(new SMUXSensorPort(SensorPort.S1, 2), true);
		
		LightSensor lightSensor = new LightSensor(SensorPort.S4);
		
		new SumoBot();
	}

	
	private void init(){
        resetMotor(left);
        resetMotor(right);
    }

    private void resetMotor(RegulatedMotor motor){
        motor.resetTachoCount();
        motor.rotateTo(0);
        motor.setSpeed(100);
        motor.setAcceleration(50);
    }

    public RegulatedMotor getLeft() {
        return left;
    }

    public RegulatedMotor getRight() {
        return right;
    }
    public void setLeftSpeed(int speed) {
        left.setSpeed(speed);
    }
    public void setRightSpeed(int speed) {
        right.setSpeed(speed);
    }

    public TouchSensor getTouch() {
        return touch;
    }
    public LightSensor getLight() {
        return lightSensor;
    }


	/**
	 * @return the uSensorFront
	 */
	public UltrasonicSensor getsonarFront() {
		return sonarFront;
	}


	
}
