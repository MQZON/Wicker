package net.mqzon.wicker.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.mqzon.wicker.Wicker;

public class ModItems {
    public static final Item WICKER = registerItem("wicker", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Wicker.MOD_ID, "wicker")))));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Wicker.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Wicker.LOGGER.info("Registering Mod Items for " + Wicker.MOD_ID);
    }
}
