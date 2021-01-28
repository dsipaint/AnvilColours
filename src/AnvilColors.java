
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AnvilColors extends JavaPlugin
{
	public void onEnable()
	{
		saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(new AnvilEvent(this), this);
	}
  
	public void onDisable()
	{
	
	}
}