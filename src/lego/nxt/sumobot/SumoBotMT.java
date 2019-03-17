/**
 * 
 */
package lego.nxt.sumobot;

import lego.nxt.sumobot.behavior.BehaviorAvoid;
import lego.nxt.sumobot.behavior.BehaviorMove;
import lego.nxt.sumobot.behavior.BehaviourContraAttack;
import lego.nxt.sumobot.behavior.BehaviourTurnToEnemy;

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
	static int SPEED = 450;
	static int ACCELERATION = 200;
	public static int LIGHTVALUE = 40;	
	public static int SONARFRONTVALUE = 77;
	public static int SONARLEFTVALUE = 77;
	public static int SONARRIGHTVALUE = 77;

	static RegulatedMotor leftMotor = Motor.C;
	static RegulatedMotor rightMotor = Motor.A;
	static RegulatedMotor sonarMotor = Motor.B;
	// new state variable
	public static UltrasonicSensor sonarFront = new UltrasonicSensor(SensorPort.S1);
	public static UltrasonicSensor sonarRight = new UltrasonicSensor(SensorPort.S2);
	
	public static LightSensor lightSensor = new LightSensor(SensorPort.S4);;

	static boolean stop = false;

	public static void main(String args[]) {
		setSpeed(SPEED);
		setAcceleration(ACCELERATION);

		Behavior move = new BehaviorMove();
		Behavior avoid = new BehaviorAvoid();		
		// may conflict
		Behavior sonars = new BehaviourTurnToEnemy();
		Behavior contraAttack = new BehaviourContraAttack();
		
		Behavior behaviors[] = { move, avoid };

		Arbitrator arbitrator = new Arbitrator(behaviors);
		arbitrator.start();
	}

	/**
	 * @param aCCELERATION2
	 */
	private static void setAcceleration(int acceleration) {
		// TODO Auto-generated method stub
		SumoBotMT.SPEED = acceleration;
		leftMotor.setAcceleration(acceleration);
		rightMotor.setAcceleration(acceleration);
	}

	/*
	 * Sets the new speed of both motors to the input value
	 */
	public static void setSpeed(int speed) {
		SumoBotMT.SPEED = speed;
		leftMotor.setSpeed(speed);
		rightMotor.setSpeed(speed);
	}
	public static int getSpeed() {
		return SumoBotMT.SPEED;
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

		
		System.out.println("--- Main  -- turn() --  SumoBotMT.LIGHTVALUE  = " + SumoBotMT.LIGHTVALUE + ", SumoBotMT.lightSensor.getLightValue() = " + SumoBotMT.lightSensor.getLightValue());

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
			Thread.sleep(10);
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
	
	public static void travel() throws Exception {
		stop = false;

		SumoBotMT.LIGHTVALUE = lightSensor.getLightValue();
		leftMotor.forward();
		rightMotor.forward();
		
		while ( !stop ) {
			//System.out.println("--- Main  -- travel() --  SumoBotMT.LIGHTVALUE  = " + SumoBotMT.LIGHTVALUE + ", SumoBotMT.lightSensor.getLightValue() = " + SumoBotMT.lightSensor.getLightValue());
			SumoBotMT.LIGHTVALUE = SumoBotMT.lightSensor.getLightValue();
					
			Thread.yield();
			Thread.sleep(50);
		}

		leftMotor.stop();
		rightMotor.stop();
	}

	public static void turnToEnemy() throws Exception {
		stop = false;

		//SumoBotMT.LIGHTVALUE = lightSensor.getLightValue();
		leftMotor.backward();
		rightMotor.forward();
		
		while ( !stop ) {
			//System.out.println("--- Main  -- travel() --  SumoBotMT.LIGHTVALUE  = " + SumoBotMT.LIGHTVALUE + ", SumoBotMT.lightSensor.getLightValue() = " + SumoBotMT.lightSensor.getLightValue());
			SumoBotMT.SONARFRONTVALUE = SumoBotMT.sonarFront.getDistance();
			SumoBotMT.SONARRIGHTVALUE = SumoBotMT.sonarRight.getDistance();
					
			Thread.yield();
			Thread.sleep(50);
		}

		leftMotor.stop();
		rightMotor.stop();
	}

	
	public static void observeLightSensorValue() throws Exception {
		stop = false;


		SumoBotMT.LIGHTVALUE = SumoBotMT.lightSensor.getLightValue();
		
		
		while ( !stop ) {
			Thread.yield();
			Thread.sleep(50);
		}

		leftMotor.stop();
		rightMotor.stop();
	}

}
