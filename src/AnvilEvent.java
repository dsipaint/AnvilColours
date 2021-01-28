
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class AnvilEvent implements Listener
{
	private Plugin main;
	
	public AnvilEvent(Plugin main)
	{
		this.main = main;
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e)
	{
		if(e.getInventory().getType() == InventoryType.ANVIL && e.getWhoClicked().hasPermission("anvil.allow"))
		{
			if(e.getRawSlot() == 2)
			{
				if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || !e.getCurrentItem().getItemMeta().hasDisplayName())
					return;
				
				ItemStack i = e.getCurrentItem();
				ItemMeta m = i.getItemMeta();
				
				if(m.getDisplayName().length() > main.getConfig().getInt("Anvil.limit"))
					m.setDisplayName(m.getDisplayName().substring(0, main.getConfig().getInt("Anvil.limit")).replace("&", "§"));
				else
					m.setDisplayName(m.getDisplayName().replace("&", "§"));
				
				i.setItemMeta(m);
				e.setCurrentItem(i);
			}
			else if(e.getRawSlot() == 0)
			{
				if (e.getCursor() == null || e.getCursor().getType() == Material.AIR || !e.getCursor().getItemMeta().hasDisplayName())
					return;
				
				ItemStack i = e.getCursor();
				ItemMeta m = i.getItemMeta();
				
				if(m.getDisplayName().length() > main.getConfig().getInt("Anvil.limit"))
					m.setDisplayName(m.getDisplayName().substring(0, main.getConfig().getInt("Anvil.limit")).replace("&", "§"));
				else
					m.setDisplayName(m.getDisplayName().replaceAll("§", "&"));
				
				i.setItemMeta(m);
				i.setAmount(i.getAmount());
			} 
		}
	}
	
	
	@EventHandler
	public void onDrag(InventoryDragEvent e)
	{
		if(e.getInventory().getType() == InventoryType.ANVIL && e.getWhoClicked().hasPermission("anvil.allow") &&
				(e.getNewItems().containsKey(Integer.valueOf(0)) || e.getNewItems().containsKey(Integer.valueOf(1)) || e.getNewItems().containsKey(Integer.valueOf(2))))
			e.setCancelled(true); 
	}
}
