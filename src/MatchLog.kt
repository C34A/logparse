class MatchLog {
    private var rounds = ArrayList<RoundLog>();

    public fun addRound(round: RoundLog) {
        rounds.add(round);
    }

    override fun toString(): String {
        var ret = "Match: "
        for((index, round) in rounds.withIndex()) {
            ret += "\nRound $index:\n$round"
        }
        return ret
    }
}