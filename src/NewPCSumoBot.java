import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.SoundSensor;

import lejos.nxt.UltrasonicSensor;
import lejos.nxt.addon.SensorSelector;
import lejos.robotics.Accelerometer;

/**
 * 
 */

/**
 * @author Mindaugas Vidmantas
 *
 * 2019-03-01
 *
 * Email: minvidm@gmail.com, mindaugas.vidmantas@ktu.lt
 *
 */
public class NewPCSumoBot {
	
	/**
     * @param args
     */
	public static void main(String[] args) throws Exception {
		// Object to communicate with proximity sensorplugged into port 4:
		//UltrasonicSensor uSensorFront = new UltrasonicSensor(SensorPort.S2);
		//UltrasonicSensor uSensorLeft = new UltrasonicSensor(SensorPort.S1);
		// Object to communicate with sound sensorplugged into port 2:
		//SoundSensor sSensor= new SoundSensor(SensorPort.S2);
		

		//Accelerometer accelerometer = SensorSelector.createAccelerometer(SensorPort.S1);
		
		//LightSensor smuxLiSensor = new LightSensor(new SMUXSensorPort(SensorPort.S1, 2), true);
		
		//HiTechnicColorSensor2 FSensor = new HiTechnicColorSensor2(SensorPort.S3,2);
		
		//LightSensor lightSensor = new LightSensor(SensorPort.S4);
		
		//ColorSensor cs = new ColorSensor(SensorPort.S4);
		//AccelSensor light = new LightSensor(SensorPort.S4);

		//Color color = cs.getColor();
		
		//int red = color.getRed();
	    
	    
		while(!Button.ESCAPE.isDown())
		{
			//if (lightSensor.getLightValue() > 55) //found white, so turn left 
			   { 
				//turn right
				Motor.A.setPower(10);
				Motor.C.setPower(10);
				
				Motor.A.backward();
				Motor.C.backward();
				doFor(2000);
				Motor.A.stop();
				Motor.C.stop();
			   } 
			   //else //found black, so turn right 
			   { 
			      //(put your right turn statements here)
				   Motor.A.setPower(50);
					Motor.C.setPower(50);
					Motor.A.setAcceleration(30);
					Motor.C.setAcceleration(30);
					
					Motor.A.forward();
					Motor.C.forward();
					doFor(2000);
			   }
			/*
			
			int white = color.getRed();
			
			  //LCD.drawInt(light.getLightValue(), 4, 0, 0);
		      //LCD.drawInt(light.getNormalizedLightValue(), 4, 0, 1);
		      //LCD.drawInt(SensorPort.S4.readRawValue(), 4, 0, 2);
		      //LCD.drawInt(SensorPort.S4.readValue(), 4, 0, 3);
		      
		      LCD.clear();
			//LCD.drawInt(SensorPort.S4.readValue(), 4, 0, 0);
		      LCD.drawString(uSensorFront.getVendorID(), 0, 0);
		      LCD.drawString(uSensorFront.getProductID(), 0, 1);
		      LCD.drawString(uSensorFront.getVersion(), 0, 2);
		      LCD.drawInt(uSensorFront.getDistance(), 0, 3);
		      
		      LCD.drawInt(uSensorLeft.getDistance(), 0, 4);
		      
		      //LCD.drawString(cs.getColor(), 0, 4);
			
			System.out.println("ULTRA DESINE  - S1, RAW = " + SensorPort.S1.readRawValue() + ", readValue() = " + SensorPort.S1.readValue());
			System.out.println("ULTRA TIESIAI - S2, RAW = " + SensorPort.S2.readRawValue() + ", readValue() = " + SensorPort.S2.readValue());
			System.out.println("ACCEL           S3, RAW = " + SensorPort.S3.readRawValue() + ", readValue() = " + SensorPort.S3.readValue());
			System.out.println("--- Spalva      S4, RAW = " + SensorPort.S4.readRawValue() + ", readValue() = " + SensorPort.S4.readValue());
			System.out.println("--- Spalva      S4, color =  = " +  color.getColor());
			*/
			//System.out.println("--- Spalva      S4, RAW = " + lightSensor.getLightValue());			
			/*
			if (color.getColor() == red ){}
			{
				System.out.println("--- Spalva  RAUDONA    S4 ");

			}
			*/
			
			
			/*
			while (true) {
			      LCD.drawInt(light.getLightValue(), 4, 0, 0);
			      LCD.drawInt(light.getNormalizedLightValue(), 4, 0, 1);
			      LCD.drawInt(SensorPort.S1.readRawValue(), 4, 0, 2);
			      LCD.drawInt(SensorPort.S1.readValue(), 4, 0, 3);
			    }
			*/
			/*
			LCD.clear();
			LCD.drawString("Accel", 0, 0);
			LCD.drawInt(accelerometer.getXAccel(), 6, 0, 1);
			LCD.drawInt(accelerometer.getYAccel(), 6, 0, 2);
			LCD.drawInt(accelerometer.getZAccel(), 6, 0, 3);
			Thread.sleep(500);
			*/

			/*
			if(uSensorFront.getDistance() < 50)
			{
			
				//Motor.A.mo
				Motor.A.setPower(10);
				Motor.C.setPower(10);
				
				Motor.A.forward();
				Motor.C.forward();
				doFor(500);
				Motor.A.stop();
				Motor.C.stop();
			}
			else if(uSensorLeft.getDistance() < 30)
			{
				Motor.A.setPower(30);
				Motor.C.setPower(30);
				
				Motor.A.backward();
				Motor.C.backward();
				doFor(100);
				Motor.A.stop();
				Motor.C.stop();
			}
			*/
			/*
			
			if (Button.LEFT.isDown())
			{
				Motor.A.setPower(30);
				Motor.C.setPower(30);
				
				Motor.A.forward();
				Motor.C.forward();
				doFor(100);
				Motor.A.stop();
				Motor.C.stop();
			}
			else if (Button.RIGHT.isDown())
			{
				Motor.A.setPower(30);
				Motor.C.setPower(30);
				
				Motor.A.backward();
				Motor.C.backward();
				doFor(100);
				Motor.A.stop();
				Motor.C.stop();
			}
			*/
		}
	}

	
	public static void doFor(int milli)
	{
		try{Thread.sleep(milli);}
		catch(Exception e){};}
	}


