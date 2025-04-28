package net.mqzon.wicker.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.mqzon.wicker.Wicker;
import net.mqzon.wicker.block.ModBlocks;
import net.mqzon.wicker.block.entity.custom.BasketBlockEntity;

public class ModBlockEntities {
    public static final BlockEntityType<BasketBlockEntity> BASKET_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(net.mqzon.wicker.Wicker.MOD_ID, "basket_be"),
                    FabricBlockEntityTypeBuilder.create(BasketBlockEntity::new,
                            ModBlocks.BASKET,
                            ModBlocks.BASKET_BLACK,
                            ModBlocks.BASKET_BLUE,
                            ModBlocks.BASKET_BROWN,
                            ModBlocks.BASKET_CYAN,
                            ModBlocks.BASKET_GRAY,
                            ModBlocks.BASKET_GREEN,
                            ModBlocks.BASKET_LIGHT_BLUE,
                            ModBlocks.BASKET_LIGHT_GRAY,
                            ModBlocks.BASKET_LIME,
                            ModBlocks.BASKET_MAGENTA,
                            ModBlocks.BASKET_ORANGE,
                            ModBlocks.BASKET_PINK,
                            ModBlocks.BASKET_PURPLE,
                            ModBlocks.BASKET_RED,
                            ModBlocks.BASKET_WHITE,
                            ModBlocks.BASKET_YELLOW
                    ).build());

    public static void registerBlockEntities() {
        Wicker.LOGGER.info("Registering Block Entities for " + net.mqzon.wicker.Wicker.MOD_ID);
    }
}
