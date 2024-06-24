package codyhuh.worldofwonder.core.other;

import codyhuh.worldofwonder.WorldOfWonder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class WonderItemTags {
    public static final TagKey<Item> FERTILIZER = bind("fertilizer");

    private static TagKey<Item> bind(String name) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(WorldOfWonder.MOD_ID, name));
    }
}