package com.mclegoman.copperbulb.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class CopperBulbBlock extends Block {
	public static final BooleanProperty POWERED;
	public static final BooleanProperty LIT;

	public CopperBulbBlock(AbstractBlock.Settings settings) {
		super(settings);
		this.setDefaultState(this.getDefaultState().with(LIT, false).with(POWERED, false));
	}

	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
		if (oldState.getBlock() != state.getBlock()) {
			world.scheduleBlockTick(pos, this, 1);
		}
	}

	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
		boolean isReceivingRedstonePower = world.isReceivingRedstonePower(pos);
		if (isReceivingRedstonePower != state.get(POWERED)) {
			world.scheduleBlockTick(pos, this, 1);
		}

	}

	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		boolean isReceivingRedstonePower = world.isReceivingRedstonePower(pos);
		if (isReceivingRedstonePower != state.get(POWERED)) {
			BlockState blockState = state;
			if (!(Boolean)state.get(POWERED)) {
				blockState = state.cycle(LIT);
				world.playSound(null, pos, blockState.get(LIT) ? SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON : SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF, SoundCategory.BLOCKS);
			}

			world.setBlockState(pos, blockState.with(POWERED, isReceivingRedstonePower), 3);
		}

	}

	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(LIT, POWERED);
	}

	public boolean hasComparatorOutput(BlockState state) {
		return true;
	}

	public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
		return world.getBlockState(pos).get(LIT) ? world.getLuminance(pos) : 0;
	}

	static {
		POWERED = Properties.POWERED;
		LIT = Properties.LIT;
	}
}