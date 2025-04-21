package net.mqzon.wicker.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.mqzon.wicker.Wicker;
import net.mqzon.wicker.block.ModBlocks;

public class ModItemGroups {

    public static final ItemGroup WICKER_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(net.mqzon.wicker.Wicker.MOD_ID, "wicker_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.WICKER))
                    .displayName(Text.translatable("itemgroup.wicker.wicker_group"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.WICKER);
                        entries.add(ModBlocks.BASKET);
                    }).build());

    public static void registerItemGroups() {
        net.mqzon.wicker.Wicker.LOGGER.info("Registering Item Groups for " + Wicker.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(ModBlocks.BASKET);
        });
    }
}
