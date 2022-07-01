package kr.AMD_5900X.mc.listener;

import kr.AMD_5900X.bot.utils.ChatSyncUtils;
import kr.AMD_5900X.mc.utils.DataContainor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@SuppressWarnings("all")
public class ChatSync extends ListenerAdapter implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        ChatSyncUtils.sendJoinMessage(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        ChatSyncUtils.sendQuitMessage(e.getPlayer());
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent e) {
        ChatSyncUtils.sendChatMessage(e.getMessage(), e.getPlayer());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        ChatSyncUtils.sendPlayerDeath(e.getEntity().getPlayer(), e.getDeathMessage());
    }

    public void onMessageReceived(MessageReceivedEvent e) {
        if (!e.getTextChannel().getId().equals(DataContainor.getChatSyncChannelID())) { return; }
        if (e.getAuthor().isBot()) { return; }

        String str = DataContainor.getMessageReceivedFromDiscord()
                        .replace("%user_name%", e.getMember().getEffectiveName())
                        .replace("%message%", e.getMessage().getContentRaw());

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(str);
        }
    }
}
