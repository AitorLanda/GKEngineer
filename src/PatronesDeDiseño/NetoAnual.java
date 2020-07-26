package PatronesDeDiseño;

//Realizaz la operacion de Neto Anual
public class NetoAnual extends Sueldo
{
    public NetoAnual(){
    }
    // ------------------------------------
    @Override
     public float Operacion( float num )
    {
    	num=((num*15)/100);
        return (num*12);
    }
}