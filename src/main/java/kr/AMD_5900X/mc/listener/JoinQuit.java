package kr.AMD_5900X.mc.listener;

import kr.AMD_5900X.mc.utils.DataContainor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@SuppressWarnings("all")
public class JoinQuit implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (DataContainor.isJoinMessageEnabled()) {
            e.setJoinMessage(
                    DataContainor.getJoinMessage()
                            .replace("%player%", e.getPlayer().getName()));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (DataContainor.isQuitMessageEnabled()) {
            e.setQuitMessage(
                    DataContainor.getQuitMessage()
                            .replace("%player%", e.getPlayer().getName()));
        }
    }
}
