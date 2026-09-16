![Villager Converting](https://imgur.com/fnqUD8S.png)

Villagers attacked by zombies always get converted instead of dying. In v0.1.0, the villagers require a name in order to be guaranteed converted. In v1.0.0 and beyond, this is configurable. Required on the server for multiplayer, but also works in single player!

<details>
<summary><b>Configuration — <code>villagerconverting.toml</code></b></summary>

You can configure the behavior of the mod from the `villagerconverting.toml` file within your server (or local instance for single player) config folder. As of v1.5.0, the config file will be generated _inside_ your world/save folder, as the configuration options have become world-specific. If you're on single player and have ModMenu installed, you can also access the configuration through the UI in ModMenu's mod list!

`modEnabled`: `true|false` - Simple toggle for the mod! When set to `false`, the villagers will fall back to vanilla behavior/probability for zombie-conversion.

`requireName`: `true|false` - When set to `true`, only villagers that have a custom name set (i.e. with a nametag) will be guaranteed to convert! Non-named villagers will fall back to vanilla behavior.

</details>

## Links

<!-- modrinth:only -->
Also on [CurseForge](https://www.curseforge.com/minecraft/mc-mods/villager-converting) · [Source on GitHub](https://github.com/Roundaround/mc-villager-converting) · [Report an issue](https://github.com/Roundaround/mc-villager-converting/issues)
<!-- /modrinth:only -->
<!-- curseforge:only -->
Also on [Modrinth](https://modrinth.com/mod/villager-converting) · [Source on GitHub](https://github.com/Roundaround/mc-villager-converting) · [Report an issue](https://github.com/Roundaround/mc-villager-converting/issues)
<!-- /curseforge:only -->

[![Support me on Ko-fi](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/compact/donate/kofi-singular-alt_vector.svg)](https://ko-fi.com/roundaround)
