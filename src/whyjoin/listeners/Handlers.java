package whyjoin.listeners;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import us.myles.ViaVersion.ViaVersionPlugin;
import whyjoin.utils.MessageControl;

import java.util.List;

public class Handlers implements Listener {

    private ViaVersionPlugin viaVersionPlugin;
    private Economy economy;
    private FileConfiguration config;

    private List<Integer> validVersion;

    public Handlers(ViaVersionPlugin viaVersionPlugin, Economy economy, FileConfiguration config) {
        this.viaVersionPlugin = viaVersionPlugin;
        this.economy = economy;
        this.config = config;
        this.validVersion = config.getIntegerList("validVersion");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (checkValidVersion(player)) reward(player);
    }

    private boolean checkValidVersion(Player player) {
        int version = viaVersionPlugin.getApi().getPlayerVersion(player);
        return validVersion.contains(version);
    }

    private void reward(Player player) {
        double addMoney= config.getDouble("addMoney");
        MessageControl.sendMessage(player, config.getString("Message").replace("%money%", String.valueOf(addMoney)));
        economy.depositPlayer(player, addMoney);
    }

}
