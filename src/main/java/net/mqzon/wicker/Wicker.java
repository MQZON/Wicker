package net.mqzon.wicker;

import net.fabricmc.api.ModInitializer;

import net.mqzon.wicker.block.ModBlocks;
import net.mqzon.wicker.block.entity.ModBlockEntities;
import net.mqzon.wicker.item.ModItemGroups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Wicker implements ModInitializer {
	public static final String MOD_ID = "wicker";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlockEntities.registerBlockEntities();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();
	}
}