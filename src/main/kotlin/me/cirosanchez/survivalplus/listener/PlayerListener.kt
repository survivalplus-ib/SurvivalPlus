package me.cirosanchez.survivalplus.listener

import io.papermc.paper.event.player.AsyncChatEvent
import me.cirosanchez.clib.CLib
import me.cirosanchez.clib.extension.colorize
import me.cirosanchez.clib.extension.placeholders
import me.cirosanchez.clib.placeholder.Placeholder
import me.cirosanchez.survivalplus.SurvivalPlus
import me.cirosanchez.survivalplus.util.ChatTypes
import me.cirosanchez.survivalplus.util.apply
import me.clip.placeholderapi.PlaceholderAPI
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.checkerframework.checker.units.qual.m


class PlayerListener : Listener {

    val aestheticsConfiguration = SurvivalPlus.get().configurationProvider.aesthetics


    val welcomeMessagesList = aestheticsConfiguration.getStringList("welcome-messages")
    val goodbyeMessagesList = aestheticsConfiguration.getStringList("goodbye-messages")


    val publicChatFormattingString = aestheticsConfiguration.getStringList("chat-formatting.public.format")
    val staffChatFormattingString = aestheticsConfiguration.getStringList("chat-formatting.staff.format")
    val executeChatFormattingString = aestheticsConfiguration.getStringList("chat-formatting.executive.format")


    val chatStatus: HashMap<Player, ChatTypes> = hashMapOf()

    /*
    Welcome message dedicated listenero
     */
    @EventHandler
    fun welcomeJoin(event: PlayerJoinEvent) {
        val name = event.player.name
        val placeholder = Placeholder("{player}", name)
        val randomMessage = welcomeMessagesList.random()
        val component = placeholder.apply(randomMessage).colorize()

        event.joinMessage(component)
    }

    /*
    Goodbye message dedicate listener
     */
    @EventHandler
    fun goodbyeQuit(event: PlayerQuitEvent){
        val name = event.player.name
        val placeholder = Placeholder("{player}", name)
        val randomMessage = goodbyeMessagesList.random()
        val component = placeholder.apply(randomMessage).colorize()

        event.quitMessage(component)
    }

    /*
    Chat Formatting dedicated listeners
     */
    @EventHandler
    fun playerChatJoinEvent(event: PlayerJoinEvent){
        val player = event.player
        if (chatStatus.containsKey(player)) return
        chatStatus[player] = ChatTypes.PUBLIC
    }

    @EventHandler
    fun playerChatEvent(event: AsyncChatEvent){
        val player = event.player
        val name = player.name
        val namePlaceholder = Placeholder("{name}", name)

        val mm = SurvivalPlus.mm()
        val stringMessage = mm.serialize(event.message())
        val messagePlaceholder = Placeholder("{message}", stringMessage)

        val type = chatStatus[player]

        var message: String = when (type) {
            ChatTypes.PUBLIC -> publicChatFormattingString.toString()
            ChatTypes.STAFF -> staffChatFormattingString.toString()
            ChatTypes.EXECUTIVE -> executeChatFormattingString.toString()
            null -> return
        }

        message = message.placeholders(namePlaceholder, messagePlaceholder)
        message = PlaceholderAPI.setPlaceholders(player, message)

        event.isCancelled = true

        when (type) {
            ChatTypes.PUBLIC -> Bukkit.broadcast(message.colorize())
            ChatTypes.STAFF -> {
                
            }
            ChatTypes.EXECUTIVE -> executeChatFormattingString.toString()
            null -> return
        }
    }
}