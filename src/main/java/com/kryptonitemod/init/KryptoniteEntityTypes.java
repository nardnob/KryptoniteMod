package com.kryptonitemod.init;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.entities.passive.GorillaEntity;
import com.kryptonitemod.entities.projectiles.KryptoniteBulletEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class KryptoniteEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, KryptoniteMod.MOD_ID);

    // Entities
    public static final RegistryObject<EntityType<GorillaEntity>> GORILLA =
            ENTITY_TYPES.register(GorillaEntity.NAME, () -> EntityType.Builder
                    .create(GorillaEntity::new, EntityClassification.CREATURE)
                    .size(2.0f, 1.0f) // Hitbox Size
                    .build(new ResourceLocation(KryptoniteMod.MOD_ID, GorillaEntity.NAME).toString()));

    // Projectiles
    public static final RegistryObject<EntityType<KryptoniteBulletEntity>> KRYPTONITE_BULLET =
            ENTITY_TYPES.register(KryptoniteBulletEntity.NAME, () ->
                    EntityType.Builder
                        .create(KryptoniteBulletEntity::new, EntityClassification.MISC)
                        .build(new ResourceLocation(KryptoniteMod.MOD_ID, KryptoniteBulletEntity.NAME).toString()));
}
