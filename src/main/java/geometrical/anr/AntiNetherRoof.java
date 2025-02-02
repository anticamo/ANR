package geometrical.anr;

import geometrical.anr.events.NetherRoofListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiNetherRoof extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new NetherRoofListener(), this);

        int pluginId = 24530;
        Metrics metrics = new Metrics(this, pluginId);
    }

    @Override
    public void onDisable() {

    }
}
