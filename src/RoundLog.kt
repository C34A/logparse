class RoundLog(val tileset: Tileset, val map: String) {
    private var kills = ArrayList<KillLog>();

    constructor(): this(Tileset.Unknown, "unknown")

    constructor(tileset: String, map: String): this(
        when(tileset.toLowerCase()) {
            "factory" -> Tileset.Factory
            "cstore", "c-store" -> Tileset.CStore
            "killhouse" -> Tileset.KillHouse
            else -> {
                println("tileset unknown: $tileset")
                Tileset.Unknown
            }
        },
        map
    )

    public fun addKill(kill: KillLog) {
        kills.add(kill);
    }

    public override fun toString(): String {
        val sb = StringBuilder();
        sb.append("tileset: ").append(tileset).append("\nmap: ").append(map).append("\n")
        for((kill, index) in kills.withIndex()) {
            sb.append("kill ").append(index).append(":\n").append(kill.toString())
        }
        return sb.toString()
    }

    public enum class Tileset {
        CStore,
        Factory,
        KillHouse,
        Unknown,
    }
}