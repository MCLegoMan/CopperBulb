package com.mclegoman.copperbulb.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class CopperBulbBlock extends Block {
	public static final BooleanProperty LIT;
	public static final BooleanProperty PREVIOUSLY_POWERED;
	public CopperBulbBlock(AbstractBlock.Settings settings) {
		super(settings);
		this.setDefaultState(this.getDefaultState().with(LIT, false));
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return this.getDefaultState().with(LIT, ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos())).with(PREVIOUSLY_POWERED, false);
	}

	@Override
	public boolean hasComparatorOutput(BlockState state) {
		return state.get(LIT);
	}

	@Override
	public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
		return world.getLuminance(pos);
	}

	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (world.isReceivingRedstonePower(pos)) {
			if (!state.get(PREVIOUSLY_POWERED)) {
				world.setBlockState(pos, state.with(LIT, !state.get(LIT)).with(PREVIOUSLY_POWERED, true), 2);
			}
		} else {
			world.setBlockState(pos, state.with(PREVIOUSLY_POWERED, false), 3);
		}
		world.scheduleBlockTick(pos, this, 2);
	}

	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
		world.updateNeighbors(pos, this);
		world.scheduleBlockTick(pos, this, 2);
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(LIT);
		builder.add(PREVIOUSLY_POWERED);
	}

	static {
		LIT = Properties.LIT;
		PREVIOUSLY_POWERED = BooleanProperty.of("previously_powered");
	}
}
