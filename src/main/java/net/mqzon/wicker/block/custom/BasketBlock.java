package net.mqzon.wicker.block.custom;

import com.mojang.serialization.MapCodec;
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

public class BasketBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final EnumProperty<Direction> FACING = Properties.HORIZONTAL_FACING;
    public static final Identifier CONTENTS_DYNAMIC_DROP_ID = Identifier.ofVanilla("contents");

    public BasketBlock(Settings settings) {
        super(settings);
    }

    public static final MapCodec<BasketBlock> CODEC = BasketBlock.createCodec(BasketBlock::new);

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BasketBlockEntity(pos, state);
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
//                player.incrementStat(Stats.OPEN_BASKET); //TODO: implement OPEN_BASKET stat?
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
                ItemStack itemStack = getItemStack(null); // TODO: change to getColor once implemented
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
            return switch (dyeColor) { //TODO: add colors
                case WHITE -> ModBlocks.BASKET;//Blocks.WHITE_SHULKER_BOX;
                case ORANGE -> ModBlocks.BASKET;//Blocks.ORANGE_SHULKER_BOX;
                case MAGENTA -> ModBlocks.BASKET;//Blocks.MAGENTA_SHULKER_BOX;
                case LIGHT_BLUE -> ModBlocks.BASKET;//Blocks.LIGHT_BLUE_SHULKER_BOX;
                case YELLOW -> ModBlocks.BASKET;//Blocks.YELLOW_SHULKER_BOX;
                case LIME -> ModBlocks.BASKET;//Blocks.LIME_SHULKER_BOX;
                case PINK -> ModBlocks.BASKET;//Blocks.PINK_SHULKER_BOX;
                case GRAY -> ModBlocks.BASKET;//Blocks.GRAY_SHULKER_BOX;
                case LIGHT_GRAY -> ModBlocks.BASKET;//Blocks.LIGHT_GRAY_SHULKER_BOX;
                case CYAN -> ModBlocks.BASKET;//Blocks.CYAN_SHULKER_BOX;
                case BLUE -> ModBlocks.BASKET;//Blocks.BLUE_SHULKER_BOX;
                case BROWN -> ModBlocks.BASKET;//Blocks.BROWN_SHULKER_BOX;
                case GREEN -> ModBlocks.BASKET;//Blocks.GREEN_SHULKER_BOX;
                case RED -> ModBlocks.BASKET;//Blocks.RED_SHULKER_BOX;
                case BLACK -> ModBlocks.BASKET;//Blocks.BLACK_SHULKER_BOX;
                case PURPLE -> ModBlocks.BASKET;//Blocks.PURPLE_SHULKER_BOX;
            };
        }
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
