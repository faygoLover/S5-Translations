package faygolover.s5lang;

import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.nio.file.Path;

/**
 * Переводы модов сборки S5 — отдельно от jar самих модов, чтобы обновления их не стирали.
 * Лежат в resourcepacks/s5lang внутри этого мода и подключаются встроенным ресурспаком поверх всех
 * (всегда включён), так что перекрывают перевод самого мода; строк, которых здесь нет, — из мода.
 */
@Mod(S5Lang.MODID)
public final class S5Lang {
    public static final String MODID = "s5lang";

    public S5Lang() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::addPacks);
    }

    private void addPacks(AddPackFindersEvent e) {
        if (e.getPackType() != PackType.CLIENT_RESOURCES) return;
        Path path = ModList.get().getModFileById(MODID).getFile().findResource("resourcepacks/s5lang");
        Pack pack = Pack.readMetaAndCreate("builtin/s5lang", Component.literal("S5: переводы"), true,
                id -> new PathPackResources(id, path, true), PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN);
        if (pack != null) e.addRepositorySource(add -> add.accept(pack));
    }
}
