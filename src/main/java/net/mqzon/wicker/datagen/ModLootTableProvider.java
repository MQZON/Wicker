package net.mqzon.wicker.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;
import net.mqzon.wicker.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.BASKET, shulkerBoxDrops((ModBlocks.BASKET)));
        addDrop(ModBlocks.BASKET_BLACK, shulkerBoxDrops(ModBlocks.BASKET_BLACK));
        addDrop(ModBlocks.BASKET_BLUE, shulkerBoxDrops(ModBlocks.BASKET_BLUE));
        addDrop(ModBlocks.BASKET_BROWN, shulkerBoxDrops(ModBlocks.BASKET_BROWN));
        addDrop(ModBlocks.BASKET_CYAN, shulkerBoxDrops(ModBlocks.BASKET_CYAN));
        addDrop(ModBlocks.BASKET_GRAY, shulkerBoxDrops(ModBlocks.BASKET_GRAY));
        addDrop(ModBlocks.BASKET_GREEN, shulkerBoxDrops(ModBlocks.BASKET_GREEN));
        addDrop(ModBlocks.BASKET_LIGHT_BLUE, shulkerBoxDrops(ModBlocks.BASKET_LIGHT_BLUE));
        addDrop(ModBlocks.BASKET_LIGHT_GRAY, shulkerBoxDrops(ModBlocks.BASKET_LIGHT_GRAY));
        addDrop(ModBlocks.BASKET_LIME, shulkerBoxDrops(ModBlocks.BASKET_LIME));
        addDrop(ModBlocks.BASKET_MAGENTA, shulkerBoxDrops(ModBlocks.BASKET_MAGENTA));
        addDrop(ModBlocks.BASKET_ORANGE, shulkerBoxDrops(ModBlocks.BASKET_ORANGE));
        addDrop(ModBlocks.BASKET_PINK, shulkerBoxDrops(ModBlocks.BASKET_PINK));
        addDrop(ModBlocks.BASKET_PURPLE, shulkerBoxDrops(ModBlocks.BASKET_PURPLE));
        addDrop(ModBlocks.BASKET_RED, shulkerBoxDrops(ModBlocks.BASKET_RED));
        addDrop(ModBlocks.BASKET_WHITE, shulkerBoxDrops(ModBlocks.BASKET_WHITE));
        addDrop(ModBlocks.BASKET_YELLOW, shulkerBoxDrops(ModBlocks.BASKET_YELLOW));

    }
}
