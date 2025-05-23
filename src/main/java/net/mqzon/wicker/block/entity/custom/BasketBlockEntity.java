package net.mqzon.wicker.block.entity.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.event.GameEvent;
import net.mqzon.wicker.block.custom.BasketBlock;
import net.mqzon.wicker.block.entity.ImplementedInventory;
import net.mqzon.wicker.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;

public class BasketBlockEntity extends LootableContainerBlockEntity implements ImplementedInventory {
    private static final int BASKET_SIZE = 9;
    private DefaultedList<ItemStack> inventory;
    private int viewerCount;
    private static final int[] AVAILABLE_SLOTS = IntStream.range(0,BASKET_SIZE).toArray();
    @Nullable
    private final DyeColor cachedColor;

    public BasketBlockEntity(@Nullable DyeColor color, BlockPos pos, BlockState state) {
        super(ModBlockEntities.BASKET_BE, pos, state);
        this.inventory = DefaultedList.ofSize(BASKET_SIZE, ItemStack.EMPTY);
        this.cachedColor = color;
    }

    public BasketBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BASKET_BE, pos, state);
        this.inventory = DefaultedList.ofSize(BASKET_SIZE, ItemStack.EMPTY);
        Block block = state.getBlock();
        DyeColor color;
        if (block instanceof BasketBlock basketBlock) {
            color = basketBlock.getColor();
        } else {
            color = null;
        }

        this.cachedColor = color;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    public void onOpen(PlayerEntity player) {
        if (!this.removed && !player.isSpectator()) {
            if (this.viewerCount < 0) {
                this.viewerCount = 0;
            }

            ++this.viewerCount;
            this.world.addSyncedBlockEvent(this.pos, this.getCachedState().getBlock(), 1, this.viewerCount);
            if (this.viewerCount == 1) {
                this.world.emitGameEvent(player, GameEvent.CONTAINER_OPEN, this.pos);
                this.world.playSound((PlayerEntity)null, this.pos, SoundEvents.BLOCK_AZALEA_STEP, SoundCategory.BLOCKS, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
            }
        }
    }

    public void onClose(PlayerEntity player) {
        if (!this.removed && !player.isSpectator()) {
            --this.viewerCount;
            this.world.addSyncedBlockEvent(this.pos, this.getCachedState().getBlock(), 1, this.viewerCount);
            if (this.viewerCount <= 0) {
                this.world.emitGameEvent(player, GameEvent.CONTAINER_CLOSE, this.pos);
                this.world.playSound((PlayerEntity)null, this.pos, SoundEvents.BLOCK_GRASS_STEP, SoundCategory.BLOCKS, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
            }
        }
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.wicker.basket");
    }


    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.readNbt(nbt, registries);
        this.readInventoryNbt(nbt, registries);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.writeNbt(nbt, registries);
        if (!this.writeLootTable(nbt)) {
            Inventories.writeNbt(nbt, this.inventory, false, registries);
        }
    }

    public void readInventoryNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        if (!this.readLootTable(nbt) && nbt.contains("Items", 9)) {
            Inventories.readNbt(nbt, this.inventory, registries);
        }
    }

    protected DefaultedList<ItemStack> getHeldStacks() {
        return this.inventory;
    }

    protected void setHeldStacks(DefaultedList<ItemStack> inventory) {
        this.inventory = inventory;
    }

    public int[] getAvailableSlots(Direction side) {
        return AVAILABLE_SLOTS;
    }

    @Nullable
    public DyeColor getColor() {
        return this.cachedColor;
    }

    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
       return new GenericContainerScreenHandler(ScreenHandlerType.GENERIC_9X1, syncId, playerInventory, this, 1);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registries) {
        return createNbt(registries);
    }
}
