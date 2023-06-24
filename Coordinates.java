public class Coordinates {
    private Double x; //Поле не может быть null
    private Integer y; //Значение поля должно быть больше -40, Поле не может быть null
    public Coordinates(double ix, int iy){
        x=ix; y=iy;
    }
    public double getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}