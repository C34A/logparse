import Team

class KillLog(var killerName: String, var killerTeam: Team, var victimName: String, var victimTeam: Team) {
    constructor(killerName: String, killerTeam: String, victimName: String, victimTeam: String) : this(
            killerName,
            when(killerTeam) {
                "ATTACKER" -> Team.Attacker
                "DEFENDER" -> Team.Defender
                "NONE" -> Team.None
                else -> Team.Unknown
            },
            victimName,
            when(victimTeam) {
                "ATTACKER" -> Team.Attacker
                "DEFENDER" -> Team.Defender
                "NONE" -> Team.None
                else -> Team.Unknown
            }
    )

    fun equals(other: KillLog): Boolean {
        return this.killerName.equals(other.killerName) &&
                this.killerTeam == other.killerTeam &&
                this.victimName.equals(other.victimName) &&
                this.victimTeam == other.victimTeam
    }

    override fun toString(): String {
        return "$killerName ($killerTeam)  killed $victimName ($victimTeam)"
    }
}