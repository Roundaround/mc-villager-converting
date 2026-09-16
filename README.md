![Villager Converting](https://imgur.com/fnqUD8S.png)

[![Modrinth Downloads](https://img.shields.io/modrinth/dt/villager-converting?style=flat&logo=modrinth&color=00AF5C)](https://modrinth.com/mod/villager-converting)
[![CurseForge Downloads](https://img.shields.io/curseforge/dt/1502027?style=flat&logo=curseforge&color=F16436)](https://www.curseforge.com/minecraft/mc-mods/villager-converting)
[![GitHub Repo stars](https://img.shields.io/github/stars/Roundaround/mc-villager-converting?style=flat&logo=github)](https://github.com/Roundaround/mc-villager-converting)

[![Support me on Ko-fi](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/compact/donate/kofi-singular-alt_vector.svg)](https://ko-fi.com/roundaround)

Villagers attacked by zombies always get converted instead of dying, rather than rolling the vanilla dice.

## Installing

Grab a build from [Modrinth](https://modrinth.com/mod/villager-converting) or [CurseForge](https://www.curseforge.com/minecraft/mc-mods/villager-converting). Fabric builds need [Fabric API](https://modrinth.com/mod/fabric-api).

## Building from source

```sh
./gradlew build
```

Dev runs are per loader: `:fabric:runClient`, and the `runServer` equivalents. Game tests run with `./gradlew :fabric:runClientGameTests` and `:fabric:runServerGameTests`.

The build is an [Allay](https://github.com/Roundaround/allay) consumer and bundles [Trove](https://github.com/Roundaround/trove).

## Contributing

Issues and pull requests are welcome at [the issue tracker](https://github.com/Roundaround/mc-villager-converting/issues).

- Branch from `main`, which tracks the newest supported Minecraft version. Older lines live on their own version-named branches.
- Keep loader-agnostic code in `common/`; only genuinely loader-specific glue belongs in a loader subproject.
- Run `./gradlew build` plus the Fabric game tests before opening a PR, and add a changelog entry under `changelogs/` named for the version you're targeting.

## License

[MIT](LICENSE)
