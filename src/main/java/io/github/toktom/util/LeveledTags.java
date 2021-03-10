package io.github.toktom.util;

import io.github.toktom.Leveled;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

public class LeveledTags
{	public static final class Blocks
	{
		public static final ITag.INamedTag<Block> OAK_BLOCKS = mod("oak_blocks");
		public static final ITag.INamedTag<Block> SPRUCE_BLOCKS = mod("spruce_blocks");
		public static final ITag.INamedTag<Block> BIRCH_BLOCKS = mod("birch_blocks");
		public static final ITag.INamedTag<Block> JUNGLE_BLOCKS = mod("jungle_blocks");
		public static final ITag.INamedTag<Block> DARK_OAK_BLOCKS = mod("dark_oak_blocks");
		public static final ITag.INamedTag<Block> ACACIA_BLOCKS = mod("acacia_blocks");
		public static final ITag.INamedTag<Block> CRIMSON_BLOCKS = mod("crimson_blocks");
		public static final ITag.INamedTag<Block> WARPED_BLOCKS = mod("warped_blocks");
		public static final ITag.INamedTag<Block> ANDESITE_BLOCKS = mod("andesite_blocks");
		public static final ITag.INamedTag<Block> DIORITE_BLOCKS = mod("diorite_blocks");
		public static final ITag.INamedTag<Block> GRANITE_BLOCKS = mod("granite_blocks");
		public static final ITag.INamedTag<Block> COBBLESTONE_BLOCKS = mod("cobblestone_blocks");
		public static final ITag.INamedTag<Block> STONE_BLOCKS = mod("stone_blocks");
		public static final ITag.INamedTag<Block> STONE_BRICKS_BLOCKS = mod("stone_bricks_blocks");
		public static final ITag.INamedTag<Block> NETHER_BRICKS_BLOCKS = mod("nether_bricks_blocks");
		public static final ITag.INamedTag<Block> PRISMARINE_BLOCKS = mod("prismarine_blocks");
		public static final ITag.INamedTag<Block> SANDSTONE_BLOCKS = mod("sandstone_blocks");
		public static final ITag.INamedTag<Block> RED_SANDSTONE_BLOCKS = mod("red_sandstone_blocks");
		public static final ITag.INamedTag<Block> RED_NETHER_BRICKS_BLOCKS = mod("red_nether_bricks_blocks");
		public static final ITag.INamedTag<Block> PURPUR_BLOCKS = mod("purpur_blocks");

		private static ITag.INamedTag<Block> mod(String path)
		{
			
			return BlockTags.createOptional(new ResourceLocation(Leveled.MOD_ID, path));
			
		}
	}
}
