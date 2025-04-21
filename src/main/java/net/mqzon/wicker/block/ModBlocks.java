package net.mqzon.wicker.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.mqzon.wicker.Wicker;
import net.mqzon.wicker.block.custom.BasketBlock;

public class ModBlocks {
    public static final Block BASKET = registerBlock("basket", new BasketBlock(AbstractBlock.Settings.create().strength(1.0F).nonOpaque()
            .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(net.mqzon.wicker.Wicker.MOD_ID, "basket")))));

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
