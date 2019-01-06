package ladysnake.illuminations.common.init;

import ladysnake.illuminations.client.renders.entities.RenderFirefly;
import ladysnake.illuminations.client.renders.entities.RenderLightningBug;
import ladysnake.illuminations.client.renders.entities.RenderWillOWisp;
import ladysnake.illuminations.common.entities.EntityFirefly;
import ladysnake.illuminations.common.entities.EntityLightningBug;
import ladysnake.illuminations.common.entities.EntityWillOWisp;
import net.fabricmc.fabric.client.render.EntityRendererRegistry;
import net.fabricmc.fabric.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

import java.util.Arrays;
import java.util.List;

import static net.minecraft.world.biome.Biome.Category.*;

public class IlluminationsEntities {

    public static EntityType<EntityFirefly> FIREFLY;
    public static EntityType<EntityLightningBug> LIGHTNING_BUG;
    public static EntityType<EntityWillOWisp> WILL_O_WISP;

    public static void init() {
        // Firefly + firefly spawns
        FIREFLY = Registry.register(Registry.ENTITY_TYPE, "illuminations:firefly", FabricEntityTypeBuilder.create(EntityFirefly.class, EntityFirefly::new).trackable(64, 1, true).build());
        List<Biome.Category> fireflyAcceptableCategories = Arrays.asList(PLAINS, SWAMP, FOREST, JUNGLE, SAVANNA, RIVER);
        for (Biome biome : Registry.BIOME) {
            if (fireflyAcceptableCategories.contains(biome.getCategory())) {
                biome.getEntitySpawnList(EntityCategory.CREATURE).add(new Biome.SpawnEntry(FIREFLY, 40, 6, 24));
            }
        }
        // LB + LB spawns
        LIGHTNING_BUG = Registry.register(Registry.ENTITY_TYPE, "illuminations:lightning_bug", FabricEntityTypeBuilder.create(EntityLightningBug.class, EntityLightningBug::new).trackable(64, 1, true).build());
        for (Biome biome : Registry.BIOME) {
            if (fireflyAcceptableCategories.contains(biome.getCategory())) {
                biome.getEntitySpawnList(EntityCategory.CREATURE).add(new Biome.SpawnEntry(LIGHTNING_BUG, 40, 6, 24));
            }
        }
        // WoW + WoW spawns
        WILL_O_WISP = Registry.register(Registry.ENTITY_TYPE, "illuminations:will_o_wisp", FabricEntityTypeBuilder.create(EntityWillOWisp.class, EntityWillOWisp::new).trackable(64, 1, true).build());
        for (Biome biome : Registry.BIOME) {
            if (biome.getCategory() == Biome.Category.SWAMP) {
                biome.getEntitySpawnList(EntityCategory.CREATURE).add(new Biome.SpawnEntry(WILL_O_WISP, 20, 1, 1));
            }
        }
    }

    public static void registerRenders() {
        EntityRendererRegistry.INSTANCE.register(EntityFirefly.class, (manager, context) -> new RenderFirefly(manager));
        EntityRendererRegistry.INSTANCE.register(EntityLightningBug.class, (manager, context) -> new RenderLightningBug(manager));
        EntityRendererRegistry.INSTANCE.register(EntityWillOWisp.class, (manager, context) -> new RenderWillOWisp<>(manager));
    }

}
