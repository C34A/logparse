class MatchLog {
    private var rounds = ArrayList<RoundLog>();

    public fun addRound(round: RoundLog) {
        rounds.add(round);
    }

    override fun toString(): String {
        var sb = StringBuilder();
        sb.append("Match:\n")
        for((round, index) in rounds.withIndex()) {
            sb.append("Round ").append(index).append(":\n").append(round.toString())
        }
        return sb.toString()
    }
}