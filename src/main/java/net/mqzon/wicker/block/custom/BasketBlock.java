package net.mqzon.wicker.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootWorldContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.mqzon.wicker.block.ModBlocks;
import net.mqzon.wicker.block.entity.custom.BasketBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class BasketBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final EnumProperty<Direction> FACING = Properties.HORIZONTAL_FACING;
    public static final Identifier CONTENTS_DYNAMIC_DROP_ID = Identifier.ofVanilla("contents");
    private final DyeColor color;


    public BasketBlock(@Nullable DyeColor color, Settings settings) {
        super(settings);
        this.color = color;
    }

    public static final MapCodec<BasketBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(DyeColor.CODEC.optionalFieldOf("color").forGetter(block -> Optional.ofNullable(block.color)), createSettingsCodec())
                    .apply(instance, (color, settings) -> new BasketBlock((DyeColor)color.orElse(null), settings))
    );

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BasketBlockEntity(this.color, pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof BasketBlockEntity) {
                world.updateComparators(pos, state.getBlock());
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world instanceof ServerWorld serverWorld) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof BasketBlockEntity basketBlockEntity) {
                player.openHandledScreen(basketBlockEntity);
                PiglinBrain.onGuardedBlockInteracted(serverWorld, player, true);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof BasketBlockEntity basketBlockEntity) {
            if (!world.isClient && player.isCreative() && !basketBlockEntity.isEmpty()) {
                ItemStack itemStack = getItemStack(this.getColor());
                itemStack.applyComponentsFrom(blockEntity.createComponentMap());
                ItemEntity itemEntity = new ItemEntity(world, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, itemStack);
                itemEntity.setToDefaultPickupDelay();
                world.spawnEntity(itemEntity);
            } else {
                basketBlockEntity.generateLoot(player);
            }
        }
        return super.onBreak(world, pos, state, player);
    }

    @Override
    protected List<ItemStack> getDroppedStacks(BlockState state, LootWorldContext.Builder builder) {
        BlockEntity blockEntity = builder.getOptional(LootContextParameters.BLOCK_ENTITY);
        if (blockEntity instanceof BasketBlockEntity basketBlockEntity) {
            builder = builder.addDynamicDrop(CONTENTS_DYNAMIC_DROP_ID, lootConsumer -> {
                for (int i = 0; i < basketBlockEntity.size(); i++) {
                    lootConsumer.accept(basketBlockEntity.getStack(i));
                }
            });
        }
        return super.getDroppedStacks(state, builder);
    }

    public static ItemStack getItemStack(@Nullable DyeColor color) {
        return new ItemStack(get(color));
    }

    public static Block get(@Nullable DyeColor dyeColor) {
        if (dyeColor == null) {
            return ModBlocks.BASKET;
        } else {
            return switch (dyeColor) {
                case WHITE -> ModBlocks.BASKET_WHITE;
                case ORANGE -> ModBlocks.BASKET_ORANGE;
                case MAGENTA -> ModBlocks.BASKET_MAGENTA;
                case LIGHT_BLUE -> ModBlocks.BASKET_LIGHT_BLUE;
                case YELLOW -> ModBlocks.BASKET_YELLOW;
                case LIME -> ModBlocks.BASKET_LIME;
                case PINK -> ModBlocks.BASKET_PINK;
                case GRAY -> ModBlocks.BASKET_GRAY;
                case LIGHT_GRAY -> ModBlocks.BASKET_LIGHT_GRAY;
                case CYAN -> ModBlocks.BASKET_CYAN;
                case BLUE -> ModBlocks.BASKET_BLUE;
                case BROWN -> ModBlocks.BASKET_BROWN;
                case GREEN -> ModBlocks.BASKET_GREEN;
                case RED -> ModBlocks.BASKET_RED;
                case BLACK -> ModBlocks.BASKET_BLACK;
                case PURPLE -> ModBlocks.BASKET_PURPLE;
            };
        }
    }

    @Nullable
    public DyeColor getColor() {
        return this.color;
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
