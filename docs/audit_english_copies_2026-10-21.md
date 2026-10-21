# Аудит: английские копии в ru_ru модов — 2026-10-21

Продолжение [audit_2026-10-20.md](audit_2026-10-20.md). Тот аудит считал только **отсутствующие** ключи, поэтому моды, у которых `ru_ru.json` есть, но строки в нём — копии `en_us`, выглядели переведёнными (так было с Tobacconist). Здесь — обратная проверка: ключ в `ru_ru` мода есть, а значение совпадает с английским.

## Как считалось

- Моды: все jar из `S5 Packs/dist/player/minecraft/mods` + `dist/admin/minecraft/mods` (266 jar, включая вложенные jar-in-jar). Взяты `assets/<ns>/lang/en_us.json` и `ru_ru.json`; если ns есть в нескольких jar, файлы объединены.
- Включённые ресурспаки сборки проверены тоже: ru_ru есть только у ReimaginedGUI и только для `minecraft` — на моды не влияет.
- Перекрытия S5 Translations (`s5lang/assets/<ns>/lang/ru_ru.json`) учтены: если ключ там есть и он не равен английскому — строка считается переведённой («перекрыто S5»). Если в S5 Translations сама строка равна английской — она в отдельном разделе ниже.
- Строка — «английская копия», если `ru_ru == en_us`. Дальше она раскладывается на три кучки:
  - **англ. текст** — настоящий непереведённый текст: фразы, Title Case названия, одиночные обычные слова («All», «Settings», «Goon»).
  - **сомнительно** — может быть нормой: аббревиатуры и обозначения (`GC2B`, `RGBA`, `X, Y, Z`, `EU`), сочетания клавиш (`Ctrl + %s`), CamelCase-бренды (`JourneyMap`), название самого мода.
  - **исключено** (в таблицу не входит, только число) — нет букв (числа, `%s/%s`, пустые строки), имена авторов и ссылки (`*.author`, «By …», «Skin - …»), идентификаторы (`snake_case`, `ns:id`), строки, уже написанные по-русски в самом `en_us`, служебные ключи `_comment…`.
- **Игровые / техн.** — по ключу: `config.*`, `options`, `key.*`, команды, отладка, autoconfig/modmenu/jade-плагины → технические; `item/block/entity/effect/tooltip/gui/subtitles/advancements/itemGroup…` → игровые; остальное — по типу мода из прошлого аудита (если его там нет — к игровым, это интерфейс мода). Разделение эвристическое, точность ±10%.
- «тип мода» — раздел прошлого аудита («Видно в игре» / «Технические»); для модов, которых там не было (были полностью «переведены»), — по большинству ключей.

## Итог

- Модов (ns) со своим `ru_ru.json`: **132**. С английскими копиями (англ. текст или сомнительные) после учёта S5 Translations: **67**.
- Английский текст под видом перевода: **1069** строк — игровых **701**, технических **368**.
- Сомнительных (скорее всего норма): **409**. Исключено как заведомо не требующее перевода: **1207** (из них ~520 — подписи авторов скинов в selfexpression «By …», ~420 — числовые подписи в окнах cybernetic_system).
- Уже перекрыто S5 Translations (копия в моде, перевод в s5lang): tobacconistmod — 917, legendarysurvivaloverhaul — 2.
- Практически весь объём — в 7 модах (≥10 строк): **journeymap** 815, **zombie_extreme** 83, **entity_texture_features** 58, **cybernetic_system** 20, **mekanism** 15, **entity_model_features** 14, **treechop** 10 = 1015 из 1069. У остальных — по 1–8 строк.

## Топ модов

| # | мод (ns) | англ. текст | игровые | техн. | строк в ru_ru мода | доля | что там |
|---|---|---|---|---|---|---|---|
| 1 | journeymap (admin) | 815 | 547 | 268 | 1437 | 57% | Только admin-сборка. ru_ru мода старый: не переведены настройки, путевые точки, подсказки миникарты; больше половины файла — английский. |
| 2 | zombie_extreme | 83 | 83 | 0 | 398 | 21% | Названия мобов, яиц призыва, предметов (`Thorium Rod`), подписи сундуков/ящиков, вкладки творческого. |
| 3 | entity_texture_features | 58 | 0 | 58 | 290 | 20% | ETF: экран настроек и длинные пояснения свойств — игрок видит только в настройках мода. |
| 4 | cybernetic_system | 20 | 19 | 1 | 1278 | 2% | Подсказки терминалов/имплантов («Your Damage has been increased», «Increases maximum health»). Ещё 80+ «сомнительных» — коды имплантов (`GC2B`, `LG3`, `TM-1`), это их названия. |
| 5 | mekanism | 15 | 14 | 1 | 1655 | 1% | Названия достижений (`High Density Storage`), скины робита (`Ace Pride`), единицы (`Joule`), отладка. |
| 6 | entity_model_features | 14 | 0 | 14 | 50 | 28% | EMF: экран настроек. |
| 7 | treechop | 10 | 3 | 7 | 59 | 17% | Подписи плагинов Jade/WAILA (`Chop counts`) и «%d/%d chops». |
| 8 | fancymenu | 8 | 5 | 3 | 3367 | 0% | Редактор меню (сочетания клавиш, отладочный оверлей) — видят только админы при настройке меню. |
| 9 | pneumaticcraft | 8 | 3 | 5 | 1982 | 0% | Модули брони (ComputerCraft Peripheral) и подписи Jade. |
| 10 | jei | 7 | 4 | 3 | 121 | 6% | Отладочные описания (`description.jei.debug.*`, деревянная дверь) — видны только в режиме отладки JEI. |
| 11 | customnpcs | 6 | 6 | 0 | 2733 | 0% | Подсказки редактора NPC (для админов) и одна битая строка `month.0` в самом моде. |
| 12 | alexsmobs | 6 | 6 | 0 | 1075 | 1% | Названия пластинок и их авторы — можно оставить. |
| 13 | soviet | 3 | 3 | 0 | 769 | 0% | `Charge:` в подсказке и одно достижение. |
| 14 | tacz | 2 | 1 | 1 | 293 | 1% |  |
| 15 | farm_and_charm | 2 | 1 | 1 | 312 | 1% |  |

## Все моды с английскими копиями

«перекрыто S5» — сколько копий из jar мода уже переведено в S5 Translations (в остальные числа не входят). (admin) — мод только в admin-сборке.

| мод (ns) | jar | тип мода | англ. текст | игровые | техн. | сомнительно | исключено | строк в ru_ru мода | перекрыто S5 |
|---|---|---|---|---|---|---|---|---|---|
| journeymap (admin) | `journeymap-forge-1.20.1-6.0.6.jar` | игровой | 815 | 547 | 268 | 40 | 12 | 1437 |  |
| zombie_extreme | `Zombie Extreme 1.20.1 0.2.6.5.jar` | игровой | 83 | 83 | 0 | 0 | 97 | 398 |  |
| entity_texture_features | `entity_texture_features-7.2.3-1.20.1-forge.jar` | техн. | 58 | 0 | 58 | 2 | 1 | 290 |  |
| cybernetic_system | `cybernetic_system-1.4.1-forge-1.20.1.jar` | игровой | 20 | 19 | 1 | 84 | 426 | 1278 |  |
| mekanism | `Mekanism-1.20.1-10.4.16.80.jar` | игровой | 15 | 14 | 1 | 25 | 19 | 1655 |  |
| entity_model_features | `entity_model_features-3.3.7-1.20.1-forge.jar` | техн. | 14 | 0 | 14 | 0 | 0 | 50 |  |
| treechop | `TreeChop-1.20.1-forge-0.19.0-fixed.jar` | техн. | 10 | 3 | 7 | 0 | 0 | 59 |  |
| fancymenu | `fancymenu_forge_3.9.6_MC_1.20.1.jar` | игровой | 8 | 5 | 3 | 27 | 6 | 3367 |  |
| pneumaticcraft | `pneumaticcraft-repressurized-6.0.22+mc1.20.1.jar` | игровой | 8 | 3 | 5 | 6 | 4 | 1982 |  |
| jei | `jei-1.20.1-forge-15.20.0.130.jar` | техн. | 7 | 4 | 3 | 6 | 1 | 121 |  |
| customnpcs | `Custom NPCs 1.20.1 Unofficial-v.1.007-05Jun2025-Snapshot.jar` | игровой | 6 | 6 | 0 | 4 | 2 | 2733 |  |
| alexsmobs | `alexsmobs-1.22.9.jar` | игровой | 6 | 6 | 0 | 3 | 0 | 1075 |  |
| soviet | `soviet-0.0.5-1.20.1-forge.jar` | игровой | 3 | 3 | 0 | 0 | 1 | 769 |  |
| tacz | `tacz-1.20.1-1.1.8-hotfix.jar` | игровой | 2 | 1 | 1 | 2 | 2 | 293 |  |
| farm_and_charm | `letsdo-farm_and_charm-forge-1.0.14.jar` | игровой | 2 | 1 | 1 | 0 | 0 | 312 |  |
| weaponmaster_ydm | `weaponmaster_ydm-forge-1.20.1-4.2.3.jar` | игровой | 2 | 1 | 1 | 0 | 1 | 44 |  |
| biomesoplenty | `BiomesOPlenty-forge-1.20.1-19.0.0.96.jar` | игровой | 1 | 1 | 0 | 3 | 0 | 425 |  |
| chatnotify | `chatnotify-forge-2.6.9+1.20.1.jar` | техн. | 1 | 0 | 1 | 2 | 1 | 290 |  |
| quark | `Quark-4.0-462.jar` | игровой | 1 | 1 | 0 | 1 | 19 | 1525 |  |
| vinery | `letsdo-vinery-forge-1.4.41.jar` | игровой | 1 | 1 | 0 | 1 | 0 | 348 |  |
| brewery | `letsdo-brewery-forge-2.0.6.jar` | игровой | 1 | 1 | 0 | 0 | 0 | 177 |  |
| camping | `camping-forge-1.20.1-2.0.0.jar` | игровой | 1 | 0 | 1 | 0 | 0 | 135 |  |
| iris | `oculus-mc1.20.1-1.8.0.jar` | техн. | 1 | 0 | 1 | 0 | 0 | 69 |  |
| jecalculation | `jecalculation-forge-1.20.1-4.0.4.jar` | техн. | 1 | 0 | 1 | 0 | 0 | 102 |  |
| orva_alarms | `orva_alarms-4.1-forge-1.20.1.jar` | игровой | 1 | 1 | 0 | 0 | 1 | 65 |  |
| walkers | `walkers-5.8.11.jar` | техн. | 1 | 0 | 1 | 0 | 0 | 29 |  |
| chisel_chipped_integration | `chisel_chipped_integration-v1.1.6-1.20.1.jar` | игровой | 0 | 0 | 0 | 116 | 0 | 730 |  |
| fzzy_config | `fzzy_config-0.7.6+1.20.1+forge.jar` | техн. | 0 | 0 | 0 | 14 | 2 | 284 |  |
| rpmedicine | `rpmedicine-0.8.5-1.20.1-forge.jar` | игровой | 0 | 0 | 0 | 5 | 8 | 1465 |  |
| scholar | `scholar-1.20.1-1.2.5.1-forge.jar` | техн. | 0 | 0 | 0 | 5 | 0 | 86 |  |
| supplementaries | `supplementaries-1.20-3.1.43-forge.jar` | игровой | 0 | 0 | 0 | 5 | 5 | 614 |  |
| exposure | `exposure-forge-1.20.1-1.9.21.jar` | игровой | 0 | 0 | 0 | 4 | 6 | 389 |  |
| securitycraft | `[1.20.1] SecurityCraft v1.10.1.jar` | игровой | 0 | 0 | 0 | 4 | 4 | 1402 |  |
| cloth-config2 | `cloth-config-11.1.136-forge.jar`, `superbwarfare-0.8.9.1-hotfix-mc1.20.1-993063bed-all.jar` | игровой | 0 | 0 | 0 | 3 | 0 | 49 |  |
| effortlessbuilding | `effortlessbuilding-1.20.1-3.11.jar` | игровой | 0 | 0 | 0 | 3 | 14 | 90 |  |
| selfexpression | `selfexpression-2.22a-forge-1.20.1.jar` | игровой | 0 | 0 | 0 | 3 | 525 | 1408 |  |
| simpleradio | `simpleradio-forge-1.20.1-3.4.6.jar` | игровой | 0 | 0 | 0 | 3 | 0 | 46 |  |
| alexscaves | `alexscaves-2.0.2.jar` | игровой | 0 | 0 | 0 | 2 | 0 | 1705 |  |
| clienttweaks | `clienttweaks-forge-1.20.1-11.1.6.jar` | техн. | 0 | 0 | 0 | 2 | 0 | 53 |  |
| curios | `curios-forge-5.14.1+1.20.1.jar`, `superbwarfare-0.8.9.1-hotfix-mc1.20.1-993063bed-all.jar` | игровой | 0 | 0 | 0 | 2 | 0 | 46 |  |
| legendarysurvivaloverhaul | `legendarysurvivaloverhaul-1.20.1-2.4.2.jar` | игровой | 0 | 0 | 0 | 2 | 1 | 199 | 2 |
| mutantmonsters | `MutantMonsters-v8.0.8-1.20.1-Forge.jar` | игровой | 0 | 0 | 0 | 2 | 0 | 107 |  |
| selfexpression_slim | `selfexpression_slim-1.5-forge-1.20.1.jar` | игровой | 0 | 0 | 0 | 2 | 0 | 163 |  |
| stardew_fishing | `stardew_fishing-3.7-1.20.1.jar` | игровой | 0 | 0 | 0 | 2 | 1 | 85 |  |
| table_top_craft | `table_top_craft-1.20.1-6.1.2.jar` | игровой | 0 | 0 | 0 | 2 | 7 | 285 |  |
| betterarcheology | `betterarcheology-1.2.1-1.20.1.jar` | игровой | 0 | 0 | 0 | 1 | 2 | 112 |  |
| bettercombat | `bettercombat-forge-1.9.0+1.20.1.jar` | игровой | 0 | 0 | 0 | 1 | 0 | 35 |  |
| carryon | `carryon-forge-1.20.1-2.1.2.7.jar` | игровой | 0 | 0 | 0 | 1 | 0 | 12 |  |
| corpse | `corpse-forge-1.20.1-1.0.23.jar` | игровой | 0 | 0 | 0 | 1 | 0 | 20 |  |
| cuffed | `Cuffed-1.20.1-1.3.13.jar` | игровой | 0 | 0 | 0 | 1 | 0 | 251 |  |
| emotecraft | `emotecraft-for-MC1.20.1-2.2.7-b.build.50-forge.jar` | игровой | 0 | 0 | 0 | 1 | 0 | 68 |  |
| entityculling | `entityculling-forge-1.10.2-mc1.20.1.jar` | техн. | 0 | 0 | 0 | 1 | 0 | 21 |  |
| gamediscs | `gamediscs-0.3.2-forge.jar` | игровой | 0 | 0 | 0 | 1 | 0 | 41 |  |
| herbalbrews | `letsdo-herbalbrews-forge-1.0.12.jar` | игровой | 0 | 0 | 0 | 1 | 0 | 122 |  |
| immersivepaintings_exposure | `immersivepaintings-exposure-1.0.0.jar` | игровой | 0 | 0 | 0 | 1 | 0 | 10 |  |
| mekanismelements | `MekanismElements-1.20.1-2.3.jar` | игровой | 0 | 0 | 0 | 1 | 0 | 218 |  |
| mekanismgenerators | `MekanismGenerators-1.20.1-10.4.16.80.jar` | игровой | 0 | 0 | 0 | 1 | 0 | 202 |  |
| modernfix | `modernfix-forge-5.27.40+mc1.20.1.jar` | техн. | 0 | 0 | 0 | 1 | 2 | 122 |  |
| movingelevators | `movingelevators-1.4.12-forge-mc1.20.1.jar` | игровой | 0 | 0 | 0 | 1 | 0 | 40 |  |
| patchouli | `Patchouli-1.20.1-85-FORGE.jar` | игровой | 0 | 0 | 0 | 1 | 2 | 90 |  |
| ponder | `effortlessbuilding-1.20.1-3.11.jar`, `superbwarfare-0.8.9.1-hotfix-mc1.20.1-993063bed-all.jar` | техн. | 0 | 0 | 0 | 1 | 0 | 61 |  |
| push_zone | `push_zone-1.1-forge-1.20.1.jar` | игровой | 0 | 0 | 0 | 1 | 0 | 2 |  |
| refurbished_furniture | `refurbished_furniture-forge-1.20.1-1.0.20.jar` | игровой | 0 | 0 | 0 | 1 | 2 | 652 |  |
| rpculinary | `rpculinary-0.3.0-1.20.1-forge.jar` | игровой | 0 | 0 | 0 | 1 | 0 | 122 |  |
| rppda | `rppda-0.4.3-1.20.1-forge.jar` | игровой | 0 | 0 | 0 | 1 | 3 | 454 |  |
| zerocontact | `ZeroContact-main-build-72-36c6a8f.jar` | игровой | 0 | 0 | 0 | 1 | 0 | 47 |  |
| zeta | `Zeta-1.0-31.jar` | игровой | 0 | 0 | 0 | 1 | 0 | 2 |  |

Только исключённые строки (числа, авторы, идентификаторы — проблем нет): bettermodsbutton (1), candlelight (1), creativecore (2), csfoundation (2), etched (1), fl_zone_arts (1), jeresources (1), lrarmor (1), mekanismexplosives (2), tobacconistmod (16), voicechat (2).

## В самом S5 Translations строка равна английской

96 строк в 24 модах. Это сознательно оставленные как есть названия: модели оружия и техники (`AK-47`, `AH-6 Little Bird`), бренды (`Pepsi`, `Monster`), названия модов в категориях клавиш, хоткеи `[Ctrl+E]`, загрузочный лог Linux в ZeroContact. Проблемой не считаются; список для сверки:

| мод (ns) | строк | примеры |
|---|---|---|
| superbwarfare | 42 | M870 MCS; M98B; M60 |
| exposure_catalog | 11 | §8[Del]; §8[Ctrl+E];  x1 |
| zerocontact | 9 | Bastion (Multicam); British23; Phonetalker IIIA |
| sa_combat | 5 | C4; C4; Monster |
| pneumaticcraft | 4 | X; Y; Z |
| sa_decor | 3 | SA Decor; %s / %s mB; ID: %s |
| scpo | 3 | SCP: Overtime; SCP-1471-A "MalO"; SCP-087-1 |
| clanginghowl | 2 | Clanging Howl; Clanging Howl |
| lrtactical | 2 | LesRaisins Tactical; LesRaisins Tactical |
| customnpcs | 1 | §7ID: %s§7 "§r%s§7" -%s |
| legendarysurvivaloverhaul | 1 | Legendary Survival Overhaul |
| anomaly_rephased | 1 | missing |
| blocksyouneed_luna | 1 | Blocks You Need |
| marbledsarsenal | 1 | Marbled's Arsenal |
| multibeds | 1 | MultiBeds |
| powered_flashlight | 1 | %s/%s FE |
| remorphed | 1 | Remorphed |
| sa_core | 1 | %s / %s mB |
| simplevoicedistance | 1 | Simple Voice Distance |
| tacz_presence | 1 | HUD |
| tacz_tactical_breaching | 1 | TaCZ Tactical Breaching |
| taczadditions | 1 | TaCZ: Additions |
| taczaddon | 1 | TaCZ addon |
| wirelessredstone | 1 | [x: %s, y: %s, z: %s] |

## Примеры по модам

**journeymap**
- `controlify.binding.journeymap.create_waypoint` = Create Waypoint at cursor
- `controlify.binding.journeymap.open_context` = Open Context Menu
- `controlify.binding.journeymap.pan_down` = Pan Down
- `controlify.binding.journeymap.pan_left` = Pan Left

**zombie_extreme**
- `gui.zombie_extreme.cupboard_gui.label_cupboard` = Cupboard
- `item_group.zombie_extreme.zombie_extreme_food` = Zombie Extreme:Food
- `entity.zombie_extreme.crawler` = Crawler
- `entity.zombie_extreme.chainsaw` = Chainsaw

**entity_texture_features**
- `config.entity_features.optifine_settings` = OptiFine Compat
- `config.entity_texture_features.optifine.desc` = These options are for backwards compatibility with OptiFine.\nSome will put limitations on 
- `config.entity_texture_features.optifine.variant_gap.title` = Limit rule number gaps
- `config.entity_texture_features.optifine.variant_gap.tooltip` = OptiFine limits random .properties file rule numbers such that in\nsequential order the rul

**cybernetic_system**
- `item.cybernetic_system.mb_2_hellhound_spawn_egg` = MB2 Hellhound Spawn Egg
- `item.cybernetic_system.cubernetics_systems_item` = Cubernetics Systems
- `entity.cybernetic_system.sit` = Sit
- `gui.cybernetic_system.gui_new_dna_update.tooltip_your_damage_has_been_increased1` = Your Damage has been increased

**mekanism**
- `advancements.mekanism.advanced_qio_drive.title` = High Density Storage
- `advancements.mekanism.configuration_copying.title` = Ctrl+C, Ctrl+V
- `constants.mekanism.debug_title` = Mekanism Debug
- `death.attack.mekanism.laser.player` = %1$s was incinerated whilst trying to escape %2$s.

**entity_model_features**
- `entity_model_features.config.animate_once` = Animate models once per entity
- `entity_model_features.config.debug_right_click` = Show EMF debug data on entity right click
- `entity_model_features.config.debug_right_click.tooltip` = If enabled, this will print out EMf debug info into\nthe log and chat when an entity is rig
- `entity_model_features.config.force_models` = Try to prevent EMF models being overridden

**treechop**
- `treechop.subtitles.block_chopped` = Block chopped
- `treechop.waila.x_out_of_y_chops` = %d/%d chops
- `treechop.waila.chopped_x` = Chopped %s
- `config.waila.plugin_treechop` = HT's TreeChop

**fancymenu**
- `fancymenu.backgrounds.glsl.compile_mode.shadertoy` = Shadertoy
- `fancymenu.decoration_overlays.glsl.compile_mode.shadertoy` = Shadertoy
- `fancymenu.editor.shortcuts.delete` = Del
- `fancymenu.editor.shortcuts.enter` = Enter

**pneumaticcraft**
- `pneumaticcraft.armor.upgrade.block_tracker.module.peripheral` = ComputerCraft Peripherals
- `pneumaticcraft.blockTracker.info.peripheral.title` = ComputerCraft Peripheral
- `pneumaticcraft.gui.amadron` = Amadron
- `config.jade.plugin_pneumaticcraft.entity` = entity

**jei**
- `description.jei.wooden.door.1` = Wooden doors allow you to block monsters from entering your building.\nTesting sentences.
- `description.jei.wooden.door.2` = Clicking on a door changes its state from open to closed and vice versa.
- `description.jei.wooden.door.3` = Wooden doors can be opened/closed via redstone circuits.
- `description.jei.debug.formatting.1` = Testing %s formatting replacements.

**customnpcs**
- `ai.hover.rot.type.1` = Always looks in the specified direction
- `ai.hover.rot.type.2` = Always looks at the first player found<br>§7If there is no player, looks at the found enti
- `ai.hover.rot.type.3` = Stands body in the indicated direction, but can move its head<br>§7There is a 4~~~§7 chanc
- `entity.customnpcs.customnpc64x32` = CNpc 64x32

**alexsmobs**
- `item.alexsmobs.music_disc_thime.desc` = LudoCrypt - Thime
- `item.alexsmobs.music_disc_daze.desc` = LudoCrypt - Daze
- `alexsmobs.sound.subtitle.la_cucaracha` = ¡La Cucaracha!
- `alexsmobs.sound.subtitle.music_disc.thime` = Thime

## Что дальше

Решение о переводе — за Alex. Полный список ключей (англ. текст + сомнительные, с английским значением) — в [audit_english_copies_2026-10-21.json](audit_english_copies_2026-10-21.json), его можно отдавать на перевод так же, как `to_translate_map.json`. Моды вообще без `ru_ru` и с отсутствующими ключами — в прошлом аудите, здесь не повторяются.

## Решение и сделано (2026-10-20, S5 Translations 1.0.5)

Alex: journeymap — не переводить, остальное — перевести.

- Переведено и добавлено в s5lang **216 строк**: zombie_extreme 83, entity_texture_features 55, cybernetic_system 20, entity_model_features 14, mekanism 13, treechop 7, pneumaticcraft 7, jei 7, customnpcs 4, soviet 3, fancymenu 1, chatnotify 1, orva_alarms 1. Плейсхолдеры, §-коды, `<br>`, ссылки и обратные косые черты проверены скриптом — совпадают с английским.
- Нарочно оставлены по-английски (названия): «[Let's Do] …», «Timeless & Classics Guns: Zero», «YDM's Weapon Master», «HT's TreeChop», Oculus, Woodwalkers, Just Enough Calculation, Amadron, Shadertoy/Markdown, пластинки Alex's Mobs / BoP, `CNpc 64x32`, `Ctrl+C, Ctrl+V`, `-aidancbrady`, OptiFine/Figura/Quark/Enhanced Block Entities в ETF. `customnpcs` `month.0` — битая строка в самом моде, не трогал. Сомнительные (аббревиатуры, коды имплантов, CTM) не трогал.
- Отдельно проверено: строк в ru_ru, которые на английском, но уже не совпадают с en_us (устаревший английский), — 9, все названия; проблемы нет.
- Сборка: `s5lang-1.0.5`, в S5 Packs коммит 57b24fc (локально), в DEP_S5_admin заменён. В игре не проверено.
