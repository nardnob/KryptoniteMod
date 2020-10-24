package com.kryptonitemod.entities.projectiles;

import com.kryptonitemod.init.KryptoniteItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

public class KryptoniteBulletEntity extends ProjectileItemEntity implements IRendersAsItem {
    public static final String NAME = "kryptonite_bullet_entity";

   public KryptoniteBulletEntity(EntityType<? extends KryptoniteBulletEntity> entityType, World worldIn) {
      super(entityType, worldIn);
   }

    @Override
    protected Item getDefaultItem() {
        return KryptoniteItems.KRYPTONITE_BULLET.get();
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(this.getDefaultItem());
    }

    @Override
    @Nonnull
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
