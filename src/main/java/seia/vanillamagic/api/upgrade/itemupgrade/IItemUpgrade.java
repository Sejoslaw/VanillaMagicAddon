package seia.vanillamagic.api.upgrade.itemupgrade;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;

/**
 * This is the base of the Upgrade System. <br>
 * After registration in ItemUpgradeRegistry each upgrade is registered in {@link MinecraftForge#EVENT_BUS}. <br>
 * This is made to simplify the Events implementing. You can write Your Events in class that implements IItemUpgrade.
 */
public interface IItemUpgrade
{
	public static final String NBT_ITEM_UPGRADE_TAG = "NBT_ITEM_UPGRADE_TAG";
	public static final String NBT_ITEM_CONTAINS_UPGRADE = "NBT_ITEM_CONTAINS_UPGRADE";
	
	/**
	 * @return Returns the "base" stack with all the NBT data written.
	 */
	default ItemStack getResult(ItemStack base)
	{
		ItemStack result = base.copy();
		result.setStackDisplayName(result.getDisplayName() + "+ Upgrade: " + getUpgradeName());
		NBTTagCompound stackTag = result.getTagCompound();
		stackTag.setString(NBT_ITEM_UPGRADE_TAG, getUniqueNBTTag());
		stackTag.setBoolean(NBT_ITEM_CONTAINS_UPGRADE, true);
		return result;
	}
	
	/**
	 * @return Returns ingredient which must be in Cauldron with the required Item.
	 */
	ItemStack getIngredient();
	
	/**
	 * This tag MUST BE UNIQUE !!! <br>
	 * It is used to recognize the {@link IItemUpgrade}. 
	 * 
	 * @return Returns Upgrade unique tag.
	 */
	String getUniqueNBTTag();
	
	/**
	 * @return Returns readable name for this upgrade. <br>
	 * It will be used as a part of new stack name. <br>
	 * For instance: "My Upgrade" or "Awesome Hyper World-Destroying Upgrade".
	 */
	String getUpgradeName();
	
	/**
	 * @return Returns if the given stack is an upgrade.
	 */
	default public boolean containsTag(ItemStack stack)
	{
		if (stack == null) return false;
		if (stack.isEmpty()) return false;
		NBTTagCompound tag = stack.getTagCompound();
		if (tag == null) return false;
		return tag.getString(NBT_ITEM_UPGRADE_TAG).equals(getUniqueNBTTag());
	}
}