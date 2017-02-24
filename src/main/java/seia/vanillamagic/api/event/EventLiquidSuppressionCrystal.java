package seia.vanillamagic.api.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import seia.vanillamagic.api.item.ICustomItem;

/**
 * Base class for all actions that will happen using Liquid Suppression Crystal.<br>
 * In this case getPos will return the position of the liquid.
 */
public class EventLiquidSuppressionCrystal extends EventCustomItem.OnUseByPlayer
{
	private final ItemStack _crystalStack;
	
	public EventLiquidSuppressionCrystal(EntityPlayer player, World world, ItemStack crystalStack, BlockPos blockPos, 
			ICustomItem liquidSuppressionCrystal) 
	{
		super(liquidSuppressionCrystal, player, world, blockPos);
		this._crystalStack = crystalStack;
	}
	
	/**
	 * @return Returns ItemLiquidCrystal. (This should be Player's off-hand.)
	 */
	public ItemStack getCrystal()
	{
		return _crystalStack;
	}
	
	/**
	 * This Event is fired before Liquid Suppression Crystal creates the air block.
	 */
	public static class CreateAirBlock extends EventLiquidSuppressionCrystal
	{
		public CreateAirBlock(EntityPlayer player, World world, ItemStack crystalStack, BlockPos blockPos, 
				ICustomItem liquidSuppressionCrystal) 
		{
			super(player, world, crystalStack, blockPos, liquidSuppressionCrystal);
		}
	}
	
	/**
	 * This Event is fired to reset the duration of TileLiquidSuppression.
	 */
	public static class UseOnTileEntity extends EventLiquidSuppressionCrystal
	{
		private final TileEntity _tile;
		
		public UseOnTileEntity(EntityPlayer player, World world, ItemStack crystalStack, BlockPos blockPos,
				ICustomItem liquidSuppressionCrystal, TileEntity tile) 
		{
			super(player, world, crystalStack, blockPos, liquidSuppressionCrystal);
			this._tile = tile;
		}
		
		/**
		 * @return Returns TileEntity with reseted duration. This is mainly the TileLiquidSuppression.
		 */
		public TileEntity getTileEntity()
		{
			return _tile;
		}
	}
}