package com.kryptonitemod.init;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.container.KryptoniteRefineryContainer;
import com.kryptonitemod.container.KryptoniteFurnaceContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Holds a list of all our {@link ContainerType}s.
 * Suppliers that create ContainerTypes are added to the DeferredRegister.
 * The DeferredRegister is then added to our mod event bus in our constructor.
 * When the ContainerType Registry Event is fired by Forge and it is time for the mod to
 * register its ContainerTypes, our ContainerTypes are created and registered by the DeferredRegister.
 * The ContainerType Registry Event will always be called after the Block and Item registries are filled.
 * Note: This supports registry overrides.
 */
public final class KryptoniteContainerTypes {
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, KryptoniteMod.MOD_ID);

    public static final RegistryObject<ContainerType<KryptoniteRefineryContainer>> KRYPTONITE_REFINERY =
            CONTAINER_TYPES.register(KryptoniteRefineryContainer.NAME, () -> IForgeContainerType.create(KryptoniteRefineryContainer::new));

    public static final RegistryObject<ContainerType<KryptoniteFurnaceContainer>> KRYPTONITE_FURNACE =
            CONTAINER_TYPES.register(KryptoniteFurnaceContainer.NAME, () -> IForgeContainerType.create(KryptoniteFurnaceContainer::new));
}