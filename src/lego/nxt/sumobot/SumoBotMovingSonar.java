/**
 * 
 */
package lego.nxt.sumobot;

import lego.nxt.sumobot.behavior.BehaviorAvoid;
import lego.nxt.sumobot.behavior.BehaviorMove;
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
 *         2019-03-10
 *
 *         Email: minvidm@gmail.com, mindaugas.vidmantas@ktu.lt
 *
 */
public class SumoBotMovingSonar {
	final static float CONVERT_DIST = 20.8f;
	final static float CONVERT_TURN = 1.93f;
	final static int SPEED = 300;
	
	static int SONAR_1_MIN_VALUE = 255; // nearest distance TO ENEMY, initial max 255
	static int SONAR_1_MIN_VALUE_ANGLE = 0; // point to enemy

	// static RegulatedMotor leftMotor = Motor.C;
	// static RegulatedMotor rightMotor = Motor.A;
	public static RegulatedMotor sonarMotor = Motor.B;
	// new state variable
	public static UltrasonicSensor sonar = new UltrasonicSensor(SensorPort.S1);
	// public static LightSensor lightSensor = new LightSensor(SensorPort.S4);;

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
		// leftMotor.setSpeed(speed);
		// rightMotor.setSpeed(speed);
		sonarMotor.setSpeed(speed);
	}

	/*
	 * Stops all turning and traveling immediately
	 */
	public static void stop() {
		stop = true;
	}

	/*
	 * Turns robot clockwise or counter-clockwise by the specified degrees.
	 */
	/*
	 * public static void turn(int angle) throws Exception { stop = false;
	 * 
	 * int numDegrees = (int) Math.abs(Math.round(angle * CONVERT_TURN));
	 * 
	 * // set motors up for counter-clockwise rotation //RegulatedMotor
	 * forwardMotor = leftMotor; //RegulatedMotor backwardMotor = rightMotor;
	 * RegulatedMotor sonarMoveMotor = sonarMotor;
	 * 
	 * // if angle is negative, switch motors for clockwise rotation if (angle <
	 * 0) { forwardMotor = rightMotor; backwardMotor = leftMotor; }
	 * 
	 * //forwardMotor.resetTachoCount(); //backwardMotor.resetTachoCount();
	 * sonarMoveMotor.resetTachoCount();
	 * 
	 * forwardMotor.forward(); backwardMotor.backward();
	 * 
	 * while (((forwardMotor.getTachoCount() < numDegrees) ||
	 * (backwardMotor.getTachoCount() > -numDegrees)) && (!stop)) { if
	 * (forwardMotor.getTachoCount() > numDegrees) forwardMotor.stop(); if
	 * (backwardMotor.getTachoCount() < -numDegrees) backwardMotor.stop();
	 * Thread.yield(); Thread.sleep(50); }
	 * 
	 * forwardMotor.stop(); backwardMotor.stop(); }
	 */

	/*
	 * Turns robot According To Sonar MAX Value
	 * turnCalibrateAcordingToSonarValue ONLY IF OBJECT CLOSER THAN 60 CM
	 */

	public static void turnAccordingToSonarValue(int angle) throws Exception {
		stop = false;

		// int numDegrees = (int) Math.abs(Math.round(angle * CONVERT_TURN));
		// int sonar_1_MAXvalue = 0;
		int sonar_1_MIN_value = 0; // nearest distance
		int sonar_1_MIN_value_angle = 0; // point to enemy

		int sonar_2_MAXvalue = 0;
		int sonar_2_MAXvalue_angle = 0;

		// set motors up for counter-clockwise rotation
		// RegulatedMotor forwardMotor = leftMotor;
		// RegulatedMotor backwardMotor = rightMotor;
		// RegulatedMotor sonarMoveMotor = sonarMotor;

		// if angle is negative, switch motors for clockwise rotation
		/*
		 * if (angle < 0) { forwardMotor = rightMotor; backwardMotor =
		 * leftMotor; }
		 */

		// forwardMotor.resetTachoCount();
		// backwardMotor.resetTachoCount();
		// sonarMoveMotor.resetTachoCount();

		// forwardMotor.forward();
		// backwardMotor.backward();

		while (!stop) {

			// sumoBot.getLight().getLightValue()

			//System.out.println("--- SumoBotMovingSonar  -- Sonar.getDistance = " + sonar.getDistance() + ", angle = " + (sonarMotor.getTachoCount()));

			/*
			if (sonar.getDistance() < sonar_1_MIN_value) {
				sonar_1_MIN_value = sonar.getDistance();
				sonar_1_MIN_value_angle = Math.abs(sonarMotor.getTachoCount());
			}
			System.out.println("--- SumoBotMovingSonar  -- Sonar.getDistance = " + sonar_1_MIN_value
					+ ", SonarMotor angle = " + sonar_1_MIN_value_angle);

			*/
			/*
			 * if (forwardMotor.getTachoCount() > numDegrees)
			 * forwardMotor.stop(); if (backwardMotor.getTachoCount() <
			 * -numDegrees) backwardMotor.stop();
			 */

			Thread.yield();
			Thread.sleep(50);
		}

		// forwardMotor.stop();
		// backwardMotor.stop();
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

		System.out.println("--- SumoBotMovingSonar  -- SONAR_1_MIN_VALUE = " + SONAR_1_MIN_VALUE);
		
		
		if ((sonarMotor.getTachoCount()) > numDegrees) {
			sonarMotor.resetTachoCount();
			sonarMotor.backward();
		} else {
			sonarMotor.resetTachoCount();
			sonarMotor.forward();
		}

		while ((Math.abs(sonarMotor.getTachoCount()) < numDegrees) && (!stop)) {

			//System.out.println("--- SumoBotMovingSonar  -- Sonar.getDistance = " + sonar.getDistance() + ", angle = " + (sonarMotor.getTachoCount()));
			if (SONAR_1_MIN_VALUE > sonar.getDistance()){
				
				SONAR_1_MIN_VALUE = sonar.getDistance();
				SONAR_1_MIN_VALUE_ANGLE = sonarMotor.getTachoCount();
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

		// leftMotor.stop();
		sonarMotor.stop();
	}
}
