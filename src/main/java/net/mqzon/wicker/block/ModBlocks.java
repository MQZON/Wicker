package net.mqzon.wicker.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.mqzon.wicker.Wicker;
import net.mqzon.wicker.block.custom.BasketBlock;

public class ModBlocks {

    public static final Block BASKET = registerBlock("basket", new BasketBlock((DyeColor) null,
            AbstractBlock.Settings.create().strength(1.0F).nonOpaque()
            .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(net.mqzon.wicker.Wicker.MOD_ID, "basket")))));

    public static final Block BASKET_BLACK = registerBlock("basket_black", new BasketBlock(DyeColor.BLACK,
            AbstractBlock.Settings.create().strength(1.0F).nonOpaque()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(net.mqzon.wicker.Wicker.MOD_ID, "basket_black")))));

    public static final Block BASKET_BLUE = registerBlock("basket_blue", new BasketBlock(DyeColor.BLUE,
            AbstractBlock.Settings.create().strength(1.0F).nonOpaque()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(net.mqzon.wicker.Wicker.MOD_ID, "basket_blue")))));

    public static final Block BASKET_BROWN = registerBlock("basket_brown", new BasketBlock(DyeColor.BROWN,
            AbstractBlock.Settings.create().strength(1.0F).nonOpaque()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(net.mqzon.wicker.Wicker.MOD_ID, "basket_brown")))));

    public static final Block BASKET_CYAN = registerBlock("basket_cyan", new BasketBlock(DyeColor.CYAN,
            AbstractBlock.Settings.create().strength(1.0F).nonOpaque()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(net.mqzon.wicker.Wicker.MOD_ID, "basket_cyan")))));

    public static final Block BASKET_GRAY = registerBlock("basket_gray", new BasketBlock(DyeColor.GRAY,
            AbstractBlock.Settings.create().strength(1.0F).nonOpaque()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(net.mqzon.wicker.Wicker.MOD_ID, "basket_gray")))));

    public static final Block BASKET_GREEN = registerBlock("basket_green", new BasketBlock(DyeColor.GREEN,
            AbstractBlock.Settings.create().strength(1.0F).nonOpaque()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(net.mqzon.wicker.Wicker.MOD_ID, "basket_green")))));

    public static final Block BASKET_LIGHT_BLUE = registerBlock("basket_light_blue", new BasketBlock(DyeColor.LIGHT_BLUE,
            AbstractBlock.Settings.create().strength(1.0F).nonOpaque()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(net.mqzon.wicker.Wicker.MOD_ID, "basket_light_blue")))));

    public static final Block BASKET_LIGHT_GRAY = registerBlock("basket_light_gray", new BasketBlock(DyeColor.LIGHT_GRAY,
            AbstractBlock.Settings.create().strength(1.0F).nonOpaque()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(net.mqzon.wicker.Wicker.MOD_ID, "basket_light_gray")))));

    public static final Block BASKET_LIME = registerBlock("basket_lime", new BasketBlock(DyeColor.LIME,
            AbstractBlock.Settings.create().strength(1.0F).nonOpaque()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(net.mqzon.wicker.Wicker.MOD_ID, "basket_lime")))));

    public static final Block BASKET_MAGENTA = registerBlock("basket_magenta", new BasketBlock(DyeColor.MAGENTA,
            AbstractBlock.Settings.create().strength(1.0F).nonOpaque()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(net.mqzon.wicker.Wicker.MOD_ID, "basket_magenta")))));

    public static final Block BASKET_ORANGE = registerBlock("basket_orange", new BasketBlock(DyeColor.ORANGE,
            AbstractBlock.Settings.create().strength(1.0F).nonOpaque()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(net.mqzon.wicker.Wicker.MOD_ID, "basket_orange")))));

    public static final Block BASKET_PINK = registerBlock("basket_pink", new BasketBlock(DyeColor.PINK,
            AbstractBlock.Settings.create().strength(1.0F).nonOpaque()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(net.mqzon.wicker.Wicker.MOD_ID, "basket_pink")))));

    public static final Block BASKET_PURPLE = registerBlock("basket_purple", new BasketBlock(DyeColor.PURPLE,
            AbstractBlock.Settings.create().strength(1.0F).nonOpaque()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(net.mqzon.wicker.Wicker.MOD_ID, "basket_purple")))));

    public static final Block BASKET_RED = registerBlock("basket_red", new BasketBlock(DyeColor.RED,
            AbstractBlock.Settings.create().strength(1.0F).nonOpaque()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(net.mqzon.wicker.Wicker.MOD_ID, "basket_red")))));

    public static final Block BASKET_WHITE = registerBlock("basket_white", new BasketBlock(DyeColor.WHITE,
            AbstractBlock.Settings.create().strength(1.0F).nonOpaque()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(net.mqzon.wicker.Wicker.MOD_ID, "basket_white")))));

    public static final Block BASKET_YELLOW = registerBlock("basket_yellow", new BasketBlock(DyeColor.YELLOW,
            AbstractBlock.Settings.create().strength(1.0F).nonOpaque()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(net.mqzon.wicker.Wicker.MOD_ID, "basket_yellow")))));



    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(net.mqzon.wicker.Wicker.MOD_ID, name), block);
    }
    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(net.mqzon.wicker.Wicker.MOD_ID, name),
                new BlockItem(block, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(net.mqzon.wicker.Wicker.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        Wicker.LOGGER.info("Registering Blocks for " + net.mqzon.wicker.Wicker.MOD_ID);
    }
}
