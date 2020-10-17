package com.kryptonitemod.entities;

import javax.annotation.Nullable;

import com.kryptonitemod.init.KryptoniteEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GorillaEntity extends AnimalEntity {
    public static final Ingredient temptationItems = Ingredient.fromItems(Items.BEETROOT);

    private EatGrassGoal _eatGrassGoal;
    private static final double _movementSpeed = 0.5;

    public GorillaEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
        super(type, worldIn);
    }

    //func_233666_p_() - registerAttributes()
    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_() //registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, _movementSpeed);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this._eatGrassGoal = new EatGrassGoal(this);
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, _movementSpeed));
        this.goalSelector.addGoal(2, new BreedGoal(this, _movementSpeed));
        this.goalSelector.addGoal(3, new TemptGoal(this, _movementSpeed, temptationItems, false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, _movementSpeed));
        this.goalSelector.addGoal(5, this._eatGrassGoal);
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, _movementSpeed));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player) { return 1 + this.world.rand.nextInt(4); }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() { return SoundEvents.ENTITY_POLAR_BEAR_AMBIENT; }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() { return SoundEvents.ENTITY_POLAR_BEAR_DEATH; }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) { return SoundEvents.ENTITY_POLAR_BEAR_HURT; }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_POLAR_BEAR_STEP, 0.15F, 1.0F);
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        return KryptoniteEntityTypes.gorillaEntity.get().create(this.world);
    }

    @Override
    public void livingTick() {
        if (this.world.isRemote) {
        }
        super.livingTick();
    }
}
