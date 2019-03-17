/**
 * 
 */
package lego.nxt.sumobot;

import lego.nxt.sumobot.behavior.MoveSonarBehavior;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

/**
 * @author Mindaugas Vidmantas
 *
 * 2019-03-16
 *
 * Email: minvidm@gmail.com, mindaugas.vidmantas@ktu.lt
 *
 */
public class SumoBot2mot2sonar1acc {
	final static float CONVERT_DIST = 20.8f;
	final static float CONVERT_TURN = 1.93f;
	final static int SPEED = 300;
	
	static int SONAR_1_MIN_VALUE = 255; // nearest distance TO ENEMY, initial max 255
	static int SONAR_1_MIN_VALUE_ANGLE = 0; // point to enemy

	static RegulatedMotor leftMotor = Motor.C;
	static RegulatedMotor rightMotor = Motor.A;
	//public static RegulatedMotor sonarMotor = Motor.B;
	// new state variable
	public static UltrasonicSensor sonarFront = new UltrasonicSensor(SensorPort.S1);
	public static UltrasonicSensor sonarRight = new UltrasonicSensor(SensorPort.S1);
	public static LightSensor lightSensor = new LightSensor(SensorPort.S4);;

	static boolean stop = false;

	public static void main(String args[]) {
		setSpeed(SPEED);

		Behavior move = new MoveSonarBehavior();
		// Behavior avoid = new BehaviorAvoid();

		Behavior behaviors[] = { move };// , avoid };

		Arbitrator arbitrator = new Arbitrator(behaviors);
		arbitrator.start();
	}

	/*
	 * Sets the new speed of both motors to the input value
	 */
	public static void setSpeed(int speed) {
		leftMotor.setSpeed(speed);
		rightMotor.setSpeed(speed);
		//sonarMotor.setSpeed(speed);
	}

	/*
	 * Stops all turning and traveling immediately
	 */
	public static void stop() {
		stop = true;
	}

	
	public static void turnAccordingToSonarValue(int angle) throws Exception {
		stop = false;

		// int numDegrees = (int) Math.abs(Math.round(angle * CONVERT_TURN));
		// int sonar_1_MAXvalue = 0;
		int sonar_1_MIN_value = 0; // nearest distance
		int sonar_1_MIN_value_angle = 0; // point to enemy

		int sonar_2_MAXvalue = 0;
		int sonar_2_MAXvalue_angle = 0;

	

		while (!stop) {
			
			Thread.yield();
			Thread.sleep(50);
		}

		leftMotor.stop();
		rightMotor.stop();
	}

	/*
	 * Drives robot forward or backwards by specified distance.
	 */
	public static void travel(int distance) throws Exception {
		stop = false;
		int numDegrees = (int) Math.abs(Math.round(distance * CONVERT_DIST));
		//System.out.println("--- SumoBotMovingSonar  -- numDegrees = " + numDegrees);
		// sonarMotor.getTachoCount()
		// System.out.println("--- SumoBotMovingSonar --
		// sonarMotor.getTachoCount() = " + sonarMotor.getTachoCount());

		// leftMotor.resetTachoCount();
		// rightMotor.resetTachoCount();
		// sonarMotor.resetTachoCount();

		//System.out.println("--- SumoBotMovingSonar  -- sonarMotor.getTachoCount() = " + sonarMotor.getTachoCount());

		/*
		 * if (distance > 0) { leftMotor.forward(); rightMotor.forward(); } else
		 * { leftMotor.backward(); rightMotor.backward(); }
		 */

		/*
		 * 
		 * sonarMotor.resetTachoCount(); // if angle is negative, switch motors
		 * for clockwise rotation if ((sonarMotor.getTachoCount()) < 0)
		 * sonarMotor.backward(); else sonarMotor.forward();
		 * 
		 */

		System.out.println("--- SumoBot2mot2sonar1acc  -- SONAR_1_MIN_VALUE = " + SONAR_1_MIN_VALUE);
		
		
		/*
		if ((sonarMotor.getTachoCount()) > numDegrees) {
			sonarMotor.resetTachoCount();
			sonarMotor.backward();
		} else {
			sonarMotor.resetTachoCount();
			sonarMotor.forward();
		}
		*/
		leftMotor.forward(); rightMotor.forward(); 

		//SUKTIS KOL APTIKS PRIESA!!!
		while ((sonarFront.getDistance()<45 || (sonarRight.getDistance()<45)) && (!stop)) {

			//System.out.println("--- SumoBotMovingSonar  -- Sonar.getDistance = " + sonar.getDistance() + ", angle = " + (sonarMotor.getTachoCount()));
			
			//if MIN in FRONT - GO straight! With Acceleration! And observe ACCELERATION Sensor
			if (SONAR_1_MIN_VALUE > sonarFront.getDistance()){
				
				SONAR_1_MIN_VALUE = sonarFront.getDistance();
				//SONAR_1_MIN_VALUE_ANGLE = sonarMotor.getTachoCount();
				System.out.println("--- SumoBotMovingSonar  -- SONAR_1_MIN_VALUE = " + SONAR_1_MIN_VALUE + 
						", SONAR_1_MIN_VALUE_ANGLE = " + SONAR_1_MIN_VALUE_ANGLE);
			}
			
			//if MIN in LEFT - TURN 90 degrees and GO!
			if (SONAR_1_MIN_VALUE > sonarRight.getDistance()){
				
				SONAR_1_MIN_VALUE = sonarRight.getDistance();
				//SONAR_1_MIN_VALUE_ANGLE = sonarMotor.getTachoCount();
				System.out.println("--- SumoBotMovingSonar  -- SONAR_1_MIN_VALUE = " + SONAR_1_MIN_VALUE + 
						", SONAR_1_MIN_VALUE_ANGLE = " + SONAR_1_MIN_VALUE_ANGLE);
			}
			
			
			// System.out.println("--- SumoBotMovingSonar -- in while
			// sonarMotor.getTachoCount() = " +
			// Math.abs(sonarMotor.getTachoCount()));

			Thread.yield();
			Thread.sleep(50);
			
			SONAR_1_MIN_VALUE = 255;
			SONAR_1_MIN_VALUE_ANGLE = 0;
		}

	
		leftMotor.stop();
		rightMotor.stop();
		
	}
}
