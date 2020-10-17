package com.kryptonitemod.init;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.entities.GorillaEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class KryptoniteEntityTypes {
    public static final DeferredRegister<EntityType<?>> entityTypes = DeferredRegister.create(ForgeRegistries.ENTITIES, KryptoniteMod.modId);

    // Entity Types
    public static final RegistryObject<EntityType<GorillaEntity>> gorillaEntity =
            entityTypes.register(GorillaEntity.name, () -> EntityType.Builder
                    .create(GorillaEntity::new, EntityClassification.CREATURE)
                    .size(2.0f, 1.0f) // Hitbox Size
                    .build(new ResourceLocation(KryptoniteMod.modId, GorillaEntity.name).toString()));
}
