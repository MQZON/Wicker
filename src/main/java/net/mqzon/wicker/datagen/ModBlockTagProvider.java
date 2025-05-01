package net.mqzon.wicker.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.mqzon.wicker.block.ModBlocks;
import net.mqzon.wicker.utils.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Blocks.LINED_BASKETS)
                .add(ModBlocks.BASKET_BLACK)
                .add(ModBlocks.BASKET_BLUE)
                .add(ModBlocks.BASKET_BROWN)
                .add(ModBlocks.BASKET_CYAN)
                .add(ModBlocks.BASKET_GRAY)
                .add(ModBlocks.BASKET_GREEN)
                .add(ModBlocks.BASKET_LIGHT_BLUE)
                .add(ModBlocks.BASKET_LIGHT_GRAY)
                .add(ModBlocks.BASKET_LIME)
                .add(ModBlocks.BASKET_MAGENTA)
                .add(ModBlocks.BASKET_ORANGE)
                .add(ModBlocks.BASKET_PINK)
                .add(ModBlocks.BASKET_PURPLE)
                .add(ModBlocks.BASKET_RED)
                .add(ModBlocks.BASKET_WHITE)
                .add(ModBlocks.BASKET_YELLOW);

        getOrCreateTagBuilder(ModTags.Blocks.BASKETS)
                .add(ModBlocks.BASKET)
                .addTag(ModTags.Blocks.LINED_BASKETS);

    }
}
