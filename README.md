# S5 Translations (`s5lang`)

Русские переводы модов сборки S5 — отдельно от jar самих модов, чтобы обновления их не стирали. Клиентский мод: встроенный ресурспак «S5: переводы» поверх всех, всегда включён. Строки, которых здесь нет, берутся из самого мода.

- Переводы: `src/main/resources/resourcepacks/s5lang/assets/<мод>/lang/ru_ru.json` — правьте или добавляйте файлы.
- Первичное наполнение — из старых jar с ручными переводами: `python ../S5\ Packs/.staging/make_lang.py src/main/resources/resourcepacks/s5lang` (берутся только строки, отличающиеся от мода в сборке).
- Кроме переводов, тот же ресурспак перекрывает отдельные ресурсы модов. Сейчас это модели сушилок Tobacconist (`assets/tobacconistmod/models/block/`): убрано z-fighting у столбов/колпаков, листьев и рамы промышленной сушилки. Это исправленные копии моделей из `tobacconistmod-4.0.0`, поэтому при обновлении Tobacconist их нужно пересобрать по новым моделям мода.
- Сборка: `./gradlew build -x test` → `build/libs/s5lang-*.jar`, затем в сборку: `python tools/add.py <jar> --layers client` (в S5 Packs).
