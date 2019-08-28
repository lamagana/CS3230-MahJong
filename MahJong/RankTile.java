public class RankTile extends Tile {

    protected int rank;

    public RankTile(int rank) {
        this.rank = rank;
    }

    public boolean matches(Tile otherObj) {
        if(!super.matches(otherObj)) return false;
        RankTile other = (RankTile)otherObj;
        return (rank == other.rank);
    }
}
