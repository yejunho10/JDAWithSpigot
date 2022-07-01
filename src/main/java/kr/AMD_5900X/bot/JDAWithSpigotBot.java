package kr.AMD_5900X.bot;

import kr.AMD_5900X.bot.listener.Commands;
import kr.AMD_5900X.bot.utils.RichPresenceUtils;
import kr.AMD_5900X.mc.listener.ChatSync;
import kr.AMD_5900X.mc.utils.DataContainor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

@SuppressWarnings("all")
public class JDAWithSpigotBot {
    public static JDA jda;
    public static String BotToken,
            BotCommandPrefix,
            ChatSyncChannelID;

    public static void initJDA() throws LoginException {
        /* [ <-- Init Variables --> ] */
        initVars();

        /* [ <-- Build JDA --> ] */
        jda = JDABuilder.createDefault(BotToken).build();

        /* [ <-- Add Listener --> ] */
        jda.addEventListener(new Commands());
        jda.addEventListener(new ChatSync());

        /* [ <-- Set Rich Presence --> ] */
        RichPresenceUtils.setStatus(DataContainor.getBotStatus());
        RichPresenceUtils.setActivity(Activity.playing(DataContainor.getBotRichPresence()), true);
    }

    public static void initVars() {
        BotToken = DataContainor.getBotToken();
        BotCommandPrefix = DataContainor.getBotCommandPrefix();
        ChatSyncChannelID = DataContainor.getChatSyncChannelID();
    }
}
