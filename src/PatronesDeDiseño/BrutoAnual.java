package PatronesDeDise�o;

//Calcula el sueldo bruto Anual
public class BrutoAnual extends Sueldo
{
    public BrutoAnual(){
    }
    // ------------------------------------
    @Override
     public float Operacion( float num )
    {
        return (num*12);
    }
}


