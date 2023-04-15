<p align="center">
  <img src="https://media.discordapp.net/attachments/816647374239694849/1096912219394031626/f94ce6e10a82d19adda08984302f846162bb324ada39a3ee5e6b4b0d3255bfef95601890afd80709da39a3ee5e6b4b0d3255bfef95601890afd8070966c0e6b78806a7dcb2be472ceb8df67a.png">
</p>

<br>

# ModDrops

<p>Add custom mob drops to your Minecraft server</p>
<p>Tested minecraft versions: </p> 

`1.19.3`

# Installation

<p>Put MobDrops.jar to your plugins folder and restart the server.</p>

# Configuration

`Use § or & sign for colors`<br>

<details><summary>config.yml</summary>

```yml
config:
  prefix: "§c[§7MobDrops§c] "
```

`prefix` - Prefix for commands<br>

</details>

<details><summary>items.yml</summary>

```yml
items:
  "diamond":
    item: DIAMOND
    name: "Super diamond!"
    lore: "Diamond dropped from a mob"
    enchantments:
      - DIG_SPEED:3
    unbreakable: false
  "emerald":
    item: EMERALD
    name: "Super emerald!"
    lore: "Emerald dropped from a mob"
    unbreakable: true
  "command":
    command: "say %player% is a very serious killer!"
```

`"diamond"` - Item ID<br>
`name` - Item name<br>
`lore` - Lore of item<br>
`enchantments` - List of enchantments (enchantment name:enchantment level) (get enchantments from https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/enchantments/Enchantment.html)<br>
`unbreakable` - If set to true then item is unbreakable<br>
`command` - Ignores all previous fields (without ID). This command will be executed if item is requested. Use %player% to get player.<br>

</details>

<details><summary>mobs.yml</summary>

```yml
mobs:
  "zombie":
    0:
      itemID: "diamond"
      percentage: 10
      countMin: 1
      countMax: 1
      firework: true
      fireworkColor:
        - 0
        - 0
        - 255
    1:
      itemID: "emerald"
      countMin: 1
      countMax: 5
      percentage: 5
      lootBonus: true
      firework: true
      fireworkColor:
        - 0
        - 255
        - 0
```

`"zombie"` - Entity name<br>
`0` - Drop 0 for this mob<br>
`itemID` - Item ID from items.yml<br>
`percentage` - Percentage to drop. Min: 0.0001, max: 100<br>
`countMin` - Min count of dropped item/executed command (random)<br>
`countMax` - Max count of dropped item/executed command (random)<br>
`lootBonus` - If set to true then looting enchantments will affect to percentage: `Actual percentage: PERCENTAGE * (1 + LOOTING_LEVEL)`<br>
`firework` - If set to true then when item is dropped/command is executed the firework will spawn<br>
`fireworkColor` - Firework color in RGB style (first element - red, second - green, third - blue)<br>

</details>

# Commands & Permissions

Permission to manage: `mobdrops.manage`<br>
`/mobdrops` - Mob drops help<br>
`/mobdrops reload` - Reload configuration<br>
`/mobdrops item` - List of items<br>
`/mobdrops mobs` - List of mobs<br>
`/mobdrops getitem <itemID>` - Get item (or execute command)<br>

