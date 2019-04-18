package whyjoin.listeners;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import us.myles.ViaVersion.ViaVersionPlugin;
import whyjoin.utils.MessageControl;

public class Handlers implements Listener {

    private ViaVersionPlugin viaVersionPlugin;
    private Economy economy;
    private FileConfiguration config;

    public Handlers(ViaVersionPlugin viaVersionPlugin, Economy economy, FileConfiguration config) {
        this.viaVersionPlugin = viaVersionPlugin;
        this.economy = economy;
        this.config = config;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        int version = viaVersionPlugin.getApi().getPlayerVersion(player);
        int minVersion = config.getInt("startVersion");

        if (version >= minVersion) {
            double addMoney= config.getDouble("addMoney");
            MessageControl.sendMessage(player, config.getString("Message").replace("%money%", String.valueOf(addMoney)));
            economy.depositPlayer(player, addMoney);
        }
    }

}
