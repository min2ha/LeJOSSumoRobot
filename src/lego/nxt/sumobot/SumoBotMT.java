/**
 * 
 */
package lego.nxt.sumobot;

import lego.nxt.sumobot.behavior.BehaviorAvoid;
import lego.nxt.sumobot.behavior.BehaviorMove;

/**
 * @author Mindaugas Vidmantas
 *
 * 2019-03-10
 *
 * Email: minvidm@gmail.com, mindaugas.vidmantas@ktu.lt
 *
 */

import lejos.nxt.*;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
//import lejos.subsumption.*;

/*
 * Multi threaded version
 * 
 * */
public class SumoBotMT {
	final static float CONVERT_DIST = 20.8f;
	final static float CONVERT_TURN = 1.93f;
	final static int SPEED = 300;

	static RegulatedMotor leftMotor = Motor.C;
	static RegulatedMotor rightMotor = Motor.A;
	static RegulatedMotor sonarMotor = Motor.B;
	// new state variable
	public static UltrasonicSensor sonar = new UltrasonicSensor(SensorPort.S1);
	public static LightSensor lightSensor = new LightSensor(SensorPort.S4);;

	static boolean stop = false;

	public static void main(String args[]) {
		setSpeed(SPEED);

		Behavior move = new BehaviorMove();
		Behavior avoid = new BehaviorAvoid();

		Behavior behaviors[] = { move, avoid };

		Arbitrator arbitrator = new Arbitrator(behaviors);
		arbitrator.start();
	}

	/*
	 * Sets the new speed of both motors to the input value
	 */
	public static void setSpeed(int speed) {
		leftMotor.setSpeed(speed);
		rightMotor.setSpeed(speed);
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
	public static void turn(int angle) throws Exception {
		stop = false;

		int numDegrees = (int) Math.abs(Math.round(angle * CONVERT_TURN));

		// set motors up for counter-clockwise rotation
		RegulatedMotor forwardMotor = leftMotor;
		RegulatedMotor backwardMotor = rightMotor;

		// if angle is negative, switch motors for clockwise rotation
		if (angle < 0) {
			forwardMotor = rightMotor;
			backwardMotor = leftMotor;
		}

		forwardMotor.resetTachoCount();
		backwardMotor.resetTachoCount();

		forwardMotor.forward();
		backwardMotor.backward();

		while (((forwardMotor.getTachoCount() < numDegrees) || (backwardMotor.getTachoCount() > -numDegrees))
				&& (!stop)) {
			if (forwardMotor.getTachoCount() > numDegrees)
				forwardMotor.stop();
			if (backwardMotor.getTachoCount() < -numDegrees)
				backwardMotor.stop();
			Thread.yield();
			Thread.sleep(50);
		}

		forwardMotor.stop();
		backwardMotor.stop();
	}

	/*
	 * Drives robot forward or backwards by specified distance.
	 */
	public static void travel(int distance) throws Exception {
		stop = false;
		int numDegrees = (int) Math.abs(Math.round(distance * CONVERT_DIST));

		leftMotor.resetTachoCount();
		rightMotor.resetTachoCount();

		if (distance > 0) {
			leftMotor.forward();
			rightMotor.forward();
		} else {
			leftMotor.backward();
			rightMotor.backward();
		}

		while (((Math.abs(leftMotor.getTachoCount()) < numDegrees)
				|| (Math.abs(rightMotor.getTachoCount()) < numDegrees)) && (!stop)) {
			Thread.yield();
			Thread.sleep(50);
		}

		leftMotor.stop();
		rightMotor.stop();
	}
}
