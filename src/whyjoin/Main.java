package whyjoin;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import us.myles.ViaVersion.ViaVersionPlugin;
import whyjoin.listeners.Handlers;
import whyjoin.utils.MessageControl;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        loadListeners();
        MessageControl.sendMessage(Bukkit.getConsoleSender(), "&8[&aWhyJoin&8] &fПлагин загружен. &7(vk.com/zhcoder)" );
    }

    private void loadListeners() {
        Bukkit.getPluginManager().registerEvents(new Handlers(getViaVersion(), getEconomy(), getConfig()), this);
    }

    private Economy getEconomy() {
        Economy econ = null;
        RegisteredServiceProvider economyProvider = getServer().getServicesManager().getRegistration( Economy.class );
        if (economyProvider != null) {
            econ = (Economy) economyProvider.getProvider();
        }
        return econ;
    }

    private ViaVersionPlugin getViaVersion() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("ViaVersion");
        if (plugin instanceof ViaVersionPlugin) return (ViaVersionPlugin) plugin;
        return null;
    }


}
