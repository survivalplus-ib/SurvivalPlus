package me.cirosanchez.survivalplus.listener

import me.cirosanchez.clib.extension.colorize
import me.cirosanchez.clib.placeholder.Placeholder
import me.cirosanchez.survivalplus.SurvivalPlus
import me.cirosanchez.survivalplus.util.apply
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent


class PlayerListener : Listener {

    val aestheticsConfiguration = SurvivalPlus.get().configurationProvider.aesthetics
    val welcomeMessagesList = aestheticsConfiguration.getStringList("welcome-messages")
    val goodbyeMessagesList = aestheticsConfiguration.getStringList("goodbye-messages")

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
}