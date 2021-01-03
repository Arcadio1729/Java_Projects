public interface IPositionChangeObserver {
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition);
    public void positionRemoved(Vector2d position);
    public void grassEaten(Vector2d position);
}
