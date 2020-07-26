package PatronesDeDiseño;

//Realiza las operaciones para conseguir el sueldo neto de forma mensual
public class NetoMensual extends Sueldo
{
    public NetoMensual(){
    }
    // ------------------------------------
    @Override
     public float Operacion( float num )
    {
    	num=((num*15)/100);
        return num;
    }
}

