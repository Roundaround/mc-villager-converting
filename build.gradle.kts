plugins {
  id("me.roundaround.allay")
}

allay {
  displayName.set("Villager Converting")
  description.set("Villagers attacked by zombies always get converted instead of dying.")
  authors.set(listOf("Roundaround"))
  license.set("MIT")
  homepage.set("https://modrinth.com/mod/villager-converting")
  repository.set("https://github.com/Roundaround/mc-villager-converting")
  issues.set("https://github.com/Roundaround/mc-villager-converting/issues")
  logoFile.set("assets/villagerconverting/banner.png")

  modrinth {
    projectId.set("villager-converting")
  }

  curseforge {
    projectId.set(1502027)
  }

  release {
    versionType.set("release")
    minecraftVersions("26.1".."26.1.2")
    changelogDir.set(file("changelogs"))
  }
}
