package org.usfirst.frc.team6101.robot;
import java.util.*;
public class SleepDemo {

	public static void main(String[] args) {
		try {
			System.out.println(new Date( ) + "\n");
			Thread.sleep(2000);
			System.out.println(new Date( ) + "\n");
			Thread.sleep(2000);
			System.out.println(new Date( ) + "\n");
			System.out.println("Four seconds.");
		} catch (Exception e) {
			System.out.println("Exception.");
		}

	}

}
