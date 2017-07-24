package seia.vanillamagic.api.upgrade.itemupgrade;

import java.lang.reflect.Method;

import javax.annotation.Nullable;

import org.apache.logging.log4j.Level;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import seia.vanillamagic.api.VanillaMagicAPI;

/**
 * Class which is used to manipulate on VM Item Upgrades.
 */
public class ItemUpgradeAPI 
{
	/**
	 * ItemUpgradeRegistry Class
	 */
	private static final String REGISTRY_CLASS = "seia.vanillamagic.item.itemupgrade.ItemUpgradeRegistry";
	
	private ItemUpgradeAPI()
	{
	}
	
	/**
	 * Main method for registering the upgrades.
	 * 
	 * @param mappingName -> For instance: "_pickaxe" or "_sword" or "_axe" or "_myNewMapping" itd.
	 * @param clazz -> MyUpgradeClass.class
	 * @param localizedName -> If the mapping don't exists this localized name will be use as a name for new mapping.
	 * For instance: for "_pickaxe" localized name is "Pickaxe". It must be human readable name.
	 */
	public static void addUpgradeMapping(String mappingName, Class<? extends IItemUpgrade> clazz, String localizedName)
	{
		try
		{
			Class<?> registryClass = Class.forName(REGISTRY_CLASS);
			Method method = registryClass.getMethod("addUpgradeMapping", String.class, Class.class, String.class);
			method.invoke(null, mappingName, clazz, localizedName);
		}
		catch(Exception e)
		{
			VanillaMagicAPI.LOGGER.log(Level.ERROR, "Couldn't register upgrade class: " + clazz.getSimpleName());
			e.printStackTrace();
		}
	}
	
	/**
	 * Add a single value to the mapping. If mapping doesn't exists it will add a new mapping. <br>
	 * For instance: addItemToMapping("_pickaxe", new MyItemPickaxe()); <br>
	 * or <br>
	 * addItemToMapping("_myMapping", new MyItem()); <br>
	 * If You want to add Block use {@link Item#getItemFromBlock(Block)}
	 */
	public static void addItemToMapping(String mappingName, Item item, String localizedName)
	{
		try
		{
			Class<?> registryClass = Class.forName(REGISTRY_CLASS);
			Method method = registryClass.getMethod("addItemToMapping", String.class, Item.class, String.class);
			method.invoke(null, mappingName, item, localizedName);
		}
		catch(Exception e)
		{
			VanillaMagicAPI.LOGGER.log(Level.ERROR, "Couldn't register item: " + item.toString() + ", in mapping: " + mappingName);
			e.printStackTrace();
		}
	}
	
	/**
	 * Add a new mapping. <br>
	 * In VanillaMagic mapping will start with "_" so "pickaxe" will be -> "_pickaxe". <br>
	 * It was made this way to prevent "pickaxe" and "axe" being counted as one mapping.
	 */
	public static void addItemMapping(String mappingName, String localizedName)
	{
		try
		{
			Class<?> registryClass = Class.forName(REGISTRY_CLASS);
			Method method = registryClass.getMethod("addItemMapping", String.class, String.class);
			method.invoke(null, mappingName, localizedName);
		}
		catch(Exception e)
		{
			VanillaMagicAPI.LOGGER.log(Level.ERROR, "Couldn't register item mapping: " + mappingName);
			e.printStackTrace();
		}
	}
	
	/**
	 * @param base -> Basic item. For instance: pickaxe
	 * @param ingredient -> Item needed to upgrade.
	 * 
	 * @return Return ItemStack with upgrade and written NBT data.
	 */
	@Nullable
	public static ItemStack getResult(ItemStack base, ItemStack ingredient)
	{
		try
		{
			Class<?> registryClass = Class.forName(REGISTRY_CLASS);
			Method method = registryClass.getMethod("getResult", ItemStack.class, ItemStack.class);
			return (ItemStack) method.invoke(null, base, ingredient);
		}
		catch(Exception e)
		{
			VanillaMagicAPI.LOGGER.log(Level.ERROR, "Couldn't get result.");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @return Returns saved Item Upgrade unique name from given ItemStack.
	 */
	public static String getMappingNameFromItemStack(ItemStack stack)
	{
		try
		{
			Class<?> registryClass = Class.forName(REGISTRY_CLASS);
			Method method = registryClass.getMethod("getMappingNameFromItemStack", ItemStack.class);
			return (String) method.invoke(null, stack);
		}
		catch(Exception e)
		{
			VanillaMagicAPI.LOGGER.log(Level.ERROR, "Couldn't get mapping from stack: " + stack.toString());
			e.printStackTrace();
			return "";
		}
	}
}