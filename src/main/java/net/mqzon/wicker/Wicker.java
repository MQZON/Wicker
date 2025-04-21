package net.mqzon.wicker;

import net.fabricmc.api.ModInitializer;

import net.mqzon.wicker.block.ModBlocks;
import net.mqzon.wicker.block.entity.ModBlockEntities;
import net.mqzon.wicker.item.ModItemGroups;
import net.mqzon.wicker.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Wicker implements ModInitializer {
	public static final String MOD_ID = "wicker";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlockEntities.registerBlockEntities();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();
	}
}