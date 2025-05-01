package net.mqzon.wicker.utils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.mqzon.wicker.Wicker;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> BASKETS = createTag("baskets");
        public static final TagKey<Block> LINED_BASKETS = createTag("lined_baskets");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Wicker.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> BASKETS = createTag("baskets");
        public static final TagKey<Item> LINED_BASKETS = createTag("lined_baskets");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Wicker.MOD_ID, name));
        }
    }
}