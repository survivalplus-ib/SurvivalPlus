package me.cirosanchez.survivalplus.configuration

import me.cirosanchez.clib.configuration.Configuration
import me.cirosanchez.clib.configuration.FileConfiguration
import java.io.ObjectInputFilter.Config

class ConfigurationProvider {
    lateinit var aesthetics: Configuration


    fun load(){
        aesthetics = FileConfiguration("aesthetics.yml").loadConfig()
    }
}