class RoundLog(val tileset: Tileset, val map: String) {
    private var kills = ArrayList<KillLog>();

    constructor(): this(Tileset.Unknown, "unknown")

    constructor(tileset: String, map: String): this(
        when(tileset.toLowerCase()) {
            "factory" -> Tileset.Factory
            "cstore", "c-store" -> Tileset.CStore
            "killhouse", "killhouse_day" -> Tileset.KillHouse
            else -> {
                println("tileset unknown: $tileset")
                Tileset.Unknown
            }
        },
        map
    )

    public fun addKill(newKill: KillLog) {
        for(kill in kills) {
            if(kill.equals(newKill))
                return
        }
        kills.add(newKill);
    }

    public override fun toString(): String {
        var ret = "map: $map ($tileset)"
        for((index, kill) in kills.withIndex()) {
            ret += "\nkill $index: $kill"
        }
        return ret
    }

    public enum class Tileset {
        CStore,
        Factory,
        KillHouse,
        Unknown,
    }
}