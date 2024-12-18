package com.example;

import net.fabricmc.api.ModInitializer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AucFThelper implements ModInitializer {

	public static boolean activAucHelper;
	public static  int hotBat = 3;
	public static  int time = 10;
	public static  int price = 100000;
 	public static Thread thread;

	public static final Logger LOGGER = LogManager.getLogger("aucfthelper");
	@Override
	public void onInitialize() {

	}
}