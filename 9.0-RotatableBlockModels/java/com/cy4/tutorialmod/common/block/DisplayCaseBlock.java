package com.cy4.tutorialmod.common.block;

import java.util.stream.Stream;

import com.cy4.tutorialmod.common.te.DisplayCaseTileEntity;
import com.cy4.tutorialmod.core.init.TileEntityTypesInit;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class DisplayCaseBlock extends Block {

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

	private static final VoxelShape SHAPE_N = Stream
			.of(Block.makeCuboidShape(3, 0, 3, 13, 1, 13), Block.makeCuboidShape(6, 1, 6, 10, 9, 10),
					Block.makeCuboidShape(3, 9, 3, 13, 10, 13), Block.makeCuboidShape(3, 10, 3, 13, 11, 4))
			.reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	private static final VoxelShape SHAPE_E = Stream
			.of(Block.makeCuboidShape(3, 0, 3, 13, 1, 13), Block.makeCuboidShape(6, 1, 6, 10, 9, 10),
					Block.makeCuboidShape(3, 9, 3, 13, 10, 13), Block.makeCuboidShape(12, 10, 3, 13, 11, 13))
			.reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	private static final VoxelShape SHAPE_W = Stream
			.of(Block.makeCuboidShape(3, 0, 3, 13, 1, 13), Block.makeCuboidShape(6, 1, 6, 10, 9, 10),
					Block.makeCuboidShape(3, 9, 3, 13, 10, 13), Block.makeCuboidShape(3, 10, 3, 4, 11, 13))
			.reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	private static final VoxelShape SHAPE_S = Stream
			.of(Block.makeCuboidShape(3, 0, 3, 13, 1, 13), Block.makeCuboidShape(6, 1, 6, 10, 9, 10),
					Block.makeCuboidShape(3, 9, 3, 13, 10, 13), Block.makeCuboidShape(3, 10, 12, 13, 11, 13))
			.reduce((v1, v2) -> {
				return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
			}).get();

	public DisplayCaseBlock() {
		super(AbstractBlock.Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(15f)
				.sound(SoundType.METAL));

		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch (state.get(FACING)) {
		case EAST:
			return SHAPE_E;
		case SOUTH:
			return SHAPE_S;
		case WEST:
			return SHAPE_W;
		default:
			return SHAPE_N;
		}
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}

	@Override
	public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
		return state.with(FACING, direction.rotate(state.get(FACING)));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(FACING);
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypesInit.DISPLAY_CASE_TILE_ENTITY_TYPE.get().create();
	}

	@SuppressWarnings("deprecation")
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (!worldIn.isRemote()) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof DisplayCaseTileEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (DisplayCaseTileEntity) te, pos);
			}
		}
		return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
	}
}
