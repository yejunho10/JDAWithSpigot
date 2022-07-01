package kr.AMD_5900X.bot.utils;

import kr.AMD_5900X.mc.utils.DataContainor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;

import java.awt.Color;

import static kr.AMD_5900X.bot.JDAWithSpigotBot.jda;

@SuppressWarnings("all")
public class ChatSyncUtils {
    private static final String ChatSyncChannelID = DataContainor.getChatSyncChannelID();
    private static final TextChannel ChatSyncChannel = jda.getTextChannelById(ChatSyncChannelID);

    public static String getChatSyncChannelID() {
        return ChatSyncChannelID;
    }

    public static TextChannel getChatSyncChannel() {
        return ChatSyncChannel;
    }

    public static void sendChatMessage(String message, Player ChatPlayer) {
        String str = DataContainor.getChatSyncMessage()
                .replace("%player%", "`" + ChatPlayer.getName() + "`")
                .replace("%message%", message);

        ChatSyncChannel.sendMessage(str).queue();
    }

    public static void sendJoinMessage(Player JoinPlayer) {
        String str = DataContainor.getChatSyncJoinMessage()
                .replace("%player%", JoinPlayer.getName());

        EmbedBuilder eb = new EmbedBuilder()
                .setAuthor(str)
                .setDescription("Now Online: " + Bukkit.getOnlinePlayers().size() + " / " + Bukkit.getMaxPlayers())
                .setColor(Color.green);

        ChatSyncChannel.sendMessageEmbeds(eb.build()).queue();
    }

    public static void sendQuitMessage(Player QuitPlayer) {
        String str = DataContainor.getChatSyncQuitMessage()
                .replace("%player%", QuitPlayer.getName());

        EmbedBuilder eb = new EmbedBuilder()
                .setAuthor(str)
                .setDescription("Now Online: " + (Bukkit.getOnlinePlayers().size() - 1) + " / " + Bukkit.getMaxPlayers())
                .setColor(Color.red);

        ChatSyncChannel.sendMessageEmbeds(eb.build()).queue();
    }

    public static void sendGoalAdvancement(Player GoalPlayer, Advancement advancement) {
        String str = DataContainor.getGoalAdvancementMessage()
                .replace("%player%", GoalPlayer.getName())
                .replace("%advancement_name%", advancement.getDisplay().getTitle());

        EmbedBuilder eb = new EmbedBuilder()
                .setAuthor(str)
                .setColor(Color.YELLOW);

        ChatSyncChannel.sendMessageEmbeds(eb.build()).queue();
    }

    public static void sendPlayerDeath(Player DeathPlayer, String cause) {
        String str = DataContainor.getPlayerDeathMessage()
                .replace("%player%", DeathPlayer.getName())
                .replace("%cause%", cause);

        EmbedBuilder eb = new EmbedBuilder()
                .setAuthor(str)
                .setColor(Color.BLACK);

        ChatSyncChannel.sendMessageEmbeds(eb.build()).queue();
    }
}
