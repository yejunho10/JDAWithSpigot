package kr.AMD_5900X.mc;

import kr.AMD_5900X.mc.listener.ChatSync;
import kr.AMD_5900X.mc.listener.JoinQuit;
import kr.AMD_5900X.mc.commands.MPCommand;
import kr.AMD_5900X.mc.utils.ConfigUtils;
import kr.AMD_5900X.mc.utils.DataContainor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.util.logging.Logger;

import static kr.AMD_5900X.bot.JDAWithSpigotBot.initJDA;
import static kr.AMD_5900X.bot.JDAWithSpigotBot.jda;
import static org.bukkit.Bukkit.getPluginCommand;
import static org.bukkit.Bukkit.getPluginManager;

@SuppressWarnings("all")
public class JDAWithSpigotMain extends JavaPlugin implements CommandExecutor {
    private static JDAWithSpigotMain plugin;
    public static YamlConfiguration config;
    private Logger log;
    public static String prefix;

    public static JDAWithSpigotMain getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        config = ConfigUtils.loadDefaultPluginConfig(plugin);
        log = getLogger();
        prefix = DataContainor.getPrefix();

        /* ## <- Setting Executor -> ## */
        getPluginCommand("mp").setExecutor(new MPCommand());

        /* ## <- Setting Event Listener -> ## */
        getPluginManager().registerEvents(new JoinQuit(), this);
        getPluginManager().registerEvents(new ChatSync(), this);

        try {
            initJDA();
        }
        catch (LoginException e) {
            e.printStackTrace();
        }

        log.info("[ <- JDAWithSpigot Enabled -> ]");
    }

    @Override
    public void onDisable() {
        jda.shutdown();

        log.info("[ <- JDAWithSpigot Disabled -> ]");
    }
}

